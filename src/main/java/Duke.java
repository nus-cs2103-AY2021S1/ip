import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------\n" +
                                "Hello! I'm Duke\n" + "What can I do for you?" +
                                        "\n-------------------------------------------\n");

        Scanner sc = new Scanner(System.in);
        String input = "";
        int i = 1;
        String[] list = new String[100];

        while (input != null) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("-------------------------------------------\n" +
                                        "Bye. Hope to see you again soon!" +
                                                "\n-------------------------------------------\n");
                System.exit(0);

            } else if (input.equals("list")) {
                System.out.println("-------------------------------------------\n");
                for(int j = 1; j < i; j++) {
                    System.out.println(j + ". " + list[j-1]);
                }
                System.out.println("-------------------------------------------\n");
            }

            else {
                list[i-1] = input;
                System.out.println("-------------------------------------------\n" +
                                        "added:" + input +
                                                "\n-------------------------------------------\n");
                i++;
            }

        }
    }
}


//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

