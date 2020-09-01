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
    public String acceptCommands(String input) {
        Scanner sc = new Scanner(System.in);
        //String input = sc.nextLine();
        //while (!input.equals("bye")) {
        Parser parser = new Parser(input, ui, taskList);
        return parser.retrieveResponse();
    }

    /**
     * Method to retrieve the response of Duke.
     * @param input user command.
     * @return the String response.
     */
    public String getResponse(String input) { // input - whatever the user type
        // return the chunk of string
        this.initialise();
        return acceptCommands(input);
    }
}
