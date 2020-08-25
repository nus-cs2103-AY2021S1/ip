package duke;

import task.TaskType;

import java.security.InvalidParameterException;

public class Parser {

    public static boolean isList(String line) {
        return line.equals("list");
    }

    public static boolean isExit(String line) {
        return line.toLowerCase().contains("bye");
    }

    public static boolean isDone(String line) {
        return line.length() >= 4 && line.substring(0, 4).equals("done");
    }

    public static boolean isDelete(String line) {
        return line.length() >= 6 && line.substring(0, 6).equals("delete");
    }

    public static TaskType taskType(String line) throws InvalidParameterException {
        if (line.length() > 8
                && line.substring(0, 8).equals("deadline")
                && line.contains(" /by ")) {
            return TaskType.DEADLINE;
        } else if (line.length() > 5
                && line.substring(0, 5).equals("event")
                && line.contains(" /at ")) {
            return TaskType.EVENT;
        } else if (line.length() > 4 && line.substring(0, 4).equals("todo")) {
            return TaskType.TODO;
        } else throw new InvalidParameterException("Invalid input");
    }

    public static String getName(String line) throws NullPointerException, InvalidParameterException {
        String name;
        try {
            if (taskType(line) == TaskType.TODO) {
                name = line.substring(5);
            } else if (taskType(line) == TaskType.DEADLINE) {
                name = line.split(" /by ")[0].substring(9);
            } else {
                name = line.split(" /at ")[0].substring(6);
            }
        } catch (InvalidParameterException e) {
            throw new InvalidParameterException("Invalid parameters");
        }
        if (name.isEmpty()) {
            throw new NullPointerException("Null Object");
        } else return name;
    }

    public static String getTime(String line) throws ArrayIndexOutOfBoundsException {
        try {
            if (taskType(line) == TaskType.DEADLINE) {
                return line.split(" /by ")[1];
            } else {
                return line.split(" /at ")[1];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

}
