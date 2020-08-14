import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String welcomeMessage = "____________________________________________________________\n"
                + "Hello I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(welcomeMessage);

        while (true) {
            String s = sc.nextLine();
            System.out.println("____________________________________________________________");
            if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(s);
            }
            System.out.println("\n____________________________________________________________");
        }
    }
}