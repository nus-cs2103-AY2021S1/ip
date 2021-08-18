import java.util.ArrayList;

public class List {
    private ArrayList<Task> taskList;

    public List() {
        this.taskList = new ArrayList<Task>();
    }

    public void addTask(String userInput) {
        taskList.add(new Task(userInput));
    }

    public void printList() {
        int numb = 1;
        System.out.println("Here are the tasks in your list:");
        for(Task task: taskList) {
            System.out.println(numb + ". " + task.printName());
            numb++;
        }
    }

    public void updateTaskStatus(int userInput) {
        if (userInput > taskList.size()) {
            System.out.println("please enter a number that's between 1 and " + taskList.size());
            return ;
        }
        Task task = taskList.get(userInput - 1);
        task.toggleComplete();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.printName());
    }
}
