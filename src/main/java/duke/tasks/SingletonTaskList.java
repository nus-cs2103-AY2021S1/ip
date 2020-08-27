package duke.tasks;

import duke.exceptions.TaskOutOfBoundException;
import duke.storage.Storage;
import duke.ui.Printer;

import java.util.List;

public class SingletonTaskList {
    List<Task> tasks;
    Storage storage = new Storage();

    private static SingletonTaskList instance;

    public static synchronized SingletonTaskList getInstance(Storage database) {
        if (instance == null) {
            instance = new SingletonTaskList(database);
        }
        return instance;
    }


    private SingletonTaskList(Storage database) {
        this.storage = database;
        tasks = storage.readAll();
    }


    public void add(Task task) {
        tasks.add(task);
        int numOfTask = tasks.size();
        Printer.printAdd(task, numOfTask);
        storage.update(tasks);
    }

    public void delete(int idx) throws TaskOutOfBoundException {
        try {
            Printer.printDelete(tasks.get(idx - 1), tasks.size() - 1);
            tasks.remove(idx - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskOutOfBoundException("task number out of bound", idx);
        }
    }

    public void listAll() {
        if(tasks.size() == 0){
            Printer.printNoTaskReminder();
        }
        Printer.printAllTask(tasks);
    }

    public void setTaskDone(int idx) throws TaskOutOfBoundException {
        try {
            Task task = this.tasks.get(idx);
            task.setStatus(true);
            Printer.printDoneTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskOutOfBoundException("Target number of task out of bound", idx + 1);
        }

    }

}
