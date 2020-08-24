package duke;

import duke.exception.WriteToStorageException;
import duke.task.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static Storage store;
    private List<Task> taskList;


    public TaskList(Storage store) {
        this.store = store;
        this.taskList = new ArrayList<>();
        store.initialiseTasks(taskList);
    }


    public Storage getStore() {
        return store;
    }
    public void addTask(Task task) throws WriteToStorageException {
        taskList.add(task);
        try {
            store.writeData(task);
        } catch (IOException e) {
            throw new WriteToStorageException();
        }
    }
    public void doneTask(int index) throws WriteToStorageException {
        taskList.get(index).markAsDone();
        try {
            store.rewriteData(taskList);
        } catch (IOException e) {
            throw new WriteToStorageException();
        }
    }

    @Override
    public String toString() {
        if(taskList.size() == 0) {
            return "No task added yet!";
        } else {
            StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
            for(int i = 0; i < taskList.size(); i++) {
                result.append(i + 1).append(". ").append(taskList.get(i).toString()).append(i + 1 == taskList.size()
                        ? "" : "\n");
            }
            return result.toString();
        }
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public Task remove(int index) {
        return taskList.remove(index);
    }
}
