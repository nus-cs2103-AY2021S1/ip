package duke;

import java.io.StreamCorruptedException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

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

    public static Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
        Date date = format.parse(dateStr);
        return date;
    }

    /**
     * The parser is able to parse input from file
     */
    public static ArrayList<Task> parseFile(String string)
            throws StreamCorruptedException, ParseException {
        Scanner scanner = new Scanner(string);
        ArrayList tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            Task task = Parser.parseFileLine(nextLine);
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Parses a single line of input from file
     */
    public static Task parseFileLine(String string)
            throws StreamCorruptedException, ParseException {
        String[] tokens = string.split(",");
        String type = tokens[0];
        switch (type) {
            case "[T]": {
                // Todo task

                if (tokens.length <= 1 || tokens[2].equals("")) {
                    throw new CommandMissingArgumentException();
                }

                // Create the task
                return new TodoTask(tokens[1].equals("1"), tokens[2]);
            }
            case "[E]": {
                // Events task

                // Arguments
                if (tokens.length <= 2
                        || tokens[2].equals("")
                        || tokens[3].equals("")) {
                    throw new CommandMissingArgumentException();
                }

                // Otherwise create the task
                return new EventTask(
                        tokens[1].equals("1"),
                        tokens[2],
                        parseDate(tokens[3])
                );
            }
            case "[D]": {
                // Deadline task

                // Arguments
                if (tokens.length <= 2
                        || tokens[2].equals("")
                        || tokens[3].equals("")) {
                    throw new CommandMissingArgumentException();
                }

                // Otherwise create the task
                return new DeadlineTask(
                        tokens[1].equals("1"),
                        tokens[2],
                        parseDate(tokens[3])
                );
            }
            default: {
                throw new StreamCorruptedException("File is corrupted");
            }
        }
    }

    /**
     * Parses a single line of input from stdin into a command
     */
    public static Command parseLine(TaskList tasks, String string) throws ParseException {
        String[] tokens = string.split(",");
        String type = tokens[0];
        switch (type) {
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
            case "bye": {
                return new ExitCommand();
            }
            case "list": {
                return new ListCommand(tasks);
            }
            case "done": {
                // Handle incorrect argument lengths
                if (tokens.length <= 1) {
                    throw new CommandMissingArgumentException();
                }

                Integer taskIndex = Parser.parseInt(tokens[1]) - 1;
                return new CompleteTaskCommand(tasks, taskIndex);
            }

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
