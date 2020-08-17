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

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

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

        List<String> inputList = new ArrayList<>();


        while (!currInput.equals("bye")) {

            if (currInput.equals("list")) {
                int i = 1;
                System.out.println("____________________________________________________________");
                for (String inputItem: inputList) {
                    System.out.println(i + ". " + inputItem);
                    i++;
                }
                System.out.println("____________________________________________________________");

            } else {
                inputList.add(currInput);
                System.out.println("____________________________________________________________");
                System.out.println(currInput);
                System.out.println("____________________________________________________________\n");
            }


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
