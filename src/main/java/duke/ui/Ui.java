package duke.ui;

import duke.task.Task;

import duke.tasklist.TaskList;

import java.util.Scanner;

/**
 * Handles the program interactions with user.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Initialises the Ui object and scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Takes in one line of user input.
     *
     * @return User input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the formatted input message.
     *
     * @param msg Input message.
     */
    public void printMsg(String msg) {
        String separator = "   ---------------------------------------------------------------------------\n";
        System.out.print(separator);
        System.out.printf("    %s\n", msg);
        System.out.println(separator);
    }

    /**
     * Greets the user upon starting the program.
     */
    public void greetings() {
        System.out.println("Hello, I'm Duke!\nWhat can I do for you?");
    }

    /**
     * Prints the goodbye message.
     * Closes scanner.
     */
    public void goodBye() {
        printMsg("Bye! Hope to see you again soon! ☺");
        scanner.close();
    }

    /**
     * Prints the file creation message.
     */
    public void fileCreationError() {
        System.out.println("Error in creating file.\n");
    }

    /**
     * Prints the file update error.
     */
    public void fileUpdateError() {
        System.out.println("Error in updating file\n");
    }

    /**
     * Prints the file read error when reading from the CSV file.
     */
    public void fileReadingError() {
        System.out.println("Error in reading from csv file\n");
    }

    /**
     * Prints out a message with basic formatting.
     *
     * @param input Input message.
     */
    public void printBasic(String input) {
        System.out.printf("%s\n\n", input);
    }

    /**
     * Prints task done message.
     *
     * @param current Input task.
     */
    public void markTaskAsDone(Task current) {
        printMsg(String.format("Nice! I've marked this task as done:\n      %s", current));
    }

    /**
     * Prints the deletion success message.
     *
     * @param current Current task.
     * @param size Size of task list.
     */
    public void deleteTask(Task current, int size) {
        printMsg(String.format("Noted. I've removed this task:\n      %s\n" +
                "    Now you have %d tasks in the list.", current, size));
    }

    /**
     * Prints the add task message.
     *
     * @param newTask New task added.
     * @param size Size of task list.
     */
    public void addTask(Task newTask, int size) {
        printMsg(String.format("Got it. I've added this task:\n      %s\n" +
                "    Now you have %d tasks in the list.", newTask, size));
    }

    /**
     * Prints the empty task list message.
     */
    public void emptyTaskList() {
        printMsg("You currently have no tasks in the list.");
    }

    /**
     * Prints the task list to the user.
     *
     * @param tasks Task list.
     * @param extra Extra word to add in, if any.
     */
    public void showTaskList(TaskList tasks, String extra) {
        StringBuilder str1 = new StringBuilder();
        str1.append(String.format("Here are the %s tasks in your list:\n", extra));
        int size = tasks.size();
        for (int i = 0; i < size - 1; i++) {
            str1.append(String.format("     %d.%s\n", i + 1, tasks.get(i)));
        }
        str1.append(String.format("     %d.%s", size, tasks.get(size - 1)));
        printMsg(str1.toString());
    }

    /**
     * Prints the no matching tasks found message.
     *
     * @param queryWord Word use to query task list.
     */
    public void emptyFind(String queryWord) {
        printMsg(String.format("There are no matching tasks with the keyword %s.", queryWord));
    }
}
