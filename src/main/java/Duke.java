import java.util.Scanner;

public class Duke {

    private static final String HORIZONTAL_RULE = "____________________________________________________________";
    private static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        displayStart();
        executeProgramme();
    }

    private static void executeProgramme() {
        final String TERMINATION_PHRASE = "bye";
        String input = sc.nextLine();
        while (!input.equals(TERMINATION_PHRASE)) {
            System.out.println(HORIZONTAL_RULE);
            System.out.println(input);
            System.out.println(HORIZONTAL_RULE);

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
}
