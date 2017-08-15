/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */
/*
Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers [start, end]. For each query, calculate the sum number between index start and end in the given array, return the result list.
*/
public class Solution {
    /**
     *@param A, queries: Given an integer array and an query list
     *@return: The result list
     */
    class SegmentTreeNode {
        long sum;
        int start;
        int end;
        SegmentTreeNode left;
        SegmentTreeNode right;
        public SegmentTreeNode(int start, int end, long sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
        
        
    }
    
    public ArrayList<Long> intervalSum(int[] A, 
                                       ArrayList<Interval> queries) {
       //这题实现segment tree的 build 和 query, node记录sum
       SegmentTreeNode root = buildTree(A, 0, A.length - 1);
       ArrayList<Long> result = new ArrayList<>();
       for (Interval q : queries) {
           result.add(query(root, q.start, q.end));
       }
       return result;
    }
    
    public SegmentTreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) return null;
        if (start == end) return new SegmentTreeNode(start, start, nums[start]);//叶子节点
        int mid = start + (end - start) / 2;
        SegmentTreeNode left = buildTree(nums, start, mid);
        SegmentTreeNode right = buildTree(nums, mid + 1, end);
        long sum = left.sum + right.sum;//求得左右各自sum, 相加
        SegmentTreeNode root = new SegmentTreeNode(start, end, sum);
        root.left = left;
        root.right = right;
        return root;//返回建立好的segment tree的根节点
    }
        
    public long query(SegmentTreeNode root, int queryStart, int queryEnd) {
        int start = root.start;
        int end = root.end;
        if (start == queryStart && end == queryEnd) {
            return root.sum;//查询区间刚好就是该node的区间
        }
        long leftSum = 0;
        long rightSum = 0;
        int mid = start + (end - start) / 2;//mid是node区间的中点
        if (queryStart <= mid) {
            leftSum = query(root.left, queryStart, Math.min(mid, queryEnd));
        }
        if (queryEnd >= mid + 1) {
            rightSum = query(root.right, Math.max(mid+1, queryStart), queryEnd);
        }
        //如果查询区间和node区间没有任何交集, 就直接返回0了
        return leftSum + rightSum;
    }
}
