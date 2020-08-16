import java.util.Scanner;


public class Duke {

    public static void readAndEcho() {
        String divider = "___________________________";
        String dividerFormatted = String.format("%" + (5 + divider.length()) + "s", divider);
        String intro1 = "Hello! I'm Duke";
        String intro2 = "What can I do for you?";

        System.out.println(dividerFormatted);
        System.out.println(String.format("%" + (6 + intro1.length()) + "s", intro1));
        System.out.println(String.format("%" + (6 + intro2.length()) + "s", intro2));
        System.out.println(dividerFormatted);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(dividerFormatted);
            System.out.println(String.format("%" + (6 + input.length()) + "s", input));
            System.out.println(dividerFormatted);
            input = sc.nextLine();
        }
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(dividerFormatted);
        System.out.println(String.format("%" + (6 + goodbye.length()) + "s", goodbye));
        System.out.println(dividerFormatted);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        readAndEcho();
    }
}
