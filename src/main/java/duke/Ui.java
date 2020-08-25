package duke;

import java.util.Scanner;

public class Ui {

    private static final String DIVIDER = "________________________________________________________\n";

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String prependIndent(String content, int indent) {
        String spaces = "";
        for (int i = 0; i < indent; i++) {
            spaces += " ";
        }
        String[] contentArray = content.split("\n");
        String result = "";
        for (int i = 0; i < contentArray.length; i++) {
            result += spaces + contentArray[i] + "\n";
        }
        return result;
    }

    public void formattedPrint(String content) {
        System.out.print(prependIndent(DIVIDER, 4));
        System.out.print(prependIndent(content, 4));
        System.out.println(prependIndent(DIVIDER, 4));
    }

    public void greet() {
        String welcomeMessage = "Konnichiwa!\n"
                + "What can I do for you?\n";
        System.out.printf(prependIndent(DIVIDER, 4));
        System.out.printf(prependIndent(welcomeMessage, 5));
        System.out.println(prependIndent(DIVIDER, 4));
    }

    public void showLoadingError() {
        String loadingErrorMessage = "I think I lost my memory... Let me start afresh.";
        System.out.printf(prependIndent(DIVIDER, 4));
        System.out.printf(prependIndent(loadingErrorMessage, 5));
        System.out.println(prependIndent(DIVIDER, 4));
    }

    public void exit() {
        String exitMessage = "Ja ne!\n";
        System.out.printf(prependIndent(DIVIDER, 4));
        System.out.printf(prependIndent(exitMessage, 5));
        System.out.println(prependIndent(DIVIDER, 4));
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
