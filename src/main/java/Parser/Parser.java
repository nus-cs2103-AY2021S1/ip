package Parser;

import TaskList.TaskList;
import Tasks.Task;

public class Parser {
    private static String longLine = "________________________________________________________________________________";

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

    //method to segment every String that is being fed into this method
    public static void lineFormatter (String printable){
        System.out.println(longLine);
        System.out.println(printable);
        System.out.println(longLine);
    }

    // standardised goodbye greeting
    public static void byeGreetings () {
        lineFormatter("Bye! Hope to see you soon again?!");
    }

    //method to mark tasks as done
    public static void taskDone(Task task) {
        lineFormatter("Nice! This task is getting done!!\n" + "[" + task.getStatusIcon() + "] " + task.getTask());
    }

    //method to mark tasks as deleted
    public static void taskDeleted(Task task) {
        lineFormatter("The following Tasks.Task is removed from the TaskList.TaskList!!\n" + "[" + task.getStatusIcon() + "] "
                + task.getTask() + "\n" +
                "You have " + TaskList.getSize() + " tasks left!"
        );
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

    //method for formatting inputs into the taskList
    public static void newTaskItem (Task task){
        lineFormatter("Now you have a new task! :\n" + task.toString() +
                "\nYou currently have " + TaskList.getSize() + " events in your list\n" +
                "Type \'list\' to check your Tasklist");
    }
}
