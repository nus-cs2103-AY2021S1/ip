package duke;

import duke.exception.*;

import java.time.LocalDate;

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
    private String changeWord;
    private String changeTarget;


    Parser() {
        comparator = "";
        commandLine = "";
        commandWord = "";
        taskName = "";
        taskDate = "";
        taskNumber = 0;
        changeWord = "";
        changeTarget = "";
    }

    /**
     * Sets the command line of <code>Parser</code> to
     * a given string inputted via CLI.
     *
     * @param commandLine The line typed into CLI.
     * @throws Exception at anytime the {@code Parser} experiences exceptions.
     */
    public void setCommandLine(String commandLine) throws Exception {
        this.commandLine = commandLine;
        parseForCommand(commandLine);
    }

    /**
     * Parses the command line selectively based on the first word.
     *
     * @param commandLine The line typed into CLI.
     * @throws Exception When the parsing is unsuccessful.
     */
    private void parseForCommand(String commandLine) throws Exception {
        String[] words = commandLine.split(" ", 2);
        assert words.length == 2 : "String was not split() successfully to two parts.";
        commandWord = words[0];

        switch (commandWord) {
        case "bye":
        case "list":
        case "help":
            break;
        case "done":
        case "delete":
            try {
                parseForNumber(words[1].trim());
            } catch (Exception e) {
                throw new NullIndexException(commandWord);
            }
            break;
        case "update":
            try {
                parseForUpdates(words[1].trim());
            } catch (DukeException e) {
                throw e;
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
        assert remain.contains("/") : "Remaining string must contain '/'.";
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

    private void parseForUpdates(String trim) throws DukeException {
        try {
            String[] updates = trim.split(" ", 3);
            parseForNumber(updates[0]);
            changeWord = updates[1];

            switch (changeWord) {
            case "undo":
                break;
            case "name":
                changeTarget = updates[2];
                break;
            case "date":
                changeTarget = updates[2];
                LocalDate.parse(changeTarget);
                break;
            default:
                assert false : changeWord;
            }
        } catch (Exception e) {
            throw new InvalidUpdateException(commandWord);
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

    public String getChangeWord() {
        return changeWord;
    }

    public String getChangeTarget() {
        return changeTarget;
    }
}
