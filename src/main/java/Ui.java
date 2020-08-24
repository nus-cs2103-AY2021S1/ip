import java.util.Scanner;

public class Ui {

    protected static final String LINE_BREAK = "    ____________________________________________________________\n";
    protected static final String PRESPACING = "     ";

    protected static final String OPENING_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    protected static final String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String getCommand() {
        return scanner.nextLine();
    }

    public void outputMessage(String message) {
        System.out.println(processString(message));
    }

    public void startup() {
        outputMessage(getOpeningText());
    }

    public void close() {
        outputMessage(getClosingText());
    }

    protected static String processString(String string) {
        return LINE_BREAK  + PRESPACING
                + string.replaceAll("\n", '\n' + PRESPACING)
                + '\n' + LINE_BREAK;
    }


    private String getOpeningText() {
        return OPENING_MESSAGE;
    }

    private String getClosingText() {
        return CLOSING_MESSAGE;
    }



}
