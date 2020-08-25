package duke;

import task.TaskType;

import java.security.InvalidParameterException;

public class Parser {

    /**
     * Checks if a command line is a "list" command
     * @param line string of a command
     * @return list or not
     */
    public static boolean isList(String line) {
        return line.equals("list");
    }

    /**
     * Checks if a command line is an "exit" command
     * @param line string of a command
     * @return exit or not
     */
    public static boolean isExit(String line) {
        return line.toLowerCase().contains("bye");
    }

    /**
     * Checks if a command line is a "done" command
     * @param line string of a command
     * @return done or not
     */
    public static boolean isDone(String line) {
        return line.length() >= 4 && line.substring(0, 4).equals("done");
    }

    /**
     * Checks if a command line is a "delete" command
     * @param line string of a command
     * @return delete or not
     */
    public static boolean isDelete(String line) {
        return line.length() >= 6 && line.substring(0, 6).equals("delete");
    }

    /**
     * Extracts task type from a given "add" command line
     * @param line string of a command
     * @return task type
     * @throws InvalidParameterException if line is invalid
     */
    public static TaskType taskType(String line) throws InvalidParameterException {
        if (line.length() > 8
            && line.substring(0, 8).equals("deadline")
            && line.contains(" /by ")) {
            return TaskType.DEADLINE;
        }
        else if (line.length() > 5
                && line.substring(0, 5).equals("event")
                && line.contains(" /at ")) {
            return TaskType.EVENT;
        }
        else if (line.length() > 4 && line.substring(0, 4).equals("todo")) {
            return TaskType.TODO;
        }
        else throw new InvalidParameterException("Invalid input");
    }

    /**
     * Extracts task name from a given "add" command line
     * @param line string of a command
     * @return task name
     * @throws InvalidParameterException if line is invalid
     * @throws NullPointerException if task name is empty
     */
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
        }
        catch (InvalidParameterException e) {
            throw new InvalidParameterException("Invalid parameters");
        }
        if (name.isEmpty()) {
            throw new NullPointerException("Null Object");
        }
        else return name;
    }

    /**
     * Extracts task time from an "add" command line of type "deadline" or "event"
     * @param line string of a command
     * @return time of the task
     * @throws ArrayIndexOutOfBoundsException if the time does not exist
     */
    public static String getTime(String line) throws ArrayIndexOutOfBoundsException{
        try {
            if (taskType(line) == TaskType.DEADLINE) {
                return line.split(" /by ")[1];
            } else {
                return line.split(" /at ")[1];
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

}
