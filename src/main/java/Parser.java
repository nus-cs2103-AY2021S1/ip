import java.time.LocalDateTime;

public class Parser {
    private String commandLine;
    private String commandWord;
    private String taskName;
    private String taskDate;
    private Integer taskNumber;

    Parser() {
        commandLine = "";
        commandWord = "";
        taskName = "";
        taskDate = "";
        taskNumber = 0;
    }

    public void setCommandLine(String commandLine) throws Exception {
        this.commandLine = commandLine;
        parseForCommand(commandLine);
    }

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

    public String getCommandLine() {
        return commandLine;
    }

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
