package Polinoame;
/**
 * 
 * @author Vlad
 *
 */
/**
 * 
 * Class is a reprezentation of a real world polynomial, having the unknown X!
 *
 */
public class Polynomial {
	public static final int MAX_DEGREE=100;// The preset maximum degree of the polynomial
	private int[] coefficients=new int[MAX_DEGREE];// vector of coefficients of the polinomial
												  //  initialized here so that it is initially 0
	private int degree;							 //   the degree of the polinomial
												//    equal to the maximum position ocuppied in the coefficients vector;
	
	/** 
	 * Constructs a new polynomial based on a String containing P(X), respecting the specified format
	 * @param s is a string containing the natural form of the polynomial P(X)
	 */
	public Polynomial(String s){
		
		//first we prepare the string so it has the expected format
		s=s.replace(" ", "");// eliminate spaces
		s=s.replace("(", "");// eliminate paranthesis
		s=s.replace(")", "");
		
		//make s lowerCase so that X is the same thing with x
		s=s.toLowerCase();
		//initialize degree
		degree=0;
		
		//local variables
		String[] members=s.split("\\+");		// split string into polynomial members 
												// we use the "+" as token, thus negative members must also be preceded by it
		int i,j,nr,memb_deg;
		boolean sign;
		char[] numbers;
		
		for(i=0;i<members.length;i++)  			//we take each member of the polynomial
		{
			numbers=members[i].toCharArray();	// convert the member to a char array so that we may dissasseble it further
			
			if(numbers[0]=='-') 				// check if number is negative
				{sign=true;j=1;}
			 else
				{sign=false;j=0;}
			
			
			for(nr=0;j<numbers.length&&numbers[j]!='x';j++) 
				{
				nr*=10;							// form the coefficient
				nr+=(numbers[j]-'0');
				}
			
			if(numbers[0]=='x')					//implicit 1 coefficient case
				nr=1;
			
			if(sign)
				if(numbers[1]=='x')
					nr=1;
			
			if(sign)							//if sign is - negate de value of nr
				nr=0-nr;
			
			if(j==numbers.length)				//case of x^0 coefficient
				coefficients[0]=nr;
				
			else
				if(j+1==numbers.length)		    //case of x^1 coefficient
					{coefficients[1]=nr;
					if(degree<1)
						degree=1;
					}
				else							//case of x^n
				{
					memb_deg=0;
					for(j+=2;j<numbers.length;j++)	//form n
						{memb_deg*=10;
						memb_deg+=(numbers[j]-'0');
						}
					coefficients[memb_deg]=nr; 		//save coefficient of x^n
					if(memb_deg>degree)
						degree=memb_deg;			//memorize maximum degree
				}
				
		}
		
	}
	/**
	 * Default polynomial, equal to zero
	 */
	public Polynomial(){
		this("0");
	}
	/**
	 * Constructs a new polynomial by copying an existing one
	 * @param p existing polynomial
	 */
	public Polynomial(Polynomial p){
		coefficients=p.getCoefficients();
		degree=p.getDegree();
		
	}
	/*
	 * Prints the coefficients array
	 *
	private void showCoefficients(){
		int i;
		for(i=0;i<=this.getDegree();i++)
			System.out.print(coefficients[i]+" ");
	}
	*/
	/**
	 * Getter method for the maximum degree of the polynomial
	 * @return degree of polynomial
	 */
	public int getDegree()
	{
		int val;
		val=degree; // val is used to make sure we do not return a reference to degree
		return val;
	}
	
	/**
	 * Returns polynomial under String form
	 */
	public String toString()
	{
		int i;
		String x="";
		
		// first case is take such that the Polynomial does not start with +X, but with X
		if(degree>1)
		if(coefficients[degree]==1) 		//make 1X just X
				x=x.concat("X^"+degree);
		else
				x=x.concat(coefficients[degree]+"X^"+degree);
		
		for(i=degree-1;i>1;i--)
			if(coefficients[i]!=0)
				if(coefficients[i]==1) 		//make 1X just X
					x=x.concat("+X^"+i);
				else
					if(coefficients[i]>1)
						x=x.concat("+" + coefficients[i]+"X^"+i);
					else
						x=x.concat(coefficients[i]+"X^"+i);
		
		//X^1- do not show power
		if(coefficients[1]!=0)
			if(coefficients[1]==1) // show X instead of 1X
				x=x.concat("+X");
			else
				if(coefficients[1]>0)
					x=x.concat("+" + coefficients[1]+"X");
				else
					x=x.concat(coefficients[1]+"X");
		
		//X^0 do not show X
		if(coefficients[0]!=0)
			if(coefficients[0]>0)
				x=x.concat("+" + coefficients[0]);
			else
				x=x.concat(coefficients[0]+"");
		
		return x;
	}
	/**
	 * Getter method for the array of coefficients
	 * @return array of coefficients
	 */
	
	protected int[] getCoefficients()
	{
		int[] copie=new int[MAX_DEGREE];// copie is made to provide o copy of the coefficients array, not it's address
		int i;
		for(i=0;i<=degree;i++)
			copie[i]=coefficients[i];
		return copie;
	}
	/**
	 * Getter method for coefficients
	 * @param position is the degree at which we want the coefficient
	 * @return coefficient value
	 */
	public int getCoefficient(int position)
	{
		return coefficients[position];
	}
	/**
	 * Setter method to modify a coefficient at a certain degree
	 * @param position is the degree at which we want to modify the coefficient
	 * @param value is the new coefficient 
	 */
	protected void setCoefficient(int position, int value)
	{
		coefficients[position]=value;
	
	}
	/**
	 * Setter method to modify the degree 
	 * @param value represents the new degree
	 */
	protected void setDegree(int value)
	{
		this.degree=value;
	}
	/**
	 * Adds caller polynomial to the parameter polynomial and returns the result as a new polynomial
	 * @param p polynomial to be added
	 * @return caller+p
	 */
	
	public Polynomial plus(Polynomial p){
		Polynomial temp=new Polynomial(this);
		int[] pc=p.getCoefficients();
		int[] tc=temp.getCoefficients();
		int newDegree=Math.max(p.getDegree(), temp.getDegree());
		int i,notNullDegree;
		
		for(i=0,notNullDegree=0;i<=newDegree;i++)
		{
			temp.setCoefficient(i,tc[i]+pc[i]);
			if(temp.getCoefficient(i)!=0)
				notNullDegree=i;
		}
		temp.setDegree(notNullDegree);
		return temp;
	}
	/**
	 * Subtracts the transmitted polynomial from the caller polynomial and returns the result as a new polynomial 
	 * @param p
	 * @return caller-p
	 */
	public Polynomial minus(Polynomial p){
		Polynomial temp=new Polynomial (this);
		int[] pc=p.getCoefficients();
		int[] tc=temp.getCoefficients();
		int i,notNullDegree,maxDegree=Math.max(p.getDegree(), temp.getDegree());
		
		for(i=0,notNullDegree=0;i<=maxDegree;i++)
			{
				temp.setCoefficient(i,tc[i]-pc[i]);    // subtract coefficient by coefficient
				if((temp.getCoefficient(i))!=0)       //check if the degree has not decreased
					notNullDegree=i;
			}
		temp.setDegree(notNullDegree);
			
		
		return temp;
	}
	/**
	 * Used to calculate P(X) in the point x, specified by the user
	 * @param x  point to calculate P(x)
	 */
	
	public double valueAt(int x)
	{
		int i;
		double val;
		
		for(i=0,val=0;i<=degree;i++)
			if(coefficients[i]!=0)
				val+=coefficients[i]*(Math.pow(x, i)); //add coefficient* x^i
		
		return val;
		
	}
	/**
	 * We shall provide an upper bound for the roots to speed up the process
	 * @return bound of polynomial roots
	 */
	
	private int rootBound()
	{
		//http://en.wikipedia.org/wiki/Properties_of_polynomial_roots
		int i,sum;
		for(i=0,sum=0;i<=degree;i++) //compute the sum of the module of the coefficients
			if(coefficients[i]<0)
				sum=sum-coefficients[i];
			else
				sum+=coefficients[i];
		return sum;
	}
	

	/**
	 * Finds integer roots of caller polynomial
	 * @return array containing the roots
	 */
	public int[] roots()
	{
		int[] roots=new int[degree+1];
		int i,j;
		
		for(i=0,j=0;i<Integer.MAX_VALUE&&j<degree&&i<=this.rootBound();i++) //generate all values until maximum number or roots is reached or Integer range is surpassed
			{
			if(this.valueAt(i)==0) //check if root
			{
				roots[j]=i;
				j++;	
			}
			if(this.valueAt(-i)==0)
			{
				roots[j]=-i;
				j++;
			}
			
			}
		roots[degree]=j;     // store the number of found roots
		return roots;
	}
	/**
	 * Prepares array of roots for printing.
	 * @param r is the array containing the integer roots of the polynomial
	 * @return String containing the roots
	 */
	public String showRoots(int[] r)
	{
		int i;
		String x="";
		for(i=0;i<r[degree]&&i<degree;i++)
			x=x.concat(" "+r[i]);
		return x;
	}

	/**
	 * Derives the caller polynomial
	 * @return initial polynomial derived
	 */
	public Polynomial derive()
	{
		Polynomial temp=new Polynomial(this);
		int[] tc=temp.getCoefficients();
		int i,deg=temp.getDegree();    //saving degree to reduce computations
		
		for(i=0;i<deg;i++)
			{
			tc[i]=tc[i+1]*(i+1);
			temp.setCoefficient(i,tc[i]);
			}
		
		temp.setCoefficient(deg, 0);
		temp.setDegree(deg-1);
		return temp;
		
	}
	
	/**
	 * Multiplies the polynomial with a scalar
	 * @param nr is the scalar we multiply with
	 * @return nr*polynomial
	 */
	protected Polynomial scalarMultiply(int nr) // used un times method! 
	{
		Polynomial t=new Polynomial(this);
		int[] tc=t.getCoefficients();
		int i;
		for(i=0;i<=t.getDegree();i++)
			{
				t.setCoefficient(i,nr*tc[i]);
			}
		return t;
	}
	
	/**
	 * Multiplies the caller polynomial with X^positions by shifting the positions vector
	 * @param positions is the number of positions to be shifted
	 * @return (Caller Polynomial)*X^positions
	 */
	protected Polynomial shiftR(int positions)
	{
		Polynomial temp=new Polynomial("0");
		int i;
		
		for(i=0;i<=degree;i++)
			temp.setCoefficient(i+positions, coefficients[i]);
		
		temp.setDegree(degree+positions);
		
		return temp;
	}
	
	/**
	 * Multiplies the caller polynomial with the parameter polynomial
	 * @param p : multiplier polynomial
	 * @return caller polynomial*p
	 */
	public Polynomial times(Polynomial p)
	{
		Polynomial temp=new Polynomial();
		int i;		
		
		for(i=0;i<=degree;i++)  			// we multiply each element of the first polynomial with p and add it to temp
		{
			temp=temp.plus((p.shiftR(i)).scalarMultiply(coefficients[i]));// temp+= coef*X^i
		}
		
		temp.setDegree(degree+p.getDegree());
		return temp;
			
	}
	
	/**
	 * Validates the input string for the polynomial construction.Minimum restrictions, full validation CAN NOT be met! Development still in progress
	 * @param s is the input String
	 * @return false if s contains any other characters than:(, ), +, -, ^, x, X, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ; true otherwise
	 */
	public static boolean checkInputString(String s)
	{
		int i;							//index
		s=s.toLowerCase();				//eliminate case sensitive cases
		s=s.replace("(", "");			//elimate paranthesis
		s=s.replace(")", "");
		char[] sArray=s.toCharArray();	//convert string into a workable form
		
		for(i=0;i<sArray.length;i++)
		{
			if(Character.isLetter(sArray[i]))
				{if(sArray[i]!='x')
					return false;			//if there are any letters different than x, reject
				}
			else
				if(!Character.isDigit(sArray[i])) //ignore if digit
					if((sArray[i]!=' ')&&(sArray[i]!='-')&&(sArray[i]!='+')&&(sArray[i]!='^')) //if not digit and not ' ', -, +, ^, REJECT 
						return false; 
			
			if((sArray[i]=='x')&&(i<sArray.length-1)) // if not last X
				if(sArray[i+1]!='^')
					if(sArray[i+1]!='+')
					return false;
			if(sArray[i]=='^')
				if(!Character.isDigit(sArray[i+1]))
					return false;
					
		}	
		
		
		return true;
	}
	/**
	 * Checks is two polynomials are equal
	 * @param p is a polynomial that is compared to the caller
	 * @return true if they are equal, false otherwise
	 */
	
	public boolean equals(Polynomial p)
	{
		if(degree!=p.getDegree())
			return false;
		
		int i;
		int[] pc=p.getCoefficients();
		for(i=0;i<=degree;i++)
			if(coefficients[i]!=pc[i])
				return false;
		
		
		return true;
	}
	
	protected static int convertX(String s) throws Exception
	{
		int x,i;
		boolean minus;
		char[] iArr=s.toCharArray();
		if(iArr[0]=='-')
		{
			i=1; minus=true;
		}
		else
		{
			i=0;minus=false;
		}
		for(x=0 ;i<iArr.length;i++)
			if(!Character.isDigit(iArr[i]))
				throw new Exception("X is not an integer");
			else
				x+=iArr[i]-'0';
		if(minus)
			x=0-x;
		return x;
	}
	
}
