import java.util.Scanner;

public class Duke {

    private static final String lineBreak = "    ____________________________________________________________\n";
    private static final String preSpacing = "     ";
    private static final String openingMessage = "Hello! I'm Duke\n" + preSpacing + "What can I do for you?";
    private static final String closingMessage = "Bye. Hope to see you again soon!";

    private static final String CLOSING_STRING = "bye";

    public static void main(String[] args) {
        System.out.println(getOpeningText());

        Scanner scanner = new Scanner(System.in);

        String userInput = null;

        while (!userInput.equals(CLOSING_STRING)) {
            userInput = scanner.next();
            if (userInput.equals(CLOSING_STRING)) {
                System.out.println(processString(userInput));
            } else {
                System.out.println(getClosingText());
            }
        }


    }

    private static String processString(String string) {
        return lineBreak  + preSpacing + string + '\n' + lineBreak;
    }

    private static String getOpeningText() {
        return processString(openingMessage);
    }

    private static String getClosingText() {
        return processString(closingMessage);
    }



}
