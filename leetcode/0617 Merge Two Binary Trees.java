/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode res = null;
        res = helper(res, t1, t2);
        return res;
    }

    private TreeNode helper(TreeNode res, TreeNode t1, TreeNode t2) {
        if (null == t1 && null == t2) {
            return res;
        } else if (null == t1 && null != t2) {
            res = new TreeNode(0);
            res.val = t2.val;
            res.left = helper(res.left, null, t2.left);
            res.right = helper(res.right, null, t2.right);
            return res;
        } else if (null != t1 && null == t2) {
            res = new TreeNode(0);
            res.val = t1.val;
            res.left = helper(res.left, t1.left, null);
            res.right = helper(res.right, t1.right, null);
            return res;
        } else {
            res = new TreeNode(0);
            res.val = t1.val + t2.val;
            res.left = helper(res.left, t1.left, t2.left);
            res.right = helper(res.right, t1.right, t2.right);
            return res;
        }
    }

    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (null == t1 && null != t2) {
            t1 = new TreeNode(t2.val);
            t1.left = mergeTrees(t1.left, t2.left);
            t1.right = mergeTrees(t1.right, t2.right);
        } else if (null != t1 && null != t2) {
            t1.val += t2.val;
            t1.left = mergeTrees(t1.left, t2.left);
            t1.right = mergeTrees(t1.right, t2.right);
        }
        return t1;
    }

    public TreeNode mergeTrees3(TreeNode t1, TreeNode t2) {
        if (null == t1) return t2;
        if (null == t2) return t1;
        t1.val += t2.val;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(t1);
        q.add(t2);
        while (!q.isEmpty()) {
            TreeNode a = q.poll();
            TreeNode b = q.poll();
            
            if (null == a.left && null != b.left) {
                a.left = b.left;
            } else if (null != a.left && null != b.left) {
                a.left.val += b.left.val;
                q.add(a.left);
                q.add(b.left);
            }
            
            if (null == a.right && null != b.right) {
                a.right = b.right;
            } else if (null != a.right && null != b.right) {
                a.right.val += b.right.val;
                q.add(a.right);
                q.add(b.right);
            }
        }
        return t1;
    }
}