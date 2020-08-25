package main.java.manager;

import main.java.exceptions.InvalidDescriptionException;
import main.java.exceptions.InvalidTimeException;
import main.java.tasks.Deadline;
import main.java.tasks.Event;
import main.java.tasks.Task;
import main.java.tasks.Todo;

public class Ui {

    private final TaskList taskList = new TaskList();
    private final Storage database = Storage.initializeDatabase();

    public Task convertTask(Commands command, String input) {
        try {
            switch (command) {
                case DEADLINE:
                    String deadlineDesc = input.substring("deadline".length(), input.indexOf("/by")).trim();
                    String endTime = input.substring(input.indexOf("/by") + "/by".length()).trim();
                    return new Deadline(deadlineDesc, endTime);
                case EVENT:
                    String eventDesc = input.substring("event".length(), input.indexOf("/at")).trim();
                    String time = input.substring(input.indexOf("/at") + "/at".length()).trim();
                    return new Event(eventDesc, time);
                case TODO:
                    String todoDesc = input.substring("todo".length()).trim();
                    return new Todo(todoDesc);
            }
        } catch (InvalidDescriptionException | InvalidTimeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void convertAction(Commands command, int index) {
        switch (command) {
            case LIST:
                this.taskList.listTasks();
                break;
            case DONE:
                this.taskList.markTaskAsDone(index);
                break;
            case DELETE:
                this.taskList.deleteTask(index);
                break;
        }
    }

    public void passTask(Task task) {
        this.taskList.addTask(task);
    }

    public int totalTasks() {
        return this.taskList.getNumberOfTasks();
    }

    public void storeTasks() {
        this.database.updateDatabase(this.taskList.getList());
    }

    public void getSavedTasks() {
        this.taskList.setList(this.database.retrieveTaskList());
    }
}
