import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void handleEvent(String[] inputs) {
        String[] tokens = inputs[1].split(" /at ");
        tasks.add(new Event(tokens[0],tokens[1]));
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void handleDeadline(String[] inputs) {
        String[] tokens = inputs[1].split(" /by ");
        tasks.add(new Deadline(tokens[0],tokens[1]));
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void handleToDo(String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        tasks.add(new ToDo(inputs[1]));
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void handleDone(String[] inputs) throws DukeException {
        int index = Integer.parseInt(inputs[1]) - 1;
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("This task does not exist");
        }
        tasks.get(index).markDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(index));
    }

    public void handleDelete(String[] inputs) throws DukeException {
        int index = Integer.parseInt(inputs[1]) - 1;
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("This task does not exist");
        }
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(index));
        tasks.remove(index);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void list() {
        System.out.println("Task list");
        int i = 1;
        for (Task task : tasks) {
            System.out.println(i++ + ". " + task);
        }
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }
}
