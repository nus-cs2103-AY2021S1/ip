package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    public static Integer parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new CommandInvalidArgumentFormatException();
        } catch (Exception e) {
            throw e;
        }
    }

    public static Date parseDate(String dateStr)
            throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
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
