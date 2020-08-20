import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static boolean isNumber(char c) {
        int c_int = Character.getNumericValue(c);
        return c_int <= 9 && c_int >= 0;
    }

    public static boolean isMarkingDone(String command) {
        String[] lst = command.split(" ");

        if (lst.length == 1 || !lst[0].equals("done")) {
            return false;
        } else {
            String secondElement = lst[1];

            if (secondElement.length() == 2) {
                return isNumber(secondElement.charAt(0)) && isNumber(secondElement.charAt(1));
            } else if (secondElement.length() == 1) {
                return isNumber(secondElement.charAt(0));
            } else {
                return false;
            }
        }
    }

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
            } else if (isMarkingDone(command)) {
                int taskId = Integer.parseInt(command.split(" ")[1]) - 1;
                if (taskId < tasks.size()) {
                    tasks.get(taskId).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(taskId).toString());
                } else {
                    System.out.println("Task does not exist; Try another task number.");
                }
            } else {
                String[] command_lst = command.split(" ", 2);

                if (command_lst[0].equals("todo")) {
                    if (command_lst.length == 2) {
                        Task newTask = new ToDo(command_lst[1]);
                        tasks.add(newTask);
                        System.out.println("Got it. I've added this task:\n" + newTask.toString());
                        System.out.println("Now you have " + numTasks(tasks) + " in the list.");
                    } else {
                        System.out.println("You have entered an invalid todo.");
                    }
                } else if (command_lst[0].equals("deadline")) {
                    if (command_lst[1].contains("/by")) {
                        String desc = command_lst[1].split(" /by ")[0];
                        String by = command_lst[1].split(" /by ")[1];
                        Task newTask = new Deadline(desc, by);
                        tasks.add(newTask);
                        System.out.println("Got it. I've added this task: \n" + newTask.toString());
                        System.out.println("Now you have " + numTasks(tasks) + " in the list.");
                    } else {
                        System.out.println("You have entered an invalid deadline.");
                    }
                } else if (command_lst[0].equals("event")) {
                    if (command_lst[1].contains("/at")) {
                        String desc = command_lst[1].split(" /at ")[0];
                        String at = command_lst[1].split(" /at ")[1];
                        Task newTask = new Event(desc, at);
                        tasks.add(newTask);
                        System.out.println("Got it. I've added this task: \n" + newTask.toString());
                        System.out.println("Now you have " + numTasks(tasks) + " in the list.");
                    } else {
                        System.out.println("You have entered an invalid event.");
                    }
                } else {
                    System.out.println("You have entered an invalid command.");
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
