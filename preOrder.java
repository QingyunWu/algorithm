public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curt = root;
        while (curt != null || !stack.isEmpty()) {
            if (curt != null) {
                result.add(curt.val);//add before left childs into stack
                stack.push(curt);
                curt = curt.left;
            } else {
                curt = stack.pop().right;
            }
        }
        return result;
    }
}