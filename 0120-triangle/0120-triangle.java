/*

class Solution {
    public int solve(int i, int j, List<List<Integer>> triangle, int n, int[][] dp) {
        if (dp[i][j] != -1)
            return dp[i][j];
        if (i == n - 1) return triangle.get(i).get(j);

        int down = triangle.get(i).get(j) + solve(i + 1, j, triangle, n, dp);
        int diag = triangle.get(i).get(j) + solve(i + 1, j + 1, triangle, n, dp);

        // Store and return min path sum
        return dp[i][j] = Math.min(down, diag);
    }
    
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return solve(0, 0, triangle, n, dp);
        
    }
}

*/

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {

 int n = triangle.size();
        
        // DP array initialized to match the bottom row's size
        int[] dp = new int[n];
        
        // Step 1: Initialize the DP table with the values of the bottom row
        for (int j = 0; j < n; j++) {
            dp[j] = triangle.get(n - 1).get(j);
        }
        
        // Step 2: Iterate backwards from the second-to-last row up to the top
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // The current spot's min path depends on the two choices directly below it
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        
        // The top of the triangle holds the minimum path total
        return dp[0];

    }}