/**
 * Your Trie object will be instantiated and called as such:
 * Trie trie = new Trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */
class TrieNode {
    // Initialize your data structure here.
    TrieNode[] map;
    boolean isEnd;
    
    public TrieNode() {
        this.map = new TrieNode[26];
        this.isEnd = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode curt = root;
        for (char c : word.toCharArray()) {
            if (curt.map[c - 'a'] == null)
                curt.map[c - 'a'] = new TrieNode();
            curt = curt.map[c - 'a'];
        }
        curt.isEnd = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode curt = root;
        for (char c : word.toCharArray()) {
            if (curt.map[c - 'a'] == null)
                return false;
            curt = curt.map[c - 'a'];
        }
        return curt.isEnd;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode curt = root;
        for (char c : prefix.toCharArray()) {
            if (curt.map[c - 'a'] == null)
                return false;
            curt = curt.map[c - 'a'];
        }
        return true;
    }
}