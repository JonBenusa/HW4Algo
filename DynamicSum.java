import java.util.ArrayList;
import java.util.Arrays;

/**
 * CISC 380
 * Algorithms Assignment 4
 * 
 * Implements dynamic programming solutions to see if a subset adds up to a value.
 * 
 * @author Jonathan Benusa
 * Due Date: xx/xx/xx
 */



public class DynamicSum{

    private Boolean [][] table;
    private int sum;

    public DynamicSum(){
        //YOUR CODE HERE
        table = null;

    }//constructor

    /**
     *Checks to see if a subset of arr adds up to exactly t with an iterative solution.
	 *
     * @param arr the array of integers to take subsets from.
     * @param t   the value the subset could add up to.
     * @return True, if a subset adds up to t, false otherwise.
     * 
     */
    public boolean isSum(int[] arr, int t){
        //YOUR CODE HERE
        Arrays.sort(arr);
        ArrayList sums = new ArrayList<Integer>();      //sums will hold all previous calculated sums

        for(int currentValue:arr){
            if(currentValue == t){                      //if the current value from the array is i
                return true;
            } else {
                int sumsSize = sums.size();             //the size of sums before looking at this current index
                //add the current index's value to all previous additions and check if the summation is equal to t
                for (int i=0; i<sums.size(); i++) {
                    Integer a = (Integer) sums.get(i);
                    Integer curSum = a+currentValue;
                    if((curSum).equals(t)) {
                        return true;
                    }else {
                        sums.add(curSum);               //if not add this sum to the list of previous sums
                    }
                }
                sums.add(currentValue);
            }
        }

        return false;
    }//isSum

    /**
     *Checks to see if a subset of arr adds up to exactly t with a memoizied solution.
	 *
     * @param arr the array of integers to take subsets from.
     * @param t   the value the subset could add up to.
     * @return    True, if a subset adds up to t, false otherwise.
     * 
     */
    public boolean isSumMem(int[] arr, int t ){
        //YOUR CODE HERE
        table = new Boolean[arr.length][arr.length];
        
        return false;
    }//isSumMem

    /**
     * Recovers the subset of arr that adds up to t, if it exists.
	 *
     * @param arr the array of integers to take subsets from.
     * @param t   the value the subset could add up to.
     * @return a subset of arr that adds up to t, null otherwise.
     * 
     */
    public int[] getSubset(int[] arr, int t){
        //YOUR CODE HERE

        return null;
    }//getSubset

}//class