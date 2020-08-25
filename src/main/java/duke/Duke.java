package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Duke class representing the chat bot.
 * author Kor Ming Soon
 */
public class Duke {

    private Storage storage;
    private Tasklist taskList;
    private UserInterface ui;
    private Parser parser;

    /**
     * Constructor for the Duke chatbot.
     */
    public Duke() {
        storage = new Storage();
        taskList = new Tasklist(storage);
        ui = new UserInterface();
    }

    public static void main(String[] args) {
        startBot();
    }

    /**
     * Method to start running the bot.
     */
    public static void startBot() {
        Duke duke = new Duke();
        duke.initialise();
        duke.acceptCommands();
        duke.exit();
    }

    /**
     * Method to send the preamble and load, if any, existing list.
     */
    public void initialise() {
        try {
            ui.welcomeMessage();
            taskList.loadList();
        } catch (IOException e) {
            System.out.print(e.getStackTrace());
        }
    }

    /**
     * Method to initialise the acceptance of commands
     */
    public void acceptCommands() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            Parser parser = new Parser(input, ui);
            parser.start(taskList);
            input = sc.nextLine();
        }
    }

    /**
     * Method for the termination of the chat bot.
     */
    public void exit() {
        ui.exitMessage();
    }

}
