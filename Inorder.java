public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curt = root;
        List<Integer> result = new ArrayList<>();
        while (curt != null || !stack.isEmpty()) {
            if (curt != null) {
                stack.push(curt);
                curt = curt.left;
            } else {
                TreeNode node = stack.pop();
                result.add(node.val);//add afther all left childs
                curt = node.right;
            }
        }
        return result;
    }
}