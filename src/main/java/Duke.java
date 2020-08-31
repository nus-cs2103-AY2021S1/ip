package main.java;
import main.java.UI.UI;
import main.java.commands.Parser;

public class Duke {
    protected static void doItAgain() {
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