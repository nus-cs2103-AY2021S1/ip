import java.util.Scanner;

public class Duke {
    public static String greeting = "Hello, I'm Duke, your personal assistant. \n What can I do for you?";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);
        while(sc.hasNextLine()) {
            String order = sc.nextLine();
            String reply = process(order);
            System.out.println(reply);
        }
    }

    private static String process(String order) {
        if (order.equals("bye")) {
            return "    Bye-bye, see you next time!";
        } else {
            return "    "+order;
        }
    }
}
