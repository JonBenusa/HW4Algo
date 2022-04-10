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

    private BoolInt[][] table;

    public DynamicSum(){
        //YOUR CODE HERE
        table = null;

    }//constructor

    /**
     *Checks to see if a subset of arr adds up to exactly t with an iterative solution.
     *
     * Will have n loops where each loop will at worst do (t-1)^2 work. giving it a runtime of O(n*t^2)
	 *
     * @param arr the array of integers to take subsets from.
     * @param t   the value the subset could add up to.
     * @return True, if a subset adds up to t, false otherwise.
     * 
     */
    public boolean isSum(int[] arr, int t){
        //YOUR CODE HERE
        int[][] sums = new int[arr.length][t+1];      //sums will hold all previous calculated sums
        int index = 0;

        sums[0][0] = arr[0];

        for(int i=1; i<arr.length; i++) {           //takes n repetitions
            index=0;
            if(arr[i]==t) {
                return true;
            }else if(arr[i]<t){
                for(int sum:sums[i-1]) {            //repeates for at worst t-1 times
                    int num=sum+arr[i];
                    if(num==t) {
                        return true;
                    }
                    if(num<t && !Arrays.stream(sums[i]).anyMatch(x -> x == num)) {      //these checks take at worst t-1
                        sums[i][index] = num;
                        index++;
                    }
                }
                int I = arr[i];
                if(!Arrays.stream(sums[i]).anyMatch(x -> x == I)) {         //t-1
                    sums[i][index] = I;
                    index++;
                }
            }
        }

        return false;
    }//isSum

    /**
     *Checks to see if a subset of arr adds up to exactly t with a memoizied solution.
     *
     * This method will at worst make n recursive calls. For calls n1 to n it at worst will do t^2 work. For call
     * n0 it will at worst run in constant time. This gives a O(n+(n-1)*t^2) which will simplify to O(n * t^2).
	 *
     * @param arr the array of integers to take subsets from.
     * @param t   the value the subset could add up to.
     * @return    True, if a subset adds up to t, false otherwise.
     * 
     */
    public boolean isSumMem(int[] arr, int t ){
        //YOUR CODE HERE
        int i = arr.length;
        table = new BoolInt[i][t+1];
        return isSumPriv(arr, t, i-1, 0);
    }//isSumMem
    private boolean isSumPriv(int[] arr, int t, int i, int j) {
        if(table[i][j]!=null && table[i][j].getIsTrue()==true) {        //if the table entry is true return true
            return true;
        }
        if(table[i][j]!=null && table[i][j].getIsTrue()==false) {       //if the table entry is false return false
            for(BoolInt entry: table[i]) {      //we need to check all entries at this level because one of them might be true while the rest are not
                if(table[i][j]!=null && table[i][j].getIsTrue()==true) {
                    return true;
                }
            }
            return false;
        } else {        //if to this point then the BoolInt is still null

            if(i==0) {  //this will be the terms for setting up the first row of the table runs in constant time
                if(arr[i]==t) {
                    table[i][j] = new BoolInt(arr[i], true);
                    return true;
                }else if(arr[i]>t) {
                    table[i][j] = new BoolInt(false);
                    return false;
                }else if(arr[i]<t) {
                    table[i][j] = new BoolInt(arr[i], false);
                    return false;
                }
            }
            if(table[i-1][j]==null) {               // if this row of the table has not been filled yet
                if(isSumPriv(arr, t, i-1, 0)==true) {       //if the previous row returns true then set this row to true and return true
                    table[i][j] = new BoolInt(-1,true);
                    return true;
                }else {
                    //runs t times
                    for(BoolInt entry : table[i-1]) {           //otherwise check by adding each previous sum and store the sum in the table if it is new and less than t
                        if(entry!=null) {
                            Boolean repeated = false;
                            int num = entry.getNum() + arr[i];
                            //runs t times with constant work
                            for(BoolInt contains : table[i-1]) {
                                if(contains != null) {
                                    if (contains.getNum() == num) {
                                        repeated = true;
                                    }
                                }

                            }
                            table[i][j] = new BoolInt(entry.getNum(), false);       //makes it so all sums that can be made with indexs 0-i are stored in row i for convienence.
                            j++;
                            if(!repeated && num<=t) {
                                table[i][j] = new BoolInt(num, false);
                                if (table[i][j].getNum() == t) {
                                    table[i][j].setIsTrue(true);                    //if any of these new sums are equal to t then set row equal to true and return true
                                    return true;
                                }
                                j++;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Recovers the subset of arr that adds up to t, if it exists.
     *
     * if the table is filled this method will recover the solution in at worst O(n*t).
     * if the table is not filled it will run in O(n*t^2) like the above function.
	 *
     * @param arr the array of integers to take subsets from.
     * @param t   the value the subset could add up to.
     * @return a subset of arr that adds up to t, null otherwise.
     * 
     */
    public int[] getSubset(int[] arr, int t){
        //YOUR CODE HERE
        boolean isTrue = isSumMem(arr, t);
        int currentSum = t;
        int index = 0;
        int[] solution = new int[0];

        if(isTrue) {
            for(int i=arr.length-1; i>=0; i--) {
                for (int j = 0; j <= t; j++) {
                    if (table[i][j] != null) {
                        if (table[i][j].getNum() != -1 && table[i][j].getIsTrue()) {
                            solution = new int[i+1];
                            currentSum = currentSum - arr[i];
                            solution[index] = arr[i];
                            index++;
                        }
                        if(table[i][j].getNum()==currentSum && solution!=null) {
                            currentSum = currentSum - arr[i];
                            solution[index] = arr[i];
                            index++;
                        }
                        if (currentSum <= 0) {
                            return solution;
                        }
                    }
                }
            }
        }

        return null;
    }//getSubset

    private class BoolInt {
        private Integer num;
        private Boolean isTrue;

        public BoolInt(int num, boolean isTrue) {
            this.num = num;
            this.isTrue = isTrue;
        }
        public BoolInt(boolean isTrue) {
            this.isTrue = isTrue;
            num  = null;
        }

        public int getNum() {
            return num;
        }
        public void updateInt(int num) {
            this.num = num;
        }
        public void setIsTrue(boolean bool) {
            isTrue = bool;
        }
        public Boolean getIsTrue() {
            return isTrue;
        }
    }
}//class