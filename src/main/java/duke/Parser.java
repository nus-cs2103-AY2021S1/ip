package duke;

import java.util.ArrayList;
import java.util.Arrays;

import duke.command.ArchiveCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CreateDeadlineCommand;
import duke.command.CreateEventCommand;
import duke.command.CreateTodoCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.LoadCommand;
import duke.command.SaveCommand;



public class Parser {
    private static final String[] TASK_TYPES = {"todo", "event", "deadline"};
    private static final String[] MEMORY_COMMAND_TYPES = {"save", "archive", "load"};

    private String currentInput;

    public Parser() {
        currentInput = "";
    }
    /**
     * Take in the String input and split into the 3 parts, namely
     * the command, the title and extra_descriptions.
     * @param input The input from the users.
     * @return a String array that contains different components of the input.
     */
    public Command createCommand(String input) throws DukeException {
        String[] inputComponents = splitIntoComponents(input);
        String command = inputComponents[0];

        if (isRelatedToStorage(command)) {
            return storageCommand(inputComponents);
        }

        if (isCreateTask(command)) {
            return createTaskCommand(inputComponents);
        }

        if (inputComponents.length == 1) {
            return singleWordCommand(command);
        }

        return taskManipulation(inputComponents);
    }

    private String[] splitIntoComponents(String input) throws DukeException {
        this.currentInput = input;
        ArrayList<String> inputComponents = new ArrayList<>();
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            inputComponents.add(input.toLowerCase());
            return inputComponents.toArray(new String[0]);
        }

        int slashIndex = input.indexOf("/");
        //Find the position of the start of the detail
        int infoIndex = input.indexOf(" ", slashIndex);
        inputComponents.add(input.substring(0, spaceIndex).toLowerCase());
        if (slashIndex == -1) {
            inputComponents.add(input.substring(spaceIndex + 1).trim());
        } else {
            inputComponents.add(input.substring(spaceIndex + 1, slashIndex).trim());
            inputComponents.add(input.substring(infoIndex + 1).trim());
        }
        return inputComponents.toArray(new String[0]);
    }

    private boolean isCreateTask(String command) {
        return Arrays.asList(TASK_TYPES).contains(command);
    }
    private boolean isRelatedToStorage(String command) {
        return Arrays.asList(MEMORY_COMMAND_TYPES).contains(command);
    }
    /**
     * Return respective Command when users enter a word.
     * @param command The word entered by user.
     * @return respective command.
     * @throws DukeException if there is error creating the Command.
     */
    public Command singleWordCommand(String command) throws DukeException {
        switch(command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        default:
            throwNoSuchCommandException();
        }
        return null;
    }

    private Command createTaskCommand(String[] input) throws DukeException {
        String command = input[0];
        switch(command) {
        case "todo":
            if (input.length != 2) {
                throw new DukeException("Arrr......\n" + "'todo' should followed by its title.");
            }
            return new CreateTodoCommand(input[1]);
        case "deadline":
            if (input.length != 3) {
                throw new DukeException("Arrr......\n" + "Format for Deadline should be:\n"
                        + "deadline TITLE /by YYYY-MM-DD HH:MM");
            }
            return new CreateDeadlineCommand(input);
        case "event":
            if (input.length != 3) {
                throw new DukeException("Arrr......\n" + "Format for Event should be:\n"
                        + "deadline TITLE /by YYYY-MM-DD HH:MM HH:MM");
            }
            return new CreateEventCommand(input);
        default:
            throwNoSuchCommandException();
        }
        return null;
    }

    private Command storageCommand(String[] input) throws DukeException {
        String command = input[0];
        if (command.equals("save")) {
            return new SaveCommand();
        }
        if (input.length != 2) {
            throw new DukeException("Invalid Input. " + command + "should be followed by the filePath");
        }
        switch(command) {
        case "archive":
            return new ArchiveCommand(input[1]);
        case "load":
            return new LoadCommand(input[1]);
        default:
            throwNoSuchCommandException();
        }
        return null;
    }

    private Command taskManipulation(String[] input) throws DukeException {
        String command = input[0];
        try {
            switch (command) {
            case "delete":
                return new DeleteCommand(Integer.parseInt(input[1]));
            case "done":
                return new DoneCommand(Integer.parseInt(input[1]));
            case "find":
                return new FindCommand(input[1]);
            default:
                throwNoSuchCommandException();
            }
        } catch (NumberFormatException err) {
            throw new DukeException("Please key in an integer after " + input[0]);
        }
        return null;
    }

    private void throwNoSuchCommandException() throws DukeException {
        throw new DukeException("Arrrrr.... I don't know what does it mean by \"" + this.currentInput + "\"");
    }
}
