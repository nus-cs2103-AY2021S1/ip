package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    
    public Parser() {}
    
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
     * Sends different instructions depending on user input
     * 
     * @param next Next line of input from the user
     * @param list List of tasks
     * @param storage Storage of tasks
     * @param ui User interface to receive input and generate output to users
     */
    public void sortInput (String next, TaskList list, Storage storage, Ui ui ) {
        try {
            next = next.trim();

            if (next.equalsIgnoreCase("list")) {      //Listing out all the tasks             
                ui.showList(list);

            } else if (next.startsWith("done")) {    //When a task is done
                Task completedTask = done(next, list);
                ui.showDoneMessage(completedTask);
                storage.writeToDataFile(list);

            } else if (next.startsWith("delete")) {
                Task deletedTask = delete(next, list);
                ui.showRemoveTaskMessage(deletedTask, list.size());
                storage.writeToDataFile(list);

            } else if (next.startsWith("todo")) {
                Task addedTodo = addTodo(next, list);
                ui.showAddTaskMessage(addedTodo, list.size());
                storage.writeToDataFile(list);

            } else if (next.startsWith("deadline")) {
                Task addedDeadline = addDeadline(next, list);
                ui.showAddTaskMessage(addedDeadline, list.size());
                storage.writeToDataFile(list);

            } else if (next.startsWith("event")) {
                Task addedEvent = addEvent(next, list);
                ui.showAddTaskMessage(addedEvent, list.size());
                storage.writeToDataFile(list);

            } else if (next.startsWith("find")) {
                TaskList filter = find(next, list);
                ui.showList(filter);
            } else {
                throw new UnsupportedOperationException();
            }
            
        } catch (DukeException e) {
            ui.showNoTaskInputException();
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showNoTimeInputException();
        } catch (IndexOutOfBoundsException e) {
            ui.showNoTaskExistException();
        } catch (UnsupportedOperationException e) {
            ui.showDoNotUnderstandMesssage();
        }
    }


    private Task done(String s, TaskList list) throws IndexOutOfBoundsException {
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
        Task t = new Todo(description);
        list.addTask(t);
        return t;
    }
    
    private Task addDeadline (String s, TaskList list) throws DukeException, ArrayIndexOutOfBoundsException {
        String description = s.split("/by")[0].substring(9).trim();
        String deadline = s.split("/by")[1].trim();
        if (description.length() == 0) {
            throw new DukeException();
        }
        Task t = new Deadline(description, deadline);
        list.addTask(t);
        return t;
    }
    
    private Task addEvent (String s, TaskList list) throws DukeException, ArrayIndexOutOfBoundsException {
        String description = s.split("/at")[0].substring(5).trim();
        String time = s.split("/at")[1].trim();
        if (description.length() == 0) {
            throw new DukeException();
        }
        Task t = new Event(description, time);
        list.addTask(t);
        return t;
    }
    
    private TaskList find (String s, TaskList list) {
        String search = s.substring(4).trim();
        return list.filter(search);
    }
}
