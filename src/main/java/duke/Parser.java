package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    /**
     *
     * @param str - takes in a string
     * @return an integer
     */
    public static Integer parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new CommandInvalidArgumentFormatException();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @param dateStr
     * @return a date
     * @throws ParseException
     */
    public static Date parseDate(String dateStr)
            throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format.parse(dateStr);
        return date;
    }

    /**
     * Parses a single line of input from stdin into a command
     */
    public static Command parseLine(TaskList tasks, String string)
            throws ParseException {

        String[] tokens = string.split(",");
        String type = tokens[0];
        switch (type) {
        case "t":
        case "todo": {
            // Todo task

            String taskName = tokens[1];

            if (taskName.equals("")) {
                throw new CommandMissingArgumentException();
            }

            // Create the task
            Task task = new TodoTask(false, taskName);
            return new CreateTaskCommand(tasks, task);
        }
        case "e":
        case "event": {
            // Event task
            if (tokens.length <= 2
                    || tokens[1].equals("")
                    || tokens[2].equals("")) {
                throw new CommandMissingArgumentException();
            }

            // Otherwise create the task
            Task task = new EventTask(
                    false,
                    tokens[1],
                    parseDate(tokens[2])
            );
            return new CreateTaskCommand(tasks, task);
        }

        case "de":
        case "deadline": {
            // Deadline task

            // Arguments
            if (tokens.length <= 2
                    || tokens[1].equals("")
                    || tokens[2].equals("")) {
                throw new CommandMissingArgumentException();
            }

            // Otherwise create the task
            Task task = new DeadlineTask(
                    false,
                    tokens[1],
                    parseDate(tokens[2])
            );
            return new CreateTaskCommand(tasks, task);
        }

        case "ed":
        case "edit": {
            if (tokens.length <= 2
                    || tokens[1].equals("")
                    || tokens[2].equals("")) {
                throw new CommandMissingArgumentException();
            }
            Integer taskIndex = parseInt(tokens[1]);
            String newTaskType = tokens[2];

            switch (newTaskType) {

            case "t":
            case "todo": {
                if (tokens.length <= 3
                        || tokens[3].equals("")) {
                    throw new CommandMissingArgumentException();
                }
                String taskName = tokens[3];

                // Create the task
                Task task = new TodoTask(false, taskName);
                return new EditTaskCommand(tasks, taskIndex, task);
            }
            case "e":
            case "event": {
                // Event task
                if (tokens.length <= 4
                        || tokens[3].equals("")
                        || tokens[4].equals("")) {
                    throw new CommandMissingArgumentException();
                }

                // Otherwise create the task
                Task task = new EventTask(
                        false,
                        tokens[3],
                        parseDate(tokens[4])
                );
                return new EditTaskCommand(tasks, taskIndex, task);
            }

            case "de":
            case "deadline": {
                if (tokens.length <= 4
                        || tokens[3].equals("")
                        || tokens[4].equals("")) {
                    throw new CommandMissingArgumentException();
                }

                // Otherwise create the task
                Task task = new DeadlineTask(
                        false,
                        tokens[1],
                        parseDate(tokens[2])
                );
                return new EditTaskCommand(tasks, taskIndex, task);
            }
            default: {
                return new InvalidCommand();
            }
            }
        }

        case "b":
        case "bye": {
            return new ExitCommand();
        }

        case "l":
        case "list": {
            return new ListCommand(tasks);
        }

        case "f":
        case "find": {
            // Find task
            if (tokens.length <= 1
                    || tokens[1].equals("")) {
                throw new CommandMissingArgumentException();
            }

            return new FindCommand(tasks, tokens[1]);
        }

        case "do":
        case "done": {
            // Handle incorrect argument lengths
            if (tokens.length <= 1) {
                throw new CommandMissingArgumentException();
            }

            Integer taskIndex = Parser.parseInt(tokens[1]) - 1;
            return new CompleteTaskCommand(tasks, taskIndex);
        }

        case "del":
        case "delete": {
            // Handle incorrect argument lengths
            if (tokens.length <= 1) {
                throw new CommandMissingArgumentException();
            }
            Integer taskIndex = Parser.parseInt(tokens[1]) - 1;
            return new DeleteTaskCommand(tasks, taskIndex);
        }

        default:
            return new InvalidCommand();
        }
    }
}
