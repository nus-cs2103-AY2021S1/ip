import java.util.Scanner;

public class Ui {

    protected static final String LINE_BREAK = "    ____________________________________________________________\n";
    protected static final String PRESPACING = "     ";

    protected static final String OPENING_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";

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

    protected static String processString(String string) {
        return LINE_BREAK  + PRESPACING
                + string.replaceAll("\n", '\n' + PRESPACING)
                + '\n' + LINE_BREAK;
    }


    private String getOpeningText() {
        return OPENING_MESSAGE;
    }



}
