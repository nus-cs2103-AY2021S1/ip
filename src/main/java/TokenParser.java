public class TokenParser {
    public static Integer parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new CommandInvalidArgumentFormatException();
        } catch (Exception e) {
            throw e;
        }
    }

    public static String[] tokenize(String str) {
        return str.split(" ", 2);
    }
}
