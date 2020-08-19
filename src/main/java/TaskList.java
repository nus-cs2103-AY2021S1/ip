import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    protected ArrayList<Task> taskList;
    protected boolean active = false;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void start() {
        active = true;
        String welcomeMessage = "____________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________________________________________\n";
        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);
        while (active) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                this.view();
            } else if (input.equals("bye")) {
                this.exit();
            } else {
                String[] inputInfo = input.split(" ", 2);
                String command = inputInfo[0];
                String info = inputInfo[1];
                if (command.equals("done")) {
                    int doneIndex = Integer.valueOf(info) - 1;
                    this.markDone(doneIndex);
                } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    this.addTask(command, info);
                }
            }
        }
        sc.close();
    }

    public void exit() {
        active = false;
        String goodbyeMessage = "____________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________\n";
        System.out.println(goodbyeMessage);
    }

    public void addTask(String type, String info) {
        Task newTask = null;
        if (type.equals("todo")) {
            newTask = new ToDo(info);
        } else if (type.equals("deadline")) {
            String[] descriptionAndBy = info.split(" /by ", 2);
            newTask = new Deadline(descriptionAndBy[0], descriptionAndBy[1]);
        } else if (type.equals("event")) {
            String[] descriptionAndAt = info.split(" /at ", 2);
            newTask = new Event(descriptionAndAt[0], descriptionAndAt[1]);
        }
        taskList.add(newTask);
        String addTaskMessage = "____________________________________________________\n" +
                         "Got it. I've added this task:\n" +
                         newTask.toString() + "\n" +
                         "Now you have " + taskList.size() + (taskList.size() > 1 ? " tasks " : " task ") + "in the list.\n" +
                         "____________________________________________________\n";
        System.out.println(addTaskMessage);
    }

    public void view() {
        String list = "____________________________________________________\n" +
                      "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String taskString = String.valueOf(i + 1) + "." + task.toString() + "\n";
            list += taskString;
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
