import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    Scanner scanner;
    List<Task> taskList;

    public Duke() {
        scanner = new Scanner(System.in);
        taskList = new ArrayList<>();
        writeOutput("Hello! I'm Duke", "What can I do for you?");
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void writeOutput(String... messages) {
        System.out.println("\t-----------------------------------------");
        for (String message : messages) {
            System.out.println("\t" + message);
        }
        System.out.println("\t-----------------------------------------");
    }

    private void addTask(String task) {
        taskList.add(new Task(task, taskList.size() + 1));
        writeOutput("added: " + task);
    }

    private void listTasks() {
        String[] taskOutputs = new String[taskList.size() + 1];
        taskOutputs[0] = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            taskOutputs[i + 1] = taskList.get(i).toString();
        }
        writeOutput(taskOutputs);
    }

    private void markDone(int position) {
        Task task = taskList.get(position - 1);
        task.markDone();
        String[] outputs = new String[] { "Nice! I've marked this task as done:",
                "\t" + task.toString() };
        writeOutput(outputs);
    }

    public boolean processInput(String input) {
        if (input.equals("bye")) {
            exit();
            return false;
        } else if (input.equals("list")) {
            listTasks();
        } else if (input.startsWith("done ")) {
            markDone(Integer.parseInt(input.split(" ")[1]));
        } else {
            addTask(input);
        }
        return true;
    }

    public void exit() {
        writeOutput("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String input;
        boolean keepGoing = true;
        while (keepGoing) {
            input = duke.readInput();
            keepGoing = duke.processInput(input);
        }
    }
}
