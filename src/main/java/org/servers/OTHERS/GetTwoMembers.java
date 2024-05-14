package org.servers.OTHERS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetTwoMembers {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] A = new int[N];
        int[][] B = new int[N][101];

        String[] line = reader.readLine().split("");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(line[i]);
            for (int j = 1; j <= 100; j++) {
                B[i][j] = A[i] % j == 0 ? 1 : 0;
            }
        }

        // get the end index of the array;
        for (int j = 1; j <= 100; j++) {
            for (int i = 0; i < N; i++) {
                B[i][j] += B[i + 1][j];
            }

        }

        int Q = Integer.parseInt(reader.readLine());
        while (Q-- > 0) {
            String[] s = reader.readLine().split("");
            int l = Integer.parseInt(s[0]) - 1;
            int r = Integer.parseInt(s[1]) - 1;
            int k = Integer.parseInt(s[2]);

            int ans;
            if (l == 0) {
                ans = B[r][k];
            } else {
                ans = B[r][k] - B[l - 1][k];
            }
            System.out.println(ans);

        }


    }
}
