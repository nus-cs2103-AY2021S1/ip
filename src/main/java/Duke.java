import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner nextCommand = new Scanner(System.in);
        while (true) {
            String command = nextCommand.nextLine();
            if (command.equals("bye")) {
                break;
            }
            System.out.println(command);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
