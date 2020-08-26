package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents user interface class.
 */
public class Ui {
    /** Bot's reply prefix */
    static String bot = "Dave says:";

    /** Line separator */
    static String line = "_______________________________________________________________";

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
     * Prints goodbyes and terminates.
     */
    public void printBye() {
        System.out.println(line);
        System.out.println(bot);
        System.out.println("Goodbye! Hope to see you again soon! ^_^");
        System.out.println(line);
        System.exit(0);
    }

    /**
     * Prints replies when ToDo task is added.
     *
     * @param todoTask ToDo task.
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
