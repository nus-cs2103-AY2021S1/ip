package chatterbox.task;

import chatterbox.Parser;

/**
 * Task that happens at a certain defined period of time.
 */
public class Event extends Task {
    /**
     * Stores the original user input including the command word, then formats and sets task content.
     *
     * @param input  User input without the command word.
     */
    public Event(String input) {
        inputString = "event " + input;
        try {
            String[] split = input.split("/", 2);
            String originalDateTime = split[1].substring(split[1].indexOf(' ') + 1);
            dateTime = Parser.parseDateTimeFromString(originalDateTime);
            contents = Parser.getDateTimeTaskString(input, dateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            contents = input;
        }
        prefix = "E";
    }
}
