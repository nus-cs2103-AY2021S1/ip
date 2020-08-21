import java.util.StringTokenizer;

public class Parser {
    
    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_DELIMITER = "/at";
    private static final String TIME_DELIMITER = "-";
    
    
    // takes in the input and returns a string arr, does exception checking here as well:
    public String[] parseCommand(String input) throws DukeException {
        input = input.trim();
        String[] words = input.split(" ");
        String command = words[0].toLowerCase().trim();
        
        if (input.equals(Command.EXIT_CMD.getCmd())) {
            return new String[]{command};
        } else if (input.equals(Command.LIST_CMD.getCmd())) {
            return new String[]{command};
        } else if (command.equals(Command.DONE_CMD.getCmd())
                || command.equals(Command.DELETE_CMD.getCmd())) {
            return parseDoneDelete(input);
        } else {
            switch (command) {
            case "todo":
                return parseToDo(input);
            case "deadline":
                return parseDeadline(input);
            case "event":
                return parseEvent(input);
            default:
                throw new DukeException(Message.ERROR_UNKNOWN_CMD.getMsg());
            }
        }
    }
    
    private String[] parseDoneDelete(String input) throws DukeException {
        StringTokenizer st = new StringTokenizer(input);
        if (st.countTokens() != 2) {
            throw new DukeException(Message.ERROR_DONEDELETE_ARGS.getMsg());
        }
        String command = st.nextToken(), taskID = st.nextToken();
        if (!isInteger(taskID)) {
            throw new DukeException(Message.ERROR_DONEDELETE_NOTINT.getMsg());
        }
        return new String[]{command, taskID};
    }
    
    private String[] parseToDo(String input) throws DukeException {
        StringTokenizer st = new StringTokenizer(input);
        st.nextToken();
        if (!st.hasMoreTokens()) {
            throw new DukeException(Message.ERROR_TODO_DESC.getMsg());
        }
        StringBuilder description = new StringBuilder();
        while (st.hasMoreTokens()) {
            description.append(st.nextToken()).append(" ");
        }
        return new String[]{"T", description.toString().stripTrailing()};
    }
    
    
    private String[] parseDeadline(String input) throws DukeException {
        String[] separatedInput = input.split(DEADLINE_DELIMITER);
        if (separatedInput.length <= 1) {
            throw new DukeException(Message.ERROR_DEADLINE_FORMAT.getMsg());
        }
        String dateString = separatedInput[1];
        StringTokenizer st = new StringTokenizer(separatedInput[0]);
        st.nextToken();
        StringBuilder newDescription = new StringBuilder();
        while (st.hasMoreTokens()) {
            newDescription.append(st.nextToken()).append(" ");
        }
        String description = newDescription.toString().stripTrailing();
        if (description.isEmpty()) {
            throw new DukeException(Message.ERROR_DEADLINE_DESC.getMsg());
        }
        return new String[]{"D",
                            description,
                            dateString};
    }
    
    // e.g. event project meeting /at Mon 2-4pm
    // todo: refactor this later
    private String[] parseEvent(String input) throws DukeException {
        String[] separatedInput = input.split(EVENT_DELIMITER); //  <words> /at <timeInfo>
        if (separatedInput.length <= 1) {
            throw new DukeException(Message.ERROR_EVENT_FORMAT.getMsg());
        }
        String[] words = separatedInput[0].split(" ");
        String[] timeInfo = separatedInput[1].split(" ");
        
        String duration = timeInfo[timeInfo.length - 1];
        String[] separatedTime = duration.split(TIME_DELIMITER);
        if (separatedTime.length <= 1) {
            throw new DukeException(Message.ERROR_EVENT_TIME.getMsg());
        }
        
        StringBuilder dateStringBuilder = new StringBuilder();
        for (int i = 0; i < timeInfo.length - 2; i++) {
            dateStringBuilder.append(timeInfo[i]).append(" ");
        }
        
        dateStringBuilder.append(timeInfo[timeInfo.length - 2]);
        String dateString = dateStringBuilder.toString();
        if (dateString.isEmpty()) {
            throw new DukeException(Message.ERROR_EVENT_DATE.getMsg());
        }
        String startTime = separatedTime[0],
                endTime = separatedTime[1];
        StringBuilder newDescription = new StringBuilder();
        for (int i = 1; i < words.length - 1; i++) {
            newDescription.append(words[i]).append(" ");
        }
        newDescription.append(words[words.length - 1]);
        return new String[]{"E",
                            newDescription.toString().stripTrailing(),
                            dateString,
                            startTime,
                            endTime};
    }
    
    private boolean isInteger(String s) {
        return s.matches("\\d+");
    }
    
}
