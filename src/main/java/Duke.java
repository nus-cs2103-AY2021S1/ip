import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static String numTasks(List<Task> lst) {
        int numTasks = lst.size();
        return numTasks == 1 ? "1 task" : numTasks + " tasks";
    }

    public static void main(String[] args) {
        System.out.println("————————————————————————————————————————————————————————————");
        System.out.println("Hello! I'm Duke!\nWhat can I do for you?");
        System.out.println("————————————————————————————————————————————————————————————");

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            System.out.println("————————————————————————————————————————————————————————————");
            if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i).toString());
                }
            } else {
                String[] command_lst = command.split(" ", 2);
                
                if (command_lst[0].equals("done")) {
                    try {
                        int taskId = Integer.parseInt(command.split(" ")[1]) - 1;
                        tasks.get(taskId).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks.get(taskId).toString());
                    } catch (Exception e) {
                        new DukeException("invalidMarkingDone");
                    }
                } else if (command_lst[0].equals("todo")) {
                    try {
                        Task newTask = new ToDo(command_lst[1]);
                        tasks.add(newTask);
                        System.out.println("Got it. I've added this task:\n" + newTask.toString());
                        System.out.println("Now you have " + numTasks(tasks) + " in the list.");
                    } catch (Exception e) {
                        new DukeException("invalidTodo");
                    }
                } else if (command_lst[0].equals("deadline")) {
                    try {
                        String desc = command_lst[1].split(" /by ")[0];
                        String by = command_lst[1].split(" /by ")[1];
                        Task newTask = new Deadline(desc, by);
                        tasks.add(newTask);
                        System.out.println("Got it. I've added this task: \n" + newTask.toString());
                        System.out.println("Now you have " + numTasks(tasks) + " in the list.");
                    } catch (Exception e) {
                        new DukeException("invalidDeadline");
                    }
                } else if (command_lst[0].equals("event")) {
                    try {
                        String desc = command_lst[1].split(" /at ")[0];
                        String at = command_lst[1].split(" /at ")[1];
                        Task newTask = new Event(desc, at);
                        tasks.add(newTask);
                        System.out.println("Got it. I've added this task: \n" + newTask.toString());
                        System.out.println("Now you have " + numTasks(tasks) + " in the list.");
                    } catch (Exception e) {
                        new DukeException("invalidEvent");
                    }
                } else {
                    new DukeException("invalidCommand");
                }
            }
            
            System.out.println("————————————————————————————————————————————————————————————");
            command = sc.nextLine();
        }

        System.out.println("————————————————————————————————————————————————————————————");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("————————————————————————————————————————————————————————————");
        sc.close();
    }
}
