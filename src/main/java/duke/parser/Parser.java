package duke.parser;

import java.time.LocalDate;

import duke.Ui;
import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ListCommand;
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
    public Parser() {

    }

    /**
     * Generates the correct Command object to be executed.
     *
     * @param command User's input.
     * @return Appropriate command to execute logic of input.
     */
    public static Command parse(String command) {
        try {
            if (command.contains("done")) {
                String[] doneInputs = command.split(" ");
                if (doneInputs.length == 1) {
                    throw new InvalidCommand("OOPS!!! Please enter at least 1 task number");
                } else if (doneInputs.length > 2) {
                    throw new InvalidCommand("OOPS!!! Please enter 1 task number only");
                } else if (doneInputs.length == 2) {
                    try {
                        Integer.parseInt(doneInputs[1]);
                    } catch (NumberFormatException ex) {
                        throw new InvalidCommand("Please enter task number instead of task name!");
                    }
                    int taskToDo = Integer.parseInt(doneInputs[1]) - 1;
                    DoneCommand dc = new DoneCommand(taskToDo);
                    return dc;
                }
            } else if (command.contains("todo")) {
                try {
                    String taskToDo = command.substring(5);
                    if (taskToDo.length() == 0) {
                        throw new InvalidCommand("OOPS!!! Please specify your task.");
                    }
                    ToDo newToDo = new ToDo(taskToDo);
                    AddCommand ac = new AddCommand(newToDo);
                    return ac;
                } catch(StringIndexOutOfBoundsException ex) {
                    throw new InvalidCommand("OOPS!!! Please specify your task.");
                } catch (InvalidCommand invalidCommand) {
                    Ui.commandError(invalidCommand);
                }
            } else if (command.contains("deadline")) {
                try {
                    String deadlineInput = command.substring(9);
                    int indexSeparator = deadlineInput.indexOf("/by");
                    if (indexSeparator == -1) {
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
                }
            } else if (command.contains("event")) {
                try {
                    String eventInput = command.substring(6);
                    int indexSeparator = eventInput.indexOf("/at");
                    if (indexSeparator == -1) {
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
                }
            } else if (command.contains("list")) {
                if (command.split(" ").length > 1) {
                    throw new InvalidCommand("You have to view your entire to-do list!");
                }
                ListCommand lc = new ListCommand();
                return lc;
            } else if (command.contains("delete")) {
                String[] deleteInput = command.split(" ");
                if (deleteInput.length > 2) {
                    throw new InvalidCommand("Please only input 1 task number!");
                }
                try {
                    int deleteIndex = Integer.parseInt(deleteInput[1]);
                    DeleteCommand dc = new DeleteCommand(deleteIndex);
                    return dc;
                } catch (NumberFormatException ex) {
                    try {
                        throw new InvalidCommand("Please enter a valid task number");
                    } catch (InvalidCommand invalidCommand) {
                        Ui.commandError(invalidCommand);
                    }
                }
            } else if (command.contains("bye")) {
                return new ByeCommand();
            } else {
                throw new InvalidCommand("Please enter the correct command");
            }
        } catch(InvalidCommand ex) {
            Ui.commandError(ex);
        }
        return null;
    }
}
