package gel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

    public String markTaskAsDone(String[] doneArr) throws GelException {
        if (doneArr.length <= 1) {
            throw new GelException("    Oi tell me what you want to mark as done la");
        }
        ArrayList<Task> listOfTasksDone = new ArrayList<>();
        ArrayList<Integer> validatedValues = validateValues(doneArr);
        for (Integer value : validatedValues) {
            Task taskToBeDone = listOfTasks.remove(value - 1);
            taskToBeDone.markAsDone();
            listOfTasks.add(value - 1, taskToBeDone);
            listOfTasksDone.add(taskToBeDone);
        }
        return ui.markTaskAsDoneMsg(listOfTasksDone);
    }

    private ArrayList<Integer> validateValues(String[] doneArr) throws GelException {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int s = 1; s < doneArr.length; s++) {
            int taskNo = Integer.parseInt(doneArr[s]);
            if (taskNo > listOfTasks.size() || taskNo <= 0) {
                throw new GelException("    Please input a valid number from 1 - " + listOfTasks.size());
            }
            assert taskNo > 0 && taskNo <= listOfTasks.size() : "taskNo out of bounds";
            integerArrayList.add(taskNo);
        }
        return integerArrayList;
    }

    public String deleteTask(String[] deleteArr) throws GelException {
        if (deleteArr.length <= 1) {
            throw new GelException("    Yo tell me what you want to delete la");
        }
        ArrayList<Task> tasksDeleted = new ArrayList<>();
        ArrayList<Integer> sorted = sortTaskInDescendingOrder(deleteArr);
        for (Integer taskNo : sorted) {
            Task taskToBeDeleted = listOfTasks.remove(taskNo - 1);
            tasksDeleted.add(taskToBeDeleted);
        }
        Collections.reverse(tasksDeleted);
        return ui.taskRemoveMsg(tasksDeleted, listOfTasks.size());
    }

    private ArrayList<Integer> sortTaskInDescendingOrder(String[] deleteArr) throws GelException {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int i = 1; i < deleteArr.length; i++) {
            int taskNo = Integer.parseInt(deleteArr[i]);
            if (taskNo <= 0 || taskNo > listOfTasks.size()) {
                throw new GelException("    Please input valid numbers from 1 - " + listOfTasks.size()
                        + "\n    format: delete x y z");
            }
            integerArrayList.add(taskNo);
        }
        Collections.sort(integerArrayList);
        Collections.reverse(integerArrayList);
        return integerArrayList;
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

