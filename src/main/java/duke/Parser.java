package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;


public class Parser {
    private boolean isDate(String time) {
        try {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            date.setLenient(false);
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
        String s = "";
        try {
            if (isDate(time)) {
                Date d = new SimpleDateFormat("yyyy-MM-dd").parse(time);
                s = new SimpleDateFormat("MMM dd yyyy").format(d);
            } else {
                s = time;
            }
        } catch (ParseException e) {
            System.out.println("Please specify date in the form yyyy-MM-dd");
        }
        return s;
    }
    /**
     *Processes user input and returns the appropriate response
     * @param next next line of user input
     * @param list list of tasks
     * @param storage storage for tasks
     * @param ui ui to show messages
     * @return returns the appropriate response to the user
     */
    public String sortInput (String next, TaskList list, Storage storage, Ui ui) {
        String[] input = next.trim().split(" ");
        String commandWord = input[0].toUpperCase();
        String output = "";
        try {
            switch (commandWord) {
            case "LIST":
                output = ui.showList(list);
                break;
            case "DONE":
                Task completedTask = done(next, list);
                storage.writeToDataFile(list);
                output = ui.showDoneMessage(completedTask);
                break;
            case "DELETE":
                Task deletedTask = delete(next, list);
                storage.writeToDataFile(list);
                output = ui.showRemoveTaskMessage(deletedTask, list.size());
                break;
            case "TODO":
                Task addedTodo = addTodo(next, list);
                storage.writeToDataFile(list);
                output = ui.showAddTaskMessage(addedTodo, list.size());
                break;
            case "DEADLINE":
                Task addedDeadline = addDeadline(next, list);
                storage.writeToDataFile(list);
                output = ui.showAddTaskMessage(addedDeadline, list.size());
                break;
            case "EVENT":
                Task addedEvent = addEvent(next, list);
                storage.writeToDataFile(list);
                output = ui.showAddTaskMessage(addedEvent, list.size());
                break;
            case "FIND":
                TaskList filter = find(next, list);
                output = ui.showList(filter);
                break;
            case "HI":
                output = ui.showWelcomeMessage();
                break;
            case "BYE":
                output = ui.showByeMessage();
                break;
            default:
                output = "Sorry >_< I don't know what you mean...";
            }
        } catch (DukeException e) {
            ui.showNoTaskInputException();
        } catch (IndexOutOfBoundsException e) {
            ui.showNoTaskExistException();
        } catch (NumberFormatException e) {
            System.out.println ("Please enter a number");
        }
        return output;
    }


    private Task done (String s, TaskList list) {
        int index = Integer.parseInt(s.replaceAll("[^0-9]", "")) - 1;
        list.setDone(index);
        return list.get(index);
    }
    private Task delete (String s, TaskList list) throws IndexOutOfBoundsException {
        int index = Integer.parseInt(s.replaceAll("[^0-9]", "")) - 1;
        Task t = list.get(index);
        list.removeTask(index);
        return t;
    }
    private Task addTodo (String s, TaskList list) throws DukeException {
        String description = s.substring(4).trim();
        if (description.length() == 0) {
            throw new DukeException();
        }
        Task t = new Todo(description, false);
        list.addTask(t);
        return t;
    }
    private Task addDeadline (String s, TaskList list) throws DukeException, IndexOutOfBoundsException {
        String description = s.split("/by")[0].substring(9).trim();
        String deadline = s.split("/by")[1].trim();
        if (description.length() == 0) {
            throw new DukeException();
        }
        Task t = new Deadline(description, deadline, false);
        list.addTask(t);
        return t;
    }
    private Task addEvent (String s, TaskList list) throws DukeException, IndexOutOfBoundsException {
        String description = s.split("/at")[0].substring(5).trim();
        String time = s.split("/at")[1].trim();
        if (description.length() == 0) {
            throw new DukeException();
        }
        Task t = new Event(description, time, false);
        list.addTask(t);
        return t;
    }
    private TaskList find (String s, TaskList list) {
        String search = s.substring(4).trim();
        return list.filter(search);
    }
}
