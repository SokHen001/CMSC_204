/**
 * The Notation class will have a method infixToPostfix to convert infix
 * notation to postfix notation that will take in a string and return a string,
 * a method postfixToInfix to convert postfix notation to infix notation that
 * will take in a string and return a string, and a method to evaluatePostfix to
 * evaluate the postfix expression. It will take in a string and return a
 * double.
 * 
 * @author Sokha Heng 
 * Project 2: Notation 
 * Due Date: 10/08/2024
 */
public class Notation extends java.lang.Object {
	private static MyQueue<String> queue;
	private static MyStack<Character> stack;

	public Notation() {

	} // end constructor

	/**
	 * Helper method for precedence of operators
	 * 
	 * @param signs - a char representing the operator for which to determine the
	 *              precedence
	 * @return the precedence level of the operator: 
	 * 		   2 for '*' and '/', 
	 *    	   1 for '+' and '-', 
	 *    	   -1 if the char is not a recognized operator
	 */
	private static int precedence(char signs) {
		switch (signs) {
			case '*':
			case '/':
				return 2;
			case '+':
			case '-':
				return 1;
			default:
				return -1; 
		}
	}

	/**
	 * Method to define if a char is an operator
	 * 
	 * @param signs - a char to check if it is an operator
	 * @return true if the char is an operator, false otherwise
	 */
	private static boolean isOperator(char signs) {
		switch (signs) {
			case '*':
			case '/':
			case '+':
			case '-':
				return true;
			default:
				return false;
		}
	}

	/**
	 * Performs a basic arithmetic operation on two operands based on the given
	 * operator. This method supports addition, subtraction, multiplication, and
	 * division.
	 * 
	 * @param op1      - the first operand (left operand)
	 * @param op2      - the second operand (right operand)
	 * @param operator - the operator to be applied; valid operators are '+', '-',
	 *                 '*', and '/'
	 * @return the result of the arithmetic calculation
	 * @throws ArithmeticException - if division by zero occurs
	 */
	private static int Calculation(int op1, int op2, char operator) throws ArithmeticException {
		switch (operator) {
			case '+':
				return op1 + op2;
			case '-':
				return op1 - op2;
			case '*':
				return op1 * op2;
			case '/':
				if (op2 == 0) {
					throw new ArithmeticException();
				}
				return op1 / op2;
			default:
				return 0;
		}
	}

	/**
	 * Convert an infix expression into a postfix expression
	 * 
	 * @param infix - the infix expression in string format
	 * @return the postfix expression in string format
	 * @throws InvalidNotationFormatException - if the infix expression format is
	 *                                        invalid
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		stack = new MyStack<>(infix.length());
		queue = new MyQueue<>();

		try {
			for (int i = 0; i < infix.length(); i++) {
				char c = infix.charAt(i);

				if (c == ' ') {
					continue;
				}

				if (Character.isLetterOrDigit(c)) {
					queue.enqueue(String.valueOf(c));
				}

				else if (c == '(') {
					stack.push(c);
				}

				else if (c == ')') {
					while (!stack.isEmpty() && stack.top() != '(') {
						queue.enqueue(String.valueOf(stack.pop()));
					}

					if (!stack.isEmpty() && stack.top() == '(') {
						stack.pop();
					} else {
						throw new InvalidNotationFormatException();
					}
				}

				else if (isOperator(c)) {
					while (!stack.isEmpty() && precedence(stack.top()) >= precedence(c)) {
						queue.enqueue(String.valueOf(stack.pop()));
					}
					stack.push(c);
				} else {
					throw new InvalidNotationFormatException();
				}
			}

			while (!stack.isEmpty()) {
				if (stack.top() == '(') {
					throw new InvalidNotationFormatException();
				}
				queue.enqueue(String.valueOf(stack.pop()));
			}

			return queue.toString();
		} catch (Exception e) {
			throw new InvalidNotationFormatException();
		}

	}

	/**
	 * Convert the Postfix expression to the Infix expression
	 * 
	 * @param postfix - the postfix expression in string mat
	 * @return the infix expression in string format
	 * @throws InvalidNotationFormationException - if the postfix expression format
	 *                                           is invalid
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		MyStack<String> stack = new MyStack<>(postfix.length());

		try {
			for (int i = 0; i < postfix.length(); i++) {
				char c = postfix.charAt(i);

				if (c == ' ') {
					continue;
				}

				if (Character.isLetterOrDigit(c)) {
					stack.push(String.valueOf(c));
				}

				else if (isOperator(c)) {

					if (stack.size() < 2) {
						throw new InvalidNotationFormatException();
					}

					String op2 = stack.pop();
					String op1 = stack.pop();

					String infixExpression = "(" + op1 + c + op2 + ")";

					stack.push(infixExpression);
				} else {
					throw new InvalidNotationFormatException();
				}
			}

			if (stack.size() != 1) {
				throw new InvalidNotationFormatException();
			}
			
			return stack.pop();
			
		} catch (Exception e) {
			throw new InvalidNotationFormatException();
		}
	}

	/**
	 * Evaluates a postfix expression from a string to a double
	 * 
	 * @param postfixExpr - the postfix expression in String format
	 * @return the evaluation of the postfix expression as a double
	 * @throws InvalidNotationFormatException - if the postfix expression format is
	 *                                        invalid
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		MyStack<Integer> stack = new MyStack<>(postfixExpr.length());

	    try {
	        for (int i = 0; i < postfixExpr.length(); i++) {
	            char c = postfixExpr.charAt(i);

	            if (c == ' ') {
	                continue;
	            }

	            if (Character.isDigit(c)) {
	                stack.push(Character.getNumericValue(c));  
	            }
	            else if (isOperator(c)) {
	                if (stack.size() < 2) {
	                    throw new InvalidNotationFormatException();
	                }

	                int operand2 = stack.pop();  
	                int operand1 = stack.pop(); 
	                int result = Calculation(operand1, operand2, c);
	                stack.push(result);
	            } else {
	                throw new InvalidNotationFormatException();
	            }
	        }

	        if (stack.size() != 1) {
	            throw new InvalidNotationFormatException();
	        }

	        return stack.pop();

	    } catch (Exception e) {
	        throw new InvalidNotationFormatException();
	    }
	}

}
