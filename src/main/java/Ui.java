import java.util.Scanner;

public class Ui {

    private static final String GREETING = "Hello Boss! How can I help you?";
    private static final String HORIZONTAL_LINE = "--------------------------------------";
    private static final String SHOW_TASK = "Here are the tasks in your list:";
    private static final String BYE = "Bye Boss! Hope to see you again!";
    private static final String TAB = "   ";

    Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getCommand() {
        return sc.nextLine();
    }

    public void greeting() {
        System.out.println(GREETING);
    }

    public void showList() {
        System.out.println(SHOW_TASK);
        for (int i = 0; i < TaskList.taskList.size(); i++) {
            int number = i + 1;
            System.out.println(number + "." + TaskList.taskList.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void bye() {
        System.out.println(BYE + "\n" + HORIZONTAL_LINE);
    }

    public void printTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(TAB + task);
        System.out.println("Now you have " + TaskList.taskList.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printDone(int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(TAB + TaskList.taskList.get(index));
        System.out.println(HORIZONTAL_LINE);
    }

    public void printDelete(int indexToDelete) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(TAB + TaskList.taskList.get(indexToDelete));
        TaskList.taskList.remove(indexToDelete);
        System.out.println("Now you have " + TaskList.taskList.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printError(String e) {
        System.out.println(e + "\n" + HORIZONTAL_LINE);
    }

    public void printDateTimeParseError(String e) {
        System.out.println(TAB + "Please enter date in 'yyyy-MM-dd' format");
    }
}
