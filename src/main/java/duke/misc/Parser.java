package duke.misc;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import duke.exception.InvalidArgumentException;


public class Parser {
    private static String getCommandType(String command) {
        return command.split(" ")[0].toLowerCase();
    }

    private static String getTaskTitle(String command) {
        return command.split(" ", 2)[0].split("/")[0];
    }

    private static String getTimeString(String command) {
        String commandType = getCommandType(command);
        if (commandType.equals("todo")) return Const.NO_TIME;

        String afterSlash = command.split("/",2)[1];
        return afterSlash.split(" ", 2)[1];
    }

    private static String[] getArguments(String command) {
        return command.split(" ", 2)[1].split(" ");
    }

    /**
     * Parses the user's input into a list of tokens.
     * Command syntax:
     *      bye
     *      clear
     *      delete <index>
     *      done <index>
     *      find <query string>
     *      list
     *      save
     *      todo <title>
     *      deadline <title> /by DD/MM/YY HHMM
     *      event <title> /at DD/MM/YY HHMM
     * @param command user's input
     * @return the list of tokens
     */
    public static List<String> parseCommand(String command) {
        String[] splitByFirstWhiteSpace = command.split(" ", 2);
        List<String> tokens = new ArrayList<>();
        String commandType = splitByFirstWhiteSpace[0];

        switch (commandType) {
        case "bye":
        case "clear":
        case "list":
        case "save":
            tokens.add(commandType);
            return tokens;
        case "delete":
        case "done":
        case "find":
            tokens.add(commandType);
            Collections.addAll(tokens, getArguments(command));
            return tokens;
        case "todo":
        case "deadline":
        case "event":
            tokens.add(commandType);
            tokens.add(getTaskTitle(command));
            tokens.add(getTimeString(command));
            tokens.add("0");
            return tokens;
        default:
            throw new Error("An unexpected error occurred");
        }
    }

    /**
     * Parses a string of datetime into a LocalDateTime object, or throw an exception if the datetime is invalid.
     *
     * @param datetimeString the string of datetime
     * @return the corresponding LocalDateTime object
     * @throws InvalidArgumentException
     */
    public static LocalDateTime stringToTime(String datetimeString) throws InvalidArgumentException {
        assert datetimeString != null : "cannot convert a null string to time";
        String[] timeTokens = datetimeString.split("[ /]");
        int time = timeTokens.length >= 4 ? Integer.parseInt(timeTokens[3]) : 0;
        try {
            return LocalDateTime.of(Integer.parseInt(timeTokens[2]),
                    Integer.parseInt(timeTokens[1]),
                    Integer.parseInt(timeTokens[0]),
                    time / 100, time % 100);
        } catch (DateTimeException e) {
            throw new InvalidArgumentException("Invalid date");
        }
    }
}
