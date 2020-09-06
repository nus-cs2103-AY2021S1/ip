package duke;

public class Parser {
    /**
     * categorize() place a task to its appropriate type based on String input
     * @param inputParts an array of Strings derived from input
     * @return corresponding TaskType object
     */
    public static TaskType categorize(String[] inputParts) throws Exception {
        if (inputParts[0].compareTo("todo") == 0) {
            return TaskType.ToDo;
        } else if (inputParts[0].compareTo("deadline") == 0) {
            return TaskType.Deadline;
        } else if (inputParts[0].compareTo("event") == 0) {
            return TaskType.Event;
        } else {
            throw new UndefinedWordException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    /**
     * extractTask extract a task name from inputParts.
     * @param inputParts array of strings derived from input
     * @return task name
     */
    private static String extractTask(String[] inputParts) {
        StringBuilder task = new StringBuilder();
        for (int i = 1; i < inputParts.length; i++) {
            task.append(inputParts[i]).append(" ");
        }
        return task.toString().trim();
    }

    /**
     * Extract details of deadline and event tasks from input.
     * @param inputParts input in form of String array
     * @return details of task
     */
    private static String extractDetails(String[] inputParts) {
        StringBuilder task = new StringBuilder();
        for (String inputPart : inputParts) {
            task.append(inputPart).append(" ");
        }
        return task.toString().trim();
    }

    /**
     * parseTask creates a Task object based on user input String.
     * @param input String of user input
     * @return corresponding Task object
     */
    public static Task parseTask(String input) throws Exception {
        String[] parts = input.split(" ");
        TaskType type;
        type = categorize(parts);

        if (type == TaskType.ToDo) {
            parts = input.split(" ");
            if (parts.length <= 1) {
                throw new NoDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.\n");
            } // if not, chillax and continue
            String name = extractTask(parts);
            return new ToDo(name);
        } else if (type == TaskType.Deadline) {
            if (input.split("/by").length <= 1) {
                throw new NoDescriptionException("☹ OOPS!!! The description of a deadline cannot be empty.\n");
            } // if not, chillax and continue
            String name = extractTask(input.split("/by")[0].split(" "));
            String deadline = extractDetails(input.split("/by")[1].split(" "));
            return new Deadline(name, deadline);
        } else {
            if (input.split("/at").length <= 1) {
                throw new NoDescriptionException("☹ OOPS!!! The description of an event cannot be empty.\n");
            } // if not, chillax and continue
            String name = extractTask(input.split("/at")[0].split(" "));
            String details = extractDetails(input.split("/at")[1].split(" "));

            return new Event(name, details);
        }
    }

    /**
     * Series of is* methods help identify what operation is specified by the
     * user.
     * @param input user input, to be compared with the operation keyword
     * @return boolean indicating whether it is some operation
     */

    public static boolean isBye(String input) {
        return input.compareTo("bye") == 0;
    }

    public static boolean isList(String input) {
        return input.compareTo("list") == 0;
    }

    public static boolean isDone(String input) {
        return input.split(" ")[0].compareTo("done") == 0;
    }

    public static boolean isDelete(String input) {
        return input.split(" ")[0].compareTo("delete") == 0;
    }

    public static boolean isFind(String input) {
        return input.split(" ")[0].compareTo("find") == 0;
    }
    public static int getIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * getKeyword obtains user's keyword that they wish to find in tasks
     * @param input user input
     * @return keyword as String
     */
    public static String getKeyword(String input) {
        return input.split(" ")[1];
    }
}
