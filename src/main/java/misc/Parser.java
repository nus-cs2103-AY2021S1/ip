package main.java.misc;

import main.java.exception.InvalidArgumentException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    /**
     * Parses the user's input into a list of tokens.
     * @param command user's input
     * @return the list of tokens
     */
    public static List<String> parseCommand(String command) {
        String[] splitted = command.split(" /");
        List<String> out = new ArrayList<>();
        for (String token : splitted) {
            String[] splitted2 = token.split(" ", 2);
            for (String tokenn : splitted2) out.add(tokenn);
        }
        if (out.get(0).equals("todo") || out.get(0).equals("deadline") || out.get(0).equals("event")) {
            if (out.size() >= 3) out.remove(2);
            if (out.size() <= 2) out.add("null");
            out.add("0");
        }
        return out;
    }

    /**
     * Parses a string of datetime into a LocalDateTime object, or throw an exception if the datetime is invalid.
     * @param datetimeString the string of datetime
     * @return the corresponding LocalDateTime object
     * @throws InvalidArgumentException
     */
    public static LocalDateTime stringToTime(String datetimeString) throws InvalidArgumentException {
        String[] timeTokens = datetimeString.split(" |/");
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
