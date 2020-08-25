package duke;

import java.io.IOException;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class Duke {

    private Storage storage;
    private Tasklist taskList;
    private UserInterface ui;
    private Parser parser;

    // constructor for the chat bot
    public Duke() {
        storage = new Storage();
        taskList = new Tasklist(storage);
        ui = new UserInterface();
    }

    public static void main(String[] args) {
        startBot();
    }

    public static void startBot() {
        Duke duke = new Duke();
        duke.initialise();
        duke.acceptCommands();
        duke.exit();
    }

    public void initialise() {
        try {
            ui.welcomeMessage();
            taskList.loadList();
        } catch (IOException e) {
            System.out.print(e.getStackTrace());
        }
    }

    public void acceptCommands() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            Parser parser = new Parser(input, ui);
            parser.start(taskList);
            input = sc.nextLine();
        }
    }

    public void exit() {
        ui.exitMessage();
    }

}
