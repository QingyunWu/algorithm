

private int quickSelect(int k , int[] nums, int start, int end) {
    if (start >= end) return nums[start];
    int i = start, j = end;
    int pivot = nums[start + (end - start) / 2];
    while (i <= j) {//i <= j 是为了最后i能大于j
        while (i <= j && nums[i] < pivot) i++;
        while (i <= j && nums[j] > pivot) j--;
        if (i <= j) {
            int temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
    }
    if (k <= j - start + 1) {
        return quickSelect(k, nums, start, j);
    } else if (k >= i - start + 1) {
        return quickSelect(k - (i - start + 1) + 1, nums, i, end);
    } else {
        return nums[j + 1];//j + 1可能是i也可能是j ,mid, i中的mid
    }
}