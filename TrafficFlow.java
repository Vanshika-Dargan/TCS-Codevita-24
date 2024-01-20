import java.util.*;

class TrafficFlow {
    static int minSumPath(int x1, int y1, int x2, int y2, int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int dp[][] = new int[n+1][m+1];

        
        for (int i = x1; i <=x2; i++) {
            for (int j = y1; j <=y2; j++) {
                if (i == x1 && j == y1)
                    dp[i][j] = matrix[i][j]; 
                else {
                    int up = (i > 0) ? dp[i - 1][j] : (int) Math.pow(10, 9);
                    int left = (j > 0) ? dp[i][j - 1] : (int) Math.pow(10, 9);

                    // if (i > x1 || j > y1)
                    //     dp[i][j] = Math.min(up, left) + matrix[i][j];
                    // else
                    //     dp[i][j] = (int) Math.pow(10, 9);
                    // int right=(i<m)?dp[i+1][j]:(int) Math.pow(10, 9);
                    // int down=(j<n)?dp[i][j+1]:(int) Math.pow(10, 9);
                    
                    dp[i][j]=Math.min(up,left)+matrix[i][j];
                }
            }
        }

        
        return dp[x2][y2];
    }

    public static void main(String args[]) {
        int matrix[][] = {
            {3,1,9,2},
            {}
        };

        int x1 = 0, y1 = 0; // Starting point
        int x2 = 1, y2 = 2; // Ending point

        // Calculate and print the minimum sum path in the matrix from (x1, y1) to (x2, y2)
        System.out.println(minSumPath(x1, y1, x2, y2, matrix));
        
    }
}