import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "    ____________________________________________________________\n" +
                "    Hello! I'm Duke\n" +
                "    What can I do for you?\n" +
                "    ____________________________________________________________";
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            }

            if (!input.equals("")) {
                String output = "    ____________________________________________________________\n" +
                        "     " + input + "\n" +
                        "    ____________________________________________________________";
                System.out.println(output);
            }
        }

        String byeMessage = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________";
        System.out.println(byeMessage);
    }
}
