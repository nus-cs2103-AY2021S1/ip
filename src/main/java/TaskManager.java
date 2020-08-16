public class TaskManager {
    Task[] tasks; // a list of tasks
    private int size;

    public TaskManager() {
        tasks = new Task[100]; // assume the maximum number of tasks is 100
        size = 0; // initialize size to 0
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < size; ++i) {
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
    }

    public void markTaskAsDone(int index) {
        tasks[index - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + tasks[index - 1]);
    }

    public void addTask(String content, String status, String time) {
        tasks[size] = new Task(content, status, time);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + tasks[size++].toString());
        System.out.println(String.format("Now you have %s %s in the list.", size, (size > 1 ? "tasks" : "task")));
    }

    public void addTask(String content, String status) {
        tasks[size] = new Task(content, status);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + tasks[size++].toString());
        System.out.println(String.format("Now you have %s %s in the list.", size, (size > 1 ? "tasks" : "task")));
    }
}
