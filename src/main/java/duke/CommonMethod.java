package duke;

public class CommonMethod {
    // Merges the String array into one string, from index start, until (not including) index end
    public static String mergeArray(String[] array, int start, int end) {
        StringBuilder output = new StringBuilder();
        for (int i = start; i < end; i++) {
            output.append(array[i]).append(" ");
        }
        return output.toString().trim();
    }

    public static boolean isLeapYear(int year) {
        boolean isLeap = false;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                isLeap = year % 400 == 0;
            } else {
                isLeap = true;
            }
        }
        return isLeap;
    }
}
