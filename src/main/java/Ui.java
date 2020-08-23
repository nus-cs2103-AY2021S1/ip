import java.util.Scanner;

public class Ui {

    static final String GREETING = "Hello Boss! How can I help you?";
    static final String HORIZONTAL_LINE = "--------------------------------------";
    static final String SHOW_TASK = "Here are the tasks in your list:";
    static final String BYE = "Bye Boss! Hope to see you again!";
    static final String TAB = "   ";

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
        for (int i = 0; i < Task.tasks.size(); i++) {
            int number = i + 1;
            System.out.println(number + "." + Task.tasks.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void bye() {
        System.out.println(BYE + "\n" + HORIZONTAL_LINE);
    }

    public void printTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(TAB + task);
        System.out.println("Now you have " + Task.tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printDone(int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(TAB + Task.tasks.get(index));
        System.out.println(HORIZONTAL_LINE);
    }

    public void printDelete(int indexToDelete) {
        System.out.println(TAB + Task.tasks.get(indexToDelete));
        Task.tasks.remove(indexToDelete);
        System.out.println("Now you have " + Task.tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printError(String e) {
        System.out.println(e + "\n" + HORIZONTAL_LINE);
    }

    public void printDateTimeParseError(String e) {
        System.out.println(TAB + "Please enter date in the format yyyy-MM-dd HHmm");
    }
}
