package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import duke.exception.DukeException;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {

    /**
     * Introduction message
     */
    public static final String GREET = "Hello! I'm Rose\n"
            + "What can I do for you? <3\n"
            + "Key in 'help' for assistance.\n";

    /**
     *  Farewell message
     */
    public static final String FAREWELL = "Bye~ Hope to see you again soon! <3\n";

    private static final String LS = System.lineSeparator();

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Initiate Ui
     * @param in   InputStream
     * @param out  PrintStream
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Read use input.
     * @return String input of user
     */
    public String readLine() {
        String input = in.nextLine();
        return input;
    }

    /**
     * Show user input message(s).
     * @param message  Message(s) to be shown
     * @return Message(s)
     */
    public static String show(String ... message) {
        String response = "";
        for (String m : message) {
            response += m;
        }
        return response;
    }

    /**
     * Show intro message.
     * @return Intro message
     */
    public static String showIntro() {
        return show(GREET);
    }

    /**
     * Show farewell message.
     * @return Farewell message
     */
    public static String showFarewell() {
        return show(FAREWELL);
    }

    /**
     * Show help message.
     * @return Help message
     */
    public static String showHelp() {
        return show("I'm glad to help you! :D\n",
                "I can help keep track of your tasks, here are the commands available:\n",
                "Add ToDo task\n    - 'todo DESCRIPTION'\n",
                "Add Deadline task\n    - 'deadline DESCRIPTION /by yyyy/mm/dd HHmm'\n",
                "Add Event task\n    - 'event DESCRIPTION /at yyyy/mm/dd HHmm'\n",
                "Show task list\n    - 'list'\n",
                "Complete task\n    - 'done INDEX'\n",
                "Delete task\n    - 'delete INDEX'\n",
                "Find task\n    - 'find KEYWORD'\n",
                "Update task\n    - 'update INDEX [d/DESCRIPTION] [dt/yyyy/mm/dd HHmm]'\n",
                "Exit application\n    - 'bye'\n");
    }

    /**
     * Show error message.
     * @param e  Exception shown
     * @return Error message
     */
    public static String showError(DukeException e) {
        return show(e.toString());
    }

    /**
     * Show add message.
     * @param task      Task added
     * @param tasklist  TaskList which Task is stored
     * @return Add message
     */
    public static String showAdd(Task task, TaskList tasklist) {
        return show("Got it. I've added this task:\n",
                task.toString(),
                "\nNow you have " + tasklist.getNumOfTask() + " tasks in the list.\n");
    }

    /**
     * Show done message.
     * @param task Task done
     * @return Done message
     */
    public static String showDone(Task task) {
        return show("Nice! I've marked this task as done:\n", task + "\n");
    }

    /**
     * Show delete message.
     * @param task      Task added
     * @param tasklist  TaskList which Task is stored
     * @return Delete message
     */
    public static String showDelete(Task task, TaskList tasklist) {
        String taskMsg = "Deleted: " + task + "\n";
        return show("Noted. I've removed this task:\n",
                task.toString(),
                "\nNow you have " + tasklist.getNumOfTask() + " tasks in the list.\n");
    }

    /**
     * Show update file failure.
     * @return Update fail message
     */
    public static String showFileError() {
        return show("Update file failed");
    }

    /**
     * Show tasks found.
     * @param input       Keyword
     * @param tasksFound  Tasks found
     * @return Return user tasks containing keyword
     */
    public static String showTaskFound(String input, String tasksFound) {
        return show("Here are the matching tasks in your list that contains \""
                +  input + "\":\n", tasksFound);
    }

    /**
     * Show no tasks found.
     * @param input  Keyword
     * @return No tasks found message
     */
    public static String showNoTaskFound(String input) {
        return show("Sorry. No tasks found containing \"" + input + "\".\n");
    }

    /**
     * Show update message.
     * @param num   Update task index
     * @param task  Task to update
     * @return Update task message
     */
    public static String showUpdate(int num, Task task) {
        return show("Okay I have updated task " + num + ":\n",
                task.toString());
    }

}
