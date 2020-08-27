package duke.Parser;

public class Parser {


    private final static String BYE_KEY = "bye";
    private final static String LIST_KEY = "list";
    private final static String DONE_KEY = "done";
    private final static String TODO_KEY = "todo";
    private final static String DEADLINE_KEY = "deadline";
    private final static String EVENT_KEY = "event";
    private final static String DELETE_KEY = "delete";
    private final static String FIND_KEY = "find";

    // check for bye command
    public static boolean isBye(String checker) {
        return checker.equals(BYE_KEY);
    }

    // check for list command
    public static boolean isList(String checker) {
        return checker.equals(LIST_KEY);
    }

    // check for done command
    public static boolean isComplete(String checker) {
        return checker.equals(DONE_KEY);
    }

    // check for todo command
    public static boolean isToDo(String checker) {
        return checker.equals(TODO_KEY);
    }

    // check for deadline command
    public static boolean isDeadline(String checker) {
        return checker.equals(DEADLINE_KEY);
    }

    // check for event command
    public static boolean isEvent(String checker) {
        return checker.equals(EVENT_KEY);
    }

    // check for delete command
    public static boolean isDelete(String checker) {
        return checker.equals(DELETE_KEY);
    }

    // check for find command
    public static boolean isFind(String checker) {
        return checker.equals(FIND_KEY);
    }




    // method to check for int in String
    public static boolean isNum(String num){
        try{
            int check = Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

}
