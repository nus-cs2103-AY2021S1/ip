package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.enums.CommandWord;
import duke.enums.Message;
import duke.exception.DukeException;

import java.util.StringTokenizer;

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
        if (input.equals(CommandWord.EXIT_CMD.getCmd())) {
            return new ExitCommand();
        } else if (input.equals(CommandWord.LIST_CMD.getCmd())) {
            return new ListCommand(new String[]{input});
        } else if (keyword.equals(CommandWord.DONE_CMD.getCmd())
                || keyword.equals(CommandWord.DELETE_CMD.getCmd())) {
            return parseDoneDelete(input);
        } else if (words[0].equals("find")) {
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
     * @throws DukeException If the format is wrong or if a non-integer is passed in as argument or  multiple arguments
     *                       are passed in
     */
    private Command parseDoneDelete(String input) throws DukeException {
        StringTokenizer st = new StringTokenizer(input);
        // if there are more than 2 tokens, then it's wrong format:
        if (st.countTokens() != 2) {
            throw new DukeException(Message.ERROR_DONEDELETE_ARGS.getMsg());
        }
        String command = st.nextToken();
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
     * @return String array of format: {<"T"><description of todo>}
     *
     * @throws DukeException If no description is passed in
     */
    private String[] parseToDo(String input) throws DukeException {
        StringTokenizer st = new StringTokenizer(input);
        st.nextToken();
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
     * @return String array of format: {<"D"><description of Deadline><String representation for the date>}
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
        st.nextToken();
        StringBuilder descriptionBuilder = new StringBuilder();
        while (st.hasMoreTokens()) {
            descriptionBuilder.append(st.nextToken()).append(" ");
        }
        String description = descriptionBuilder.toString().stripTrailing();
        if (description.isEmpty()) {
            throw new DukeException(Message.ERROR_DEADLINE_DESC.getMsg());
        }
        return new String[]{"D",
                            description,
                            dateString};
    }
    
    /**
     * Parses user input that is related to the Event command
     *
     * @param input User input related to Event command
     *
     * @return String array of format: {<"E"><description of Event><String representation for the date><start time><end
     *         time>}
     *
     * @throws DukeException If the format is wrong or if it lacks proper description
     */
    private String[] parseEvent(String input) throws DukeException {
        String[] separatedInput = input.split(EVENT_DELIMITER); //  <words> /at <timeInfo>
        // not enough info in user input:
        if (separatedInput.length <= 1) {
            throw new DukeException(Message.ERROR_EVENT_FORMAT.getMsg());
        }
        String[] words = separatedInput[0].split(" ");
        String[] timeInfo = separatedInput[1].split(" ");
        
        String duration = timeInfo[timeInfo.length - 1];
        String[] separatedTime = duration.split(TIME_DELIMITER);
        if (separatedTime.length <= 1) {
            throw new DukeException(Message.ERROR_EVENT_TIME.getMsg());
        }
        
        StringBuilder dateStringBuilder = new StringBuilder();
        for (int i = 0; i < timeInfo.length - 2; i++) {
            dateStringBuilder.append(timeInfo[i]).append(" ");
        }
        
        dateStringBuilder.append(timeInfo[timeInfo.length - 2]);
        String dateString = dateStringBuilder.toString();
        if (dateString.isEmpty()) {
            throw new DukeException(Message.ERROR_EVENT_DATE.getMsg());
        }
        String startTime = separatedTime[0];
        String endTime = separatedTime[1];
        StringBuilder newDescription = new StringBuilder();
        for (int i = 1; i < words.length - 1; i++) {
            newDescription.append(words[i]).append(" ");
        }
        newDescription.append(words[words.length - 1]);
        return new String[]{"E",
                            newDescription.toString().stripTrailing(),
                            dateString,
                            startTime,
                            endTime};
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
