import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void printList(Ui ui) {
        if (tasks.size() == 0) ui.say("Your task list is currently empty.");
        else {
            ui.say("Here is your task list.");
            int count = 1;
            for (Task task : tasks) {
                System.out.println(count + ". " + task);
                count++;
            }
        }
    }

    public void done(int number, Ui ui) {
        Task task = tasks.get(number - 1);
        task.markDone();
        ui.say("I have marked it as done!");
        System.out.println(task);
    }

    public void delete(int number, Ui ui) {
        Task task = tasks.get(number - 1);
        tasks.remove(number - 1);
        ui.say("I have deleted this task!");
        System.out.println(task);
        ui.say("You have " + size() + " items in your task list now.");
    }

    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        ui.say("You have " + size() + " items in your task list now.");
    }

    public int size() {
        return tasks.size();
    }
}