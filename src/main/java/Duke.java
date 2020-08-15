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
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String command = input.split(" ")[0];
            switch (command) {
                case "list":
                    int id = 1;
                    String output = "Here are the tasks in your list:";
                    for (Task task : tasks) {
                        output += "\n" + tab + id + ". " + task;
                        id++;
                    }
                    chatPrint(output);
                    break;
                case "done":
                    int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task doneTask = tasks.get(idx).done();
                    tasks.set(idx, doneTask);
                    chatPrint("Nice! I've marked this task as done:\n" +
                            tab + "   " + doneTask);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    Task newTask = addTask(tasks, command, input.substring(input.indexOf(' ') + 1));
                    chatPrint("Got it. I've added this task:\n" +
                            tab + "   " + newTask + "\n" +
                            tab + "Now you have " + tasks.size() + " tasks in the list.");
            }
            input = sc.nextLine();
        }
        chatPrint("Bye. Hope to see you again soon!");
    }

    public static void chatPrint(String toPrint) {
        String tab = "     ";
        String line = "____________________________________________________________";
        System.out.println(tab + line);
        System.out.println(tab + toPrint);
        System.out.println(tab + line + "\n");
    }

    public static Task addTask(List<Task> tasks, String type, String info) {
        Task newTask;
        switch(type) {
            case "todo":
                newTask = new Todo(info);
                break;
            case "deadline":
                String dDesc = info.substring(0, info.indexOf('/'));
                String by = info.substring(info.indexOf('/') + 4);
                newTask = new Deadline(dDesc, by);
                break;
            default:
                String eDesc = info.substring(0, info.indexOf('/'));
                String at = info.substring(info.indexOf('/') + 4);
                newTask = new Event(eDesc, at);
                break;
        }
        tasks.add(newTask);
        return newTask;
    }
}
