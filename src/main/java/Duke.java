import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcome = "Hello. I am Claude! What may I do for you today?";
        String goodbye = "Goodbye! Hope to see you again soon!";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println(welcome);
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                printList(taskList);
            } else if (command.startsWith("done ")) {
                String[] temp = command.split(" ");
                int doneIndex = Integer.parseInt(temp[1]) - 1;
                markTaskDone(doneIndex, taskList);
            } else {
                addNewTask(command, taskList);
            }
        }
        System.out.println(goodbye);
    }

    private static void addNewTask(String description, ArrayList<Task> taskList) {
        Task task = new Task(description);
        taskList.add(task);
        System.out.println("\n Task added: " + description);
    }

    private static void markTaskDone(int taskIndex, ArrayList<Task> taskList) {
        Task doneTask = taskList.get(taskIndex);
        doneTask.markAsDone();
        System.out.println("The following task has been marked as done: \n" + doneTask);
    }

    private static void printList(ArrayList<Task> al) {
        if (al.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            String s = "";
            for (int i = 0; i < al.size(); i++) {
                s = s + (i + 1) + ". " + al.get(i) + "\n";
            }
            System.out.println(s);
        }
    }
}
