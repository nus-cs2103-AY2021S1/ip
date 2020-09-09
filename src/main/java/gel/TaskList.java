package gel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import gel.exception.GelException;
import gel.task.Deadline;
import gel.task.Event;
import gel.task.Task;
import gel.task.Todo;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    private final List<Task> listOfTasks;
    private final Ui ui;

    public TaskList(Ui ui) {
        listOfTasks = new ArrayList<>();
        this.ui = ui;
    }

    public String showListOfTask() {
        return ui.showListOfTask(this.listOfTasks);
    }

    public String findDescription(String keyword) {
        return ui.showSearchResults(this.listOfTasks, keyword);
    }

    public String markTaskAsDone(String input) throws GelException {
        int index = Integer.parseInt(input.substring(5)) - 1;
        if (index >= listOfTasks.size() || index < 0) {
            throw new GelException("    Please input a valid number from 1 - " + listOfTasks.size());
        }
        Task taskToBeDone = listOfTasks.remove(index);
        taskToBeDone.markAsDone();
        listOfTasks.add(index, taskToBeDone);
        return ui.markTaskAsDoneMsg(taskToBeDone);
    }

    public String deleteTask(String deleteNumber) {
        int taskNo = Integer.parseInt(deleteNumber);
        Task taskToBeDeleted = listOfTasks.remove(taskNo - 1);
        return ui.taskRemoveMsg(taskToBeDeleted, listOfTasks.size());
    }

    public String addDeadline(String input, int dateIndex) throws GelException {
        String by = input.substring(dateIndex + 4);
        String description = input.substring(9, dateIndex - 1);
        LocalDateTime byDateTime = Parser.toDateTime(by);
        Deadline deadline = new Deadline(description, byDateTime);
        listOfTasks.add(deadline);
        return ui.addTaskToListMsg(deadline, listOfTasks.size());
    }

    public String addEvent(String input, int dateIndex) throws GelException {
        String at = input.substring(dateIndex + 4);
        String description = input.substring(6, dateIndex - 1);
        LocalDateTime atDateTime = Parser.toDateTime(at);
        Event event = new Event(description, atDateTime);
        listOfTasks.add(event);
        return ui.addTaskToListMsg(event, listOfTasks.size());
    }

    public String addTodo(String input) {
        String description = input.substring(5);
        Todo todo = new Todo(description);
        listOfTasks.add(todo);
        return ui.addTaskToListMsg(todo, listOfTasks.size());
    }

    public void addTodoFromFile(String description, int done) {
        Todo todo = new Todo(description);
        if (done == 1) {
            todo.markAsDone();
        }
        listOfTasks.add(todo);
    }

    public void addEventFromFile(String description, String at, int done) {
        LocalDateTime dateTime = LocalDateTime.parse(at);
        Event event = new Event(description, dateTime);
        if (done == 1) {
            event.markAsDone();
        }
        listOfTasks.add(event);
    }

    public void addDeadlineFromFile(String description, String by, int done) {
        LocalDateTime dateTime = LocalDateTime.parse(by);
        Deadline deadline = new Deadline(description, dateTime);
        if (done == 1) {
            deadline.markAsDone();
        }
        listOfTasks.add(deadline);
    }

    public List<Task> getListOfTask() {
        return this.listOfTasks;
    }
}

