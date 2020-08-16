import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner inputs = new Scanner(System.in);
        boolean run = true;

        System.out.println("Hello~, I'm Duke!\n"
        + "What can I do for you?");

        while (run == true) {
            String next = inputs.next();
            System.out.println("____________________________________________________________");
            if (next.equals("bye")) {
                System.out.println("Goodbye~ Hope to see you again soon!");
                run = false;
            } else {
                System.out.println(next);
            }
            System.out.println("____________________________________________________________");
        }
    }


//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
}
