class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        int exp = nums[0];
        for (int j = 0; j < nums.length; ) {
            if (nums[j] == exp) {
                exp++;
                j++;
            } else {
                res.add(exp);
                exp++;
            }
        }
        return res;
    }
}