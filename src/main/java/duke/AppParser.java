package duke;

import duke.Command.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AppParser extends Parser {

    public AppUi appUi;
    public Storage storage;

    /**
     * Constructs a AppParser object
     * @throws FileNotFoundException for handling file
     */
    public AppParser() throws FileNotFoundException {
        appUi = new AppUi();
        storage = new Storage("data/main.java.duke.txt");
    }

    /**
     * Parses for the app
     * @param inputCommand user input
     * @param list user's TaskList
     * @return response for user input
     * @throws IOException for handling file
     */
    public String appParse(String inputCommand, TaskList list) throws IOException {
        String[] command = inputCommand.split(" ");
        int ptr = 0;

        // if the user input is empty, continue the loop
        if (command.length <= 0 || inputCommand.equals("")) {
            return appUi.getInputEmptyErrorMsg();
        }

        while (command[ptr].equals("")) {
            ptr++;
        }

        switch (command[ptr]) {
            case "bye":
                return new ByeCommand(inputCommand, storage, list).execute();
            case "list":
                return new ListCommand(inputCommand, list).execute();
            case "done":
                return new DoneCommand(inputCommand, command, ptr, list).execute();
            case "delete":
                return new DeleteCommand(inputCommand, command, ptr, list).execute();
            case "find":
                return new FindCommand(inputCommand, command, ptr, list).execute();
            case "todo":
                return new TodoCommand(inputCommand, list).execute();
            case "deadline":
                return new DeadlineCommand(inputCommand, list).execute();
            case "event":
                return new EventCommand(inputCommand, list).execute();
            default:
                return appUi.getNoSuchCommandMsg();
        }
    }
}
