import java.util.ArrayList;
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
        ArrayList<String> textList = new ArrayList<String>();
        while (true) {
            user_input = scanner.nextLine();  // Read user input
            if (user_input.equals("bye")) {
                // quit
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (user_input.equals("list")) {
                // list text
                int index = 1;
                for (String text : textList) {
                    System.out.println(String.format("%s. %s", index, text));
                    index += 1;
                }
            } else {
                // add text
                textList.add(user_input);
                System.out.println(String.format("added: %s", user_input));  // Output user input
            }
        }
    }
}
