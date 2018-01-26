
import java.lang.reflect.*;
import java.util.Scanner;

class MyReflectTest
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String scValue = sc.nextLine();
		int countParams = 0;
		int countMethod = 0;
		int mean; 
		int maximum = 0;
		String maxMethodName = "";
		try
		{
			//Get the class name and load it
			Class cls = Class.forName(scValue);
			
			// Get the list of methods in the class
			Method[] methodObj = cls.getMethods();
			
			//Method[] methodObj = cls.getDeclaredMethods();
			
			// Get each method in the class
			for(Method methodList : methodObj)
			{
				
				// Get the list of the parameters in the method
				Class<?>[] params = methodList.getParameterTypes();
				
				
				//Check if the parameters in each method is equals to one
				//if(params.length == 1)
				//{
					// Get each parameters in the list
					for(Class paramsList : params)
					{
						// Optional : get the return type and Name
						Class rType = methodList.getReturnType();
						String rTypeName = rType.getName();
						
						// Get the method modifier
						String nModifier = Modifier.toString(paramsList.getModifiers());
						
						// Check if the parameters is an integer
						if(paramsList.equals(Integer.TYPE))
						{
							// Get method names
							System.out.print("Method : "+methodList.getName());
							
							// get number of method parameters
							System.out.println(" - Numuber of Parameters is: "+params.length);
							
							countParams += params.length;
							countMethod++;
							
							// check the maximum
							if(params.length > maximum)
							{
								maximum = params.length;
								maxMethodName = methodList.getName();
							}
							
							//  rTypeName rType.getCanonicalName()
							//System.out.println(nModifier+" "+methodList.getName()+" ("+paramsList+")");
						}
					}
				//}
				//System.out.println("=================================================");
				//System.out.println(methodList.getName());
			}
			mean = countParams / countMethod;
			System.out.println("The mean : "+mean);
			System.out.println("Method "+maxMethodName+" has maximum parameter of "+maximum);
			
			System.out.println("=================================================");
			System.out.println("");
			
			
			System.out.println("Please Enter a method Name: ");
			String nMthd = sc.nextLine(); //add
			
			System.out.println("Please Enter an integer value: ");
			int intNMthd = sc.nextInt();
			
			// Declaring the time fields
			long startTime, endTime, timeDiff;
			
			
			for(Method methodList : methodObj) // add, colour ....
			{
				if(methodList.getName().equals(nMthd))
				{
					//Get the start time
					startTime = System.currentTimeMillis();
					
					Object objName = new Object(); // invoke static method
					// non static: cls objName = new cls(); // invoke non static method
					objName = methodList.invoke(objName, intNMthd);
					System.out.println("The return value for "+nMthd+" Method : "+objName);
					
					// Get the end
					endTime = System.currentTimeMillis();
					
					
					timeDiff = endTime - startTime;
					System.out.println("The execution time is : "+ timeDiff+" millisecs");
				}
			}
			
			
		}//ClassNotFoundException
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
}