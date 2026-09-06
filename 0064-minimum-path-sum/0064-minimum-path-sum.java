/*
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i-1][0];
        }
        
        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j-1];
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }
        
        return grid[m-1][n-1];
    }
}
*/

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }
        return fk(m-1,n-1,dp,grid);
    }
    private static int fk(int i,int j,int[][] dp,int[][]grid){
        if(i<0||j<0)  return (int) 1e9;
        if(i == 0 && j == 0) return grid[0][0];
        if(dp[i][j]!= -1) return dp[i][j];
        // Compute path by going up
        int up = grid[i][j] + fk(i - 1, j,dp,grid);
        // Compute path by going left
        int left = grid[i][j] + fk(i, j - 1, dp,grid);
        return dp[i][j] = Math.min(up,left);
    } 
}