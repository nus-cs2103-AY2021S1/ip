import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList = new ArrayList<>();

    public TaskList(List<Task> inputTasks) {
        for (Task task : inputTasks) {
            this.taskList.add(task);
        }
    }

    public TaskList() {

    }

    Integer countTotalTasks() {
        return this.taskList.size();
    }

    Task getTask(int index) {
        return this.taskList.get(index);
    }

    void addTask(Task obj) throws DukeException {
        if (obj.getName().length() == 0) {
            throw new DukeException(obj.returnMissingNameError());
        }
        this.taskList.add(obj);
        String numOfTasks = this.taskList.size() == 1 ? "1 task" : this.taskList.size() + " tasks";
        String message = "Got it. I've added the following task: " + Ui.NEW_LINE
                + Ui.PADDING + "  " + obj.toString() + Ui.NEW_LINE
                + Ui.PADDING + "Now you have "  + numOfTasks + " in total.";
        Ui.print(message);
    }

    void displayTasks() {
        if (this.taskList.size() == 0) {
            Ui.print("Your list is empty, try adding some tasks to it");
            return;
        }
        StringBuilder output = new StringBuilder("You have the following tasks in your list:" + Ui.NEW_LINE);
        int counter = 1;
        for (Task ele: this.taskList) {
            output.append(Ui.PADDING).append(counter).append(". ").append(ele.toString()).append(Ui.NEW_LINE);
            counter++;
        }
        output = new StringBuilder(output.substring(0, output.length() - 1));
        Ui.print(output.toString());
    }

    void setTaskDone(int index) throws DukeException {
        if (index <= 0 || index > this.taskList.size()) {
            throw new DukeException("Invalid index, cannot find task.");
        }
        this.taskList.get(index-1).setDoneness(true);
        String message = "Nice job! I'll mark that as done:" + Ui.NEW_LINE + Ui.PADDING
                + "  " + this.taskList.get(index-1).toString();
        Ui.print(message);
    }

    void deleteTask(int index) throws DukeException {
        if (index <= 0 || index > this.taskList.size()) {
            throw new DukeException("Invalid index, cannot find task.");
        }
        Task task = this.taskList.get(index-1);
        this.taskList.remove(index-1);
        String numOfTasks = this.taskList.size() == 1 ? "1 task" : this.taskList.size() + " tasks";
        String message = "Noted. The following task has been removed:"
                + Ui.NEW_LINE + Ui.PADDING + "  " + task.toString() + Ui.NEW_LINE
                + Ui.PADDING + "Now you have "  + numOfTasks + " left.";
        Ui.print(message);
    }

    void deleteAllTasks() throws DukeException {
        if (this.taskList.size() == 0) {
            throw new DukeException("Your list is already empty.");
        }
        this.taskList.clear();
        String message = "Noted. All tasks have been removed.";
        Ui.print(message);
    }
}
