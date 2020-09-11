package duke;

import java.util.StringTokenizer;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.enums.CommandWord;
import duke.enums.Message;
import duke.exception.DukeException;


/**
 * Parses the user input to determine what command the user intends to make
 */
public class Parser {
    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_DELIMITER = "/at";
    private static final String TIME_DELIMITER = "-";
    /**
     * Returns the correct command after parsing the user's input
     *
     * @param input user's input
     *
     * @return a Command that represents user's intended action
     *
     * @throws DukeException If User's input can't be understood or doesn't follow the correct format
     */
    public Command parseCommand(String input) throws DukeException {
        input = input.trim();
        String[] words = input.split(" ");
        String keyword = words[0].toLowerCase().trim(); // keyword tells us what command to create
        if (keyword.equals(CommandWord.EXIT_CMD.getCmd())) {
            return new ExitCommand();
        } else if (keyword.equals(CommandWord.HELP_CMD.getCmd())) {
            return new HelpCommand(input);
        } else if (keyword.equals(CommandWord.LIST_CMD.getCmd())) {
            return new ListCommand(new String[]{input});
        } else if (keyword.equals(CommandWord.DONE_CMD.getCmd()) || keyword.equals(CommandWord.DELETE_CMD.getCmd())) {
            return parseDoneDelete(input);
        } else if (keyword.equals(CommandWord.FIND_CMD.getCmd())) {
            return new FindCommand(input);
        } else { // parse the command based on the identified keyword:
            switch (keyword) {
            case "todo":
                return new AddCommand(parseToDo(input));
            case "deadline":
                return new AddCommand(parseDeadline(input));
            case "event":
                return new AddCommand(parseEvent(input));
            default:
                throw new DukeException(Message.ERROR_UNKNOWN_CMD.getMsg());
            }
        }
    }
    /**
     * Parses user input that is related to the done and delete commands
     *
     * @param input User input related to Done and Delete commands
     *
     * @return Either Done or Delete Command
     *
     * @throws DukeException If the format is wrong, a non-integer is passed in as argument,
     *                       multiple arguments are passed in
     */
    private Command parseDoneDelete(String input) throws DukeException {
        StringTokenizer st = new StringTokenizer(input);
        // if there are more than 2 tokens, then it's wrong format:
        if (st.countTokens() != 2) {
            throw new DukeException(Message.ERROR_DONEDELETE_ARGS.getMsg());
        }
        String command = st.nextToken();
        assert command.equals(CommandWord.DONE_CMD.getCmd()) || command.equals(CommandWord.DELETE_CMD.getCmd())
                : "parseDoneDelete(): neither done nor delete cmd";
        String taskID = st.nextToken();
        if (!isInteger(taskID)) {
            throw new DukeException(Message.ERROR_DONEDELETE_NOTINT.getMsg());
        }
        String[] parsedInput = new String[]{command, taskID};
        return command.equals(CommandWord.DELETE_CMD.getCmd())
               ? new DeleteCommand(parsedInput)
               : new DoneCommand(parsedInput);
    }
    /**
     * Parses user input that is related to the todo
     *
     * @param input User input related to todo command
     *
     * @return String[] array of format: description of todo
     *
     * @throws DukeException If no description is passed in
     */
    private String[] parseToDo(String input) throws DukeException {
        StringTokenizer st = new StringTokenizer(input);
        String command = st.nextToken();
        assert command.equals(CommandWord.TODO_CMD.getCmd()) : "parseToDo(): incorrect cmd";
        if (!st.hasMoreTokens()) {
            throw new DukeException(Message.ERROR_TODO_DESC.getMsg());
        }
        StringBuilder description = new StringBuilder();
        while (st.hasMoreTokens()) {
            description.append(st.nextToken()).append(" ");
        }
        return new String[]{"T", description.toString().stripTrailing()};
    }
    /**
     * Parses user input that is related to the Deadline command
     *
     * @param input User input related to Deadline command
     *
     * @return String array of format: description of Deadline String representation for the date
     *
     * @throws DukeException If the format is wrong or if it lacks proper description
     */
    private String[] parseDeadline(String input) throws DukeException {
        String[] separatedInput = input.split(DEADLINE_DELIMITER);
        if (separatedInput.length <= 1) {
            throw new DukeException(Message.ERROR_DEADLINE_FORMAT.getMsg());
        }
        String dateString = separatedInput[1];
        StringTokenizer st = new StringTokenizer(separatedInput[0]);
        String command = st.nextToken();
        assert command.equals(CommandWord.DEADLINE_CMD.getCmd()) : "parseToDo(): incorrect cmd";
        StringBuilder descriptionBuilder = new StringBuilder();
        while (st.hasMoreTokens()) {
            descriptionBuilder.append(st.nextToken()).append(" ");
        }
        String description = descriptionBuilder.toString().stripTrailing();
        if (description.isEmpty()) {
            throw new DukeException(Message.ERROR_DEADLINE_DESC.getMsg());
        }
        return new String[]{"D", description, dateString};
    }
    /**
     * Parses user input that is related to the Event command
     *
     * @param input User input related to Event command
     *
     * @return String array of format: "E" description of Event String representation for the date start time end
     *         time>}
     *
     * @throws DukeException If the format is wrong or if it lacks proper description
     */
    // todo: refactor this if possible. so long :(
    private String[] parseEvent(String input) throws DukeException {
        String[] separatedInput = input.split(EVENT_DELIMITER); //  <words> /at <timeInfo>
        // not enough info in user input:
        if (separatedInput.length <= 1) {
            throw new DukeException(Message.ERROR_EVENT_FORMAT.getMsg());
        }
        // parse description field for the event:
        String words = separatedInput[0];
        StringTokenizer wordsTokenizer = new StringTokenizer(words);
        String command = wordsTokenizer.nextToken();
        assert command.equals(CommandWord.EVENT_CMD.getCmd()) : "[parseEvent()]: incorrect command";
        StringBuilder descriptionBuilder = new StringBuilder();
        while (wordsTokenizer.hasMoreTokens()) {
            descriptionBuilder.append(wordsTokenizer.nextToken()).append(" ");
        }
        String description = descriptionBuilder.toString().stripTrailing();
        // parse start and end timing for the event:
        String[] timeInfo = separatedInput[1].split(" ");
        String startAndEndTiming = timeInfo[timeInfo.length - 1];
        String[] separatedStartAndEndTiming = startAndEndTiming.split(TIME_DELIMITER);
        if (separatedStartAndEndTiming.length <= 1) {
            throw new DukeException(Message.ERROR_EVENT_TIME.getMsg());
        }
        String startTime = separatedStartAndEndTiming[0];
        String endTime = separatedStartAndEndTiming[1];
        // parse string representation for the date:
        StringBuilder dateStringBuilder = new StringBuilder();
        for (int i = 0; i <= timeInfo.length - 2; i++) {
            dateStringBuilder.append(timeInfo[i]).append(" ");
        }
        String dateString = dateStringBuilder.toString().stripTrailing();
        if (dateString.isEmpty()) {
            throw new DukeException(Message.ERROR_EVENT_DATE.getMsg());
        }
        return new String[]{"E", description, dateString, startTime, endTime};
    }
    /**
     * Checks if input is a string representation of an integer
     *
     * @param s input string
     *
     * @return True if input is representing an Integer
     */
    private boolean isInteger(String s) {
        return s.matches("\\d+");
    }
}
