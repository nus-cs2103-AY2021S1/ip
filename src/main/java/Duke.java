import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
*/
        Scanner sc = new Scanner(System.in);
        String lines = "---------------------------\n";

        System.out.println(lines +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                lines);
        String input;
        while (true) {
            input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(lines +
                        "Bye. Hope to see you again soon!\n" +
                        lines);
                break;
            } else {
                System.out.println(lines +
                        input + "\n" +
                        lines);
            }
        }
    }
}
