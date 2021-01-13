package tasklist;

import static data.task.Task.containsDate;
import static ui.Ui.echo;
import static ui.Ui.getTaskAddedString;
import static ui.Ui.line;
import static ui.Ui.taskAdded;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import data.task.Task;
import storage.Storage;
import ui.Ui;

public class TaskList {
    // Task storage
    private ArrayList<Task> storage;
    // Date storage
    private HashMap<String, ArrayList<Task>> dateStorage;

    /**
     * constructor for TaskList
     */
    public TaskList() {
        storage = new ArrayList<>();
        dateStorage = new HashMap<>();
    }

    /**
     * overloaded constructor that accepts Storage and updates list of tasks
     *
     * @param stores class that handles the save file
     */
    public TaskList(Storage stores) {
        storage = new ArrayList<>();
        dateStorage = new HashMap<>();
        stores.load(this);
    }

    public ArrayList<Task> getStorage() {
        return storage;
    }

    public HashMap<String, ArrayList<Task>> getDateStorage() {
        return dateStorage;
    }

    /**
     * Marks a certain task as complete
     *
     * @param taskNumber the index number of the task
     */
    public void markComplete(int taskNumber) {
        Task currentTask = storage.get(taskNumber);
        currentTask.complete();
        line();
        System.out.println("Marked task " + (taskNumber + 1) + " as complete.");
        System.out.println(currentTask);
        line();
    }

    /**
     * String version of markComplete
     *
     * @param taskNumber index number of task
     * @return string output of task that's marked complete
     */
    public String getMarkCompleteString(int taskNumber) {
        StringBuilder response = new StringBuilder();
        Task currentTask = storage.get(taskNumber);
        currentTask.complete();
        response.append("Marked task ").append(taskNumber + 1).append(" as complete. \n");
        response.append(currentTask);
        return response.toString();
    }


    /**
     * Depreciated
     * Adds a task to the task storage
     *
     * @param type the type of task to be added
     * @param name the name of the task
     */
    public void addTask(String type, String name) {
        Task taskToAdd = new Task(type, name);
        storage.add(taskToAdd);
        if (taskToAdd.hasDate()) {
            if (!dateStorage.containsKey(taskToAdd.getDate())) {
                dateStorage.put(taskToAdd.getDate(), new ArrayList<>());
            }
            dateStorage.get(taskToAdd.getDate()).add(taskToAdd);
        }
        taskAdded(taskToAdd, storage);
    }


    /**
     * Overloaded store that reads from save file
     * NOT depreciated
     *
     * @param wholeLine each line in the save file
     */
    public void addTask(String wholeLine) {
        String type;
        boolean completionStatus;
        String name;
        String date = null;
        try {
            Scanner currentLine = new Scanner(wholeLine);
            // something like [T][✓]
            if (currentLine.hasNext()) {
                String typeCompletion = currentLine.next();
                switch (typeCompletion.charAt(1)) {
                case ('T'):
                    type = "todo";
                    break;
                case ('E'):
                    type = "event";
                    break;
                case ('D'):
                    type = "deadline";
                    break;
                default:
                    // this shouldn't happen
                    type = null;
                }
                completionStatus = typeCompletion.charAt(4) != 'X';
            } else {
                throw Ui.DukeException.fileError();
            }

            if (currentLine.hasNext()) {
                name = currentLine.nextLine().trim();
            } else {
                throw Ui.DukeException.fileError();
            }
            assert type != null;
            int dateLocation = containsDate(name);
            if (dateLocation >= 0) {
                date = name.substring(dateLocation);
                name = name.substring(0, dateLocation - 5);
            }
            Task taskToAdd = new Task(type, name, completionStatus, date);
            storage.add(taskToAdd);
            if (taskToAdd.hasDate()) {
                if (!dateStorage.containsKey(taskToAdd.getDate())) {
                    dateStorage.put(taskToAdd.getDate(), new ArrayList<>());
                }
                dateStorage.get(taskToAdd.getDate()).add(taskToAdd);
            }
            taskAdded(taskToAdd, storage);
        } catch (Ui.DukeException e) {
            echo(e.getMessage());
        }
    }

    /**
     * String version of store
     *
     * @param type type of task
     * @param name name of task
     * @return String output of task added
     */
    public String getAddTaskString(String type, String name) {
        Task taskToAdd = new Task(type, name);
        storage.add(taskToAdd);
        if (taskToAdd.hasDate()) {
            if (!dateStorage.containsKey(taskToAdd.getDate())) {
                dateStorage.put(taskToAdd.getDate(), new ArrayList<>());
            }
            dateStorage.get(taskToAdd.getDate()).add(taskToAdd);
        }
        return getTaskAddedString(taskToAdd, storage);
    }

    /**
     * Deletes a certain task
     *
     * @param taskNumber the index number of the task
     */
    public void delete(int taskNumber) {
        Task currentTask = storage.get(taskNumber);
        line();
        System.out.println("Deleted task:");
        System.out.println(currentTask);
        storage.remove(taskNumber);
        System.out.println("There are now " + storage.size() + " task(s) remaining.");
        line();
    }

    /**
     * String version of delete
     *
     * @param taskNumber index of task to be deleted
     * @return String stating task deleted
     */
    public String getDeleteString(int taskNumber) {
        StringBuilder response = new StringBuilder("Deleted task:\n");
        Task currentTask = storage.get(taskNumber);
        response.append(currentTask).append("\n");
        storage.remove(taskNumber);
        response.append("There are now ").append(storage.size()).append(" task(s) remaining.");
        return response.toString();
    }


    public String getUpdateString(int taskNumber, String changeField, String changeTo) {
        StringBuilder response = new StringBuilder("Updated task:\n");
        Task currentTask = storage.get(taskNumber);
        switch (changeField) {
        case "name":
            currentTask.updateName(changeTo);
            break;
        case "date":
            currentTask.updateDate(changeTo);
            break;
        default:
            return "Invalid change field, task not updated";
        }
        response.append(currentTask).append("\n");
        return response.toString();
    }
}
