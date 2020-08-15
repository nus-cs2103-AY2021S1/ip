import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    // Attributes
    private static final String NAME = "DUKE";
    private static final String INTRODUCTION = String.format(
            "Hello! I'm %s\n" +
                    "I was created for the module CS2103T\n" +
                    "What can I do for you?", NAME);
    private static final String EXIT = "Bye. Hope to see you again soon!";
    private static final String LINE = "____________________________________________________________";

    private static List<String> lst = new ArrayList<>();

    // Scanner
    private final static Scanner sc = new Scanner(System.in);

    // Greeting
    private static void greeting() {
        System.out.println(wrapText(INTRODUCTION));
    }

    // Wrap text in lines
    private static String wrapText(String text) {
        return LINE + "\n" + text + "\n" + LINE + "\n";
    }

    // Prints list
    public static void printList() {
        StringBuilder sb = new StringBuilder();
        int sizeOfList = lst.size();

        for (int i = 0; i < sizeOfList; i++) {
            int number = i + 1;
            String text = lst.get(i);
            sb.append(String.format("\n%s. %s", number, text));
        }

        System.out.println(wrapText(sb.toString()));
    }


    // Exit
    private static void exit() {
        System.out.println(wrapText(EXIT));
    }

    public static void main(String[] args) {
        greeting();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exit();
                break;
            } else if (input.equals("list")) {
                printList();
            } else {
                lst.add(input);
                System.out.println(wrapText("added: " + input));
            }
        }
    }
}
