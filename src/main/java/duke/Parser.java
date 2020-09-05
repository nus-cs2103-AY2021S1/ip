package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.commands.Command;
import duke.commands.ByeCommand;
import duke.commands.ListCommand;
import duke.commands.FindCommand;
import duke.commands.DoneCommand;
import duke.commands.AddTaskCommand;
import duke.commands.RemoveTaskCommand;

import duke.exceptions.DukeNoTaskDescriptionException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;


public class Parser {
    
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
     * @param time Time specified in the task
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
     *Processes user input 
     * @param next next line of user input
     * @param taskList list of tasks
     * @param storage storage for tasks
     * @param ui ui to show messages
     */
    public void sortInput (String next, TaskList taskList, Storage storage, Ui ui) {
        String[] input = next.trim().split(" ");
        String commandWord = input[0].toUpperCase();
        Command command = new Command();

        try {
            switch (commandWord) {
            case "LIST":
                command = new ListCommand();
                break;

            case "DONE":
                command = new DoneCommand(parseIndex(next));
                break;

            case "DELETE":
                command = new RemoveTaskCommand(parseIndex(next));
                break;

            case "TODO":
                Task todo = parseTodo(next);
                command = new AddTaskCommand(todo);
                break;

            case "DEADLINE":
                Task deadline = parseDeadline(next);
                command = new AddTaskCommand(deadline);
                break;

            case "EVENT":
                Task event = parseEvent(next);
                command = new AddTaskCommand(event);
                break;

            case "FIND":
                String search = next.substring(4).trim();
                command = new FindCommand(search);
                break;

            case "BYE":
                command = new ByeCommand();
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
        }
    }


    private int parseIndex (String s) {
        return Integer.parseInt(s.replaceAll("[^0-9]", "")) - 1;
    }
    
    private Task parseTodo (String s) throws DukeNoTaskDescriptionException {
        String description = s.substring(4).trim();
        if (description.length() == 0) {
            throw new DukeNoTaskDescriptionException("Please specify a task description");
        }
        return new Todo(description, false);
    }
    
    private Task parseDeadline (String s) throws DukeNoTaskDescriptionException, ArrayIndexOutOfBoundsException {
        String description = s.split("/by")[0].substring(8).trim();
        
        if (description.length() == 0) {
            throw new DukeNoTaskDescriptionException("Please specify a task description");
        }
        
        String deadline = s.split("/by")[1].trim();

        return new Deadline(description, deadline, false);
    }
    
    private Task parseEvent (String s) throws DukeNoTaskDescriptionException, ArrayIndexOutOfBoundsException {
        String description = s.split("/at")[0].substring(5).trim();
        if (description.length() == 0) {
            throw new DukeNoTaskDescriptionException("Please specify a task description");
        }

        String time = s.split("/at")[1].trim();
        
        return new Event(description, time, false);
    }
}
