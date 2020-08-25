package main.java;

/**
 * Parser class to interpret the input given by the user
 */
public class Parser {

    /**
     * Initializes the parser object
     */
    public Parser () {}

    /**
     * Interprets the input given by the user
     * @param line input given by user
     * @return Command object determined by the interpretation
     */
    public Command interpret (String line) {
        if (line.indexOf("todo") == 0) {
            return new Command(line, "todo");
        } else if (line.indexOf("delete") == 0) {
            return new Command(line, "delete");
        } else if (line.indexOf("deadline") == 0) {
            return new Command(line, "deadline");
        } else if (line.indexOf("event") == 0) {
            return new Command(line, "event");
        } else if (line.indexOf("done") == 0 || line.equals("bye") || line.equals("list")) {
            return new Command(line, line);
        } else {
            return new Command(line, null);
        }
    }
}
