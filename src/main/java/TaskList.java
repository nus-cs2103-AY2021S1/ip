import java.util.List;

public class TaskList {

    List<Task> taskList;

    TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    public int noOfTasks() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void setTask(int index, Task task) {
        this.taskList.set(index, task);
    }

    public void removeTask(int index) {
        this.taskList.remove(index);
    }

    public void deleteTask(TaskList current, String[] input, Ui ui) throws DeleteFailureException {
        try {
            if (input.length == 2) {
                TaskList updated = current;
                int taskNumber = Integer.parseInt(input[1]);
                Task removedTask = current.getTask(taskNumber - 1);
                current.removeTask(taskNumber - 1);
                ui.showSuccessfulDelete(removedTask, updated.noOfTasks());
            } else {
                throw new DeleteFailureException("Duke says: Please try again with a " +
                        "valid format.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DeleteFailureException("Duke says: Please try again with a valid number.");
        }
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void addTask(String[] data, String keyWord, TaskList tasks, Ui ui) {
        if (keyWord.equals("todo")) {
            ToDo toDo = new ToDo(data[0]);
            tasks.addTask(toDo);
            ui.showTasksAdded(toDo, tasks.noOfTasks());
        } else if (keyWord.equals("deadline")) {
            Deadline deadline = new Deadline(data[0], data[1], data[2]);
            tasks.addTask(deadline);
            ui.showTasksAdded(deadline, tasks.noOfTasks());
        } else if (keyWord.equals("event")) {
            Event event = new Event(data[0], data[1], data[2]);
            tasks.addTask(event);
            ui.showTasksAdded(event, tasks.noOfTasks());
        }
    }

    public void doneTask(String[] splitInput, TaskList tasks, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(splitInput[1]);
            Task doneTask = tasks.getTask(taskNumber - 1);
            doneTask.markDone();
            tasks.setTask(taskNumber - 1, doneTask);
            ui.showTaskIsDone(doneTask);
        } catch (Exception ex) {
            ui.showInvalidTaskNumber();
        }
    }
}
