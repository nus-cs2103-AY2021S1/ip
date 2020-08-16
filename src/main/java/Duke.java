import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Duke {

    public static void main(String[] args) {
        String logo = "_________     _____  .______________\n"
                + "\\_   ___ \\   /  _  \\ |   \\__    ___/\n"
                + "/    \\  \\/  /  /_\\  \\|   | |    |   \n"
                + "\\     \\____/    |    \\   | |    |   \n"
                + " \\______  /\\____|__  /___| |____|   \n"
                + "        \\/         \\/               \n";
        System.out.println("Hi! I'm\n" + logo);
        System.out.println("What can I help you with?");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye! Let's talk again soon!");
                sc.close();
                break;
            } else if (command.equals("list")) {
                TaskManager.readList();
            } else {
                TaskManager.manageTask(command);
            }
        }
    }
}
