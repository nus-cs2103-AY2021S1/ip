import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String lineBreak = "    ____________________________________________________________\n";
    private static final String preSpacing = "     ";
    private static final String openingMessage = "Hello! I'm Duke\n" + preSpacing + "What can I do for you?";
    private static final String closingMessage = "Bye. Hope to see you again soon!";

    private static final String CLOSING_STRING = "bye";
    private static final String LIST_STRING = "list";

    private static final List<String> stringRecords = new ArrayList<String>();

    public static void main(String[] args) {
        System.out.println(getOpeningText());

        Scanner scanner = new Scanner(System.in);

        String userInput = "";

        while (!userInput.equals(CLOSING_STRING)) {
            userInput = scanner.next();
            switch (userInput) {
                case CLOSING_STRING:
                    System.out.println(getClosingText());
                    break;
                case LIST_STRING:
                    System.out.println(processString(getListString()));
                default:
                    System.out.println(processString(createAddedString(userInput)));


            }
        }


    }

    private static String getListString() {
        StringBuilder builder = new StringBuilder();
        int count = 1;
        for (String string : stringRecords) {
            builder.append(count++ + ". " + string);
        }
        return builder.toString();
    }

    private static String createAddedString(String addedString) {
        return "added: " + addedString;
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
