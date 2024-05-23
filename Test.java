package XmlExtraction;

import java.io.*;


public class Test {

	   public static void main(String[] args)  {
		   
		   Stack calculations = new Stack();
		   Stack calculations2 = new Stack();
		   
		   StringBuilder expression = new StringBuilder();       
	       String line;
	       int openexpr=0;
	       int closeexpr=0;

	       try (BufferedReader reader = new BufferedReader(new FileReader("project.xml"))) {         
	    	   while ((line = reader.readLine()) != null) {
//	    		   System.out.println(line);
	    		   if(line.contains("<operator")){
	    			  String value = line.substring(line.indexOf("\"")+1, line.lastIndexOf("\""));
	    			  char op = value.charAt(0);
	    			  if(Character.isDigit(op)) {
	    				  System.out.println("-----exiting System due to type mismatching tag type-----");
	    				  System.exit(0);
	    			  }else
	    				  
	    			  expression.append(value);
	    			  calculations.push(value);
	    			  calculations2.push(value);
	    		   }
	    		   
	    		   if(line.contains("<atom")){
	     			  String value = line.substring(line.indexOf("\"")+1, line.lastIndexOf("\""));
	     			  char op = value.charAt(0);
	     			  if((!Character.isDigit(op))) {
	     				  System.out.println("-----exiting System due to type mismatching tag type-----");
	     				  System.exit(0);
	     			  }else
	     				  
	     			  expression.append(value);
	     			  calculations.push(value);
	     			  calculations2.push(value);
	     		   }
	    		   
	    		   if(!(line.endsWith(">"))) {
	    			   System.out.println("unclosed tag found!"+"\n-------------");
	    		   }
	    		   
	    		   if(line.contains("<expr")) {
	    			   openexpr++;
	    		   }
	    		   if(line.contains("</expr>")) {
	    			   closeexpr++;
	    		   }
	    		     		   
	    	   }
	    	   reader.close()
	    	   ;
	    	   if(openexpr==closeexpr) {
	    		   System.out.println("File is balanced, No Errors"+"\n-------------");
	    	   }else {
	    		   System.out.println("Open expression found, Error!"+"\n-------------"); 
	    	   }
	    	   
	    	    
	    	   
	    	   System.out.println("original prefix expression= "+expression.toString()+"\n-------------");
	    	   System.out.println("Infix expression= "+ prefixToInfix(calculations)+"\n-------------");
	    	   System.out.println("Result= "+ InfexEvaluation(calculations2)+"\n-------------");
	    	   
	        }catch(IOException e) {
	        	System.out.println(e.getMessage());
	        }
	    		   
	   }
	   
	   public static Object prefixToInfix(Stack stack) {
	       Stack evaluating = new Stack();

	       while(!stack.isEmpty()) {
	           String value = (String) stack.pop();
	           char c = value.charAt(0);
	           if (Character.isDigit(c)) {
	        	   evaluating.push(value);
	           } else {
	               Object operand2 = evaluating.pop();
	               Object operand1 = evaluating.pop();
	               String infix = "(" + operand2 + c + operand1 + ")";
	               evaluating.push(infix);
	           }
	       }

	       return evaluating.peek();
	   }
	   
	   public static Object InfexEvaluation(Stack stack) {
	       Stack evaluating = new Stack();
	       
	       while(!stack.isEmpty()) {
	           String value = (String) stack.pop();
	           char c = value.charAt(0);
	           if (Character.isDigit(c)) {
	        	   evaluating.push(StringtoInt(value));
	           } else {
	               int operand2 =  (int) evaluating.pop();
	               int operand1 =  (int) evaluating.pop();
	               int result = performOperation(c,operand1,operand2);
	               evaluating.push(result);
	           }
	       }
	       return evaluating.peek();
	   }
	   
	   public static int StringtoInt(String string) {
		   int result = 0;
		   int index = 0;
		   int len = string.length();
		   
		   while (index < len) {
		      char c = string.charAt(index++);
		      result = result * 10 + (c - '0');
		    }
		   return result;
	   }

	    private static int performOperation(char operator, int operand1, int operand2) {
	    	
	        switch (operator) {
	            case '+':
	                return operand2 + operand1;
	            case '-':
	                return operand2 - operand1;
	            case '*':
	                return operand2 * operand1;
	            case '/':
	                if (operand1 == 0) {
	                    throw new ArithmeticException("Division by zero");
	                }
	                return operand2 / operand1;
	            case'%':
	            	return operand2 % operand1;
	            case'^':
	            	return (int) Math.pow(operand2, operand1);
	            default:
	                throw new IllegalArgumentException("Invalid operator: " + operator);
	        }
	    }
	  }