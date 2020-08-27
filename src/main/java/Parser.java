public class Parser {
    public static TaskType categorize(String[] input_parts) throws Exception {
        if (input_parts[0].compareTo("todo") == 0) {
            return TaskType.ToDo;
        } else if (input_parts[0].compareTo("deadline") == 0) {
            return TaskType.Deadline;
        } else if (input_parts[0].compareTo("event") == 0) {
            return TaskType.Event;
        } else {
            throw new UndefinedWordException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    // Can be used to get the details too
    public static String extractTask(String[] input_parts) {
        String task = "";
        for (int i = 1; i < input_parts.length; i++) {
            task += input_parts[i] + " ";
        }
        return task.trim();
    }

    public static String extractDetails(String[] input_parts) {
        String task = "";
        for (int i = 0; i < input_parts.length; i++) {
            task += input_parts[i] + " ";
        }
        return task.trim();
    }
    
    public static Task parseTask(String input) throws Exception { // catch duke exception from extractTask and categorize, throw to op()
        String[] parts = input.split(" ");
        TaskType type;
        try {
            type = categorize(parts);
        } catch (UndefinedWordException e) {
            throw e;
        }

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

    public static String getKeyword(String input) {
        return input.split(" ")[1];
    }
}