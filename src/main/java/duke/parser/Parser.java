package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private enum Commands {
        BYE,
        LIST,
        DONE,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
    }
    
    public static Command parse(String input) throws DukeException {
        try {
            String[] separated = input.split("\\s+");
            Commands commandType = Commands.valueOf(separated[0].toUpperCase());
            Command command = null;
            boolean isError = false;
            String errorMessage = "";
            switch (commandType) {
            case BYE:
                command = new ByeCommand();
                break;
            case LIST:
                command = new ListCommand();
                break;
            case DONE:
            case DELETE:
                if (separated.length <= 1) {
                    isError = true;
                    errorMessage = " Task index must be specified :(";
                    break;
                }
                try {
                    int index = Integer.parseInt(separated[1]);
                    switch (commandType) {
                        case DONE:
                            command = new DoneCommand(index);
                            break;
                        default:
                            command = new DeleteCommand(index);
                            break;
                    }
                } catch (NumberFormatException error) {
                    isError = true;
                    errorMessage = " Task index must be an integer :(";
                }
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                if (separated.length <= 1) {
                    isError = true;
                    errorMessage = " Task description cannot be empty :(";
                    break;
                }
                String description;
                String[] content;
                LocalDate time;
                switch (commandType) {
                    case TODO:
                        description = input.substring(5);
                        command = new ToDoCommand(description);
                        break;
                    case DEADLINE:
                        content = input.substring(9).split(" /by ");
                        if (content.length <= 1) {
                            isError = true;
                            errorMessage = " Deadline date cannot be empty :(";
                            break;
                        }
                        description = content[0];
                        time = LocalDate.parse(content[1]);
                        command = new DeadlineCommand(description, time);
                        break;
                    default:
                        content = input.substring(6).split(" /at ");
                        if (content.length <= 1) {
                            isError = true;
                            errorMessage = " Deadline date cannot be empty :(";
                            break;
                        }
                        description = content[0];
                        time = LocalDate.parse(content[1]);
                        command = new EventCommand(description, time);
                        break;
                }
                break;
            }
            if (isError) {
                throw new DukeException(errorMessage);
            }
            return command;
        } catch(IllegalArgumentException error) {
            throw new DukeException(" Command not recognized :(");
        } catch(DateTimeParseException error) {
            throw new DukeException(" I cannot recognize the date you put in :(");
        }
    }
}
