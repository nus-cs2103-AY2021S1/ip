import java.util.Scanner;

/**
 * Ui class is responsible for printing the necessary messages
 * such that the user would be able to view them on
 * the interface.
 */
public class Ui {

    static final String GREETING = "Hello Boss! How can I help you?";
    static final String HORIZONTAL_LINE = "--------------------------------------";
    static final String SHOW_TASK = "Here are the tasks in your list:";
    static final String BYE = "Bye Boss! Hope to see you again!";
    static final String TAB = "   ";

    Scanner sc;

    /**
     * Constructor of Ui.
     */
    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the next sentence of user input.
     *
     * @return string sentence of user input.
     */
    public String getCommand() {
        return sc.nextLine();
    }

    /**
     * Returns greeting message.
     */
    public void greeting() {
        System.out.println(GREETING);
    }

    /**
     * Shows all the list.
     */
    public void showList() {
        System.out.println(SHOW_TASK);
        for (int i = 0; i < TaskList.taskList.size(); i++) {
            int number = i + 1;
            System.out.println(number + "." + TaskList.taskList.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Returns bye message.
     */
    public void bye() {
        System.out.println(BYE + "\n" + HORIZONTAL_LINE);
    }

    /**
     * Returns task message when added.
     *
     * @param task task to be added.
     */
    public void printTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(TAB + task);
        System.out.println("Now you have " + TaskList.taskList.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Returns done message.
     *
     * @param index number of list item to be marked done.
     */
    public void printDone(int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(TAB + TaskList.taskList.get(index));
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Returns message of delete and deletes the item from the list.
     *
     * @param indexToDelete number of list item to be deleted.
     */
    public void printDelete(int indexToDelete) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(TAB + TaskList.taskList.get(indexToDelete));
        TaskList.taskList.remove(indexToDelete);
        System.out.println("Now you have " + TaskList.taskList.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Returns the error message.
     *
     * @param e error message.
     */
    public void printError(String e) {
        System.out.println(e + "\n" + HORIZONTAL_LINE);
    }

    /**
     * Returns the error message of date time parse error.
     *
     * @param e error message.
     */
    public void printDateTimeParseError(String e) {
        System.out.println(TAB + "Please enter date in 'yyyy-MM-dd' format");
    }
}
