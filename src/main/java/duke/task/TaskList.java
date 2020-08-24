package duke.task;

import duke.io.Layout;
import duke.io.Parser;
import duke.io.Storage;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;
    private Parser parser;
    private Layout layout;
    
    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
        this.parser = new Parser();
        this.layout = new Layout();
    }
    
    public enum Type {
        TODO,
        DEADLINE,
        EVENT
    };
    
    public enum Action {
        DONE, 
        DELETE
    }

    public void addTask(Type type, String [] arr) {
        Task task;
        
        try {
            String date = parser.getDateAndDescription(arr)[0];
            String description = parser.getDateAndDescription(arr)[1];
            if (description.equals("") || arr.length == 1) {
                throw new DukeException("The description of a " + type + " cannot be empty");
            }

            switch (type) {
                case DEADLINE:
                    if (date.equals("")) {
                        throw new DukeException("Please specify a due date using /by");
                    } else {
                        task = new Deadline(description, date);
                    }
                    break;
                case EVENT:
                    if (date.equals("")) {
                        throw new DukeException("Please specify an event date using /at");
                    } else {
                        task = new Event(description, date);
                    }
                    break;
                default: //case: todo
                    task = new Todo(description);
                    break;
            }

            tasks.add(task);
            layout.printAddedMessage(task.toString(), tasks.size());
        } catch (DukeException e) {
            layout.print(e.getMessage());
        }
    }
    
    public void showTasks() {
        layout.printTaskList(tasks);
    }

    public void modifyTask(Action type, String i) {
        try {
            int index = Integer.parseInt(i);
            Task task = tasks.get(index - 1);
            switch (type) {
                case DONE:
                    task.markDone();
                    layout.printMarkedDone(task);
                    break;
                case DELETE:
                    tasks.remove(index - 1);
                    layout.printDeleted(task, tasks.size());
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            DukeException d = new DukeException("Task " + i + " cannot be found");
            layout.print(d.getMessage());
        } catch (NumberFormatException e) {
            DukeException d = new DukeException(i + " is not an integer");
            layout.print(d.getMessage());
        }

    }
    
    public void closeDuke() {
        storage.writeFile(tasks);
        layout.print("Bye. Hope to see you again soon!");
        System.exit(0);
    }

}
