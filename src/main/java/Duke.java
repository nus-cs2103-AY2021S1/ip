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
        addTask(new Todo(task));
    }

    private void addTask(String task, boolean isEvent) {
        Task newTask;
        String[] taskSplit;
        if (isEvent) {
            taskSplit = task.split("/at");
            newTask = new Event(taskSplit[0].strip(), taskSplit[1].strip());
        } else {
            taskSplit = task.split("/by");
            newTask = new Deadline(taskSplit[0].strip(), taskSplit[1].strip());
        }
        addTask(newTask);
    }

    private void addTask(Task task) {
        taskList.add(task);
        writeOutput("Got it. I've added this task:", task.toString(),
                String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    private void listTasks() {
        String[] taskOutputs = new String[taskList.size() + 1];
        taskOutputs[0] = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            taskOutputs[i + 1] = (i + 1) + ". " + taskList.get(i).toString();
        }
        writeOutput(taskOutputs);
    }

    private void markDone(int position) {
        Task task = taskList.get(position - 1);
        task.markDone();
        writeOutput("Nice! I've marked this task as done:", "\t" + task.toString());
    }

    public boolean processInput(String input) {
        if (input.equals("bye")) {
            exit();
            return false;
        } else if (input.equals("list")) {
            listTasks();
        } else {
            String[] inputSplit = input.split(" ", 2);
            if (inputSplit[0].equals("todo")) {
                addTask(inputSplit[1]);
            } else if (inputSplit[0].equals("deadline")) {
                addTask(inputSplit[1], false);
            } else if (inputSplit[0].equals("event")) {
                addTask(inputSplit[1], true);
            } else if (inputSplit[0].equals("done")){
                markDone(Integer.parseInt(inputSplit[1]));
            }
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
