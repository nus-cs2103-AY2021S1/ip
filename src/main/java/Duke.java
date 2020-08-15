import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String message = "____________________________________________________________\n" +
                "  Hello! I'm Duke\n" +
                "  What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(message);

        Scanner scanner = new Scanner(System.in);
        String string1 = scanner.nextLine();
        String message1;

        while (!string1.equals("bye")) {
            message1 = "____________________________________________________________\n" +
                    "  " + string1 + "\n" +
                    "____________________________________________________________\n";
            System.out.println(message1);
            string1 = scanner.nextLine();
        }

        message1 = "____________________________________________________________\n" +
                "  Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(message1);
    }
}
