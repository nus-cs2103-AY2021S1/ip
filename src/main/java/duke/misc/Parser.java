package duke.misc;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import duke.exception.InvalidArgumentException;


public class Parser {
    /**
     * Parses the user's input into a list of tokens.
     *
     * @param command user's input
     * @return the list of tokens
     */
    public static List<String> parseCommand(String command) {
        String[] splitBySlash = command.split(" /");
        List<String> tokens = new ArrayList<>();
        for (String token : splitBySlash) {
            String[] splitByWhiteSpace = token.split(" ", 2);
            Collections.addAll(tokens, splitByWhiteSpace);
        }

        if (!tokens.get(0).equals("todo")
                && !tokens.get(0).equals("deadline")
                && !tokens.get(0).equals("event")) { return tokens; }

        if (tokens.size() >= 3) {
            tokens.remove(2);
        }

        if (tokens.size() <= 2) {
            tokens.add(Const.NO_TIME);
        }

        tokens.add("0");

        return tokens;
    }

    /**
     * Parses a string of datetime into a LocalDateTime object, or throw an exception if the datetime is invalid.
     *
     * @param datetimeString the string of datetime
     * @return the corresponding LocalDateTime object
     * @throws InvalidArgumentException
     */
    public static LocalDateTime stringToTime(String datetimeString) throws InvalidArgumentException {
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
