package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.commands.AddTaskCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.RemoveTaskCommand;
import duke.commands.UndoCommand;

import duke.exceptions.DukeInvalidIndexException;
import duke.exceptions.DukeInvalidUndoException;
import duke.exceptions.DukeNoTaskDescriptionException;
import duke.lists.CommandList;
import duke.lists.DeletedTaskList;
import duke.lists.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Parser processes user input and executes appropriate command
 * Parser processes date input for deadline class and event class
 */
public class Parser {
    
    private final CommandList commandList = new CommandList();
    private final DeletedTaskList deletedTaskList = new DeletedTaskList();
    
    private boolean isDate(String time) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        date.setLenient(false);
        try {
            date.parse(time.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }


    /**
     * Returns the date (MMM dd yyyy) of the specified time
     * Time must be formatted as yyyy-MM-dd to be converted to date
     * If time does not match the format, the original time will be returned
     *
     * @param time time specified in the task
     * @return Time in date format
     */
    public String convertDate(String time) {
        String convertDate = "";
        try {
            if (isDate(time)) {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
                convertDate = new SimpleDateFormat("MMM dd yyyy").format(date);
            } else {
                convertDate = time;
            }
        } catch (ParseException e) {
            System.out.println("Please specify date in the form yyyy-MM-dd");
        }
        return convertDate;
    }
    
    
    /**
     * Processes user input and executes the appropriate command
     * 
     * @param nextLine next line of user input
     * @param taskList list of tasks
     * @param storage storage for task information
     * @param ui ui to show messages
     */
    public void sortInput (String nextLine, TaskList taskList, Storage storage, Ui ui) {
        String[] input = nextLine.trim().split(" ");
        String commandWord = input[0].toUpperCase();
        Command command = new Command();
        

        try {
            switch (commandWord) {
            case "LIST":
                command = new ListCommand();
                break;

            case "DONE":
                command = new DoneCommand(parseIndex(nextLine), true);
                commandList.addCommand(command);
                break;

            case "DELETE":
                int index = parseIndex(nextLine);
                command = new RemoveTaskCommand(index);
                deletedTaskList.addDeletedTask(taskList.getTask(index));
                commandList.addCommand(command);
                break;

            case "TODO":
                Task todo = parseTodo(nextLine);
                command = new AddTaskCommand(todo);
                commandList.addCommand(command);
                break;

            case "DEADLINE":
                Task deadline = parseDeadline(nextLine);
                command = new AddTaskCommand(deadline);
                commandList.addCommand(command);
                break;

            case "EVENT":
                Task event = parseEvent(nextLine);
                command = new AddTaskCommand(event);
                commandList.addCommand(command);
                break;

            case "FIND":
                String search = nextLine.substring(4).trim();
                command = new FindCommand(search);
                break;

            case "BYE":
                command = new ByeCommand();
                commandList.clear();
                deletedTaskList.clear();
                break;
                
            case "UNDO":
                new UndoCommand().executeCommand(ui, storage, taskList, commandList, deletedTaskList);
                break;

            default:
                ui.defaultMessage();
            }

            command.executeCommand(ui, storage, taskList);
            
        } catch (DukeNoTaskDescriptionException e) {
            ui.setOutputMessage(e.getExceptionMessage());
            
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.setOutputMessage("Please specify a time");
            
        } catch (IndexOutOfBoundsException e) {
            ui.setOutputMessage("Task does not exist");
            
        } catch (DukeInvalidIndexException e) {
            ui.setOutputMessage(e.getExceptionMessage());
            
        } catch (DukeInvalidUndoException e) {
            ui.setOutputMessage(e.getExceptionMessage());
            
        } catch (NumberFormatException e) {
            ui.setOutputMessage("Please enter a number");
        }
    }
    
    
    /**
     * Extract integer from the user input and returns the index of the required task in the taskList
     * 
     * @param input user input
     * @return index of specified task in the taskList
     * @throws DukeInvalidIndexException If index given is 0
     * @throws NumberFormatException If index given is not an integer
     */
    private int parseIndex (String input) throws DukeInvalidIndexException, NumberFormatException {
        int index = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
        if (index < 0) {
            throw new DukeInvalidIndexException("Index starts from 1 instead of 0 :)");
        }
        return index;
    }
    
    private Task parseTodo (String input) throws DukeNoTaskDescriptionException {
        String description = input.substring(4).trim();
        if (description.length() == 0) {
            throw new DukeNoTaskDescriptionException("Please specify a task description");
        }
        return new Todo(description, false);
    }
    
    private Task parseDeadline (String input) throws DukeNoTaskDescriptionException, ArrayIndexOutOfBoundsException {
        String description = input.split("/by")[0].substring(8).trim();
        if (description.length() == 0) {
            throw new DukeNoTaskDescriptionException("Please specify a task description");
        }
        String deadline = input.split("/by")[1].trim();
        return new Deadline(description, deadline, false);
    }
    
    private Task parseEvent (String input) throws DukeNoTaskDescriptionException, ArrayIndexOutOfBoundsException {
        String description = input.split("/at")[0].substring(5).trim();
        if (description.length() == 0) {
            throw new DukeNoTaskDescriptionException("Please specify a task description");
        }
        String time = input.split("/at")[1].trim();
        return new Event(description, time, false);
    }
}
