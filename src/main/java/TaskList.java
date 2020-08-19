import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    protected ArrayList<Task> taskList;
    protected boolean active = false;
    protected String welcomeMessage = "____________________________________________________\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            "____________________________________________________\n";
    protected String goodbyeMessage = "____________________________________________________\n" +
            "Bye. Hope to see you again soon!\n" +
            "____________________________________________________\n";

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void start() {
        active = true;
        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);
        while (active) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                this.view();
            } else if (input.equals("bye")) {
                this.exit();
            } else if (input.split(" ")[0].equals("done")) {
                int doneIndex = Integer.valueOf(input.split(" ")[1]) - 1;
                this.markDone(doneIndex);
            } else {
                this.addTask(input);
            }
        }
        sc.close();
    }

    public void exit() {
        active = false;
        System.out.println(goodbyeMessage);
    }

    public void addTask(String description) {
        Task newTask = new Task(description);
        taskList.add(newTask);
        String addTaskText = "____________________________________________________\n" +
                         "added: " + description + "\n" +
                         "____________________________________________________\n";
        System.out.println(addTaskText);
    }

    public void view() {
        String list = "____________________________________________________\n" +
                      "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String taskText = String.valueOf(i + 1) + ".[" +  task.getStatusIcon() + "] " + task.getDescription() + "\n";
            list += taskText;
        }
        list += "____________________________________________________\n";
        System.out.println(list);
    }

    public void markDone(int i) {
        Task task = taskList.get(i);
        task.makeDone();
        String markDoneMessage = "____________________________________________________\n" +
                "Nice! I've marked this task as done:\n" +
                "[\u2713] " + task.getDescription() + "\n" +
                "____________________________________________________\n";
        System.out.println(markDoneMessage);
    }
}
