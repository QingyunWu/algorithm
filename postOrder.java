public class Solution {
   public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curt = root;
       //root right left, then reverse
        //先类似先序遍历得到根右左, 再反向就行
        while (!stack.isEmpty() || curt != null) {
            if (curt != null) {
                results.add(curt.val);
                stack.push(curt);
                curt = curt.right;
            } else {
                curt = stack.pop().left;
            }
        }
        Collections.reverse(results);
        return results;
    }
}