package gameutil.math.geom;

import java.util.ArrayList;

import gameutil.text.Console;

public class Matrix {
	private double[][] vals;
	
	public Matrix(double[][] vals) {
		this.vals=vals;
	}
	
	public double get(int i, int j) {
		return vals[i][j];
	}
	
	public Vector getRow(int i) {
		return new Vector(vals[i]);
	}
	
	public void set(int i,int j,double value) {
		vals[i][j]=value;
	}
	
	public Matrix swapRow(int row1,int row2) {
		double[][] newMatrixVals=this.vals.clone();
		newMatrixVals[row1]=vals[row2];
		newMatrixVals[row2]=vals[row1];
		return new Matrix(newMatrixVals);
	}
	
	public Matrix swapCol(int col1,int col2) {
		double[][] newMatrixVals=this.vals.clone();
		for (int i=0;i<vals.length;i++) {
			newMatrixVals[i][col1]=vals[i][col2];
			newMatrixVals[i][col2]=vals[i][col1];
		}
		return new Matrix(newMatrixVals);
	}
	
	public Vector getRowAsVector(int n) {
		return new Vector(vals[n]);
	}
	
	public Matrix $A$(Matrix m) {
		double[][] sum=new double[vals.length][vals[0].length];
		for (int i=0;i<vals.length;i++) {
			for (int j=0;j<vals[0].length;j++) {
				sum[i][j]=vals[i][j]+m.vals[i][j];
			}
		}
		return new Matrix(sum);
	}
	
	public Matrix $A$(double d) {
		double[][] sum=new double[vals.length][vals[0].length];
		for (int i=0;i<vals.length;i++) {
			for (int j=0;j<vals[0].length;j++) {
				sum[i][j]=vals[i][j]+d;
			}
		}
		return new Matrix(sum);
	}
	
	public Matrix $AV$(Vector v) {
		double[][] sum=new double[vals.length][vals[0].length];
		for (int i=0;i<vals.length;i++) {
			for (int j=0;j<vals[0].length;j++) {
				sum[i][j]=vals[i][j]+v.getSpds().i(i);
			}
		}
		return new Matrix(sum);
	}
	
	public Matrix $AH$(Vector v) {
		double[][] sum=new double[vals.length][vals[0].length];
		for (int i=0;i<vals.length;i++) {
			for (int j=0;j<vals[0].length;j++) {
				sum[i][j]=vals[i][j]+v.getSpds().i(j);
			}
		}
		return new Matrix(sum);
	}
	
	public Matrix $X$(double d) {
		double[][] prod=new double[vals.length][vals[0].length];
		for (int i=0;i<vals.length;i++) {
			for (int j=0;j<vals[i].length;j++) {
				prod[i][j]=vals[i][j]*d;
			}
		}
		return new Matrix(prod);
	}
	/**Element-wise multiplication
	 * 
	 * @param d
	 * @return
	 */
	public Matrix $X$(Matrix m) {
		double[][] prod=new double[vals.length][vals[0].length];
		for (int i=0;i<vals.length;i++) {
			for (int j=0;j<vals[i].length;j++) {
				prod[i][j]=vals[i][j]*m.vals[i][j];
			}
		}
		return new Matrix(prod);
	}
	
	public Vector $X$(Vector v) {
		Vector prod=new Vector(Tuple.origin(0));
		for (int j=0;j<vals[0].length;j++) {
			double[] vec=new double[vals.length];
			for (int i=0;i<vals.length;i++) {
				vec[i]=vals[i][j];
			}
			prod.$A$(new Vector(vec).$X$(v.getSpds().i(j)));
		}
		
		return prod;
	}
	
	/**Note that for non-square matrices this op does not commute!
	 * 
	 * @param m
	 * @return
	 */
	public Matrix $DOT$(Matrix m) {
		double[][] prod=new double[vals.length][vals[0].length];
		for (int i=0;i<vals.length;i++) {
			double[] row=new double[vals.length];
			
			for (int j=0;j<vals[i].length;j++) {
				row[j]=vals[i][j];
				
			}
			Vector rvec=new Vector(row);
			for (int k=0;k<vals.length;k++) {
				double[] col=new double[vals.length];
				for (int j=0;j<vals[i].length;j++) {
					col[j]=m.vals[j][k];
				}
				prod[i][k]=rvec.$DOT$(new Vector(col));
			}
			
		}
		return new Matrix(prod);
	}
	
	public static Matrix I(int n) {
		double[][] vals=new double[n][n];
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				if (j==i) {
					vals[i][j]=1;
				} else {
					vals[i][j]=0;
				}
			}
		}
		return new Matrix(vals);
	}
	
	public static Matrix O(int n) {
		double[][] vals=new double[n][n];
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				vals[i][j]=0;
			}
		}
		return new Matrix(vals);
	}
	
	public static Matrix O(int m,int n) {
		double[][] vals=new double[m][n];
		for (int i=0;i<m;i++) {
			for (int j=0;j<n;j++) {
				vals[i][j]=0;
			}
		}
		return new Matrix(vals);
	}
	
	public double absdeterminant() {
		Vector[] vecs=new Vector[vals[0].length];
		Vector min;
		//boolean positive=true;
		for (int j=0;j<vals[0].length;j++) {
			double[] vec=new double[vals.length];
			for (int i=0;i<vals.length;i++) {
				vec[i]=vals[i][j];
			}
			Vector v=new Vector(vec);
			//v.getSpds().printVals("No: "+j);
			vecs[j]=v;
			Console.s.println(v.project(Vector.getUnitVector(j+1)).getSpds().i(j));
			//positive=!(positive^(v.project(Vector.getUnitVector(j+1)).getSpds().i(j)<0));
			
		}
		//make sure no vectors are colinear
		ArrayList<Vector> visitedVecs=new ArrayList<>();
		//this obvious doesn't work for matrices with no elements but like... those aren't really square anyway right? Maybe? I guess you could say the determinant is 0 for those but it probably doesn't really matter.
		double volume=vecs[0].magnitude();
		for (Vector v : vecs) {
			for (Vector vis:visitedVecs) {
				if (v.normalize().equals(vis)) {
					//if any vector is colinear then the determinant is 0 because the volume of paralleletope must be zero 
					return 0;
				}
			}
			//v.normalize().getSpds().printVals("normal");
			//if the normalized vector wasn't found in the visited vectors then it must be a vector that is not colinear with any of the previously visited vectors
			visitedVecs.add(v.normalize());
			if (!v.equals(vecs[0])) {
				volume=volume*v.$S$(v.project(vecs[0])).magnitude();//the volume of a paralleletope is the product of it's side lengths
			}
		}
		//need to figure out how to determine the sign so that the signed determinant can be found...
		return volume;
	}
	
	public Matrix clone() {
		return new Matrix(vals.clone());
	}
}

