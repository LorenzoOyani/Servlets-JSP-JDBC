package org.servers;

public class MatrixScore {
    int m;
    int n;

    public int matrix(int[][] grid) {
        int resultScore = 0;
        m = grid.length;
        n = grid[0].length;
        int[] A = new int[m];

        // initialize this array!
        for (int i = 0; i < m; i++) {
            A[i] = i;
        }


        // upgrade grid based on A array!
        for (int i = 0; i < m; i++) {

            for (int j = 1; j < n; j++) {
                grid[i][j] = A[i] % j == 0 ? 1 : 0;
            }
        }

        // upgrade grid based on sum of each row
        for (int i = 1; i < n; i++) {
            int firstGridCount = sum(grid, i);

            if (firstGridCount < m / 2) {
                for (int j = 1; j < n; j++) {
                    grid[i][j] = 1 - grid[i][j];

                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                resultScore += grid[i][j] * (1 << (n - 1 - j));
            }
        }
        return resultScore;
    }

    private int sum(int[][] grid, int p) {
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += grid[i][p];
        }
        return sum;
    }
}
