/**
 * <h1>Parser class</h1>
 * This class helps to parse the data that users input
 * and separate the different key points in each line
 * of input, making it easier for the program to decide
 * which command action to call.
 */
public class Parser {
    private String line;
    private String commandType = "";
    private String taskDetails = "";
    private String date = "";

    public Parser(String line) {
        this.line = line;
    }

    /**
     * Parses the current line of input and checks which command it falls under.
     * Helps separate out key details in the input such as the task details and date.
     * @throws DukeException When user input is wrong or incomplete.
     */
    public void parse() throws DukeException {
        if (line.contains("bye")) {
            commandType = "bye";

        } else if (line.contains("list")) {
            commandType = "list";

        } else if (line.contains("todo")) {
            commandType = "todo";
            if (line.length() < 6) {
                throw new IncompleteInputException();
            } else {
                taskDetails = line.substring(5);
            }

        } else if (line.contains("event")) {
            commandType = "event";
            if (line.length() < 7 || !line.contains("at")) {
                throw new IncompleteInputException();
            } else {
                taskDetails = line.substring(6, line.indexOf('/') - 1);
                date = line.substring(line.lastIndexOf("at") + 4);
            }

        } else if (line.contains("deadline")) {
            commandType = "deadline";
            if (line.length() < 10 || !line.contains("by")) {
                throw new IncompleteInputException();
            } else {
                taskDetails = line.substring(9, line.indexOf('/') - 1);
                date = line.substring(line.lastIndexOf("by") + 4);
            }

        } else if (line.contains("done")) {
            commandType = "done";
            if (line.length() < 6) {
                throw new MissingNumberException();
            } else {
                taskDetails = line.substring(5);
            }

        } else if (line.contains("delete")) {
            commandType = "delete";
            if (line.length() < 8) {
                throw new MissingNumberException();
            } else {
                taskDetails = line.substring(7);
            }

        } else if (line.contains("find")) {
            commandType = "find";
            if (line.length() < 5) {
                throw new IncompleteInputException();
            } else {
                taskDetails = line.substring(5);
            }

        } else {
            throw new WrongCommandException();
        }
    }

    public String getCommandType() {
        return commandType;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public String getDate() {
        return date;
    }
}
