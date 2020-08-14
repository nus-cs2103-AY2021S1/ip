import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String LINE = Colour.Red("____________________________________________________________");
    private static final String INDENT = "    ";

    public static void printLine() {
        System.out.println(INDENT + LINE);
    }

    public static void respond(List<String> responses) {
        printLine();
        for (String s : responses) {
            System.out.print(INDENT);
            System.out.println(s);
        }
        printLine();
    }

    public static void main(String[] args) {
        List<String> welcomeMessage = new ArrayList<>();
        welcomeMessage.add("Hello! I'm Duke");
        welcomeMessage.add("What can I do for you?");
        respond(welcomeMessage);

        boolean run = true;
        Scanner sc = new Scanner(System.in);
        while (run) {
            String input = sc.nextLine();
            List<String> responses = new ArrayList<>();
            if (input.equals("bye")) {
                responses.add("Bye. Hope to see you again soon!");
                run = false;
            } else {
                responses.add(input);
            }
            respond(responses);
        }
    }
}

