import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String greeting = "Hello I'm Duke, your favourite chatbot! \n"
                + "   What can I do for you? ";
        System.out.println(formatPrint(greeting));

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.strip().toLowerCase().equals("bye")) {
                System.out.println(formatPrint("Bye! Hope to see you again soon! "));
                break;
            } else {
                System.out.println(formatPrint(input));
            }
        }

        sc.close();
    }

    private static String formatPrint(String str) {
        String divider = "  ---------------------------- \n   ";
        return divider + str + "\n" + divider;
    }
}
