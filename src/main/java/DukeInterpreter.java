/**
 * Represents the interpreter used to encode and decode tasks for the storage.
 */
public class DukeInterpreter {

    /**
     * Encodes the task and returns a string to be stored in the duke.txt.
     * @param task task to be encoded.
     * @return String representation for storage.
     */
    public static String encode(Task task) {
        assert task != null : "There should be a task to encode!";

        String taskString = task.toString();
        char type = taskString.charAt(1);
        char symbol = taskString.charAt(4);
        String[] rest = taskString.substring(7).split("\\(", 2);
        String details = rest[0].trim();
        String extraDetails = ""; // extra info for deadline/event.
        if (rest.length == 2) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                extraDetails = deadline.getDateTime().toString();
            } else if (task instanceof Event) {
                Event event = (Event) task;
                extraDetails = event.getDateTime().toString();
            }
        }
        return encodeHelper(type, symbol, details, extraDetails);
    }

    private static String encodeHelper(char type, char symbol, String details, String extra) {
        return type + " | " + symbol + " | " + details
                + (extra.equals("") ? "" : " | " + extra);
    }

    /**
     * Decodes the string representation and returns the Task to be added to tasklist.
     * @param code string representation of task.
     * @return Task to be added.
     * @throws DukeException If invalid date/time is provided.
     */
    public static Task decode(String code) throws DukeException {
        assert code != null : "There should be a task to decode!";

        char taskType = code.charAt(0);
        String[] splittedWords = code.split("\\|");
        boolean isCompleted = splittedWords[1].trim().equals("✓");
        String details = splittedWords[2].trim();
        String extraDetails = null; // extra info for deadline/event
        if (splittedWords.length == 4) {
            // since the tostring has a 'T' char inside
            String dateTime = splittedWords[3].trim();
            String date = dateTime.substring(0, 10);
            String time = dateTime.substring(11, 16);
            extraDetails = date + " " + time;
        }
        switch (taskType) {
        case 'T':
            return decodeToDo(details, isCompleted);
        case 'D':
            return decodeDeadline(details, isCompleted, extraDetails);
        case 'E':
            return decodeEvent(details, isCompleted, extraDetails);
        default:
            return null;
        }
    }

    private static ToDo decodeToDo(String details, boolean isCompleted) {
        ToDo toDo = new ToDo(details);
        if (isCompleted) {
            toDo.markTaskAsCompleted();
        }
        return toDo;
    }

    private static Deadline decodeDeadline(String details, boolean isCompleted,
                                           String extraDetails) throws DukeException {
        Deadline deadline = new Deadline(details, extraDetails);
        if (isCompleted) {
            deadline.markTaskAsCompleted();
        }
        return deadline;
    }

    private static Event decodeEvent(String details, boolean isCompleted,
                                     String extraDetails) throws DukeException {
        Event event = new Event(details, extraDetails);
        if (isCompleted) {
            event.markTaskAsCompleted();
        }
        return event;
    }

}
