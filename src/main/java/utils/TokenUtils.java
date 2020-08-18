package utils;

public class TokenUtils {
    public static String[] dropFirst(String[] tokens) {
        if (tokens.length == 0) {
            return tokens;
        }
        String[] result = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, result, 0, tokens.length - 1);
        return result;
    }

    public static void printTokens(String[] tokens) {
        System.out.println("Token here");
        for (String token: tokens) {
            System.out.print(token + " ");
        }
        System.out.println("");
    }
}
