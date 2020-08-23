package main.java;

public class Parser {

    enum Input {
        LIST, BYE, DONE, TODO, DEADLINE, EVENT, DELETE
    }

    public static Command parse(String fullCommand) throws DukeException {
        String[] commands = fullCommand.split(" ", 2);
        if (inputExist(commands[0])) {
            switch (Input.valueOf(commands[0].toUpperCase())) {
            case LIST:
                return ListCommand;
            break;
            case BYE:
                return ByeCommand;
            break;
            case DONE:
                return DoneCommand;
            break;
            case TODO:
                return ToDoCommand;
            break;
            case DEADLINE:
                return DeadlineCommand;
            break;
            case EVENT:
                return EventCommand;
            break;
            case DELETE:
                return DeleteCommand;
            break;
            }
        } else {
            throw new DukeException("~\n ERROR... INPUT NOT RECOGNIZED. \n PLEASE TRY AGAIN \n~");
        }
        return null;
    }

    private static boolean inputExist(String input) {
        for (Command.Input i : Command.Input.values()) {
            if (input.toUpperCase().equals(i.toString())) {
                return true;
            }
        }
        return false;
    }
}
