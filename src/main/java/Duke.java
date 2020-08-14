import java.util.*;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // displayed once main is run, without input from user
        String greetings =
            "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________";

        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);

        // continuously scan for input from user until "bye" is invoked
        while (sc.hasNextLine()) {
            String input = sc.next();

            if (!input.equals("bye")) {
                String output =
                    "    ____________________________________________________________\n" +
                    "     " + input + "\n" +
                    "    ____________________________________________________________";

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
        }
    }
}