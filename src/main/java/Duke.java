import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();

    public static void addToList(Task task) {
        list.add(task);
        System.out.println("New task added!");
        System.out.println(task);
        System.out.println("You now have " + list.size() + " tasks.");
    }

    public static void readList() {
        System.out.println("Here's all your tasks to complete:");
        int i = 1;
        for (Task ele : list) {
            System.out.println(i + ". " + ele);
            i++;
        }
        System.out.println("Time to get to work! :D");
    }

    public static void setDoneTask(int i) {
        Task doneTask = list.get(i - 1);
        doneTask.markAsDone();
        System.out.println("Task marked as done! Good job!");
        System.out.println(doneTask);
    }

    public static void manageTask(String command) {
        String taskType = command.split(" ")[0];
        String taskName;
        if (taskType.equals("todo")) {
            taskName = command.substring(command.indexOf("todo") + 5);
            Todo todo = new Todo(taskName);
            addToList(todo);
        } else if (taskType.equals("deadline")) {
            taskName = command.substring(command.indexOf("deadline") + 9);
            taskName = taskName.substring(0, taskName.indexOf("/by") - 1);
            String by = command.split("/by ")[1];
            Deadline deadline = new Deadline(taskName, by);
            addToList(deadline);
        } else if (taskType.equals("event")) {
            taskName = command.substring(command.indexOf("event") + 6);
            taskName = taskName.substring(0, taskName.indexOf("/at") - 1);
            String at = command.split("/at ")[1];
            Event event = new Event(taskName, at);
            addToList(event);
        } else {
            System.out.println("Wrong input!");
        }
    }

    public static void main(String[] args) {
        String logo = "_________     _____  .______________\n"
                + "\\_   ___ \\   /  _  \\ |   \\__    ___/\n"
                + "/    \\  \\/  /  /_\\  \\|   | |    |   \n"
                + "\\     \\____/    |    \\   | |    |   \n"
                + " \\______  /\\____|__  /___| |____|   \n"
                + "        \\/         \\/               \n";
        System.out.println("Hi! I'm\n" + logo);
        System.out.println("What can I help you with?");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye! Let's talk again soon!");
                sc.close();
                break;
            } else if (command.equals("list")) {
                readList();
            } else if (command.split(" ")[0].equals("done")) {
                setDoneTask(parseInt(command.split(" ")[1]));
            } else {
                //Task task = new Task(command);
                //addToList(task);
                //System.out.println("added: " + command);
                manageTask(command);
            }
        }
    }
}
