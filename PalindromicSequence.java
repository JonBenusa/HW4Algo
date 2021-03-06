/**
 * CISC 380
 * Algorithms Assignment 4 EXTRA CREDIT
 * 
 * Implements a dynamic programming solution to find the length of the longest palindromic subsequence.
 * 
 * @author Jonathan Benusa and Timothy Larson
 * Due Date: 04/10/22
 */


public class PalindromicSequence{

    /**
     * Implements a dynamic programming solution to find the length of the longest Palindromic subsequence of the given string.
     * 
     * 
     * @param x the string that may contain a palindromic subsequence
     * @return the length of the longest palindromic subsequence, or 0 if there is none.
     */
    public static int getLengthLongestPalindrome(String x){
        int[][] length = fillTable(x);
        /*for(int i = 0; i <= x.length(); i++){
            for(int j = 0; j <= x.length(); j++){
                System.out.print(" " + length[i][j]);
            }
            System.out.println("");
        }*/
        return length[x.length()][0];
    }//longestPalindrome

    /**
     * Implements a dynamic programming solution to return the longest palindromic subsequence of the given string 
     * @param x the string that may contain a palindromic subsequence
     * @return the string of the longest palindrome, or null if there is none
     */
    public static String getLongestPalindrome(String x) {
        int[][] length = fillTable(x);
        String retString = "";
        int a = x.length();
        int b = 0;
        if (length[a][b] == 0) {
            return null;
        }
        while(length[a][b] > 0){
            if((length[a][b] == length[a-1][b])){
                a = a-1;
            } else if((length[a][b] -1) == length[a-1][b+1]){
                //retString = retString + x.charAt(a);        //time complexity of this?
                a = a - 1;
                b = b + 1;
                //System.out.println("a is "+ a);
                //System.out.println("b is "+ b);
            } else{
                b = b + 1;
                //System.out.println("b is " + b);
            }
        }
        return retString;
    }

    private static int[][] fillTable(String x){
        int[][] length = new int[x.length()+1][x.length()+1];
        for(int i = 0; i<=x.length() ; i++){
            for(int j = x.length() ; j>=0; j--){
                if(i == 0 || j == x.length()){
                    length[i][j] = 0;
                } else if(x.charAt(i-1) == x.charAt(j)){
                    length[i][j] = length[i-1][j+1] + 1;
                } else{
                    if(length[i][j+1] > length[i-1][j]) {
                        length[i][j] = length[i][j + 1];
                    } else{
                        length[i][j] = length[i-1][j];
                    }
                }
            }
        }
        return length;
    }



}//class
