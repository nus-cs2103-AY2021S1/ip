package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import duke.task.*;

/**
 * The Ui class deals with interactions with the user.
 *
 * @author  Yen Pin Hsuan
 * @version 1.0
 */
public class Ui {

    public static final String LINE = "    ____________________________________________________________\n";
    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    private Scanner sc;

    /** Create and initiate an Ui object. */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /** Greet the user. */
    public void greet() {
        String greet = LINE + "    Hello! I'm Duke\n" + "    What can I do for you?\n" + LINE;
        System.out.println(greet);
    }

    /** Say bye to the user. */
    public void bye() {
        String exit = LINE + "     Bye. Hope to see you again soon!\n" + LINE;
        System.out.println(exit);
    }

    /**
     * Inform user that the task is marked as done.
     * @param task The task that has being marked as done.
     */
    public void done(Task task) {
        System.out.println(LINE +
                "    Nice! I've marked this task as done:" + "\n" +
                "    " + task.toString() + "\n" +
                LINE);
    }

    /**
     * Returns the input given by user.
     * @return The input of user.
     */
    public String readCommand() {
        return sc.next();
    }

    /**
     * Return the task number the user input.
     * @return Task number that the user input.
     */
    public int readTaskNumber() {
        return sc.nextInt();
    }

    /**
     * Display the error message.
     * @param message The message ti be displayed.
     */
    public void showError(String message) {
        System.out.println(LINE + "    " + message + "\n" + LINE);
    }

    /**
     * Return the todo the user specified.
     * @return A todo with the details given by user.
     */
    public ToDo getToDo() throws DukeException {
        String detail = sc.nextLine().trim();
        if (detail.equals("")) {
            throw new DukeException("Oops! Todo cannot be empty");
        }
        return new ToDo(detail);
    }


    /**
     * Return the event the user specified.
     * @return A event with the details and date given by user.
     */
    public Event getEvent() throws DukeException {
        String s = sc.nextLine();
        if (s.trim().equals("")) {
            throw new DukeException("Oops! Event cannot be empty");
        }
        String[] arr = s.split("/at");
        if (arr.length == 1) {
            throw new DukeException("Oops! You need to include both detail and time.");
        }
        String detail = arr[0].trim();
        LocalDateTime date = LocalDateTime.parse(arr[1].trim(), df);
        return new Event(detail, date);
    }

    /**
     * Return the deadline the user specified.
     * @return A deadline with the details and date given by user.
     */
    public Deadline getDeadline() throws DukeException {
        String s = sc.nextLine();
        if (s.trim().equals("")) {
            throw new DukeException("Oops! Deadline cannot be empty");
        }
        String[] arr = s.split("/by");
        if (arr.length == 1) {
            throw new DukeException("Oops! You need to include both detail and time.");
        }
        String detail = arr[0].trim();
        LocalDateTime date = LocalDateTime.parse(arr[1].trim(), df);
        return new Deadline(detail, date);
    }

    /**
     * Inform the user that the task is deleted.
     * @param task The task that has being deleted.
     * @param taskList The list of task remaining.
     */
    public void deleteTask(Task task, TaskList taskList) {
        System.out.println(LINE +
                "    Noted. I've removed this task:" + "\n" +
                "      " + task.toString() + "\n" +
                String.format("    Now you have %d tasks in the list.\n", taskList.size()) +
                LINE);
    }

    /**
     * Inform the user that the task is added to the list.
     * @param task The task that is added.
     * @param taskList The list containing all tasks.
     */
    public void addTask(Task task, TaskList taskList) {
        System.out.println(LINE +
                "    Got it! I have added this task to the list!" + "\n" +
                "      " + task + "\n" +
                String.format("    Now you have %d tasks in the list.", taskList.size()) + "\n" +
                LINE);
    }
}
