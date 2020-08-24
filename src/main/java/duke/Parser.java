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
    
    public boolean isDate(String time) {
        try {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            date.setLenient(false);
            date.parse(time.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    

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
                
            } else {
                throw new UnsupportedOperationException();
            }
            
        } catch (DukeException e) {
            System.out.println ("Please specify a task description");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println ("Please specify a time");
        } catch (IndexOutOfBoundsException e) {
            System.out.println ("Task does not exist");
        } catch (UnsupportedOperationException e) {
            System.out.println ("Sorry >_< I don't know what you mean...");
        }
    }


    public Task done(String s, TaskList list) throws IndexOutOfBoundsException {
        int index = Integer.parseInt(s.replaceAll("[^0-9]", "")) - 1;
        list.setDone(index);
        return list.get(index);
    }
    
    public Task delete (String s, TaskList list) throws IndexOutOfBoundsException { 
        int index = Integer.parseInt(s.replaceAll("[^0-9]", "")) - 1;
        Task t = list.get(index);
        list.removeTask(index);
        return t;
    }
    
    public Task addTodo (String s, TaskList list) throws DukeException {
        String description = s.substring(4).trim();
        
        if (description.length() == 0) {
            throw new DukeException();
        }
        Task t = new Todo(description);
        list.addTask(t);
        return t;
    }
    
    public Task addDeadline (String s, TaskList list) throws DukeException, ArrayIndexOutOfBoundsException {
        String description = s.split("/by")[0].substring(9).trim();
        String deadline = s.split("/by")[1].trim();
        if (description.length() == 0) {
            throw new DukeException();
        }
        Task t = new Deadline(description, deadline);
        list.addTask(t);
        return t;
    }
    
    public Task addEvent (String s, TaskList list) throws DukeException, ArrayIndexOutOfBoundsException {
        String description = s.split("/at")[0].substring(5).trim();
        String time = s.split("/at")[1].trim();
        if (description.length() == 0) {
            throw new DukeException();
        }
        Task t = new Event(description, time);
        list.addTask(t);
        return t;
    }
}
