package parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import command.Command;
import command.DeadlineCommand;
import command.DeleteAllCommand;
import command.DeleteCommand;
import command.DoneAllCommand;
import command.DoneCommand;
import command.EventCommand;
import command.ExitCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import command.ShowAfterCommand;
import command.ShowBeforeCommand;
import command.TodoCommand;
import command.WrongCommand;
import exception.DescriptionException;
import exception.DukeDateTimeParserException;
import exception.DukeKeywordException;
import exception.NoIndexException;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TodoTask;


/**
 * The Parser class are used to parse any user input and process
 * them into executable command and data.
 */
public class Parser {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * An empty constructor to initialize Parser object.
     */
    public Parser() {}

    /**
     * This method parse a file path into parent directory and the file name.
     *
     * @param filepath String The path of a file
     * @return String[] array of separated String.
     */
    public static String[] fileParser(String filepath) {
        return filepath.split("/");
    }


    /**
     * This method parse the content of a file line by line and
     * turns into the correct Task object.
     *
     * @param line String Each line of strings in the file.
     * @return Task Return the correct Task type from reading the file.
     */
    public static Task readFileParser(String line) {
        String[] strings = line.split("\\|", 3);
        String taskType = strings[0].trim();

        switch(taskType) {
        case "T" :
            TodoTask todoTask = new TodoTask();
            if (strings[1].trim().equals("1")) {
                todoTask.setDone(true);
            } else {
                todoTask.setDone(false);
            }
            todoTask.setDescription(strings[2].trim());
            return todoTask;
        case "D" :
            DeadlineTask deadlineTask = new DeadlineTask();
            if (strings[1].trim().equals("1")) {
                deadlineTask.setDone(true);
            } else {
                deadlineTask.setDone(false);
            }
            String[] taskDetails = strings[2].split("\\|");
            deadlineTask.setDescription(taskDetails[0].trim());
            deadlineTask.setDate(LocalDateTime.parse(taskDetails[1].trim(), formatter));
            return deadlineTask;
        case "E" :
            EventTask eventTask = new EventTask();

            if (strings[1].trim().equals("1")) {
                eventTask.setDone(true);

            } else {
                eventTask.setDone(false);
            }
            String[] taskDetails2 = strings[2].split("\\|");
            eventTask.setDescription(taskDetails2[0].trim());
            eventTask.setDateTime(LocalDateTime.parse(taskDetails2[1].trim(), formatter));
            return eventTask;
        default :
            return new Task();
        }
    }

    /**
     * This method parse given user command and turns it into and
     * Command object.
     *
     * @param command String User command.
     * @return Command Return various Command type based on user input.
     */
    public static Command parseCommand(String command) {
        if (command.toLowerCase().equals(Command.EXIT_COMMAND)) {
            return new ExitCommand();
        } else if (command.toLowerCase().equals(Command.LIST_COMMAND)) {
            return new ListCommand();
        } else if (command.toLowerCase().equals(Command.DONE_ALL_COMMAND)) {
            return new DoneAllCommand();
        } else if (command.toLowerCase().contains(Command.DONE_COMMAND)) {
            return new DoneCommand(command);
        } else if (command.toLowerCase().contains(Command.TODO_COMMAND)) {
            return new TodoCommand(command);
        } else if (command.toLowerCase().contains(Command.DEADLINE_COMMAND)) {
            return new DeadlineCommand(command);
        } else if (command.toLowerCase().contains(Command.EVENT_COMMAND)) {
            return new EventCommand(command);
        } else if (command.toLowerCase().equals(Command.HELP_COMMAND)) {
            return new HelpCommand();
        } else if (command.toLowerCase().contains(Command.DELETE_ALL_COMMAND)) {
            return new DeleteAllCommand();
        } else if (command.toLowerCase().contains(Command.DELETE_COMMAND)) {
            return new DeleteCommand(command);
        } else if (command.toLowerCase().contains(Command.SHOW_AFTER_COMMAND)) {
            return new ShowAfterCommand(command);
        } else if (command.toLowerCase().contains(Command.SHOW_BEFORE_COMMAND)) {
            return new ShowBeforeCommand(command);
        } else if (command.toLowerCase().contains(Command.FIND_COMMAND)) {
            return new FindCommand(command);
        } else {
            return new WrongCommand(command);
        }
    }

    /**
     * This method would parse user command(usually DeleteCommand and
     * DoneCommand), returns the number that was passed in the command.
     *
     * @param input user command.
     * @return int The index specified by the user command.
     * @throws NoIndexException This exception is thrown when user forget
     * to specify the index of the task list.
     */
    public static int findIndexParser(String input) throws NoIndexException {
        try {
            int index = Integer.parseInt(input.split("\\s")[1]);
            return index;
        } catch (IndexOutOfBoundsException e) {
            throw new NoIndexException();
        }
    }

    /**
     * This method would parse user command(usually ShowAfterCommand
     * or ShowBeforeCommand) and returns the date that was passed in the
     * command.
     *
     * @param input String user command.
     * @return LocalDate The date that was passed in the user command.
     * @throws DukeDateTimeParserException This exception is thrown when user forget
     * to specify the date in the command.
     */
    public static LocalDate findDateParser(String input) throws DukeDateTimeParserException {
        try {
            LocalDate localDate = LocalDate.parse(input.split("\\s")[2]);
            return localDate;
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeParserException();
        }
    }

    /**
     * This method would return Map(String,String) that contains task's description and
     * task's date-time. Usually used by DeadlineCommand and EventCommand.
     *
     * @param input user command.
     * @return Map (String,String) Returns map that contains task's description and task's date-time.
     * @throws DescriptionException This exception is thrown when user forget to specify
     * either the task description or the date-time.
     */
    public static Map<String, String> findDescriptionParser(String input) throws DescriptionException {
        try {
            Map<String, String> map = new HashMap<>();
            String[] getDetails = input.split("\\s", 2);
            String[] details = getDetails[1].split("/", 2);
            map.put("taskDescription", details[0].trim());
            String[] splitTimeDetails = details[1].split("\\s", 2);
            map.put("taskTime", splitTimeDetails[1]);
            return map;
        } catch (IndexOutOfBoundsException e) {
            throw new DescriptionException();
        }
    }

    /**
     * This method would parse specific TodoCommand and return the task description.
     *
     * @param input user command.
     * @return String task description of type TodoTask.
     * @throws DescriptionException This exception is thrown when user forget to specify
     * the task's description on the command.
     */
    public static String findTodoParser(String input) throws DescriptionException {
        try {
            return input.split("\\s", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DescriptionException();
        }
    }

    /**
     * Finds the keyword specified in user command
     *
     * @param input String user command.
     * @return String keyword
     * @throws DukeKeywordException Thrown when user failed to specify the keyword
     * in the command.
     */
    public static String findKeywordParser(String input) throws DukeKeywordException {
        try {
            String keyword = input.split("\\s", 2)[1];
            if (keyword.equals("") || keyword.equals("\\s")) {
                throw new DukeKeywordException();
            } else {
                return keyword.trim();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeKeywordException();
        }
    }
}
