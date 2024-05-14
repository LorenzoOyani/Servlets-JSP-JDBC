package org.servers.OTHERS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleCalculator {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Stack stack = new Stack(1000);

        while (true) {
            System.out.println("enter an integer, ) ( included: ");
            String s = reader.readLine();
            int sLen = s.length();
            int result = 0;
            int sign = 0; // -1 for negative sign;
            int number = 0;


            for (int i = 0; i < sLen; i++) {
                char ch = s.charAt(i);
                if (Character.isDigit(ch)) {
                    number = 10 * number + (ch - '0');
                    if (ch == '+') {
                        result += sign * number;
                        sign = 1;
                        number = 0;

                    } else if (ch == '-') {
                        result += sign * number;
                        sign = -1;
                        number = 0;


                    } else if (ch == '(') {
                        stack.push(result);
                        stack.push(sign);

                        sign = 1;
                        result = 0;
                    } else if (ch == ')') {
                        result *= sign * number;

                        result *= stack.pop();
                        stack.pop(); // pop the last computation from the based Integer;

                        result *= stack.pop();
                        stack.pop();

                        number = 0;


                    }


                }
            }

        }


    }
}
