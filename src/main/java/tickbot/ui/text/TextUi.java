package tickbot.ui.text;

import java.util.Scanner;

import tickbot.ui.Output;
import tickbot.ui.Parser;
import tickbot.ui.Ui;

/**
 * The class to represent the text UI.
 */
public class TextUi implements Ui {
    @Override
    public void mainLoop(String[] args) {
        Output.printMessage("Hello, this is tickbot! How can I help you?");
        Scanner inputScanner = new Scanner(System.in);
        Parser parser = new Parser();
        boolean running = true;
        while (running) {
            System.out.print("==> ");
            if (!inputScanner.hasNextLine()) {
                break; // end of file
            }
            String command = inputScanner.nextLine();
            running = parser.executeCommand(command);
        }
        inputScanner.close();
    }
}
