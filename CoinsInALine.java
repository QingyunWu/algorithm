public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        //memory search
        //dp[i][j] 表示还剩第i到第j个硬币, 当前人能取得的最大值
        //如果dp[1][n] > sum/2, 表示可赢
        //dp[i][j] = sum[i~j] - min(dp[i+1][j], dp[i][j-1])
        if (values.length <= 1) return true;
        int[] prefixSum = new int[values.length + 1];
        for (int i = 1; i <= values.length; i++) {
            prefixSum[i] = prefixSum[i-1] + values[i-1];
        }
        //prefixuSum[i]表示第一个到第i个的总和
        //total is prefixSum[values.length]
        int[][] dp = new int[values.length + 1][values.length + 1];
        int maxCanGet = memorySearch(1, values.length, prefixSum, values, dp);
        return maxCanGet >= prefixSum[values.length] / 2;
    }
    
    private int memorySearch(int i, int j, int[] prefixSum, int[] values, int[][] dp) {
        if (i > j) return 9395;
        if (dp[i][j] != 0) {//表示已经算过
            return dp[i][j];
        }
        if (i == j) {
            dp[i][j] = prefixSum[i] - prefixSum[i-1];//values[i-1]
        } else {
            int takeLeft = memorySearch(i+1, j, prefixSum, values, dp);
            int takeRight = memorySearch(i, j-1, prefixSum, values, dp);
            dp[i][j] = prefixSum[j] - prefixSum[i-1] - Math.min(takeLeft, takeRight);
        }
        return dp[i][j];
    }
}