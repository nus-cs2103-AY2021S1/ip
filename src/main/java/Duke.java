import java.util.*;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        // arraylist to store all text entered by user
        ArrayList<String> stored = new ArrayList<>();

        // displayed once main is run, without input from user
        String greetings =
            "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________\n";

        System.out.println(greetings);
        Scanner sc = new Scanner(System.in);

        // continuously scan for input from user until "bye" is invoked
        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (!input.equals("list") && !input.equals("bye")) {
                stored.add(input);
            }

            System.out.println("    ____________________________________________________________");

            // display list of items to user when requested with "list" command
            if (input.equals("list")) {
                for (int i = 1; i < stored.size() + 1; i++) {
                    String item = "     " + i + ". " + stored.get(i - 1);
                    System.out.println(item);
                }

            } else if (!input.equals("bye")) {
                String output = "     added: " + input;
                System.out.println(output);

            } else {
                // 'bye' command invoked, print goodbye message and exit program
                String goodbye =
                    "    ____________________________________________________________\n" +
                    "     Bye. Hope to see you again soon!\n" +
                    "    ____________________________________________________________";

                System.out.println(goodbye);
                break;
            }

            System.out.println("    ____________________________________________________________\n");

        }
    }
}