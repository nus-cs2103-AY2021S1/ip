import java.util.Scanner;

public class Duke {
    private static String line = "    ____________________________________________________________\n";
    private static String welcomeMessage = "Hello, I'm Duke!\n" +
        "     What can I do for you?";
    private static String goodbyeMessage = "Bye. Hope to see you again soon! :)";

    private static String makeWrappedString(String txt) {
        return line + "     " + txt + "\n" + line;
    }

    public static void main(String[] args) {
        System.out.println(makeWrappedString(welcomeMessage));

        Scanner sc = new Scanner(System.in);

        String input = sc.next();
        while (!input.equals("bye")) {
            System.out.println(makeWrappedString(input));
            input = sc.next();
        }

        System.out.println(makeWrappedString(goodbyeMessage));
    }
}
