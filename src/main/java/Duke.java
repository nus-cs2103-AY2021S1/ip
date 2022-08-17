import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner nextCommand = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<String>();

        while (true) {
            String command = nextCommand.nextLine();
            if (command.equals("bye")) {
                break;
            }
            if (command.equals("list")) {
                for(int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else {
                tasks.add(command);
                System.out.println("added: " + command);
            }

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
