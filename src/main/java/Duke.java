import java.util.Scanner;

public class Duke {

    public static String HORIZONTAL_LINE =
            "_________________________________________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println(Duke.HORIZONTAL_LINE);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.next();
            System.out.println(Duke.HORIZONTAL_LINE);
            if (input.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(input);
            }
            System.out.println(Duke.HORIZONTAL_LINE);
        }
    }
}