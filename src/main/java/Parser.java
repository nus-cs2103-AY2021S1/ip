public class Parser {
    private String line;
    private String commandType = "";
    private String taskDetails = "";
    private String date = "";

    public Parser(String line) {
        this.line = line;
    }

    public void parse() {
        try {
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
                    date = line.substring(line.lastIndexOf("at") + 3);
                }

            } else if (line.contains("deadline")) {
                commandType = "deadline";
                if (line.length() < 10 || !line.contains("by")) {
                    throw new IncompleteInputException();
                } else {
                    taskDetails = line.substring(9, line.indexOf('/') - 1);
                    date = line.substring(line.lastIndexOf("by") + 3);
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

            } else {
                throw new WrongCommandException();
            }
        } catch (DukeException e) {

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
