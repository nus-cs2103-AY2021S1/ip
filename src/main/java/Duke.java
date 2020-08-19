import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    ArrayList<Task> tasks;

    String addTaskLine;
    String doneTaskLine;
    String listTaskLine;
    String byeLine;
    String errorLine;
    String indent;

    Duke() {
        tasks = new ArrayList<>();
        addTaskLine = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
        doneTaskLine = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        listTaskLine = "________________________________________________________";
        byeLine = "========================================================";
        errorLine = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        indent = "    ";
    }
    
    void displayNoDescriptionError(String input) {
        DukeException e = new DukeNoDescriptionException(input);
        System.out.println(
                indent + errorLine + "\n"
                + indent + e.toString() + "\n"
                + indent + errorLine
        );
    }
    
    void displayAddTaskSuccess(Task task, int taskCount) {
        System.out.println(
                indent + addTaskLine + "\n"
                + indent + "Added task:" +"\n"
                + indent + indent + task.toString() + "\n"
                + indent + "You now have " + taskCount + " task(s) in the list.\n"
                + indent + addTaskLine
        );
    }

    public boolean handleInput(String input) {
        String[] processedInput = input.split(" ");
        if (input.equals("bye")) {
            exit();
            return true;
        } else if (input.equals("list")) {
            System.out.println(indent + listTaskLine);
            for (Task task : tasks) {
                System.out.println(indent + (tasks.indexOf(task) + 1) + "." + task.toString()
                );
            }
            if (tasks.size() == 0) {
                System.out.println(indent + "None");
            }
            System.out.println(indent + listTaskLine);
            return false;
        } else if (processedInput[0].equals("done")) {
            int index = Integer.parseInt(processedInput[1]);
            Task task = tasks.get(index - 1);
            task.setDone();
            System.out.println(
                    indent + doneTaskLine + "\n"
                    + indent + "The following task has been marked as done:\n"
                    + indent + task.toString()
                    + "\n" + indent + doneTaskLine
            );
            return false;
        } else if (processedInput[0].equals("todo")) {
            if (processedInput.length == 1) {
                displayNoDescriptionError(input);
                return false;
            }
            String taskDetails = input.substring(5);
            Task task = new ToDo(taskDetails);
            tasks.add(task);
            displayAddTaskSuccess(task, tasks.size());
            return false;
        } else if (processedInput[0].equals("deadline")) {
            if (processedInput.length == 1) {
                displayNoDescriptionError(input);
                return false;
            }
            String[] taskDetails = input.substring(9).split(" /by ");
            Task task = new Deadline(taskDetails[0], taskDetails[1]);
            tasks.add(task);
            displayAddTaskSuccess(task, tasks.size());
            return false;
        } else if (input.startsWith("event")) {
            if (processedInput.length == 1) {
                displayNoDescriptionError(input);
                return false;
            }
            String[] taskDetails = input.substring(6).split(" /at ");
            Task task = new Event(taskDetails[0], taskDetails[1]);
            tasks.add(task);
            System.out.println(
                    indent + addTaskLine + "\n"
                    + indent + "Added task:" +"\n"
                    + indent + indent + task.toString() + "\n"
                    + indent + "You now have " + tasks.size() + " task(s) in the list.\n"
                    + indent + addTaskLine
            );
            return false;
        } else {
            DukeException e = new DukeUnknownCommandException(input);
            System.out.println(
                    indent + errorLine + "\n"
                    + indent + e.toString() + "\n"
                    + indent + errorLine
            );
            return false;
        }
    }

    void exit() {
        System.out.println(
            indent + byeLine + "\n"
            + indent + "Goodbye\n"
            + indent + byeLine
        );
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello my name\n" + logo + "\nHow may I help?");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            boolean exit = duke.handleInput(input);
            if (exit) {
                scanner.close();
                System.exit(0);
            }
        }
    }
}
