import java.util.Scanner;

public class Duke {

    public static String messageFormatter(String msg) {
        return ("    ____________________________________________________________\n"
                + "    " + msg + "\n"
                + "    ____________________________________________________________\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf(messageFormatter(
                "Hello! I'm Duke\n" +
                "    What can I do for you?"));

        while (scanner.hasNext()) {
            String msg = scanner.next();
            if (msg.equals("bye")) {
                System.out.printf(messageFormatter("Bye. Hope to see you again soon!"));
                break;
            }
            System.out.printf(messageFormatter(msg));
        }
    }
}
