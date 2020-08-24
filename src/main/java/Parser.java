package main.java;

import main.java.Command.Command;
import main.java.Command.DeadlineCommand;
import main.java.Command.DeleteCommand;
import main.java.Command.DoneCommand;
import main.java.Command.EventCommand;
import main.java.Command.ExitCommand;
import main.java.Command.HelpCommand;
import main.java.Command.ListCommand;
import main.java.Command.ShowAfterCommand;
import main.java.Command.ShowBeforeCommand;
import main.java.Command.TodoCommand;
import main.java.Command.WrongCommand;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Parser(){}

    public static String[] fileParser(String filepath) {
        return filepath.split("/");
    }

    public static Task readFileParser(String line) {
        String[] strings = line.split("\\|", 3);
        String taskType = strings[0].trim();

        switch(taskType) {
        case "T" :
            TodoTask todoTask = new TodoTask();
            if (strings[1].trim().equals("1")) {
                todoTask.setStatus(true);
            } else {
                todoTask.setStatus(false);
            }
            todoTask.setDescription(strings[2].trim());
            return todoTask;
        case "D" :
            DeadlineTask deadlineTask = new DeadlineTask();
            if (strings[1].trim().equals("1")) {
                deadlineTask.setStatus(true);
            } else {
                deadlineTask.setStatus(false);
            }
            String[] taskDetails = strings[2].split("\\|");
            deadlineTask.setDescription(taskDetails[0].trim());
            deadlineTask.setDate(LocalDateTime.parse(taskDetails[1].trim(),formatter));
            return deadlineTask;
        case "E" :
            EventTask eventTask = new EventTask();
            if(strings[1].trim().equals("1")) {
                eventTask.setStatus(true);
            } else {
                eventTask.setStatus(false);
            }
            String[] taskDetails2 = strings[2].split("\\|");
            eventTask.setDescription(taskDetails2[0].trim());
            eventTask.setDateTime(LocalDateTime.parse(taskDetails2[1].trim(),formatter));
            return eventTask;
        default :
            return new Task() ;
        }
    }

    public static Command commandParser(String command) {
        if (command.equals(Command.EXIT_COMMAND)) {
            return new ExitCommand();
        } else if (command.equals(Command.LIST_COMMAND)) {
            return new ListCommand();
        } else if (command.contains(Command.DONE_COMMAND)) {
            return new DoneCommand(command);
        } else if (command.contains(Command.TODO_COMMAND)) {
            return new TodoCommand(command);
        } else if (command.contains(Command.DEADLINE_COMMAND)) {
            return new DeadlineCommand(command);
        } else if (command.contains(Command.EVENT_COMMAND)) {
            return new EventCommand(command);
        } else if (command.equals(Command.HELP_COMMAND)){
            return new HelpCommand();
        } else if (command.contains(Command.DELETE_COMMAND)){
            return new DeleteCommand(command);
        } else if (command.contains(Command.SHOW_AFTER_COMMAND)) {
            return new ShowAfterCommand(command);
        } else if (command.contains(Command.SHOW_BEFORE_COMMAND)) {
            return new ShowBeforeCommand(command);
        } else {
            return new WrongCommand(command);
        }
    }

    public static int findIndexParser(String input) throws NoIndexException {
        try {
            int index = Integer.parseInt(input.split("\\s")[1]);
            return index;
        } catch(IndexOutOfBoundsException e) {
            throw new NoIndexException();
        }
    }

    public static LocalDate findDateParser(String input) throws DukeDateTimeParserException {
        try {
            LocalDate localDate = LocalDate.parse(input.split("\\s")[2]);
            return localDate;
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeParserException();
        }
    }

    public static Map<String,String> taskDescriptionParser(String input) throws DescriptionException {
        try {
            Map<String, String> map = new HashMap<>();
            String[] getDetails = input.split("\\s", 2);
            String[] details = getDetails[1].split("/", 2);
            map.put("taskDescription", details[0].trim());
            String[] splitTimeDetails = details[1].split("\\s",2);
            map.put("taskTime", splitTimeDetails[1]);
            return map;
        } catch (IndexOutOfBoundsException e) {
            throw new DescriptionException();
        }
    }

    public static String todoParser(String input) throws DescriptionException {
        try {
            return input.split("\\s", 2)[1];
        } catch(NullPointerException e) {
            throw new DescriptionException();
        }
    }
}
