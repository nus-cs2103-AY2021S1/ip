import java.util.Scanner;

public class Duke {
    private final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final static String bar = "____________________________________________________________";
    private final static String greeting = "Hello! I'm Duke\n"
            + "What can I do for you?";
    private final static String goodbye = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        System.out.println("Hello from\n" + Duke.logo);
        System.out.println(Duke.bar);
        System.out.println(Duke.greeting);
        System.out.println(Duke.bar);

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(Duke.bar);
            System.out.println(userInput);
            System.out.println(Duke.bar);
            userInput = sc.nextLine();
        }
        System.out.println(Duke.bar);
        System.out.println(Duke.goodbye);
        System.out.println(Duke.bar);
    }
}
