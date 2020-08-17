import java.util.StringTokenizer;

public class Parser {

    private static final String DEADLINE_DELIMITER = "by";
    private static final String EVENT_DELIMITER = "at";
    private static final String TIME_DELIMITER = "-";


    public Parser() {
    }

    // takes in the input and returns a string arr, does exception checking here as well:
    public String[] parseCommand(String input) {
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
                    return new String[]{"ERROR"};
            }
        }
    }

    private String[] parseDone(String input) {
        StringTokenizer st = new StringTokenizer(input);
        assert st.countTokens() == 2;
        return new String[]{st.nextToken(), st.nextToken()};
    }


    private String[] parseToDo(String input) {
        StringTokenizer st = new StringTokenizer(input);
        st.nextToken();
        StringBuilder description = new StringBuilder();
        while (st.hasMoreTokens()) {
            description.append(st.nextToken()).append(" ");
        } // got extra space at the end though
        return new String[]{"ToDo", description.toString().stripTrailing()};
    }


    private String[] parseDeadline(String input) {
        String[] separatedInput = input.split("/" + DEADLINE_DELIMITER);
        String dateString = separatedInput[1];
        StringTokenizer st = new StringTokenizer(separatedInput[0]);
        st.nextToken();
        StringBuilder newDescription = new StringBuilder();
        while (st.hasMoreTokens()) {
            newDescription.append(st.nextToken()).append(" ");
        }
        return new String[]{"Deadline",
                newDescription.toString().stripTrailing(),
                dateString};
    }

    // e.g. event project meeting /at Mon 2-4pm
    // todo: refactor this later
    private String[] parseEvent(String input) {
        String[] separatedInput = input.split("/" + EVENT_DELIMITER);
        String[] words = separatedInput[0].split(" ");
        String[] separatedTimeStrings = separatedInput[1].split(" ");
        String duration = separatedTimeStrings[separatedTimeStrings.length - 1];
        String[] separatedTime = duration.split(TIME_DELIMITER);

        StringBuilder dateStringBuilder = new StringBuilder();
        for (int i = 0; i < separatedTimeStrings.length - 2; i++) {
            dateStringBuilder.append(separatedTimeStrings[i]).append(" ");
        }
        dateStringBuilder.append(separatedTimeStrings[separatedTimeStrings.length - 2]);
        String dateString = dateStringBuilder.toString();

        String startTime = separatedTime[0],
                endTime = separatedTime[1];
        StringBuilder newDescription = new StringBuilder();
        for (int i = 1; i < words.length - 1; i++) {
            newDescription.append(words[i]).append(" ");
        }
        newDescription.append(words[words.length - 1]);
        return new String[]{"Event",
                newDescription.toString().stripTrailing(),
                dateString,
                startTime,
                endTime};
    }

}
