import java.util.ArrayList;

class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public static void createTask(ArrayList<Task> tasks, String task) {
        ToDos todo = new ToDos(task);
        tasks.add(todo);
        String str = "   ____________________________________________________________"
                + "\n    Got it. I've added this task:"
                + "\n      " + tasks.get(tasks.size() - 1)
                + "\n    Now you have " + tasks.size() + " tasks in the list."
                + "\n   ____________________________________________________________\n";
        System.out.println(str);
    }

    public static void invalid_input() {
        invalid_input("Missing description!\n" +
                "    Did you follow the below format???\n" +
                "    todo <task description>");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
