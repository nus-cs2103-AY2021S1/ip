import java.util.Scanner;

public class Duke {
    private static final String horizontalLine = "==================================================\n";

    private static String output(String message) {
        return horizontalLine + message + "\n" + horizontalLine;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(output("Hello! I'm Duke\nWhat can I do for you?"));

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(output(input));
            input = sc.nextLine();
        }
        System.out.println(output("Bye. Hope to see you again soon!"));
    }
}
