package duke.parsers;

import java.time.LocalDateTime;

import duke.command.Command;
import duke.command.metacommands.ContentMetaCommand;
import duke.command.metacommands.DurationMetaCommand;
import duke.command.metacommands.MetaCommand;
import duke.command.metacommands.TimeMetaCommand;
import duke.exceptions.CommandNotFoundException;
import duke.exceptions.DukeException;
import duke.exceptions.LackOfTimeException;
import duke.exceptions.NullCommandContentException;
import duke.exceptions.TaskNotSpecifyException;
import duke.utils.Pair;



public class CommandParser implements Parser<MetaCommand> {
    private static final String TIME_DELIMITER = "/";
    private String input;

    public CommandParser(String input) {
        this.input = input;
    }

    @Override
    public MetaCommand parse() throws DukeException {
        //separate the first word(command) from the rest
        String[] commandAndRest = input.trim().split("\\s+", 2);
        assert commandAndRest.length > 0 : "the command is empty"; //this should be handled in UI
        String command = commandAndRest[0];

        for (Command commandType : Command.values()) {
            //the command is in the list
            if (commandType.matchPattern(command)) {
                switch (commandType) {
                case EXIT:
                    return handleSimpleMetaCommand(Command.EXIT);
                case LIST:
                    return handleSimpleMetaCommand(Command.LIST);
                case DONE:
                    return handleNumberContentMetaCommand(Command.DONE, commandAndRest);
                case TODO:
                    return handleContentMetaCommand(Command.TODO, commandAndRest);
                case DEADLINE:
                    return handleTimeMetaCommand(Command.DEADLINE, commandAndRest);
                case EVENT:
                    return handleDurationMetaCommand(Command.EVENT, commandAndRest);
                case DELETE:
                    return handleNumberContentMetaCommand(Command.DELETE, commandAndRest);
                case FIND:
                    return handleContentMetaCommand(Command.FIND, commandAndRest);
                case HELP:
                    return handleSimpleMetaCommand(Command.HELP);
                default:
                    break;
                }
            }
        }
        throw new CommandNotFoundException(command);
    }

    private MetaCommand handleSimpleMetaCommand(Command type) {
        MetaCommand mc = new MetaCommand();
        mc.setType(type);
        return mc;
    }

    private MetaCommand handleContentMetaCommand(Command type, String[] input) throws DukeException {
        ContentMetaCommand cmc = new ContentMetaCommand();
        cmc.setType(type);
        try {
            cmc.setContent(input[1]);
        } catch (IndexOutOfBoundsException e) {
            switch (type) {
            case TODO:
                throw new NullCommandContentException("Description cannot be null", "Todo");
            case FIND:
                throw new NullCommandContentException("no query body", "FIND");
            default:
                throw new DukeException("unspecified exception");
            }
        }
        return cmc;
    }

    private MetaCommand handleNumberContentMetaCommand(Command type, String[] input) throws DukeException {
        ContentMetaCommand cmc = new ContentMetaCommand();
        cmc.setType(type);
        try {
            String idxStr = input[1].trim();
            //make sure the content is in the correct format
            int idx = Integer.parseInt(idxStr);
            cmc.setContent(idxStr);
        } catch (IndexOutOfBoundsException e) {
            switch (type) {
            case DELETE:
                throw new TaskNotSpecifyException("task to deletion not specified", "DELETE");
            case DONE:
                throw new TaskNotSpecifyException("task to be done not specified", "DONE");
            default:
                throw new DukeException("unspecified exception");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Content of the command is not valid. Should be in the form of integer");
        }
        return cmc;
    }

    private MetaCommand handleTimeMetaCommand(Command command, String[] input) throws DukeException {
        TimeMetaCommand tmc = new TimeMetaCommand();
        tmc.setType(command);
        String[] splitContent;
        //set content
        try {
            String content = input[1].trim();
            splitContent = content.split(TIME_DELIMITER, 2);
            tmc.setContent(splitContent[0]);
            if (content.isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new NullCommandContentException("Description cannot be null", "Deadline");
        }

        //set time
        try {
            LocalDateTime date = new DateParser(splitContent[1]).parse();
            tmc.setTime(date);
        } catch (IndexOutOfBoundsException e) {
            throw new LackOfTimeException("The time cannot be empty", "Duke.Deadline");
        }
        return tmc;
    }

    private MetaCommand handleDurationMetaCommand(Command command, String[] input) throws DukeException {
        DurationMetaCommand dmc = new DurationMetaCommand();
        dmc.setType(command);
        String[] splitInput;
        //set content
        try {
            String content = input[1].trim();
            splitInput = content.split(TIME_DELIMITER, 2);
            if (content.isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            dmc.setContent(splitInput[0]);
        } catch (IndexOutOfBoundsException e) {
            throw new NullCommandContentException("Description cannot be null", "Event");
        }

        //set start and end time
        try {
            Pair<LocalDateTime, LocalDateTime> duration = new DurationParser(splitInput[1]).parse();
            dmc.setStartTime(duration.getFirst());
            dmc.setEndTime(duration.getSecond());
        } catch (IndexOutOfBoundsException e) {
            throw new LackOfTimeException("The time cannot be empty", "Event");
        }

        return dmc;
    }
}
