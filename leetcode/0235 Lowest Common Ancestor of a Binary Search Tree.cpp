// solution1
// time complexity O(n)
// space complexity O(n)
class Solution {
public:
    TreeNode* result;

    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        helper(root, p, q);
        return result;
    }

    int helper(TreeNode* root, TreeNode* p, TreeNode* q) {
        if (result != nullptr || root == nullptr) return 0;

        int ret = 0;
        if (root == p || root == q) ++ret;

        ret += helper(root->left, p, q);
        ret += helper(root->right, p, q);

        if (ret == 2 && result == nullptr) result = root;
        return ret;
    }
};

// solution2
// time complexity O(n)
// space complexity O(n)
class Solution {
public:
    TreeNode* res;
    
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        helper(root, p, q);
        return res;
    }

    int helper(TreeNode* root, TreeNode* p, TreeNode* q) {
        if (res != nullptr || root == nullptr) return 0;
        
        TreeNode* smallerNode;
        TreeNode* greaterNode;
        if (p->val > q->val) {
            smallerNode = q;
            greaterNode = p;
        } else {
            smallerNode = p;
            greaterNode = q;
        }

        int sum = 0;
        if (root->val <= smallerNode->val) {
            if (root == smallerNode) ++sum;
            sum += helper(root->right, p, q);
        } else if (smallerNode->val < root->val && root->val < greaterNode->val) {
            sum += helper(root->left, p, q);
            sum += helper(root->right, p, q);
        } else if (root->val >= greaterNode->val) {
            if (root == greaterNode) ++sum;
            sum += helper(root->left, p, q);
        }
        
        if (sum == 2 && res == nullptr) res = root;
        return sum;
    }
};