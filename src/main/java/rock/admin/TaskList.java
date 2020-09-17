package rock.admin;

import java.util.ArrayList;

import rock.command.DeadlineCommand;
import rock.command.DeleteCommand;
import rock.command.DoWithinCommand;
import rock.command.DoneCommand;
import rock.command.EventCommand;
import rock.command.FindCommand;
import rock.command.ListCommand;
import rock.command.ToDoCommand;
import rock.task.Task;
import rock.utility.RockResponse;

/**
 * Represent the list that stores all the tasks and handle new tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor.
     * @param tasks List of previous stored tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getArrayList() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void add(Task tmp) {
        tasks.add(tmp);
    }

    public void remove(int i) {
        tasks.remove(i);
    }

    /**
     * Handle command: list
     * @param response
     */
    public void handleList(RockResponse response) {
        ListCommand.handle(response, this);
    }

    /**
     * Handle command: done
     * @param cmd
     * @param response
     */
    public void handleDone(String cmd, RockResponse response) {
        DoneCommand.handle(cmd, response, this);
    }

    /**
     * Handle command: todo
     * @param cmd
     * @param response
     */
    public void handleToDo(String cmd, RockResponse response) {
        ToDoCommand.handle(cmd, response, this);
    }

    /**
     * Handle command: deadline.
     * @param cmd
     * @param response
     */
    public void handleDeadline(String cmd, RockResponse response) {
        DeadlineCommand.handle(cmd, response, this);
    }

    /**
     * Handle command: event.
     * @param cmd
     * @param response
     */
    public void handleEvent(String cmd, RockResponse response) {
        EventCommand.handle(cmd, response, this);
    }

    /**
     * handle command: delete.
     * @param cmd
     * @param response
     */
    public void handleDelete(String cmd, RockResponse response) {
        DeleteCommand.handle(cmd, response, this);
    }

    /**
     * handle command: find
     * @param cmd
     * @param response
     */
    public void handleFind(String cmd, RockResponse response) {
        FindCommand.handle(cmd, response, this);
    }

    /**
     * handle dowithin
     * @param cmd
     * @param response
     */
    public void handleDoWithin(String cmd, RockResponse response) {
        DoWithinCommand.handle(cmd, response, this);
    }
}
