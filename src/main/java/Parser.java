public class Parser {
    protected static String[] parse(String text) {
        String[] inputArr = deconstruct(text);
        String keyWord = getKeyWord(inputArr);
        String restOfWord = getRestOfWord(inputArr);
        return new String[] {keyWord, restOfWord};
    }

    private static String[] deconstruct(String input) {
        String formattedString = input.trim().replaceAll("\\s{2,}", " ");
        return formattedString.split(" ", 2);
    }

    private static String getKeyWord(String[] arr) {
        return arr[0].toLowerCase();
    }

    private static String getRestOfWord(String[] arr) {
        return arr.length == 1 ? "" : arr[1];
    }
}
