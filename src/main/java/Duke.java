import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    Scanner scanner;
    List<Task> taskList;

    public Duke() {
        scanner = new Scanner(System.in);
        taskList = new ArrayList<>();
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
        String[] taskOutputs = new String[taskList.size()];
        for (int i = 0; i < taskOutputs.length; i++) {
            taskOutputs[i] = taskList.get(i).toString();
        }
        writeOutput(taskOutputs);
    }

    public boolean processInput(String input) {
        if (input.equals("bye")) {
            exit();
            return false;
        } else if (input.equals("list")) {
            listTasks();
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

        duke.writeOutput("Hello! I'm Duke", "What can I do for you?");
        String input;
        boolean keepGoing = true;
        while (keepGoing) {
            input = duke.readInput();
            keepGoing = duke.processInput(input);
        }
    }
}
