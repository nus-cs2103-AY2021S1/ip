import java.util.Scanner;

public class Duke {
    private static final String horizontalLine = "\t==================================================\n\t  ";

    private static String output(String message) {
        return horizontalLine + message + "\n" + horizontalLine;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(output("Hello! I'm Duke\n\t  What can I do for you?"));
        String[] list = new String[100];
        int count = 0;
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(output(input));
            list[count++] = input;
            input = sc.nextLine();
        }
        System.out.println(output("Bye. Hope to see you again soon!"));
    }
}
