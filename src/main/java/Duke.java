import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------\n" +
                                "Hello! I'm Duke\n" + "What can I do for you?" +
                                        "\n-------------------------------------------\n");

        Scanner sc = new Scanner(System.in);
        String input;

        do {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("-------------------------------------------\n" +
                                        "Bye. Hope to see you again soon!" +
                                                "\n-------------------------------------------\n");
                System.exit(0);

            } else {
                System.out.println("-------------------------------------------\n" +
                                        input +
                                                "\n-------------------------------------------\n");
            }

        } while (input != null);
        System.out.println("-------------------------------------------\n" +
                                        input +
                                                "\n-------------------------------------------\n");


    }
}


//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

