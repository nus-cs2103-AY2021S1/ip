import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> listOfTask;
    private Ui ui;

    public TaskList(Ui ui) {
        listOfTask = new ArrayList<>();
        this.ui = ui;
    }

    public void showListOfTask() {
        ui.showListOfTask(this.listOfTask);
    }

    public void doneTask(String input) throws GelException{
        int index = Integer.parseInt(input.substring(5)) - 1;
        if (index >= listOfTask.size() || index < 0) {
            throw new GelException("    Please input a valid number from 1 - " + listOfTask.size());
        }
        Task taskToBeDone = listOfTask.remove(index);
        taskToBeDone.markAsDone();
        listOfTask.add(index, taskToBeDone);
        ui.markTaskAsDoneMsg(taskToBeDone);
    }

    public void deleteTask(String deleteNumber) throws Exception {
        int taskNo = Integer.parseInt(deleteNumber);
        Task taskToBeDeleted = listOfTask.remove(taskNo - 1);
        ui.taskRemoveMsg(taskToBeDeleted, listOfTask.size());
    }

    public void addDeadline(String input, int dateIndex) throws GelException {
        String by = input.substring(dateIndex + 4);
        String description = input.substring(9, dateIndex - 1);
        LocalDateTime byDateTime = Parser.toDateTime(by);
        Deadline deadline = new Deadline(description, byDateTime);
        listOfTask.add(deadline);
        ui.addTaskToListMsg(deadline, listOfTask.size());
    }

    public void addEvent(String input, int dateIndex) throws GelException {
        String at = input.substring(dateIndex + 4);
        String description = input.substring(6, dateIndex - 1);
        LocalDateTime atDateTime = Parser.toDateTime(at);
        Event event = new Event(description, atDateTime);
        listOfTask.add(event);
        ui.addTaskToListMsg(event, listOfTask.size());
    }

    public void addTodo(String input) {
        String description = input.substring(5);
        Todo todo = new Todo(description);
        listOfTask.add(todo);
        ui.addTaskToListMsg(todo, listOfTask.size());
    }

    public void addTodoFromFile(String description, int done) {
        Todo todo = new Todo(description);
        if (done == 1) {
            todo.markAsDone();
        }
        listOfTask.add(todo);
    }

    public void addEventFromFile(String description, String at, int done) {
        LocalDateTime dateTime = LocalDateTime.parse(at);
        Event event = new Event(description, dateTime);
        if (done == 1) {
            event.markAsDone();
        }
        listOfTask.add(event);
    }

    public void addDeadlineFromFile(String description, String by, int done) {
        LocalDateTime dateTime = LocalDateTime.parse(by);
        Deadline deadline = new Deadline(description, dateTime);
        if (done == 1) {
            deadline.markAsDone();
        }
        listOfTask.add(deadline);
    }

    public List<Task> getListOfTask() {
        return this.listOfTask;
    }
}
