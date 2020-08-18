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

import java.util.Arrays;
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

        String currInput = sc.nextLine();

        List<String> inputList = new ArrayList<>();
        List<String> checkBox = new ArrayList<>();

        while (!currInput.equals("bye")) {

            String toSplit = String.valueOf(currInput);
            String[] splitString = toSplit.split(" ");


            if (currInput.equals("list")) {

                System.out.println("____________________________________________________________");
                for (int i = 0; i < inputList.size(); i++) {
                    System.out.println((i + 1) + ". " + "[" + checkBox.get(i) + "]" + inputList.get(i));
                }
                System.out.println("____________________________________________________________");

            } else if (splitString[0].equals("done")) {
                int index = Integer.parseInt(splitString[1]);
                checkBox.set(index -1 , "✓");

                System.out.println("____________________________________________________________");
                for (int i = 0; i < inputList.size(); i++) {
                    System.out.println((i + 1) + ". " + "[" + checkBox.get(i) + "]" + inputList.get(i));
                }
                System.out.println("____________________________________________________________");

            } else {
                inputList.add(currInput);
                checkBox.add("✗");
                System.out.println("____________________________________________________________");
                System.out.println(currInput);
                System.out.println("____________________________________________________________\n");
            }


            currInput = sc.nextLine();
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
