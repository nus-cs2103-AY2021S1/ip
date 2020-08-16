import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>(100);

        String lines = "____________________\n";
        String farewell = "Sad to see you go!\n";

        System.out.println(lines
                + "Hello, I'm your chatbot!\n"
                + "What can I do for you?\n"
                + lines);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(lines + farewell + lines);
                break;
            } else if (input.equals("list")) {
                System.out.println(lines + "Here are the tasks in your list:");
                int number = 1;
                for (Task task : tasks) {
                    System.out.println(String.format("%d.", number++) + task);
                }
                System.out.println(lines);
            } else if (input.startsWith("done")) {
                int toDo = Integer.parseInt(input.substring(5)) - 1;
                Task task = tasks.get(toDo);
                task.setDone();
                System.out.println(lines
                        + "Well done! The following task is complete:\n" + task
                        + "\n" + lines);
            } else {
                Task newTask = null;

                if (input.startsWith("todo")) {
                    newTask = new ToDo(input.substring(5));
                } else if (input.startsWith("deadline")) {
                    String[] taskInfo = input.substring(9).split("/by ", 2);
                    newTask = new Deadline(taskInfo[0], taskInfo[1]);
                } else if (input.startsWith("event")) {
                    String[] taskInfo = input.substring(6).split("/at ", 2);
                    newTask = new Event(taskInfo[0], taskInfo[1]);
                } else {
                    continue;
                }

                tasks.add(newTask);
                System.out.println(lines +
                        "Got it, I have added this task:\n"
                        + newTask
                        + "\nNow you have " + tasks.size() + " tasks in this list\n"
                        + lines);
            }
        }
    }
}