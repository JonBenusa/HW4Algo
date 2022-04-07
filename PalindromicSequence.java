import java.util.Arrays;

/**
 * CISC 380
 * Algorithms Assignment 4 EXTRA CREDIT
 * 
 * Implements a dynamic programming solution to find the length of the longest palindromic subsequence.
 * 
 * @author YOUR NAME HERE
 * Due Date: xx/xx/xx
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
        int[][] length = new int[x.length()+1][x.length()+1];
        int maxSoFar = 0;
        int maxRow = 0; //does this return false positives ever?
        int maxCol = 0;
        for(int i = 0; i<=x.length() ; i++){
            for(int j = x.length() ; j>=0; j--){
                if(i == 0 || j == x.length()){
                    length[i][j] = 0;
                } else if(x.charAt(i-1) == x.charAt(j)){
                    length[i][j] = length[i-1][j+1] + 1;
                    if(length[i][j] > maxSoFar){
                        maxSoFar = length[i][j];
                        maxRow = i;
                        maxCol = j; //save these as pointer for recovery
                    }
                } else{
                    if(length[i][j+1] > length[i-1][j]) {
                        length[i][j] = length[i][j + 1];
                    } else{
                        length[i][j] = length[i-1][j];
                    }
                }
            }
        }
        for(int i = 0; i<=x.length(); i++){
            for(int j = 0; j<=x.length(); j++){
                System.out.print(length[i][j] + " ");
            }
            System.out.println();
        }
        return maxSoFar;
    }//longestPalindrome

    /**
     * Implements a dynamic programming solution to return the longest palindromic subsequence of the given string 
     * @param x the string that may contain a palindromic subsequence
     * @return the string of the longest palindrome, or null if there is none
     */
    public static String getLongestPalindrome(String x) {
        //YOUR EXTRA CREDIT CODE HERE
        return null;
    }



}//class
