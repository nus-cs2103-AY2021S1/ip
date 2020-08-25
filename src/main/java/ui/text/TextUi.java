package ui.text;

import java.util.Scanner;

public class TextUi {
    public void mainLoop() {
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