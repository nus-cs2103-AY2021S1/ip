package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.TagCommand;
import duke.exception.InvalidCommand;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;


/**
 * Represents parser to parse and create appropriate Command object to handle
 * the commands given by user.
 *
 */
public class Parser {
    private static final int ITEM_NOT_FOUND_INDEX = -1;

    public Parser() {
    }

    /**
     * Generates the correct Command object to be executed.
     *
     * @param command User's input.
     * @return Appropriate command to execute logic of input.
     */
    public static Command parse(String command) throws InvalidCommand {
        if (command.contains("done")) {
            DoneCommand newCommand = processDone(command);
            return newCommand;
        } else if (command.contains("todo")) {
            AddCommand newCommand = processToDo(command);
            return newCommand;
        } else if (command.contains("deadline")) {
            AddCommand newCommand = processDeadline(command);
            return newCommand;
        } else if (command.contains("event")) {
            AddCommand newCommand = processEvent(command);
            return newCommand;
        } else if (command.contains("list")) {
            ListCommand newCommand = processList(command);
            return newCommand;
        } else if (command.contains("delete")) {
            DeleteCommand newCommand = processDelete(command);
            return newCommand;
        } else if (command.contains("bye")) {
            return new ByeCommand();
        } else if (command.contains("find")) {
            FindCommand newCommand = processFind(command);
            return newCommand;
        } else if (command.contains("help")) {
            HelpCommand newCommand = new HelpCommand();
            return newCommand;
        } else if (command.contains("tag")) {
            TagCommand newCommand = processTag(command);
            return newCommand;
        }
        //Throws an error if user command does not match any keywords.
        throw new InvalidCommand("Please enter the correct command");
    }

    private static TagCommand processTag(String command) throws InvalidCommand {
        try {
            if (command.contains("|")) {
                throw new InvalidCommand(("Please do not use '|' in your commands!"));
            }
            int taskIndex;
            try {
                taskIndex = Integer.parseInt(command.substring(4, 5));
            } catch (NumberFormatException ex) {
                throw new InvalidCommand("Please enter task number instead of task name!");
            }
            int taskIndexInList = taskIndex - 1;
            String tagWord = command.substring(6);
            TagCommand tc = new TagCommand(taskIndexInList, tagWord);
            return tc;
        } catch (StringIndexOutOfBoundsException ex) {
            throw new InvalidCommand("OOPS!!! Please specify your task.");
        }
    }

    private static FindCommand processFind(String command) throws InvalidCommand {
        try {
            String requestKeywords = command.substring(5);
            if (requestKeywords.length() == 0) {
                throw new InvalidCommand("OOPS!!! Please specify your task.");
            }
            FindCommand fc = new FindCommand(requestKeywords);
            return fc;
        } catch (StringIndexOutOfBoundsException ex) {
            throw new InvalidCommand("OOPS!!! Please specify your task.");
        }
    }

    private static DeleteCommand processDelete(String command) throws InvalidCommand {
        String[] deleteInput = command.split(" ");
        if (deleteInput.length > 2) {
            throw new InvalidCommand("Please only input 1 task number!");
        }
        try {
            int deleteIndex = Integer.parseInt(deleteInput[1]);
            int indexInTaskList = deleteIndex - 1;
            DeleteCommand dc = new DeleteCommand(indexInTaskList);
            return dc;
        } catch (NumberFormatException ex) {
            throw new InvalidCommand("Please enter a valid task number");
        }
    }

    private static ListCommand processList(String command) throws InvalidCommand {
        if (command.split(" ").length > 1) {
            throw new InvalidCommand("You have to view your entire to-do list!");
        }
        return new ListCommand();
    }

    private static AddCommand processEvent(String command) throws InvalidCommand {
        try {
            if (command.contains("|")) {
                throw new InvalidCommand(("Please do not use '|' in your commands!"));
            }
            String eventInput = command.substring(6);
            int indexSeparator = eventInput.indexOf("/at");
            if (indexSeparator == ITEM_NOT_FOUND_INDEX) {
                throw new InvalidCommand("Please include your event date using /at !");
            }
            String eventTaskName = eventInput.substring(0, indexSeparator - 1);
            String eventTime = eventInput.substring(indexSeparator + 4);
            LocalDate eventDate = LocalDate.parse(eventTime);
            Event newEvent = new Event(eventTaskName, eventDate);
            AddCommand ac = new AddCommand(newEvent);
            return ac;
        } catch (StringIndexOutOfBoundsException ex) {
            throw new InvalidCommand("OOPS!!! Please specify your task.");
        } catch (DateTimeParseException ex) {
            throw new InvalidCommand ("OOPS!!! Invalid date.");
        }
    }

    private static AddCommand processDeadline(String command) throws InvalidCommand {
        try {
            if (command.contains("|")) {
                throw new InvalidCommand(("Please do not use '|' in your commands!"));
            }
            String deadlineInput = command.substring(9);
            int indexSeparator = deadlineInput.indexOf("/by");
            if (indexSeparator == ITEM_NOT_FOUND_INDEX) {
                throw new InvalidCommand("Please include your deadline using /by !");
            }
            String deadlineTaskName = deadlineInput.substring(0, indexSeparator - 1);
            String deadlineTime = deadlineInput.substring(indexSeparator + 4);
            LocalDate deadlineDate = LocalDate.parse(deadlineTime);
            Deadline newDeadline = new Deadline(deadlineTaskName, deadlineDate);
            AddCommand ac = new AddCommand(newDeadline);
            return ac;
        } catch (StringIndexOutOfBoundsException ex) {
            throw new InvalidCommand("OOPS!!! Please specify your task.");
        } catch (DateTimeParseException ex) {
            throw new InvalidCommand ("OOPS!!! Invalid date.");
        }
    }

    private static AddCommand processToDo(String command) throws InvalidCommand {
        try {
            if (command.contains("|")) {
                throw new InvalidCommand(("Please do not use '|' in your commands!"));
            }
            String taskToDo = command.substring(5);
            if (taskToDo.length() == 0) {
                throw new InvalidCommand("OOPS!!! Please specify your task.");
            }
            ToDo newToDo = new ToDo(taskToDo);
            AddCommand ac = new AddCommand(newToDo);
            return ac;
        } catch (StringIndexOutOfBoundsException ex) {
            throw new InvalidCommand("OOPS!!! Please specify your task.");
        }
    }

    private static DoneCommand processDone(String command) throws InvalidCommand {
        String[] doneInputs = command.split(" ");
        if (doneInputs.length == 1) {
            throw new InvalidCommand("OOPS!!! Please enter at least 1 task number");
        } else if (doneInputs.length > 2) {
            throw new InvalidCommand("OOPS!!! Please enter 1 task number only");
        }
        int taskToDo;
        try {
            taskToDo = Integer.parseInt(doneInputs[1]);
        } catch (NumberFormatException ex) {
            throw new InvalidCommand("Please enter task number instead of task name!");
        }
        int indexInTaskList = taskToDo - 1;
        DoneCommand dc = new DoneCommand(indexInTaskList);
        return dc;
    }
}
