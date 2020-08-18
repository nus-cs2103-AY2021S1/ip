import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi! I'm Duke" + "\n" + "What can I do for you?");
        String currInput = scanner.nextLine();
        List<Task> storedTasks = new ArrayList<>();

        while (isNotTerminateCommand(currInput)) {
            if (isListCommand(currInput)) {
                //List out all tasks' description
                System.out.println(readList(storedTasks));
            } else if (isDoneCommand(currInput)) {

                if (isValidDoneCommand(currInput, storedTasks.size())) {
                    String[] parts = currInput.split(" ");
                    int index = Integer.parseInt(parts[1]) - 1;

                    storedTasks.set(index, storedTasks.get(index).markDone());

                    System.out.println("Nice! I've marked this task as done:" + "\n" + "[✓] " + storedTasks.get(index).toString());
                } else {
                    System.out.println("Sorry, invalid command");
                }
            } else {
                //Is a task
               /*Task newTask = new Task(currInput);
               storedTasks.add(newTask);*/
                addTasks(currInput, storedTasks);
               System.out.println("Got it. I've added this task: " + "\n" + "  " + storedTasks.get(storedTasks.size() - 1)
               + "\n" + "Now you have " + storedTasks.size() + " tasks in the list.");
            }
                currInput = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public static boolean isDoneCommand(String input) {
        String[] parts = input.split(" ");
        return parts[0].equals("done") && parts.length == 2;
    }

    public static boolean isValidDoneCommand(String input, int numOfTasks) {
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        return index > -1 && index < numOfTasks;
    }

    public static boolean isListCommand(String input) {
        return input.equals("list");
    }

    public static boolean isNotTerminateCommand(String input) {
        return !input.equals("bye");
    }

    public static void addTasks(String input, List<Task> tasks) {
        String[] parts = input.split(" ", 2);
        String taskType = parts[0];
        Task newTask;

        if (taskType.equals("todo")) {
            newTask = new Task(parts[1]);
            tasks.add(newTask);
        } else if (taskType.equals("deadline")) {
            String[] split = parts[1].split("/by");
            String desc = split[0];
            String deadline = split[1];
            newTask = new Deadlines(split[0], split[1]);
            tasks.add(newTask);
        } else if (taskType.equals("event")) {
            String[] split = parts[1].split("/at");
            String desc = split[0];
            String startTime = split[1];
            newTask = new Events(desc, startTime);
            tasks.add(newTask);
        } else {
            System.out.println("Sorry, invalid command");
        }
    }

    public static String readList(List<Task> list) {
        String listOfTasks = "Here are the tasks in your list:";


        for (int i = 1; i <= list.size(); i++) {
            Task currTask = list.get(i - 1);
                listOfTasks += "\n" + i + "." + currTask.toString();
        }

        if (list.size() == 0) {
            return "Currently no tasks in you list";
        }
        return listOfTasks;
    }
}

