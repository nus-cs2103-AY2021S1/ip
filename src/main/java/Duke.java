import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

    private LocalDate validateDateTime(String time) throws DukeException {
        if (time.equals("")) {
            throw new DukeException("Task date cannot be empty.");
        }
        LocalDate parsed;
        try {
            parsed = LocalDate.parse(time);
            return parsed;
        } catch (DateTimeParseException ex) {
            throw new DukeException("Invalid date entered. Use format YYYY-MM-DD");
        }
    }

    private void addTask(String task) {
        addTask(new Todo(task));
    }

    private void addTask(String task, boolean isEvent) throws DukeException {
        Task newTask;
        String[] taskSplit;
        if (isEvent) {
            taskSplit = task.split("/at");
            if (taskSplit.length != 2) {
                throw new DukeException("Invalid description for an event. Use /at followed by date");
            }
            LocalDate dateTime = validateDateTime(taskSplit[1].strip());
            newTask = new Event(taskSplit[0].strip(), dateTime);
        } else {
            taskSplit = task.split("/by");
            if (taskSplit.length != 2) {
                throw new DukeException("Invalid description for a deadline. Use /by followed by date");
            }
            LocalDate dateTime = validateDateTime(taskSplit[1].strip());
            newTask = new Deadline(taskSplit[0].strip(), dateTime);
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

    private void markDone(int position) throws DukeException {
        if (position < 0 || position > taskList.size()) {
            throw new DukeException("Invalid task number provided");
        }
        Task task = taskList.get(position - 1);
        task.markDone();
        writeOutput("Nice! I've marked this task as done:", "\t" + task.toString());
    }

    private void deleteTask(int position) throws DukeException {
        if (position < 0 || position > taskList.size()) {
            throw new DukeException("Invalid task number provided");
        }
        Task task = taskList.remove(position - 1);
        writeOutput("Noted. I've removed this task:", "\t" + task.toString(),
                String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    public boolean processInput(String input) throws DukeException {
        if (Commands.BYE.check(input)) {
            exit();
            return false;
        } else if (Commands.LIST.check(input)) {
            listTasks();
        } else {
            String[] inputSplit = input.split(" ", 2);

            if (Commands.TODO.check(inputSplit[0])) {
                if (inputSplit.length < 2) {
                    throw new DukeException("The description of a todo cannot be empty");
                }
                addTask(inputSplit[1]);
            } else if (Commands.DEADLINE.check(inputSplit[0])) {
                if (inputSplit.length < 2) {
                    throw new DukeException("The description of a deadline cannot be empty");
                }
                addTask(inputSplit[1], false);
            } else if (Commands.EVENT.check(inputSplit[0])) {
                if (inputSplit.length < 2) {
                    throw new DukeException("The description of an event cannot be empty");
                }
                addTask(inputSplit[1], true);
            } else if (Commands.DONE.check(inputSplit[0])) {
                if (inputSplit.length < 2) {
                    throw new DukeException("Task number cannot be empty");
                }
                try {
                    markDone(Integer.parseInt(inputSplit[1]));
                } catch (NumberFormatException ex) {
                    throw new DukeException("Task number must be a valid integer");
                }
            } else if (Commands.DELETE.check(inputSplit[0])) {
                if (inputSplit.length < 2) {
                    throw new DukeException("Task number cannot be empty");
                }
                try {
                    deleteTask(Integer.parseInt(inputSplit[1]));
                } catch (NumberFormatException ex) {
                    throw new DukeException("Task number must be a valid integer");
                }
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :(");
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
            try {
                keepGoing = duke.processInput(input);
            } catch (DukeException de) {
                duke.writeOutput(de.getMessage());
            }
        }
    }
}
