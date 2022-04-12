package gameutil.math;

import gameutil.math.geom.Tuple;

public class Polynumber {
	private Tuple t;
	private int constantIndex=0;
	public Polynumber(Tuple t) {
		this.t=t;
	}
	
	public Polynumber(double[] vals) {
		this.t=new Tuple(vals);
	}
	
	public Polynumber(double[] vals,int constantIndex) {
		this.t=new Tuple(vals);
		this.constantIndex=constantIndex;
	}
	
	public Polynumber(Tuple t,int constantIndex) {
		this.t=t;
		this.constantIndex=constantIndex;
	}
	
	public double apply(double x) {
		double val=0;
		for (int i=0; i<t.n(); i++) {
			val=val+Math.pow(x, i)*t.i(i);
		}
		return val;
	}
	
	public Polynumber $A$(Polynumber p) {
		return new Polynumber(t.$A$(p.t));
	}
	
	public Tuple getTuple() {
		return t.clone();
	}
	
	public Polynumber $S$(Polynumber p) {
		return new Polynumber(t.$S$(p.t));
	}
	
	public Polynumber $X$(Polynumber p) {
		/*
		  
		  lastNonZeroDigit=0
		  product=[]
		  if len(num1)>len(num2):
		    mdigits=2*len(num1) #we need to include some 0 digits in order to get the full product -- there might be a faster way to do this but this should work for now
		  else:
		    mdigits=2*len(num2)

		  for i in range(0,mdigits):
		    idig=art["0"]
		    #index i of the product polynumber will be n1[0]*n2[i]+n1[1]*n2[i-1] ... n1[i]*n2[0]
		    #so if we order this from left to right then the jth term will be equal to n1[j]*n2[i-j]
		    for j in range(0,i+1): #i+1 because we want to include i because i already starts at 0 (whereas mdigits starts at 1 and counts the digits)
		      idig=art["+"](idig,art["*"](polydig(num1,j),polydig(num2,i-j)))
		    product.append(idig)
		    if (idig!=0):
		      lastNonZeroDigit=i
		  #only do this if the last nonzero digit isn't the last digit
		  
		  #debug prints
		  #print("Digits:"+str(mdigits-1))
		  #print("Last nonzero digit:"+str(lastNonZeroDigit))
		  if (lastNonZeroDigit<mdigits-1):
		    for k in range(lastNonZeroDigit+1,mdigits):
		      #pop off those trailing zeros!
		      product.pop(len(product)-1)
		  return product

		#presision determines the number of terms to find with negative exponents values of alpha (alpha=[0,1]). (the number of terms to find on the left side of '.')
		def dPoly(num1,num2,precision=0,art=arith["cmpx"]):

		  if (polyEq(num2,[art["0"]],art)):
		    #cannot divide by zero so throw error 
		    return [art["/"](art["1"],art["0"])]
		  if (polyEq(num1,[art["0"]],art)):
		    #if the numerator is zero and the denominator is non-zero the quotient is 0
		    return [art["0"]]
		  sza=0 #successive 'a' values that are zero (the first 'a' value (a-1) is num1 the next is a-1 - (b-1 * b0) = a0 then a1 = a0 - (b0*b1) and so on)
		  #b-1 is num2. bi+1 = [0,1]^(deg(ai)-deg(bi)))*h(ai)/h(bi) where h(x) = the coefficient of the highest term or zero if x=0
		  term=degPoly(num1)-degPoly(num2)
		  wPoly=[]
		  a=copy.deepcopy(num1)
		  b=polydig(num2,degPoly(num2),art) #this is b-1
		  while (term>=-precision):
		    if(sza>=2):
		      #if there are 2 (or more) successive a values then the rest of the b values are 0 (meaning the most exact answer has been calculated)
		      #this should really stop at sza=2 and never get to an sza larger than 2 but just to be safer from radiation and such I guess
		      break
		    #print("term: "+str(term))
		    #val is bi+1
		    #a is ai
		    val=multAlphaPow([art["/"](polydig(a,degPoly(a),art),b)],term,art)
		    #print(val)
		    wPoly=aPoly(wPoly,val,art)
		    a=sPoly(a,mPoly(num2,val,art),art)
		    #print("a:" + str(a))
		    #print("deg(a): "+str(degPoly(a)))
		    #now a is the next a (ai+1)
		    
		    term=degPoly(a)-degPoly(num2) #note that after each division the value of
		    if (polyEq(a,[art["0"]],art)):
		      #if a zero is found as an a value add it to the counter for successive zero a values
		      sza=sza+1
		    else:
		      #otherwise a is not zero so reset the counter
		      sza=0
		  #for now just return whole part since no non-whole parts are being calculated right now
		  return wPoly */
		int lastNonZeroDigit=0;
		
		
		return new Polynumber(t.$S$(p.t));
	}
	
	public Polynumber multAlphaPow(int power) {
		int constantIndex=this.constantIndex;
		if (power>0) {
			if (constantIndex>0) {
				if (power>constantIndex) {
					constantIndex=0;
					power=power-constantIndex;
				} else {
					constantIndex=constantIndex-power;
					return new Polynumber(t.clone(),constantIndex);
				}
			} else {
				double[] vals=new double[t.n()+power];
				for (int i=0;i<power+t.n();i++) {
					if (i<power) {
						vals[i]=0;
					} else {
						vals[i]=t.i(i);
					}
				}
				return new Polynumber(vals);
			}
		} else {
			boolean encounteredNonZero=false;
			double[] vals;
			//int valsSize=;
			for (int i=0;i<power;i++) {
				if (!encounteredNonZero) {
					if (t.i(i)==0) {
						
					} else {
						
					}
				}
				if (encounteredNonZero) {
					
				}
			}
		}
		return null;
	}
	
}
