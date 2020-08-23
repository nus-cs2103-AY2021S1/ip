import java.util.Scanner;

public class Ui {

    Scanner sc;
    public Ui() {
        sc = new Scanner(System.in);
    }
    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
