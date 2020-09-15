package godfather.ui;

import java.util.ArrayList;
import java.util.Scanner;

import godfather.enums.Message;

public class TextUi implements Ui {
    // constant strings:
    private static final String LINE_BREAK = "____________________________________________________________";
    private static final String INDENT = "    ";
    @Override
    public String readCommand(Scanner sc) {
        return sc.nextLine();
    }
    @Override
    public void greet() {
        ArrayList<String> greeting = new ArrayList<>();
        greeting.add("Hello I'm Duke");
        greeting.add("What can I do for you?");
        display(greeting);
    }
    @Override
    public void bidFarewell() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(Message.EXIT_GREETING.getMsg());
        this.display(lines);
    }
    @Override
    public void display(ArrayList<String> lines) {
        printResponse(prettify(lines));
    }
    @Override
    public void displayError(String message) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(message);
        this.display(lines);
    }
    /**
     * Prints out collection of lines to the Command Line
     *
     * @param response Collection of confirmed lines
     */
    private static void printResponse(ArrayList<String> response) {
        for (String s : response) {
            System.out.println(s);
        }
    }
    /**
     * Makes UI message lines pretty by encapsulating in line breaks and indenting them
     *
     * @param rawResponse Ugly response
     *
     * @return Pretty lines
     */
    private static ArrayList<String> prettify(ArrayList<String> rawResponse) {
        // rawResponse.add(0, LINE_BREAK);
        // rawResponse.add(rawResponse.size(), LINE_BREAK);
        return indentLines(rawResponse);
    }
    /**
     * Indents a collection of lines
     *
     * @param responseLines Collection of the lines to indent
     *
     * @return Collection of the indented lines
     */
    private static ArrayList<String> indentLines(ArrayList<String> responseLines) {
        ArrayList<String> result = new ArrayList<>();
        for (String current : responseLines) {
            current = TextUi.INDENT + current;
            result.add(current);
        }
        return result;
    }
}
