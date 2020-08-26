package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents user interface class.
 */
public class Ui {
    /** Bot's reply prefix */
    private static final String bot = "Dave says:";

    /** Line separator */
    private static final String line = "_______________________________________________________________";

    /** Scanner object */
    Scanner scanner;

    /**
     * Constructs a new Ui object.
     */
    Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints greetings.
     */
    public void printGreetings() {
        System.out.println(line);
        System.out.println(bot);
        System.out.println("Greetings from me, Dave!\n" + "How can I help you? ^_^");
        System.out.println(line);
    }

    /**
     * Returns prefix of reply.
     *
     * @return String of prefix of reply.
     */
    public static String getBot() {
        return Ui.bot;
    }

    /**
     * Returns line separator.
     * @return Line separator.
     */
    public static String getLine() {
        return Ui.line;
    }

    /**
     * Prints goodbye and terminates.
     */
    public void printBye() {
        System.out.println(line);
        System.out.println(bot);
        System.out.println("Goodbye! Hope to see you again soon! ^_^");
        System.out.println(line);
        System.exit(0);
    }

    /**
     * Prints tasks in task list.
     *
     * @param tasks ArrayList containing tasks.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(line);
            System.out.println(bot);
            System.out.println("There are no tasks in your list yet! >_<");
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println(bot);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                //System.out.println(i + 1 + "." + " " + "[" + tasks.get(i).getStatusIcon() + "]" + " " + tasks.get(i).getTask());
                System.out.println(i + 1 + "." + " " + tasks.get(i));
            }
        }
    }

    /**
     * Marks tasks as done.
     *
     * @param tasks Arraylist of tasks.
     * @param pos Index in arraylist.
     * @throws DukeException When index < 0 or index > tasks.size().
     */
    public void printDone(ArrayList<Task> tasks, int pos) throws DukeException {
        if (pos <= tasks.size() && pos > 0) {
            tasks.get(pos - 1).markAsDone(); //marking task as done
            System.out.println(line);
            System.out.println(bot);
            System.out.println("Great work! I've marked this task as done:");
            //System.out.println("[" + tasks.get(pos - 1).getStatusIcon() + "]" + " " + tasks.get(pos - 1).getTask());
            System.out.println(tasks.get(pos - 1));
            System.out.println("Keep the ticks going! ^_^");
        } else {
            throw new DukeException("You have keyed in an invalid number!");
        }
    }

    /**
     * Prints tasks with the keyword.
     * @param findings Arraylist of tasks with the keyword.
     * @throws DukeException When findings is empty.
     */
    public void printFindings(ArrayList<Task> findings) throws DukeException {
        System.out.println(line);
        System.out.println(bot);
        if (findings.isEmpty()) {
            throw new DukeException("There are no such tasks with this keyword! :(");
        } else {
            System.out.println("These are the tasks with your keyword:");
            for (Task finding : findings) {
                System.out.println(finding);
            }
        }
    }

    /**
     * Prints replies when ToDo task is added.
     *
     * @param todoTask Task to be done.
     * @param tasks TaskList to add task to.
     */
    public void printAddTodo(ToDo todoTask, TaskList tasks) {
        System.out.println(line);
        System.out.println(bot);
        System.out.println("Got it! I've added this task:");
        System.out.println(todoTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints replies when Event task is added.
     *
     * @param eventTask Event task.
     * @param tasks TaskList to add task to.
     */
    public void printAddEvent(Event eventTask, TaskList tasks) {
        System.out.println(line);
        System.out.println(bot);
        System.out.println("Got it! I've added this task:");
        System.out.println(eventTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints replies when Deadline task is added.
     *
     * @param deadlineTask Deadline task.
     * @param tasks TaskList to add task to.
     */
    public void printAddDeadline(Deadline deadlineTask, TaskList tasks) {
        System.out.println(line);
        System.out.println(bot);
        System.out.println("Got it! I've added this task:");
        System.out.println(deadlineTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Returns user inputs.
     *
     * @return User input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Checks if there is more user input.
     *
     * @return True if there is more input, if not false.
     */
    public boolean hasMoreInput() {
        return scanner.hasNext();
    }

    /**
     * Prints loading of tasks error.
     */
    public void showLoadingError() {
        System.out.println("I am unable to load your tasks! D:");
    }
}
