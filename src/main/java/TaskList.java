import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void showTaskList() {
        System.out.println("Here is your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Ui.showMessage((i + 1) + ". " + tasks.get(i) + "\n");
        }
        return;
    }

    public void markTaskAsDone(int idx) throws DukeException {
        if (idx < 1 || idx > tasks.size()) {
            throw new DukeException("Sorry, but you inputted an invalid task index.");
        }
        tasks.get(idx - 1).markAsDone();
        Ui.showMessage("Great job!\nI have marked the task as done");
        Ui.showMessage(tasks.get(idx - 1).toString());
    }

    public void deleteTask(int idx) throws DukeException {
        if (idx < 1 || idx > tasks.size()) {
            throw new DukeException("Sorry, but you inputted an invalid task index.");
        }

        Ui.showMessage("Noted!\nI have deleted this task from your task list.");
        Ui.showMessage(tasks.get(idx - 1).toString());
        tasks.remove(idx - 1);
        Ui.showMessage(String.format(
                "Currently you have %d tasks in your list, don't forget to do them!\n",
                tasks.size()));
    }

    @Override
    public String toString() {
        String data = "";
        for (Task task : tasks) {
            data += task.writeToFile() + '\n';
        }

        return data;
    }
}
