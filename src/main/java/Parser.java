public class Parser {
    public static String[] stringSplit(String toSplit, String split) {
        return toSplit.split(split);
    }

    public static String[] stringSplitLimit(String toSplit, String split, int limit) {
        return toSplit.split(split, limit);
    }

    public static String getCommand(String line) {
        return stringSplitLimit(line, " ", 2)[0];
    }

    public static String getDetails(String line) {
        String[] splitString =  stringSplitLimit(line, " ", 2);
        return splitString.length < 2 ? "" : splitString[1];
    }

    public static int getIndex(String line) {
        return Integer.parseInt(getDetails(line)) - 1;
    }
}
