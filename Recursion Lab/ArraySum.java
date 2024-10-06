/**
 * 
 * This class is a recursive method that
 * sum the values in an array of integers. 
 * 
 * @author Sokha Heng
 */
public class ArraySum {

	/**
	 * 
	 * @param myArray - an array of type Integer
	 * @param i - an integer that shows which number in the array to sum next
	 * @return the sum of the values in an array of integers
	 */
	public int sumOfArray(Integer[] myArray, int i) {
	
		int sum;
		if(i < 0) // base case
		{
			return 0; 
		} else 
		{ // reduced problem
			sum = myArray[i] + sumOfArray(myArray, i - 1); // recursive call
		}
		return sum;

	} // end sumOfArray

}
