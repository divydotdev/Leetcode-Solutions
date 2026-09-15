class Solution {
    public boolean primeSubOperation(int[] nums) {
        int max = 1000;
        boolean[] isPrime = new boolean[max + 1];
        Arrays.fill(isPrime,true);
        for (int i = 2; i * i <= max; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= max; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int prev = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int p = nums[i] - 1; p >= 2; p--) {
                if (isPrime[p] && nums[i] - p > prev) {

                    nums[i] = nums[i] - p;
                    break;
                }
            }
            if (nums[i] <= prev) {
                return false;
            }

            prev = nums[i];
        }

        return true;
    }
}