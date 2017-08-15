/**
 * Your Trie object will be instantiated and called as such:
 * Trie trie = new Trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */
class TrieNode {
    // noted here that ch and str instance variable are not necessary here
    char ch;// denote the char for the trie node, but it is not necessray, since the map's key is char arleady
    String str;// if the trie node is end of a word, str is the word, still not necessaray
    Map<Character, TrieNode> map;
    boolean isEnd;
    
    public TrieNode(char ch) {
        this.map = new HashMap<>();
        this.ch = ch;
    }
    public TrieNode() {
        this.map = new HashMap<>();
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode curtNode = root;
        for (char curtChar : word.toCharArray()) {
            if (!curtNode.map.containsKey(curtChar)) {
                curtNode.map.put(curtChar, new TrieNode(curtChar));
            }
            curtNode = curtNode.map.get(curtChar);
        }
        curtNode.isEnd = true;//for the last char trie node, make it marked as end
        curtNode.str = word;//curt node get the string word
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode curtNode = root;
        for (char curtChar : word.toCharArray()) {
            if (!curtNode.map.containsKey(curtChar)) {
                return false;
            }
            curtNode = curtNode.map.get(curtChar);//move to the next node
        }
        return curtNode.isEnd;//final test if it's marked isEnd
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String word) {
       TrieNode curtNode = root;
       for (char curtChar : word.toCharArray()) {
           if (!curtNode.map.containsKey(curtChar)) {
               return false;
           }
           curtNode = curtNode.map.get(curtChar);
       }
       return true;
    }
}