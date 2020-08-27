import java.util.Scanner;

public class UI {
    private final Scanner sc;

    public UI() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void displayFormat(String format, Object... args) {
        System.out.printf(format, args);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        displayMessage("Hello from\n" + logo);
        displayMessage("What can I do for you?\n");
    }

    public void bye() {
        displayMessage("Bye. Hope to see you again soon!");
    }
}
