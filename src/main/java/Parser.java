public class Parser {

    public enum Command {
        BYE, LIST, DONE, DELETE, TODO, EVENT, DEADLINE, FIND, INVALID
    }

    public static Command parseCommand(String fullCommand) {
        String[] splitWords = fullCommand.split(" ");
        String keyword = splitWords[0].toLowerCase();
        switch (keyword) {
            case "bye":
                return Command.BYE;
            case "list":
                return Command.LIST;
            case "done":
                return Command.DONE;
            case "delete":
                return Command.DELETE;
            case "todo":
                return Command.TODO;
            case "event":
                return Command.EVENT;
            case "deadline":
                return Command.DEADLINE;
            case "find":
                return Command.FIND;
            default:
                return Command.INVALID;
        }
    }

    /**
     * Helper function to process a timed task description (Event, Deadline) and
     * split it into 2 strings, the description and the task date.
     *
     * @param str The raw string to be processed.
     * @return A String array of size 2. Index 0 contains the task's description,
     * index 1 contains the date of the task.
     */
    public static String[] parseTimedTask(String str) {
        String[] result = new String[2];
        try {
            // Split the string first then loop through to find the stop point at either /at or /by.
            String[] arr = str.split(" ");
            int indexToStop = -1;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals("/at") || arr[i].equals("/by")) {
                    indexToStop = i;
                    // Stop index when indicator /at or /by is found.
                }
            }
            if (indexToStop == -1) throw new DukeException("Incorrect Input for timed task.");

            // Use StringBuilder Class to recreate the description and time separately.
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < indexToStop; j++) {
                sb.append(arr[j]);
                if (j != indexToStop - 1) sb.append(" ");
            }
            // Event / Deadline description has been built, pass it to result[0].
            result[0] = sb.toString();

            sb = new StringBuilder();
            for (int k = indexToStop + 1; k < arr.length; k++) {
                sb.append(arr[k]);
                if (k != arr.length - 1) sb.append(" ");
            }
            String date = sb.toString();
            // Now to check if this date can be formatted nicely using DateTimeProcessor class.
            String parsedDate = new DateTimeProcessor().getParsedDate(date);
            result[1] = parsedDate;
        } catch (DukeException e) {
            System.out.println("Exception occurred while processing timed task: " + e);
        }
        return result;
    }
}
