import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private static final String HORIZONTAL_RULE = "____________________________________________________________";
    private static Scanner sc;
    private static List<String> itemsList;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        itemsList = new ArrayList<>();
        executeProgramme();
    }

    private static void executeProgramme() {
        final String TERMINATION_PHRASE = "bye";
        final String LIST_PHRASE = "list";

        displayStart();

        String input = sc.nextLine();
        while (!input.equals(TERMINATION_PHRASE)) {

            if (input.equals(LIST_PHRASE)) {
                printList();
            } else {
                itemsList.add(input);

                System.out.println(HORIZONTAL_RULE);
                System.out.println("added: " + input);
                System.out.println(HORIZONTAL_RULE);
            }

            input = sc.nextLine();
        }

        displayExit();
    }

    private static void displayStart() {
        final String HELLO_MESSAGE = "Hello, and welcome to my humble abode. \n" +
                "I'm supposed to help you but I'll be the judge of that. Anyway what do you want.";

        System.out.println(HORIZONTAL_RULE);
        System.out.println(HELLO_MESSAGE);
        System.out.println(HORIZONTAL_RULE);
    }

    private static void displayExit() {
        final String BYE_MESSAGE = "Thank you for that utter waste of time.\n" +
                "Can't wait to see you again...";

        System.out.println(HORIZONTAL_RULE);
        System.out.println(BYE_MESSAGE);
        System.out.println(HORIZONTAL_RULE);
    }

    private static void printList() {
        System.out.println(HORIZONTAL_RULE);
        for (int i = 1; i <= itemsList.size(); i++) {
            System.out.println(i + ". " + itemsList.get(i - 1));
        }
        System.out.println(HORIZONTAL_RULE);
    }
}
