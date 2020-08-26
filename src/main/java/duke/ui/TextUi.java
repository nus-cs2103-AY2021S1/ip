package duke.ui;

import duke.enums.Message;

import java.util.ArrayList;
import java.util.Scanner;

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
    
    @Override
    public void displayLoadError() { //todo: add this error display
    }
    
    
    private static void printResponse(ArrayList<String> response) {
        for (String s : response) {
            System.out.println(s);
        }
    }
    
    
    // encapsulates and indents response lines:
    private static ArrayList<String> prettify(ArrayList<String> rawResponse) {
        rawResponse.add(0, LINE_BREAK);
        rawResponse.add(rawResponse.size(), LINE_BREAK);
        return indentLines(rawResponse);
    }
    
    private static ArrayList<String> indentLines(ArrayList<String> responseLines) {
        ArrayList<String> result = new ArrayList<>();
        for (String current : responseLines) {
            current = TextUi.INDENT + current;
            result.add(current);
        }
        return result;
    }
    
}
