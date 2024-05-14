package org.servers.OTHERS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AlbumCosts {

    int L, R, K;

    void main() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] A = new int[N];
        int[][] B = new int[N][101];

        String[] s = reader.readLine().split("");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(s[i]);
            for (int j = 1; j <= 100; j++) {
                B[i][j] = A[i] % j == 0 ? 1 : 0;

            }
        }
        // create an end index for the array;
        for (int j = 1; j <= 100; j++) {
            for (int i = 1; i < N; i++) {
                B[i][j] += B[i - 1][j];
            }
        }

        int Q = Integer.parseInt(reader.readLine());
        while (Q-- > 0) {
            s = reader.readLine().split("");
            L = Integer.parseInt(s[0]) - 1;
            R = Integer.parseInt(s[1]) - 1;
            K = Integer.parseInt(s[2]);

            int ans;
            if (L == 0) {
                ans = B[R][K];
            } else {
                ans = B[R][K] - B[L - 1][K];
            }
            System.out.println(ans);
        }


    }
}
