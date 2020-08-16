import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(
            "Hello! I'm Duke\n" +
            "What can I do for you?");

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String user_input = "";
        while (true) {
            user_input = scanner.nextLine();  // Read user input
            if (user_input.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");  // quit
                break;
            } else {
                System.out.println(user_input);  // Output user input
            }
        }
    }
}
