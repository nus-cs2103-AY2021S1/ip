/**
 * Represents a parser that parses the different user input.
 */
public class Parser {


    /**
     * Parses the input to check for "bye".
     *
     * @param input Input of the user.
     * @return True if there is "bye".
     */
    public static boolean isBye(String input) {
        return input.equals(TaskElement.BYE.label);
    }


    /**
     * Parses the input to check for "list".
     *
     * @param input Input of the user.
     * @return True if there is "list".
     */
    public static boolean isList(String input) {
        return input.equals(TaskElement.LIST.label);

    }

    public static boolean isDone(String input) {
        return input.split(" ")[0].equals(TaskElement.DONE.label);
    }


    public static boolean isToDo(String input) {
        return input.split(" ")[0].equals(TaskElement.TODO.label);
    }

    public static boolean isDeadline(String input) {
        return input.split(" ")[0].equals(TaskElement.DEADLINE.label);
    }

    public static boolean isEvent(String input) {
        return input.split(" ")[0].equals(TaskElement.EVENT.label);
    }

    public static boolean isDelete(String input) {
        return input.split(" ")[0].equals(TaskElement.DELETE.label);
    }

    /**
     * Parses the remaining input to check for "done".
     *
     * @param input Input of the user.
     * @return True if there is "done".
     */
    public static boolean parseDone(String input) {
        return input.split(" ")[0].equals(TaskElement.DONE.label);
    }

    /**
     * Parses the remaining input to check for "todo".
     *
     * @param input Input of the user.
     * @return True if there is "todo".
     */
    public static boolean parseToDo(String input) {
        return input.split(" ")[0].equals(TaskElement.TODO.label);
    }

    /**
     * Parses the remaining input to check for "deadline".
     *
     * @param input Input of the user.
     * @return True if there is "deadline".
     */
    public static boolean parseDeadline(String input) {
        return input.split(" ")[0].equals(TaskElement.DEADLINE.label);
    }

    /**
     * Parses the remaining input to check for "event".
     *
     * @param input Input of the user.
     * @return True if there is "event".
     */
    public static boolean parseEvent(String input) {
        return input.split(" ")[0].equals(TaskElement.EVENT.label);
    }

    /**
     * Parses the input to check for "delete".
     *
     * @param input Input of the user.
     * @return True if there is "delete".
     */
    public static boolean parseDelete(String input) {
        return input.split(" ")[0].equals(TaskElement.DELETE.label);
    }

    /**
     * Builds up a string .
     *
     * @param arr   Array of the strings seperated.
     * @param start Starting Index.
     * @param end   Ending Index.
     * @return Returns the string that is built.
     */
    public static String stringBuilder(String[] arr, int start, int end) {
        String store = "";
        for (int i = start; i <= end; i++) {
            if (i == end) {
                store += arr[i];
            } else {
                store += arr[i] + " ";
            }

        }
        return store;
    }

    public static boolean isFind(String input){
        return input.split(" ")[0].equals(TaskElement.FIND.label);
    }


}
