package main.java;

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
        System.out.println(UI.Messages.LOGO.toString() + UI.Messages.WELCOME_MSG.toString());
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