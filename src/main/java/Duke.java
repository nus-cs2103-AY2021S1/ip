import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Duke {
    ArrayList<Task> taskList;

    Duke() {
        this.taskList = new ArrayList<>();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initialise();
    }

    private void initialise() {
        Scanner sc = new Scanner(System.in);
        greet();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                list();
            } else if (input.startsWith("done ")) {
                int indexOfDoneTask = Integer.parseInt(input.substring(5));
                markAsDone(indexOfDoneTask);
            } else {
                add(input);
            }
        }
        exit();
    }

    private void greet() {
        System.out.println("    ______________________________________________________\n" +
                "     Hello! I'm Duke \n     What can I do for you?\n" +
                "    ______________________________________________________");
    }

    private void echo(String command) {
        System.out.println("    ______________________________________________________\n" +
                "     " + command + "\n" +
                "    ______________________________________________________");
    }

    private void exit() {
        System.out.println("    ______________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ______________________________________________________");
    }

    private void add(String input) {
        Task newTask = new Task(input);
        taskList.add(newTask);
        System.out.println("    ______________________________________________________\n" +
                "     added: " + newTask.getDesc() + "\n" +
                "    ______________________________________________________");
    }

    private void list() {
        System.out.println("    ______________________________________________________\n" +
                "     Here are the tasks in your list:");
        ListIterator<Task> listIterator = taskList.listIterator();
        int i = 1;
        while (listIterator.hasNext()) {
            Task t = listIterator.next();
            System.out.println("     " + i + ". [" + t.getStatusIcon() + "] " + t.getDesc());
            i++;
        }
        System.out.println("    ______________________________________________________");
    }

    private void markAsDone(int indexOfDoneTask) {
        Task doneTask = taskList.get(indexOfDoneTask - 1);
        doneTask.markAsDone();
        System.out.println("    ______________________________________________________\n" +
                "     Nice! I've marked this task as done:\n" +
                "       [" + doneTask.getStatusIcon() + "] " + doneTask.getDesc() + "\n" +
                "    ______________________________________________________");
    }
}
