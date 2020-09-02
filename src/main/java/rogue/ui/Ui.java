package rogue.ui;

import rogue.Rogue;
import rogue.logic.Report;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The interface of {@code Rogue} with which the user interacts.
 * Reads user input from the console, and displays messages,
 * errors, and other textual information onto the console.
 */
public class Ui {
    public static void init(Stage stage, Rogue rogue) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(rogue.Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRogue(rogue);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
