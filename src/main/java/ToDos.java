import java.util.ArrayList;

public class ToDos extends Task {
    public ToDos(String taskTitle) {
        super(taskTitle);
    }

    public static void addTodoTask(String taskTitle, ArrayList<Task> tasks) {
        ToDos newTodoTask = new ToDos(taskTitle);
        tasks.add(newTodoTask);

        String outputMsg = "\n___________________________________________________________"
                + "\n (^.^)"
                + "\n Got it. I've added this task:"
                + "\n     " + tasks.size() + "." + tasks.get(tasks.size() - 1)
                + "\n Now you have " + tasks.size() + " tasks in the list."
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
