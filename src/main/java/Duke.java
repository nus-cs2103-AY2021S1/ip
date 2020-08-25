import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private static final String HORIZONTAL_RULE = "____________________________________________________________";
    private static Scanner sc;
    private static List<Task> itemsList;
    private static DataStorage data = null;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        itemsList = new ArrayList<>();
        try {
            data = new DataStorage("./data/tasks.txt");
        } catch (IOException e) {
            System.err.println(e);
        }

        try {
            if (data != null) {
                itemsList = data.getTaskList();
            }
        } catch (FileNotFoundException | DukeException e) {
            System.err.println(e);
        }

        executeProgramme();
    }

    private static void executeProgramme() {

        Printer.displayStart();

        String command = sc.next();

        while (!command.equals(TaskType.TERMINATION.toString())) {

            try {
                switch (command) {
                case "list":
                    Printer.printList(itemsList);
                    break;
                case "done":
                    Printer.displayCompleteTask();

                    int index = sc.nextInt() - 1;
                    itemsList.get(index).doTask();
                    break;
                case "todo":
                    handleTodo();
                    break;
                case "deadline":
                    handleDeadline();
                    break;
                case "event":
                    handleEvent();
                    break;
                case "delete":
                    handleDelete();
                    break;
                default:
                    throw DukeException.badCommand();
                }
            } catch (DukeException e) {
                System.err.println(e);
            }

            command = sc.next();
        }

        Printer.displayExit();
    }



    private static void handleTodo() throws DukeException {
        addAndPrintTask(ToDo.makeTaskFromInput(sc.nextLine()));
    }

    private static void handleDeadline() throws DukeException {
        String taskName = sc.nextLine();
        if (taskName.isBlank()) {
            throw DukeException.badDeadlineTask();
        }

        // String array whose first element is the task and second element is the deadline
        // substring(1) to remove the starting space
        String[] taskAndDeadline = taskName.substring(1).split(" /by ");
        if (taskAndDeadline.length < 2) {
            throw DukeException.badDeadlineDate();
        }

        addAndPrintTask(Deadline.makeTaskFromInput(taskAndDeadline[0], taskAndDeadline[1]));
    }

    private static void handleEvent() throws DukeException {
        String taskName = sc.nextLine();
        if (taskName.isBlank()) {
            throw DukeException.badEventTask();
        }

        // String whose first element is task and second element is time of event
        String[] eventAndTime = taskName.substring(1).split(" /at ");
        if (eventAndTime.length < 2) {
            throw DukeException.badEventDate();
        }

        addAndPrintTask(Event.makeTaskFromInput(eventAndTime[0], eventAndTime[1]));
    }

    private static void handleDelete() {
        int taskIndex = sc.nextInt() - 1;
        Task removedTask = itemsList.remove(taskIndex);
        Printer.displayDeleteTask(removedTask, itemsList.size());
    }

    private static void addAndPrintTask(Task task) {
        itemsList.add(task);
        try {
            data.writeTask(task);
        } catch (IOException e) {
            System.err.println(e);
        }
        Printer.displayAddTask(task, itemsList.size());
    }
}