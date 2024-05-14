package org.servers.OTHERS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuessingGame {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a guess: ");
        do {
            try {
                String line;

                while ((line = reader.readLine()) != null) {
                    if (line.equals("exit")) {
                        break;
                    }
                }
                final Pattern pattern = Pattern.compile("^[a-zA-Z]+(?:[A-Z][a-z]*)*$");
                assert line != null;
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    Random random = new Random();
                    int length = line.length();
                    StringBuilder builder = new StringBuilder(length);
                    for (int i = 0; i < length; i++) {
                        random.nextInt(line.length());
                        builder.append(line.charAt(i));

                    }
                    String randomString = builder.toString();

                    if (line.compareTo(randomString) == 0) {
                        System.out.println("The strings are equal");
                    } else {
                        System.out.println("The string are not equal");
                        System.out.println(STR."line \{line}");
                        System.out.println(STR."RandomString \{randomString}");

                    }

                } else {
                    System.out.println("Enter a valid string that consist of letters only");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (true);


    }


}
