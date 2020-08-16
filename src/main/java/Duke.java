import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String LINES = "____________________\n";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>(100);
        String farewell = "Sad to see you go!\n";

        System.out.println(LINES
                + "Hello, I'm your chatbot!\n"
                + "What can I do for you?\n"
                + LINES);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(LINES + farewell + LINES);
                break;
            } else if (input.equals("list")) {
                list(tasks);
            } else if (input.startsWith("done")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                completeTask(tasks, index);
            } else {
                addTask(tasks, input);
            }
        }
    }

    private static void list(List<Task> tasks) {
        System.out.println(LINES + "Here are the tasks in your list:");
        int number = 1;
        for (Task task : tasks) {
            System.out.println(String.format("%d.", number++) + task);
        }
        System.out.println(LINES);
    }

    private static void completeTask(List<Task> tasks, int index) {
        Task task = tasks.get(index);
        task.setDone();
        System.out.println(LINES
                + "Well done! The following task is complete:\n" + task
                + "\n" + LINES);
    }

    private static void addTask(List<Task> tasks, String task) {
        Task newTask = null;

        if (task.startsWith("todo")) {
            newTask = new ToDo(task.substring(5));
        } else if (task.startsWith("deadline")) {
            String[] taskInfo = task.substring(9).split(" /by ", 2);
            newTask = new Deadline(taskInfo[0], taskInfo[1]);
        } else if (task.startsWith("event")) {
            String[] taskInfo = task.substring(6).split(" /at ", 2);
            newTask = new Event(taskInfo[0], taskInfo[1]);
        }

        if (newTask != null) {
            tasks.add(newTask);
            System.out.println(LINES +
                    "Got it, I have added this task:\n"
                    + newTask
                    + "\nNow you have " + tasks.size() + " task(s) in this list\n"
                    + LINES);
        }
    }
}