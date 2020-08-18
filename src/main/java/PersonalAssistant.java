import java.util.Scanner;
public class PersonalAssistant {
    public static void run() {
        System.out.println("> What can I do for you?");
        getUserCommands();
    }

    /**
     * Gets user input from STDIN
     * If the input command is "bye"
     * Exit and echo a farewell message
     * Otherwise echoes the input
     */
    public static void getUserCommands() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your command or \"bye\" to exit: ");
        String cmd = reader.nextLine();
        if (cmd.equals("bye")) {
            System.out.println("Goodbye!");
        } else {
            System.out.println(cmd);
            System.out.println("\n");
            getUserCommands();
        }
    }
}
