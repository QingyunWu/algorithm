public class Solution {
    
    class TrieNode {
        TrieNode[] map = new TrieNode[26];
        String str;
        boolean isEnd;
    }
    
    TrieNode root = new TrieNode();
    
    private void addWord(String word) {
        TrieNode curt = this.root;
        for (char ch : word.toCharArray()) {
            if (curt.map[ch - 'a'] == null)
                curt.map[ch - 'a'] = new TrieNode();
            curt = curt.map[ch - 'a'];
        }
        curt.str = word;
        curt.isEnd = true;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (board.length == 0)
            return result;
        for (String word : words)
            addWord(word);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(result, root.map[board[i][j] - 'a'], board, i, j);
            }
        }
        return result;
    }
    
    private void dfs(List<String> result, TrieNode curt, char[][] board, int i, int j) {
        if (curt == null)
            return;
        if (curt.isEnd && !result.contains(curt.str))
            result.add(curt.str);
        char temp = board[i][j];
        board[i][j] = 0;//mark it as visited
        int[] dx = {0,0,-1,1};
        int[] dy = {1,-1,0,0};
        for (int k = 0; k < 4; k++) {
            int nx = dx[k] + i;
            int ny = dy[k] + j;
            if (nx >= 0 && ny >= 0 && nx < board.length && ny < board[0].length &&
               board[nx][ny] != 0) {
                dfs(result, curt.map[board[nx][ny] - 'a'], board, nx, ny);
            }
        }
        board[i][j] = temp;//make it as unvisited
    }
}