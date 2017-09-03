 class Main {
    public static void main(String[] args) {
        int[] arr = {1,4,5,7,9};
        System.out.println(getIndex(arr, 5));
    }
    
    static int getIndex(int[] nums, int target) {
        // starts at 0, 1, 2, 4, 8, 16....
        // if  index = 16, break, can be due to out of bounds or bigger than target
        if (nums[0] == target)
            return 0;
        int index = 1;

        while (true) {
            try {
                if (nums[index] == target) {
                    return index;
                } else if (nums[index] < target) {
                    index *= 2;
                } else if (nums[index] > target) {
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
        
        //now index is bigger than target or out of bounds
        //left bound is index / 2 + 1, rightbound is index - 1
        int left = index / 2 + 1, right = index - 1;
        while (left <= right) {
            try {
                int mid = left + (right - left) / 2;//mid could be out of bounds
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } catch(Exception e) {
                right = left + (right - left) / 2 - 1;// mid - 1
            }
        }
        return -1;
        
    }
}