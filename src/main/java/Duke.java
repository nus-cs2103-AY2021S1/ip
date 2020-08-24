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
    
    void displayError(String errorMessage) {
        System.out.println(
                indent + errorLine + "\n"
                + indent + errorMessage + "\n"
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
        if (DukeCommand.equalsCommand(input, DukeCommand.EXIT)) {
            exit();
            return true;
        } else if (DukeCommand.equalsCommand(input, DukeCommand.LIST)) {
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
        } else if (DukeCommand.equalsCommand(processedInput[0], DukeCommand.DONE)) {
            if (processedInput.length == 1) {
                DukeException e = new DukeNoItemToMarkDoneException(input);
                displayError(e.toString());
                return false;
            }
            int index = Integer.parseInt(processedInput[1]);
            if (index < 1 || index > tasks.size()) {
                DukeException e = new DukeOutOfBoundsException(input);
                displayError(e.toString());
                return false;
            }
            Task task = tasks.get(index - 1);
            task.setDone();
            System.out.println(
                    indent + doneTaskLine + "\n"
                    + indent + "The following task has been marked as done:\n"
                    + indent + task.toString()
                    + "\n" + indent + doneTaskLine
            );
            return false;
        } else if (DukeCommand.equalsCommand(processedInput[0], DukeCommand.TODO)) {
            if (processedInput.length == 1) {
                DukeException e = new DukeNoDescriptionException(input);
                displayError(e.toString());
                return false;
            }
            String taskDetails = input.substring(5);
            Task task = new ToDo(taskDetails);
            tasks.add(task);
            displayAddTaskSuccess(task, tasks.size());
            return false;
        } else if (DukeCommand.equalsCommand(processedInput[0], DukeCommand.DEADLINE)) {
            if (processedInput.length == 1) {
                DukeException e = new DukeNoDescriptionException(input);
                displayError(e.toString());
                return false;
            }
            String[] taskDetails = input.substring(9).split(" /by ");
            Task task = new Deadline(taskDetails[0], taskDetails[1]);
            tasks.add(task);
            displayAddTaskSuccess(task, tasks.size());
            return false;
        } else if (DukeCommand.equalsCommand(processedInput[0], DukeCommand.EVENT)) {
            if (processedInput.length == 1) {
                DukeException e = new DukeNoDescriptionException(input);
                displayError(e.toString());
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
        } else if (DukeCommand.equalsCommand(processedInput[0], DukeCommand.DELETE)) {
            if (processedInput.length == 1) {
                DukeException e = new DukeNoItemToDeleteException(input);
                displayError(e.toString());
                return false;
            }
            int index = Integer.parseInt(processedInput[1]);
            if (index < 1 || index > tasks.size()) {
                DukeException e = new DukeOutOfBoundsException(input);
                displayError(e.toString());
                return false;
            }
            Task task = tasks.get(index - 1);
            tasks.remove(index - 1);
            System.out.println(
                    indent + doneTaskLine + "\n"
                            + indent + "The following task has been removed:\n"
                            + indent + task.toString() + "\n"
                            + indent + "You now have " + tasks.size() + " task(s) in the list.\n"
                            + indent + doneTaskLine
            );
            return false;
        } else {
            DukeException e = new DukeUnknownCommandException(input);
            displayError(e.toString());
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
