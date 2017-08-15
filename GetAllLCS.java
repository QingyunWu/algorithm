public class Solution {

    
    
    public List<String> getAllLCS(String str1, String str2) {
        //first get the longgest common subsequnce len
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        List<String> result = new ArrayList<String>();
        dfs(result, str1.length(), str2.length(), "", dp, str1, str2);

        return result;
        
    }

    private void dfs(List<String> result, int i, int j, String path, 
                            int[][] dp, String str1, String str2) {
        if (i == 0 || j == 0) {
            result.add(path);
            return;
        }
        //if equals, add the curt char to tail
        //if not equal, try the direction with larger dp[i][j]
        if (str1.charAt(i-1) == str2.charAt(j-1)) {
            dfs(result, i - 1, j - 1, str1.charAt(i-1) + path, dp, str1, str2);
        } else if (dp[i-1][j] > dp[i][j-1]) {//try str1
            dfs(result, i - 1, j, path, dp, str1, str2);
        } else if (dp[i-1][j] < dp[i][j-1]) {//try str2
            dfs(result, i, j - 1, path, dp, str1, str2);
        } else {//if equals, try both directions
            dfs(result, i - 1, j, path, dp, str1, str2);
            dfs(result, i, j - 1, path, dp, str1, str2);
        }
        
    }
};