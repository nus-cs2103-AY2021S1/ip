import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);
    public void displayWelcome() {
        System.out.println("Hello from Bikini Bottom!");
        System.out.println("____________________________________________________________\n"
            + "Hello! I'm Spongebob\n"
            + "What can I do for you?");
    }

    public void displayLine() {
        System.out.println("____________________________________________________________\n");
    }

    public String readCommand() {
        String str = scanner.nextLine();
        return str;
    }

    public void displayBye() {
        System.out.println("Bye. Hope to see you again soon! Bahahahaha!\n"
                + "____________________________________________________________\n");
    }
}
