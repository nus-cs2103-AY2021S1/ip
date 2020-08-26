package rogue.ui;

import rogue.logic.Report;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ui {
    private final BufferedReader textParser;

    public Ui() {
        textParser = new BufferedReader(new InputStreamReader(System.in));
    }

    public Ui(InputStream in) {
        textParser = new BufferedReader(new InputStreamReader(in));
    }

    public String readCommand() {
        String text = "";

        try {
            text = textParser.readLine();
        } catch(IOException e) {
            System.err.println("ERROR: Unable to parse input stream!\n");
            e.printStackTrace();
            System.exit(1);
        }

        return text;
    }

    public void greet() {
        printWithDecorations("yOu HavE nO cOnTrOL ovEr ME!");
    }

    public void print(String text) {
        printWithDecorations(text);
    }

    public void print(Report report) {
        printWithDecorations(report.toString());
    }

    private String indentText(String textToIndent) {
        return String.format("\t %s", textToIndent);
    }

    private void printDecorations() {
        System.out.printf("\t|%s|\n", " @ . ".repeat(20));
        System.out.print("\n");
    }

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
