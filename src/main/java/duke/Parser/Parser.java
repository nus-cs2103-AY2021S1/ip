package duke.Parser;

/**
 * A Parser Class does all the checking required in the programme
 */

public class Parser {


    private final static String BYE_KEY = "bye";
    private final static String LIST_KEY = "list";
    private final static String DONE_KEY = "done";
    private final static String TODO_KEY = "todo";
    private final static String DEADLINE_KEY = "deadline";
    private final static String EVENT_KEY = "event";
    private final static String DELETE_KEY = "delete";
    private final static String FIND_KEY = "find";

    /**
     * Checks for BYE Command
     * @param checker
     * @return boolean
     */
    public static boolean isBye(String checker) {
        return checker.equals(BYE_KEY);
    }

    /**
     * Checks for LIST Command
     * @param checker
     * @return boolean
     */
    public static boolean isList(String checker) {
        return checker.equals(LIST_KEY);
    }

    /**
     * Checks for DONE Command
     * @param checker
     * @return boolean
     */
    public static boolean isComplete(String checker) {
        return checker.equals(DONE_KEY);
    }

    /**
     * Checks for TODO Command
     * @param checker
     * @return boolean
     */
    public static boolean isToDo(String checker) {
        return checker.equals(TODO_KEY);
    }

    /**
     * Checks for DEADLINE Command
     * @param checker
     * @return boolean
     */
    public static boolean isDeadline(String checker) {
        return checker.equals(DEADLINE_KEY);
    }

    /**
     * Checks for EVENT Command
     * @param checker
     * @return boolean
     */
    public static boolean isEvent(String checker) {
        return checker.equals(EVENT_KEY);
    }

    /**
     * Checks for DELETE Command
     * @param checker
     * @return boolean
     */
    public static boolean isDelete(String checker) {
        return checker.equals(DELETE_KEY);
    }

    /**
     * Checks for FIND Command
     * @param checker
     * @return boolean
     */
    public static boolean isFind(String checker) {
        return checker.equals(FIND_KEY);
    }


    /**
     * Checks if input is a number
     * @param num
     * @return boolean
     */
    public static boolean isNum(String num){
        try{
            int check = Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

}
