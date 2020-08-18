import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String str = "";
        System.out.println("    _________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    _________________________________");

        while (!str.equals("bye")) {
            str = sc.next();
            if (!str.equals("bye")) {
                System.out.println("    _________________________________");
                System.out.println("    " + str);
                System.out.println("    _________________________________");
            }
        }

        System.out.println("    _________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _________________________________");

    }
}
