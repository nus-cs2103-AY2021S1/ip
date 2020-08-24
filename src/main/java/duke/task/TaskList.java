package duke.task;

import duke.io.Layout;
import duke.io.Parser;
import duke.io.Storage;

import java.util.ArrayList;

/**
 * Represents a current task list manager.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage storage;
    private final Parser parser;
    private final Layout layout;
    
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

    /**
     * Add specific tasks to task list.
     * 
     * @param type Type of task.
     * @param arr Array of words from the tasks's description and (if any) date.
     */
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

    /**
     * Get task list and display it to the user.
     */
    public void showTasks() {
        layout.printTaskList(false, tasks);
    }

    /**
     * Modify specific tasks in the task list.
     * Actions include deleting and marking a task as done.
     * 
     * @param type Type of action to execute: delete or mark done.
     * @param i Index of the task in the task list.
     */
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
    
    public void findTask(String [] arr) {
        String filterWord;
        try {
            filterWord = parser.getFilterWord(arr);
            ArrayList<Task> shallowCopy = new ArrayList<>(tasks);
            shallowCopy.removeIf(task -> 
                !(task.containsWord(filterWord))
            );
            layout.printTaskList(true, shallowCopy);
        } catch (DukeException e) {
            layout.print(e.getMessage());
        }
    }


    /**
     * Store task list in hard disk.
     * Exit process.
     */
    public void closeDuke() {
        storage.writeFile(tasks);
        layout.print("Bye. Hope to see you again soon!");
        System.exit(0);
    }

}
