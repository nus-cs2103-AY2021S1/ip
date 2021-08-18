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
        for(Task task: taskList) {
            System.out.println(numb + ". " + task.printName());
            numb++;
        }
    }
}
