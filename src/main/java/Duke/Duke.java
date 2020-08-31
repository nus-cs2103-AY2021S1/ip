package Duke;

import Duke.TaskList.TaskList;
import Duke.UI.UI;
import Duke.commands.Parser;

public class Duke {

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return Parser.parseAndAddToList(input);
        } catch (DukeExceptions e) {
            return e.getMessage();
        }
    }

    public static String getGreeting() {
        return UI.getMessage("WELCOME_MSG") + "\n";
    }

    private static void doItAgain() {
        try {
            UI.start();
            Storage.writeToFile();
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
            UI.printWrongInput();
            UI.printLine();
            doItAgain();
        }
    }

    /**
     * Main method for Duke.TaskList.Duke.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(UI.getMessage("LOGO") + UI.getMessage("WELCOME_MSG"));
        UI.printLine();
        Storage.createNewFile();
        try {
            Parser.readSave(Storage.getTmpFile());
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }
        doItAgain();
    }
}