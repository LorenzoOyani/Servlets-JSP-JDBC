package org.servers.OTHERS;

import java.util.Optional;

public class Skip_string {
    void main() {
        String s = "Hello";
        char c = 'l';
        System.out.println(skip(s, c));
    }

    public static String skip(String line, char c) {
        if (line.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder(line.substring(0, 1)); // initialize stringBuilder to contain the specified size of the subsequent substring
        for (int i = 1; i < line.length(); i++) {
            if (line.charAt(i) != c) {
                result.append(line.charAt(i));

            }
        }
        return result.toString();

    }

}
