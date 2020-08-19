import java.util.Scanner;

public class Chatterbox {
    private static final String SEPARATOR = "++++++++++++++++++++++++++++++++++++++++++++++++++++++";

    private static String format(String s) {
        return SEPARATOR + "\n" + s + "\n" + SEPARATOR;
    }

    public static void main(String[] args) {
        System.out.println("Hello I'm Chatterbox. What can I do for you?");

        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while (!input.equals("bye")) {
            System.out.println(format(input));
            input = s.nextLine();
        }

        System.out.println(format("Goodbye! Hope to see you again soon!"));
    }
}

