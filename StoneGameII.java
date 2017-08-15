/*
There is a stone game.At the beginning of the game the player picks n piles of stones in a circle.

The goal is to merge the stones in one pile observing the following rules:

At each step of the game,the player can merge two adjacent piles to a new pile.
The score is the number of stones in the new pile.
You are to determine the minimum of the total score.
[1,4,4,1] return 18
*/
public class Solution {
    /**
     * @param A an integer array
     * @return an integer
     */
    public int stoneGame2(int[] A) {
        if (A.length == 0) return 0;
        int[] newA = new int[A.length*2];
        //取mod的方法来复制数组*2
        for (int i = 0; i < newA.length; i++) {
            newA[i] = A[i % A.length];
        }
        //处理rotated array, 直接首尾拼接长度乘以2
        int[][] dp = new int[A.length * 2][A.length * 2];
        int[] sum = new int[newA.length + 1];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i-1] + newA[i-1];
        }
        dp[0][dp.length - 1] = memorySearch(0, dp.length - 1, newA, sum, dp);
        int result = Integer.MAX_VALUE;
        //找min值最小的一个窗口
        for (int i = 0; i < A.length; i++) {
            result = Math.min(result, dp[i][i + A.length - 1]);
        }
        return result;
    }
    
    private int memorySearch(int i, int j, int[] newA, int[] sum, int[][] dp) {
        if (dp[i][j] != 0) return dp[i][j];
        if (i == j) {
            dp[i][j] = 0;//没有合并, 所以没有分数
        } else {
            int sumOfSection = sum[j+1] - sum[i];
            int min = Integer.MAX_VALUE;
            for (int k = i; k <= j - 1; k++) {
                min = Math.min(min, memorySearch(i, k, newA, sum, dp) + memorySearch(k+1, j, newA, sum, dp) + sumOfSection);
            }
            dp[i][j] = min;
        }
        return dp[i][j];
    }
}