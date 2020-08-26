package duke.Parser;

public class Parser {


    private static String bye_key = "bye";
    private static String list_key = "list";
    private static String done_key = "done";
    private static String todo_key = "todo";
    private static String deadline_key = "deadline";
    private static String event_key = "event";
    private static String delete_key = "delete";

    // check for bye command
    public static boolean isBye(String checker) {
        return checker.equals(bye_key);
    }

    // check for list command
    public static boolean isList(String checker) {
        return checker.equals(list_key);
    }

    // check for done command
    public static boolean isComplete(String checker) {
        return checker.equals(done_key);
    }

    // check for todo command
    public static boolean isToDo(String checker) {
        return checker.equals(todo_key);
    }

    // check for deadline command
    public static boolean isDeadline(String checker) {
        return checker.equals(deadline_key);
    }

    // check for event command
    public static boolean isEvent(String checker) {
        return checker.equals(event_key);
    }

    // check for delete command
    public static boolean isDelete(String checker) {
        return checker.equals(delete_key);
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
