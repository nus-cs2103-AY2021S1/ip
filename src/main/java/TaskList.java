import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArrayList;

    public TaskList() {
        taskArrayList = new ArrayList<>();
    }


    public void addTask(Storage storage, Task task) throws IOException {
        taskArrayList.add(task);
        Ui.print("     Got it. I've added this task:\n" + "     " + task.toString() +
                "\n     Now you have " + taskArrayList.size() + " tasks in the list");
        storage.writeToFile(task);
    }


    public Task getTask(int i) throws DukeException {
        if (i < 0 || i >= taskArrayList.size()) {
            throw new DukeException("invalid task number");
        }
        return taskArrayList.get(i);
    }

    public void printTasks() {
        if (taskArrayList.size() == 0){
            Ui.print("There are no tasks!\n");
        }
        else Ui.printList(this.taskArrayList);
    }

    public void delete(int i) throws DukeException {
        if (i < 0 || i > taskArrayList.size()) {
            throw new DukeException("invalid task number");
        }
        taskArrayList.remove(i - 1);
        Ui.printList(this.taskArrayList);
    }

    public void setDone(int i, Storage storage) {
        Task doneTask = taskArrayList.get(i - 1);
        doneTask.markAsDone();
        Ui.print("Nice! I've marked this task as done:\n" + doneTask);
        storage.replaceDone(doneTask.getDescription());
    }
}
