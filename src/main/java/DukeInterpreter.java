public class DukeInterpreter {
    private String code;

    public DukeInterpreter(String code) {
        this.code = code;
    }

    public static String encode(Task task) {
        String taskString = task.toString();
        char type = taskString.charAt(1);
        char symbol = taskString.charAt(4);
        String[] rest = taskString.substring(7).split("\\(", 2);
        String details = rest[0].trim();
        String extra = "";
        if (rest.length == 2) {
            extra = rest[1].split(":", 2)[1].trim();
            extra = extra.substring(0, extra.length() - 1);
        }
        return encodeHelper(type, symbol, details, extra);
    }

    private static String encodeHelper(char type, char symbol, String details, String extra) {
        return type + " | " + symbol + " | " + details +
            (extra.equals("") ? "" : " | " + extra);
    }

    public static Task decode(String code) {
        char taskType = code.charAt(0);
        String[] splittedWords = code.split("\\|");
        boolean isCompleted = splittedWords[1].trim().equals("✓");
        String details = splittedWords[2].trim();
        String extra = null;
        if (splittedWords.length == 4) {
            extra = splittedWords[3].trim();
        }
        switch (taskType) {
        case 'T':
            return decodeToDo(details, isCompleted);
        case 'D':
            return decodeDeadline(details, isCompleted, extra);
        case 'E':
            return decodeEvent(details, isCompleted, extra);
        }
        return null;
    }

    private static ToDo decodeToDo(String details, boolean isCompleted) {
        ToDo toDo = new ToDo(details);
        if (isCompleted) {
            toDo.markAsCompleted();
        }
        return toDo;
    }

    private static Deadline decodeDeadline(String details, boolean isCompleted, String extra) {
        Deadline deadline = new Deadline(details, extra);
        if (isCompleted) {
            deadline.markAsCompleted();
        }
        return deadline;
    }

    private static Event decodeEvent(String details, boolean isCompleted, String extra) {
        Event event = new Event(details, extra);
        if (isCompleted) {
            event.markAsCompleted();
        }
        return event;
    }

}
