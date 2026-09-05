class Solution {
    // Function to solve the problem using recursion
    private int func(int i, int j, int[][] dp) {
        // Base case
        if (i == 0 && j == 0) return 1;

        // If we go out of bounds, there are no ways
        if (i < 0 || j < 0) return 0;
        
        // If already computed, return it
        if (dp[i][j] != -1)
            return dp[i][j];

        // Recursive calls for up and left moves
        int up = func(i - 1, j, dp);
        int left = func(i, j - 1, dp);

        // Store the result and return
        return dp[i][j] = up + left;
    }

    // Function to count total unique paths
    public int uniquePaths(int m, int n) {
        // DP array initialized with -1
        int[][] dp = new int[m][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return func(m - 1, n - 1, dp);
    }
}












/*
class Solution {
    public int uniquePaths(int m, int n) {
        int [][]arr=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0||j==0){
                    arr[i][j]=1;
                }else{
                    arr[i][j]=arr[i-1][j]+arr[i][j-1];
                }
            }
        }
        return arr[m-1][n-1];
    }
}

*/