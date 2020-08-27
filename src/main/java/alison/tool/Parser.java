package alison.tool;

import alison.command.*;
import alison.exception.AlisonException;
import alison.task.*;

public class Parser {


    /**
     * This method parse a task string into its corresponding Task.
     * @param taskString Saved line from file path.
     * @return Corresponding Task.
     * @throws AlisonException
     */
    public static Task parseTask(String taskString) throws AlisonException {
        String[] words = taskString.split(" \\| ");
        String type = words[0];
        boolean isDone = Boolean.parseBoolean(words[1]);
        String description = words[2];
        try {
            if (type.equals("T")) {
                return new ToDo(description, isDone);
            } else if (type.equals("E")) {
                return new Event(description, isDone, words[3]);
            } else if (type.equals("D")) {
                return new Deadline(description, isDone, words[3]);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw AlisonException.loadingException();
        }
    }

    /**
     * This method deals with making sense of the user command.
     * @param cmd Full string of command entered by the user.
     * @return a Command object.
     * @throws AlisonException
     */
    public static Command parse(String cmd) throws AlisonException {
        String[] words = cmd.split(" ");
        String command = words[0];
        if (words.length == 1) {
            switch (command) {
                case "bye" -> {
                    return new ExitCommand();
                }
                case "list" -> {
                    return new ShowCommand();
                }
                case "done", "delete" -> throw AlisonException.operationException();
                case "todo", "deadline", "event" -> throw AlisonException.emptyDescriptionException();
                default -> throw AlisonException.defaultException();
            }
        }
        if (words.length > 1) {
            String description = cmd.split(" ", 2)[1];
            switch (command) {
                case "done" -> {
                    try {
                        int doneIndex = Integer.parseInt(description);
                        return new DoneCommand(doneIndex);
                    } catch (Exception e) {
                        throw AlisonException.invalidIndexException();
                    }
                }
                case "delete" -> {
                    try {
                        int deleteIndex = Integer.parseInt(description);
                        return new DeleteCommand(deleteIndex);
                    } catch (Exception e) {
                        throw AlisonException.invalidIndexException();
                    }
                }
                case "todo" -> {
                    ToDo todo = new ToDo(description);
                    return new AddCommand(todo);
                }
                case "deadline" -> {
                    String[] contentAndTime = description.split(" /by ");
                    if (contentAndTime.length == 1) {
                        throw AlisonException.deadlineException();
                    } else {
                        Deadline ddl = new Deadline(contentAndTime[0], contentAndTime[1]);
                        return new AddCommand(ddl);
                    }
                }
                case "event" -> {
                    String[] contentAndTime = description.split(" /at ");
                    if (contentAndTime.length == 1) {
                        throw AlisonException.eventException();
                    } else {
                        Event e = new Event(contentAndTime[0], contentAndTime[1]);
                        return new AddCommand(e);
                    }
                }
                default -> {
                    return new UnknownCommand();
                }
            }
        }

        return new UnknownCommand();
    }
}
