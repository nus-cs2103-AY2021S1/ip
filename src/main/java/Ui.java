import java.util.Scanner;

/**
 * Represents an object that is responsible for user interaction.
 * This includes reading user input and displaying the correspondent output.
 */
public class Ui {
    private final String LINE_BREAK = "   ____________________________" +
            "________________________________";
    private final String SPACE1 = "    ";
    private final String SPACE2 = "     ";
    private final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);

        System.out.println(LINE_BREAK);
        System.out.println(SPACE1 + "Hey, I'm Emilia \u2764 !\n" + SPACE1 +
                "What can I do for you?");
        System.out.println(LINE_BREAK);
    }

    public void showError(String errorMessage) {
        System.out.println(SPACE1 + errorMessage);
    }

    public void showErrorLoad(String errorMessage) {
        System.out.println("\n" + errorMessage);
        System.out.println("Initialize an empty TaskList!\n");
    }
    public void showLine() {
        System.out.println(LINE_BREAK);
    }

    public String readLine() {
        return sc.nextLine().trim().toLowerCase();
    }

    public void showBye() {
        System.out.println(SPACE1 + "Welcome back \u2764 !" );
    }

    public void showList() {
        System.out.println(SPACE1 + "Please take a look at the tasks:");
    }

    public void showTask(int count, Task task) {
        System.out.println(SPACE1 + count + ". " + task);
    }

    public void showDone(Task task) {
        System.out.println(SPACE1 + "Understood, I've marked this " +
                "task as done:\n" + SPACE2 + task);
    }

    public void showDelete(Task task, TaskList list) {
        System.out.println(SPACE1 + "Understood, I've deleted this task\n" +
                SPACE2 + task + "\n" + SPACE1 + "You have " + list.getSize() +
                " tasks in your list now!");
    }

    public void showCheck() {
        System.out.println(SPACE1 + "Hey! I have printed out the tasks that match the date:");
    }

    public void showAdd(Task current, TaskList list) {
        System.out.println(SPACE1 + "Understood! I've added this task:\n" +
                SPACE2 + current);
        System.out.println(SPACE1 + "You have " +
                list.getSize() + " tasks in your list now!");
    }

    public void showFind() {
        System.out.println(SPACE1 + "Hey! I have printed out the tasks that match the name:");
    }

    public void showNothingFound() {
        System.out.println(SPACE1 + "Hmm, I didn't find anything that match your input");
    }

}
