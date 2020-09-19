package duke;
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
        } else if (line.indexOf("done") == 0) {
            return new Command(line, "done");
        } else if (line.equals("bye")) {
            return new Command(line, "bye");
        } else if (line.equals("list")) {
            return new Command(line, "list");
        } else if (line.indexOf("find") == 0) {
            return new Command(line, "find");
        } else if (line.indexOf("help") == 0) {
            return new Command(line, "help");
        } else {
            return new Command(line, "invalid");
        }
    }
}
