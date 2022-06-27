package test.screen;

import java.util.ArrayList;
import java.util.Random;

import org.joml.Matrix4f;
import org.joml.Quaternionfc;

import gameutil.math.geom.Point;
import gameutil.text.Console;
import graphics.gl.GLPanel;
import graphics.gl.Shader;

public class GtPanel extends GLPanel{

	private Shader defaultShader;
	private Random rand=new Random();
	private float[] pos=new float[] {0,0,0,-1};
	private float[] right=new float[] {1,0,0,0};
	private float[] up=new float[] {0,1,0,0};
	private float[] ana=new float[] {0,0,0,1};
	
	public GtPanel() {
		super("Unicorn Test");
		// TODO Auto-generated constructor stub
		
	}
	
	//square
	
	float[] vertexArray = {
		.5f, -.5f, 0f, /*1f,*/		1f, 0f, 0f, 1f, //0 lower left
		.5f, .5f, 0f, /*1f,*/		0f, 1f, 0f, 1f, //1 lower right
		-.5f, -.5f, 0f, /*1f,*/		0f, 0f, 1f, 1f, //2 upper left
		-.5f, .5f, 0f, /*1f,*/		1f, 0f, 1f, 1f, //3 upper right
		
	};
	
	int elementArray[] = {
		0,1,2,	//0,	//use these for line drawing
		
		3,2,1	//,3
	};
	
	
	//cube
	/*
	float[] vertexArray = {
			.5f, -.5f, -.5f,		1f, 0f, 0f, 1f, //0 lower left in
			.5f, .5f, -.5f,			0f, 1f, 0f, 1f, //1 lower right in
			-.5f, -.5f, -.5f,		0f, 0f, 1f, 1f, //2 upper left in
			-.5f, .5f, -.5f,		1f, 0f, 1f, 1f, //3 upper right in
			.5f, -.5f, .5f,			1f, 0f, 0f, 1f, //4 lower left out
			.5f, .5f, .5f,			0f, 1f, 0f, 1f, //5 lower right out
			-.5f, -.5f, .5f,		0f, 0f, 1f, 1f, //6 upper left out
			-.5f, .5f, .5f,			1f, 0f, 1f, 1f, //7 upper right out
			
		};
		
		int elementArray[] = {
			//front face
			0,1,2,
			
			3,2,1,
			
			//back face
			4,5,6,
			
			7,6,5,
			
			//left face
			0,4,2,
			
			3,2,1,
			
			
			0,1,2,
			
			3,2,1,
			
			
			0,1,2,
			
			3,2,1,
			
			
			0,1,2,
			
			3,2,1,
		};
	*/
	/*
	float[] vertexArray = {
			.5f, -.5f, 0, 1,															1f, 0f, 0f, 1f, 		//0 left
			0f ,-.5f ,0f ,1f, 														0f, 1f, 0f, 1f, 		//1 top
			0f, -.5f, .5f, 1f,															0f, 0f, 1f, 1f, 	//2 front
			0f, -.5f, 0f, 1.5f,															1f, 0f, 1f, 1f, 	//3 ana
			1.61803398875f/4, 1.61803398875f/4-.5f, 1.61803398875f/4, 1+1.61803398875f/4,			1f,	1f, 1f,	1f, //4 most left,ana,up,front point
		};
		
		int elementArray[] = {
			4,0,
			4,1,
			4,2,
			4,3,
			3,0,
			3,1,
			3,2,
			2,0,
			2,1,
			1,0
		};*/
		
		
	private Matrix4f view=new Matrix4f();
	private Matrix4f proj=new Matrix4f();
	
	
	public void shaderInit() {
		//defaultShader=new Shader("assets/shader/poly.glsl");
		defaultShader=new Shader("assets/shader/poly.glsl");
		defaultShader.compile();
		defaultShader.uploadFlags(vertexArray, elementArray);
		view.identity();
		proj.identity();
		proj.set(new float[] {
				1f  ,0  ,0  ,0  ,
				0  ,1f ,0  ,0  ,
				0  ,0  ,1f  ,0  ,
				0  ,0  ,0  ,0 ,
		});
		defaultShader.uploadMat4f("uProjection", proj);
		//vertexArray=getTVerticesAsFloatArray(new Point(new double[] {-.5,-.5,-.5,-.5}),1);
		//defaultShader.uploadFlags(vertexArray, getEdges());
	}
	
	public void paint() {
		clear();
		/*Matrix4f rot=new Matrix4f();
		float st=(float)Math.sin(1f);
		float ct=(float)Math.cos(1f);
		//Console.s.println(st);
		//rot.identity();
		/*rot.set(new float[] {
				1  ,0  ,0  ,0  ,
				0  ,1  ,0  ,0  ,
				0  ,0  ,ct ,-st,
				0  ,0  ,st ,ct ,
		});*/
		view.identity();
		view.set(new float[] {
				right[0] ,right[1] ,right[2]  ,right[3]  ,
				up[0] ,up[1],up[2]  ,up[3]  ,
				-up[0] ,-up[1],-up[2]  ,-up[3]  ,
				ana[0] ,ana[1],ana[2]  ,ana[3]  ,
		});
		proj.identity();
		proj.set(new float[] {
				1f  ,0  ,0  ,-pos[0]  ,
				0  ,1f ,0  ,-pos[1]  ,
				0  ,0  ,1f  ,-pos[2]  ,
				0  ,0  ,0  ,-pos[3] ,
		});
		//view=rot.mul(view);
		defaultShader.use();
		defaultShader.uploadMat4f("uView", view);
		defaultShader.uploadMat4f("uProjection", proj);
		defaultShader.updatePolygon();
		
		//defaultShader.detach();
	}
	
	public void update() {
		
		//defaultShader.uploadFlags4D(vertexArray, elementArray);
		
		
	}
	
	public float[] getTVerticesAsFloatArray(Point p,float size) {
		float[] vertices=new float[16*(4+4)];//16 vertices of 4D points with 4D colors
		for (int i=0;i<2;i++) {
			for (int j=0;j<2;j++) {
				for (int k=0;k<2;k++) {
					for (int l=0;l<2;l++) {
						int index=(i*6+j*4+k*2+l)*8;
						vertices[index]=(float)(p.tuple.i(0)+i*size);
						vertices[index+1]=(float)(p.tuple.i(1)+j*size);
						vertices[index+2]=(float)(p.tuple.i(2)+k*size);
						vertices[index=3]=(float)(p.tuple.i(3)+l*size);
						//color
						vertices[index+4]=rand.nextFloat();
						vertices[index+5]=rand.nextFloat();
						vertices[index+6]=rand.nextFloat();
						vertices[index=7]=1;//make alpha always 1
					}
				}
			}
			
		}
		return vertices;
	}
	
	public int[] getEdges() {
		int[] edges=new int[64];
		/*edges= new int[]{
				4, 8, 10, 14, 12,
				4, 8, 9, 13, 12,
				4, 12, 13, 15, 14,
				4, 8, 9, 11, 10,
				4, 10, 11, 15, 14,
				4, 9, 11, 15, 13,
				4, 0, 4, 12, 8,
				4, 4, 6, 14, 12,
				4, 0, 2, 10, 8,
				4, 0, 2, 6, 4,
				4, 2, 6, 14, 10,
				4, 4, 5, 13, 12,
				4, 0, 1, 9, 8,
				4, 0, 1, 5, 4,
				4, 1, 5, 13, 9,
				4, 4, 5, 7, 6,
				4, 6, 7, 15, 14,
				4, 5, 7, 15, 13,
				4, 0, 1, 3, 2,
				4, 2, 3, 11, 10,
				4, 1, 3, 11, 9,
				4, 2, 3, 7, 6,
				4, 1, 3, 7, 5,
				4, 3, 7, 15, 11
		};*/
		
		ArrayList<java.awt.Point> es=new ArrayList<java.awt.Point>();
		for (int i=0;i<vertexArray.length-8;i+=8) {
			Point v=new Point(new double[] {vertexArray[i],vertexArray[i+1],vertexArray[i+2],vertexArray[i+3]});
			for (int k=0;k<vertexArray.length;k+=8) {
				
			}
		}
		/*for (int i=0;i<2;i++) {
			for (int j=0;j<2;j++) {
				for (int k=0;k<2;k++) {
					for (int l=0;l<2;l++) {
						int index=(i*6+j*4+k*2+l)*3;
						edges[index]=index/3;
						edges[index+4]=index/3;
						edges[index+9]=index/3;
					}
				}
			}
			
		}*/
		
		return edges;
		
	}
	
}
