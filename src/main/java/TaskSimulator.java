import java.util.ArrayList;

public class TaskSimulator {
    private static ArrayList<Task> listOfTasks;
    private static final String line = "     _____________________________________";

    public TaskSimulator() {
        listOfTasks = new ArrayList<>();
    }

    public void getList() {
        int noOfTasks = listOfTasks.size();
        if (noOfTasks == 0) {
            System.out.println(line);
            System.out.println("     There is no task in the list yet!");
            System.out.println(line);
        } else {
            System.out.println(line);
            for (int i = 0; i < noOfTasks; i++) {
                System.out.println("     " + (i + 1) + ". " + listOfTasks.get(i).getName());
            }
            System.out.println(line);
        }
    }

    public void addTask(Task newTask) {
        listOfTasks.add(newTask);
        System.out.println(line);
        System.out.println("     The task: [" + newTask.getName() + "] is added into the list!");
        System.out.println(line);
    }

}
