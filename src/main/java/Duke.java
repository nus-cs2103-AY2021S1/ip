//public class Duke {
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
//}

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String welcomeMessage = (
            "____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "____________________________________________________________\n"
        );

        System.out.println(welcomeMessage);

        Scanner sc = new Scanner(System.in);

        String currInput = sc.next();

        while (!currInput.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(currInput);
            System.out.println("____________________________________________________________\n");

            currInput = sc.next();
        }

        String endMessage = (
            "____________________________________________________________\n" +
            "     Bye. Hope to see you again soon!\n" +
            "____________________________________________________________"
        );

        System.out.println(endMessage);

        sc.close();

    }
}
