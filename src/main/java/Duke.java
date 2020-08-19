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
    //----- level 3 here -----
    private static Task[] list = new Task[100];
    private static int count = 0;
    private final static String done = "Nice! I've marked this task as done:";

    public static void main(String[] args) {
        System.out.println("Hello from\n" + Duke.logo);
        System.out.println(Duke.bar);
        System.out.println(Duke.greeting);
        System.out.println(Duke.bar);

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(Duke.bar);
            if (userInput.substring(0, 4).equals("done")) {
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                list[index].markAsDone();
                System.out.println(Duke.done);
                System.out.println("  " + list[index]);
            } else {
                switch (userInput) {
                    case "list":
                        for (int i = 0; i < Duke.count; i++) {
                            System.out.println(i+1 + ". " + list[i]);
                        }
                        break;
                    default:
                        Duke.list[Duke.count++] = new Task(userInput);
                        System.out.println("added: " + userInput);
                }
            }
            System.out.println(Duke.bar);
            userInput = sc.nextLine();
        }
        sc.close();
        System.out.println(Duke.bar);
        System.out.println(Duke.goodbye);
        System.out.println(Duke.bar);
    }
}
