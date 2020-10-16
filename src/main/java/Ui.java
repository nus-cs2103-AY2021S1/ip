import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents a class for storage that deals with interactions with the user.
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;
    private static final String LINE_TOP =    "╭⋟───────────────────────────❀• *₊。❀°。─╮\n";
    private static final String LINE_BOTTOM = "╰──────────────────────────────────────⋞╯\n";
    private static final String HELP = "Here are the list of commands that Marco understands:\n" +
            "- help\n" +
            "[get a full list of commands and their usage]\n" +
            "- bye\n" +
            "[say goodbye to Marco]\n" +
            "- list\n" +
            "[list down all existing tasks in the task list]\n" +
            "- done TASK_NUMBER\n" +
            "[mark the task indexed at TASK_NUMBER as done]\n" +
            "- find KEY_WORD\n" +
            "[search for tasks contains KEY_WORD in description]\n" +
            "- delete TASK_NUMBER\n" +
            "[delete the task indexed at TASK_NUMBER]\n" +
            "- todo DESCRIPTION\n" +
            "[add a todo task with DESCRIPTION]\n" +
            "- event DESCRIPTION /at YYYY-MM-DD\n" +
            "[add an event with DESCRIPTION at YYYY-MM-DD]\n" +
            "- deadline DESCRIPTION /by YYYY-MM-DD\n" +
            "[add a deadline with DESCRIPTION at YYYY-MM-DD]\n";

    private static final String GREETING= "Yo ~ This is M-A-R-C-O Marco!ヾ(≧∇≦*)ゝ \n" +
            "The best task manager from the Puppy Universe U｡･ｪ･｡U \n" +
            "Is there anything that I can do for you?\n" +
            "Type 'help' to view the list of commands available \n";

    private static final String BYE = "Awwww, I guess you are gonna leave... o(TヘTo) \n" +
            "Marco will keep track of your tasks nicely! \n" +
            "Call me when you need me! " +
            "Marco is always here waiting for you ☀♪ ~ " +
            "Bye~ Have a nice day~ (●'◡'●)ﾉ♥ \n";


    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public static Scanner getIn() {return new Scanner(System.in);}

    /**
     * This method is used to greet the user.
     */
    public static String greet() {
        return LINE_TOP + GREETING + LINE_BOTTOM;
    }

    /**
     * This method is used to say good bye to the user.
     */
    public static String bye() {
        return LINE_TOP + BYE + LINE_BOTTOM;
    }

    public static String getHelp() {
        return HELP;
    }

    /**
     * This method is used to get command from the user.
     */
    public static String getUserCommand() {
        String fullInputLine = new Scanner(System.in).nextLine();
        return fullInputLine;
    }

    /**
     * This method is used to print all the tasks in the task list.
     * @param taskList The task list used for printing.
     */
    public static void printAllTasks(TaskList taskList) {
        System.out.println(returnAllTasks(taskList));
    }

    public static String returnAllTasks(TaskList taskList) {
        String stringToReturn = LINE_TOP + "Here are the tasks in your list: \n";
        int num = taskList.getNoOfTasks();
        assert (num != 0) : "There is no existing task in your list! ";
        for (int i = 1; i < num + 1; i++) {
            Task cur = taskList.list.get(i - 1);
            stringToReturn += ("" + i + "." + cur + "\n");
        }
        stringToReturn += LINE_BOTTOM;
        return stringToReturn;
    }


    public static void printRelevantTasks(TaskList taskList, String keyWord) {
        System.out.println(returnRelevantTasks(taskList,keyWord));
    }

    public static String returnRelevantTasks(TaskList taskList, String keyWord) {
        String stringToReturn = LINE_TOP + "Here are the matching tasks in your list: \n";
        int n = taskList.getNoOfTasks();
        assert (n != 0) : "There is no matching task in your list! ";
        for (int i = 1; i < n + 1; i++) {
            Task cur = taskList.list.get(i - 1);
            if (!cur.toString().contains(keyWord)) {
                continue;
            }
            stringToReturn += ("" + i + "." + cur + "\n");
        }
        stringToReturn += LINE_BOTTOM;
        return stringToReturn;
    }


    /**
     * This method is used to warn the user that the list is empty.
     */
    public static String emptyList() {
        Exception ex = new InvalidInputException("Oops, your list is currently empty. Add some tasks first!");
        String stringToReturn = LINE_TOP + ex.getMessage() + "\n" + LINE_BOTTOM;
        return stringToReturn;
    }

    // ADD RELATED
    public static String addSuccessful(Task added, int noOfTasks) {
        String stringToReturn = LINE_TOP + "Got it. I've added this task: \n"
                                + added + "\n" + "Now you have " + noOfTasks +
                                 " tasks in the list.\n " + LINE_BOTTOM ;
        return stringToReturn;
    }

    /**
     * This method is used to warn the user that the command is undefined.
     */
    public static String unknownCommand() {
        Exception ex = new InvalidInputException("Ah oh! I didn't know what that means >n<, sorry! ");
        String stringToReturn = LINE_TOP + ex.getMessage() + "\n" + LINE_BOTTOM;
        return stringToReturn;
    }

    // DELETE RELATED
    public static String deleteSuccessful(Task deleted, TaskList list) {
        assert (list.getNoOfTasks() >= 0) : "There must be zero or more tasks in a task list.";
        String stringToReturn = LINE_TOP + "Got it. I've removed this task: \n" + deleted +
                                "\n" + "Now you have " + list.getNoOfTasks() +
                                " tasks in the list. " + "\n" + LINE_BOTTOM;
        return  stringToReturn;
    }

    /**
     * This method is used to warn the user that task to be deleted is not specified.
     */
    public static String incompleteDeleteCommand() {
        Exception ex = new InvalidInputException("Hey, you forgot to tell me which task to delete");
        String stringToReturn = LINE_TOP + ex.getMessage() + "\n" + LINE_BOTTOM;
        return stringToReturn;
    }

    public static String setTagSuccessful(String tag, Task task) {
        String stringToReturn = LINE_TOP + "Nice! I've added the tag <" + tag + "> for the following task: \n" +
                                task + "\n" + LINE_BOTTOM;
        return stringToReturn;
    }

    // MODIFY
    public static String markDoneSuccessful(Task done) {
        assert (done.isDone == true) : "Task is not marked as done as intended.";
        String stringToReturn = LINE_TOP + "Nice! I've marked this task as done: \n" +
                                done + "\n" + LINE_BOTTOM;
        return stringToReturn;
    }

    // PRINT RELATED
    public static String markDoneFailure() {
        Exception ex = new InvalidInputException("Hey, you forgot to tell me which task is done!");
        String stringToReturn = LINE_TOP + ex.getMessage() + "\n" + LINE_BOTTOM;
        return stringToReturn;
    }

    public static String duplicatedMarkDone() {
        Exception ex = new InvalidInputException("Hey, this task is already done!");
        String stringToReturn = LINE_TOP + ex.getMessage() + "\n" + LINE_BOTTOM;
        return stringToReturn;
    }

    /**
     * This method is used to warn the user that the task referred to has not been created.
     */
    public static String uncreatedTask() {
        Exception ex = new InvalidInputException("Oops, this task has not been created yet!");
        String stringToReturn = LINE_TOP + ex.getMessage() + "\n" + LINE_BOTTOM;
        return stringToReturn;
    }

    /**
     * This method is used to warn the user that there is not prior data from Duke.
     */
    public static String showLoadingError() {
        return "Creating the storage file...";
    }


    public static String  missingDescription(String type) {
        assert (type == "T" | type == "D" | type =="E") : "Incorrect type.";
        Exception ex = new InvalidInputException("Hey, you forget the description for your " + type +"!");
        String stringToReturn = LINE_TOP + ex.getMessage() + "\n" + LINE_BOTTOM;
        return stringToReturn;
    }

    /**
     * This method is used to warn the user that the deadline information is missing.
     */
    public static String missingDeadline() {
        Exception ex = new InvalidInputException("Sorry, but I can't help if you don't tell me the exact deadline!");
        String stringToReturn = LINE_TOP + ex.getMessage() + "\n" + LINE_BOTTOM;
        return stringToReturn;
    }

    /**
     * This method is used to warn the user that the event time is missing.
     */
    public static String missingEventTime() {
        Exception ex = new InvalidInputException("I see...But what time is this event at?");
        String stringToReturn = LINE_TOP + ex.getMessage() + "\n" + LINE_BOTTOM;
        return stringToReturn;
    }

    public static String incorrectDoneFormat() {
        Exception ex = new InvalidInputException("Your input format for 'done' command is wrong!" +
                                                 " It should be 'done TASK_NUMBER.");
        String stringToReturn = LINE_TOP + ex.getMessage() + "\n" + LINE_BOTTOM;
        return stringToReturn;
    }
}
