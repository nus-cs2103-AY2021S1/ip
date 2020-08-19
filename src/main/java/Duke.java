import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        String hello = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" + logo +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";

        System.out.println(hello);

        Scanner sc = new Scanner(System.in);


        while (sc.hasNextLine()) {
            String command = sc.nextLine();

            //Case: List
            if (command.equals("list")) {
                System.out.println("    ____________________________________________________________\n" +
                        "     list\n" +
                        "    ____________________________________________________________\n");
            }
            //Case: Blah
            else if (command.equals("blah")) {
                System.out.println("    ____________________________________________________________\n" +
                        "     blah\n" +
                        "    ____________________________________________________________\n");

            }
            //Case: Bye
            else if (command.equals("bye")) {
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________\n");
                break;
            }
        }
    }
}

