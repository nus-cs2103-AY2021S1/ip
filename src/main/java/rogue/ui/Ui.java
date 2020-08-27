package rogue.ui;

import rogue.logic.Report;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The interface of {@code Rogue} with which the user interacts.
 * Reads user input from the console, and displays messages,
 * errors, and other textual information onto the console.
 */
public class Ui {
    private final BufferedReader textParser; // Reads user inputs

    /**
     * Constructs the {@code Ui}.
     */
    public Ui() {
        textParser = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Constructs the {@code Ui}.
     *
     * @param in The source from which the Ui should read user input.
     */
    public Ui(InputStream in) {
        textParser = new BufferedReader(new InputStreamReader(in));
    }

    /**
     * Reads a line of input from the specified source.
     * A line is terminated by any of the '\n', \r', '\r\n', or EOF characters.
     * Terminates program if an I/O error occurs.
     */
    public String readCommand() {
        String text = "";

        try {
            text = textParser.readLine();
        } catch (IOException e) {
            System.err.println("ERROR: Unable to parse input stream!\n");
            e.printStackTrace();
            System.exit(1);
        }

        return text;
    }

    /**
     * Displays a greeting on console.
     */
    public void greet() {
        printWithDecorations("yOu HavE nO cOnTrOL ovEr ME!");
    }

    /**
     * Displays stylized text on console.
     *
     * @param text The text to print.
     */
    public void print(String text) {
        printWithDecorations(text);
    }

    /**
     * Displays stylized text on console.
     *
     * @param report The report to print.
     */
    public void print(Report report) {
        printWithDecorations(report.toString());
    }

    /**
     * Prefixes the text with a '\t' character and whitespace.
     *
     * @param textToIndent The text to indent.
     * @return Indented text
     */
    private String indentText(String textToIndent) {
        return String.format("\t %s", textToIndent);
    }

    /**
     * Displays a header and footer for texts.
     */
    private void printDecorations() {
        System.out.printf("\t|%s|\n", " @ . ".repeat(20));
        System.out.print("\n");
    }

    /**
     * Formats the text with a header and footer.
     *
     * @param text The text to format.
     */
    private void printWithDecorations(String text) {
        String[] sentences = text.split("\n");

        printDecorations();
        for (String sentence : sentences) {
            System.out.println(indentText(sentence));
        }
        System.out.print("\n");
        printDecorations();
    }
}
