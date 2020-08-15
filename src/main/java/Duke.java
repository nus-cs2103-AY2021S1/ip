import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =
                "     ____        _        \n"
                        + "    |  _ \\ _   _| | _____ \n"
                        + "    | | | | | | | |/ / _ \\\n"
                        + "    | |_| | |_| |   <  __/\n"
                        + "    |____/ \\__,_|_|\\_\\___|";


        System.out.println(logo);

        displayThis("Hello! I'm Duke\n    What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!input.equals("bye")) {

            displayThis(input);

            input = scanner.nextLine();
        }

        displayThis("Bye. Hope to see you again soon!");

    }

    private static void displayThis(String s) {
        System.out.println("\n    ---------------------------------");

        System.out.println("    " + s);

        System.out.println("    ---------------------------------\n");

    }

}