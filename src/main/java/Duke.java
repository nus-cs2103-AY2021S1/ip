import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String tab = "     ";
        chatPrint("Hello! I'm Duke\n" +
                tab + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        while (true) {
            String input = sc.nextLine();
            String command = input.split(" ")[0];
            if (command.equals("bye")) {
                chatPrint("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                int id = 1;
                String output = "Here are the tasks in your list:";
                for (Task task : tasks) {
                    output += "\n" + tab + id + ". " + task;
                    id++;
                }
                chatPrint(output);
            } else if (command.equals("done")) {
                int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                Task doneTask = tasks.get(idx).done();
                tasks.set(idx, doneTask);
                chatPrint("Nice! I've marked this task as done:\n" +
                        tab + "   " + doneTask);
            } else {
                Task newTask = addTask(tasks, command, input.substring(input.indexOf(' ') + 1));
                chatPrint("Got it. I've added this task:\n" +
                        tab + "   " + newTask + "\n" +
                        tab + "Now you have " + tasks.size() + " tasks in the list.");
            }
        }
    }

    public static void chatPrint(String toPrint) {
        String tab = "     ";
        String line = "____________________________________________________________";
        System.out.println(tab + line);
        System.out.println(tab + toPrint);
        System.out.println(tab + line + "\n");
    }

    public static Task addTask(List<Task> tasks, String type, String info) {
        Task newTask = new Task(info);
        tasks.add(newTask);
        return newTask;
    }
}
