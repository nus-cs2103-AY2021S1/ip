package utils;

import java.util.stream.Stream;

/**
 * Some utils which are repeatedly used
 */
public class TokenUtils {
    /**
     * Drop the first element of the token array
     * @param tokens array
     * @return new array without the first element
     */
    public static String[] dropFirst(String[] tokens) {
        if (tokens.length == 0) {
            return tokens;
        }
        String[] result = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, result, 0, tokens.length - 1);
        return result;
    }

    /**
     * A list of tokens to string
     * @param tokens array of tokens
     * @return a string
     */
    public static String tokensToString(String[] tokens) {
        Stream<String> stream = Stream.of(tokens);
        return stream.reduce((a, b) -> a + " " + b).orElseGet(() -> "");
    }

    /**
     * A string to list of tokens
     * @param s a string
     * @return a list of tokens
     */
    public static String[] stringToTokens(String s) {
        return s.split(" ");
    }

    /**
     * Helper to help debug
     */
    public static void printTokens(String[] tokens) {
        System.out.println("Token here");
        for (String token: tokens) {
            System.out.print(token + " ");
        }
        System.out.println("");
    }

    /**
     * Test whether a string S is a subsequence of String t
     * @param s the String s
     * @param t the String t
     * @return answer
     */
    public static boolean isSubsequence(String s, String t) {
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j + 1 < t.length() && t.charAt(j) != s.charAt(i)) {
                j++;
            }
            if (j == t.length()) {
                return false;
            }
        }
        return true;
    }
}
