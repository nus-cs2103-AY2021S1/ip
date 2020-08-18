import java.util.StringTokenizer;

public class Parser {

    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_DELIMITER = "/at";
    private static final String TIME_DELIMITER = "-";


    public Parser() {

    }

    // takes in the input and returns a string arr, does exception checking here as well:
    public String[] parseCommand(String input) throws DukeException {
        input = input.trim();
        String[] words = input.split(" ");
        String command = words[0].toLowerCase();

        if (input.equals("bye")) {
            return new String[]{"bye"};
        } else if (input.equals("list")) {
            return new String[]{"list"};
        } else if (command.equals("done")) {
            return parseDone(input);
        } else {
            switch (command) {
                case "todo":
                    return parseToDo(input);
                case "deadline":
                    return parseDeadline(input);
                case "event":
                    return parseEvent(input);
                default:
                    throw new DukeException("upset parser: unknown command");
            }
        }
    }

    private String[] parseDone(String input) throws DukeException {
        StringTokenizer st = new StringTokenizer(input);
        if (st.countTokens() != 2) {
            throw new DukeException("invalid done command: do things one at a time pls!");
        }
        String command = st.nextToken(), taskID = st.nextToken();
        if (!isInteger(taskID)) {
            throw new DukeException("invalid done command: you gotta pass an integer");
        }
        return new String[]{command, taskID};
    }

    private String[] parseToDo(String input) throws DukeException {
        StringTokenizer st = new StringTokenizer(input);
        st.nextToken();
        if (!st.hasMoreTokens()) {
            throw new DukeException("invalid todo command: do what?");
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
            throw new DukeException("invalid deadline command: bad format, try deadline <desc> /by <dateString>");
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
            throw new DukeException("invalid deadline command: bro, where's the deadline description at?");
        }
        return new String[]{"D",
                description,
                dateString};
    }

    // e.g. event project meeting /at Mon 2-4pm
    // todo: refactor this later
    public String[] parseEvent(String input) throws DukeException {
        String[] separatedInput = input.split(EVENT_DELIMITER); //  <words> /at <timeInfo>
        if (separatedInput.length <= 1) {
            throw new DukeException("invalid event command: bad format, try event <desc> /at <dateString> <startTime>-<endTime>");
        }
        String[] words = separatedInput[0].split(" ");
        String[] timeInfo = separatedInput[1].split(" ");

        if(timeInfo.length <= 1) {
            throw new DukeException("invalid event command: ???");
        }

        String duration = timeInfo[timeInfo.length - 1];
        String[] separatedTime = duration.split(TIME_DELIMITER);
        if(separatedTime.length <= 1) {
            throw new DukeException("invalid event command: you need to pass in a start and end time for your event");
        }

        StringBuilder dateStringBuilder = new StringBuilder();
        for (int i = 0; i < timeInfo.length - 2; i++) {
            dateStringBuilder.append(timeInfo[i]).append(" ");
        }

        dateStringBuilder.append(timeInfo[timeInfo.length - 2]);
        String dateString = dateStringBuilder.toString();
        if(dateString.isEmpty()) {
            throw new DukeException("invalid event command: you didn't pass in the date!");
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
