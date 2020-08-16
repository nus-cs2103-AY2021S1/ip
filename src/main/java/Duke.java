import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        response();
    }

    public static void response() {
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        if (next.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else {
            System.out.println(next);
            response();
        }
    }
}
