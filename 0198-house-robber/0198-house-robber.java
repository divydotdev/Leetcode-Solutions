class Solution {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return rec(nums, 0, dp);
    }
    private static int rec(int[] nums, int i, int[] dp) {
        if (i >= nums.length) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int raid = nums[i] + rec(nums, i + 2, dp);
        int skip = rec(nums, i + 1, dp);
        dp[i] = Math.max(skip, raid);
        return dp[i];
    }
}