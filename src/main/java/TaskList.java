import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> todoList;
    private Ui ui;

    public TaskList() {
        this.todoList = new ArrayList<>();
        this.ui = new Ui();
    }

    public void list() {
        this.ui.generateDivider();
        for (int i = 0; i < todoList.size(); i++) {
            int index = i + 1;
            this.ui.generateLeftPadding();
            System.out.println(index + ". " + todoList.get(i).toString());
        }
        this.ui.generateDivider();
    }

    public Task get(int index) {
        return this.todoList.get(index);
    }

    public void remove(int index) {
        this.todoList.remove(index);
    }

    public int getSize() {
        return this.todoList.size();
    }

    public void addTask(String isCompleted, String task) {
        Task newTask = new Task(isCompleted, task);
        this.todoList.add(newTask);
        System.out.println("added: " + task);
    }

    public void addTodo(String isCompleted, String task) {
        Todo newTodo = new Todo(isCompleted, task);
        this.todoList.add(newTodo);
    }

    public void addDeadline(String isCompleted, String task, String deadline) {
        Deadline newDeadline = new Deadline(isCompleted, task, deadline);
        this.todoList.add(newDeadline);
    }

    public void addEvent(String isCompleted, String task, String eventDate) {
        Event newEvent = new Event(isCompleted, task, eventDate);
        this.todoList.add(newEvent);
    }
}
