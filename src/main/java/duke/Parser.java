package duke;

import duke.exception.DukeException;
import duke.exception.EmptyComparatorException;
import duke.exception.NullIndexException;
import duke.exception.NullTaskNameException;

/**
 * Parses and contains the important info required
 * for each call to Duke methods given through CLI.
 */
public class Parser {
    public String comparator;
    private String commandLine;
    private String commandWord;
    private String taskName;
    private String taskDate;
    private Integer taskNumber;

    Parser() {
        comparator = "";
        commandLine = "";
        commandWord = "";
        taskName = "";
        taskDate = "";
        taskNumber = 0;
    }

    /**
     * Sets the command line of <code>Parser</code> to
     * a given string inputted via CLI
     *
     * @param commandLine The line typed into CLI
     * @throws Exception
     */
    public void setCommandLine(String commandLine) throws Exception {
        this.commandLine = commandLine;
        parseForCommand(commandLine);
    }

    /**
     * Parses the command line selectively based on the first word.
     *
     * @param commandLine The line typed into CLI
     * @throws Exception When the parsing is unsuccessful
     */
    private void parseForCommand(String commandLine) throws Exception {
        String[] words = commandLine.split(" ", 2);
        commandWord = words[0];

        switch (commandWord) {
        case "bye":
        case "list":
            break;
        case "done":
        case "delete":
            try {
                parseForNumber(words[1].trim());
            } catch (Exception e) {
                throw new NullIndexException(commandWord);
            }
            break;
        case "todo":
        case "deadline":
        case "event":
            try {
                parseForDetails(words[1].trim());
            } catch (Exception e) {
                throw new NullTaskNameException(commandWord);
            }
            break;
        case "find":
            try {
                parseForComparator(words[1].trim());
            } catch (Exception e) {
                throw new EmptyComparatorException(commandWord);
            }
            break;
        default:
            throw new DukeException(commandWord);
        }
    }

    private void parseForNumber(String remain) throws DukeException {
        try {
            taskNumber = Integer.parseInt(remain);
        } catch (NumberFormatException e) {
            throw new NullIndexException(commandWord);
        }
    }

    private void parseForDetails(String remain) {
        String[] words = remain.split("/", 2);
        taskName = words[0].trim();

        if (words.length < 2) {
            taskDate = "";
        } else {
            String[] unformatted = words[1].split(" ", 2);
            taskDate = unformatted[1];
        }
    }

    private void parseForComparator(String remain) throws DukeException {
        String trimmedRemain = remain.trim();

        if (trimmedRemain.isBlank()) {
            comparator = "";
            throw new EmptyComparatorException(commandWord);
        } else {
            comparator = trimmedRemain;
        }
    }

    /**
     * Class getter routines.
     */
    public String getCommandWord() {
        return commandWord;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }
}
