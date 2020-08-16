public class TaskManager {
    Task[] tasks; // a list of tasks
    private int size;

    public TaskManager() {
        tasks = new Task[100]; // assume the maximum number of tasks is 100
        size = 0; // initialize size to 0
    }

    public void printList() {
        for (int i = 0; i < size; ++i) {
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
    }

    public void markTaskAsDone(int index) {
        tasks[index - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[index - 1]);
    }

    public void addTask(String content) {
        tasks[size] = new Task(content);
        System.out.println("added:" + tasks[size++].toString());
    }
}
