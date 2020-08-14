import java.util.Scanner;

/**
 * Driver class for Duke chat bot
 */
public class Duke {
    public static void main(String[] args) {
        String intro = "Hello! I'm Jarvis\n"
                + "What can I do for you?";
        String command;

        System.out.println(intro);

        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        while (!command.equals("bye")) {
            System.out.println(command);
            command = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
