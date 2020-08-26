import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public void printList() {
        Ui.printWithLines(this.toString());
    }

    public void completeTask(int i) {
        String prefix = "Roger roger! I'm gonna mark this task as done:\n";
        Task task = super.get(i);
        task.complete();
        Ui.printWithLines(String.format("  %s%s\n", prefix, task));
    }

    public void addTask(Task newTask) {
        super.add(newTask);
        String prefix = "Okay! I shall add this task:\n";
        String suffix = String.format("Now you got a total of %s task%s in your list!\n", super.size(),
                super.size() == 1 ? "" : "s");
        Ui.printWithLines(prefix + newTask + "\n" + suffix);
    }

    public void deleteTask(int index) {
        Task task = super.get(index);
        super.remove(index);
        String prefix = "Very well! I shall delete this task:\n";
        Ui.printWithLines(prefix + task + "\n");
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        StringBuilder prefix = new StringBuilder("Here are the tasks in your list:\n");
        int l = super.size();
        for (int i = 0; i < l; i++) {
            list.append(i + 1).append(".").append(super.get(i).toString()).append("\n");
        }
        return prefix.append(list).toString();
    }
}