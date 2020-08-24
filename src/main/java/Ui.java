import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    static String LINE = "    ____________________________________________________________";
    static String INDENT = "    ";
    Scanner sc = new Scanner(System.in);

    void showWelcome() {
        System.out.println(LINE);
        System.out.println(INDENT + "Hello! What can I do for you?");
        System.out.println(LINE);
    }

    void showLine() {
        System.out.println(LINE);
    }

    String readCommand() {
        return sc.nextLine();
    }

    void printNumTask(int numTask) {
        System.out.println(String.format("%sNow you have %d tasks in the list.", INDENT, numTask));
    }

    void showDelete(Task removed, int numLeft) {
        showLine();
        System.out.println(INDENT + "Tasked removed: ");
        System.out.println(INDENT + removed.getOutput());
        printNumTask(numLeft);
        showLine();
    }

    public void printList(Storage storage) {
        ArrayList<Task> arrTask = storage.load();
        System.out.println(LINE);
        System.out.println(INDENT + "Here are the tasks in your list:");
        for(int i = 0; i < arrTask.size(); i++) {
            System.out.println(String.format("%s%d. %s",INDENT,  i + 1, arrTask.get(i).getOutput()));
        }
        System.out.println(LINE);
    }

    public void bye() {
        System.out.println(LINE);
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
