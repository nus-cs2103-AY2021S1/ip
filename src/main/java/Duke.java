import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String LINE_BREAK = "    ____________________________________________________________\n";
    private static final String PRESPACING = "     ";
    private static final String OPENING_MESSAGE = "Hello! I'm Duke\n" + PRESPACING + "What can I do for you?";
    private static final String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";

    private static final String CLOSING_STRING = "bye";
    private static final String LIST_STRING = "list";

    private static final List<String> stringRecords = new ArrayList<String>();

    public static void main(String[] args) {
        System.out.println(getOpeningText());

        Scanner scanner = new Scanner(System.in);

        String userInput = "";

        while (!userInput.equals(CLOSING_STRING)) {
            userInput = scanner.nextLine();
            switch (userInput) {
                case CLOSING_STRING:
                    System.out.println(getClosingText());
                    break;
                case LIST_STRING:
                    System.out.println(processString(getListString()));
                    break;
                default:
                    stringRecords.add(userInput);
                    System.out.println(processString(createAddedString(userInput)));
                    break;


            }
        }


    }

    private static String getListString() {
        StringBuilder builder = new StringBuilder();
        int count = 1;
        boolean first = true;
        for (String string : stringRecords) {
            if (!first) {
                builder.append('\n' + PRESPACING);
            } else first = false;
            builder.append(count++ + ". " + string);
        }
        return builder.toString();
    }

    private static String createAddedString(String addedString) {
        return "added: " + addedString;
    }


    private static String processString(String string) {
        return LINE_BREAK  + PRESPACING + string + '\n' + LINE_BREAK;
    }

    private static String getOpeningText() {
        return processString(OPENING_MESSAGE);
    }

    private static String getClosingText() {
        return processString(CLOSING_MESSAGE);
    }



}
