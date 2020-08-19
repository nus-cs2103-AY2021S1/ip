import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String divider = "____________________________________________________________\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String introduction = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(divider + logo + introduction + divider);

        // Read input from input.txt
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String nextLine = sc.nextLine();
            if (nextLine.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(nextLine);
            }
        }
    }
}
