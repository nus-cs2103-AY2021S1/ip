import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private static final String HORIZONTAL_RULE = "____________________________________________________________";
    private static Scanner sc;
    private static List<Task> itemsList;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        itemsList = new ArrayList<>();
        executeProgramme();
    }

    private static void executeProgramme() {
        final String TERMINATION_PHRASE = "bye";
        final String LIST_PHRASE = "list";
        final String COMPLETE_TASK_PHRASE = "done";
        final String TODO_PHRASE = "todo";
        final String DEADLINE_PHRASE = "deadline";
        final String EVENT_PHRASE = "event";
        final String DELETE_PHRASE = "delete";

        Printer.displayStart();

        String command = sc.next();

        while (!command.equals(TERMINATION_PHRASE)) {

            try {
                switch (command) {
                    case LIST_PHRASE:
                        Printer.printList(itemsList);
                        break;
                    case COMPLETE_TASK_PHRASE:
                        Printer.displayCompleteTask();

                        int index = sc.nextInt() - 1;
                        itemsList.get(index).doTask();
                        break;
                    case TODO_PHRASE:
                        handleTodo();
                        break;
                    case DEADLINE_PHRASE:
                        handleDeadline();
                        break;
                    case EVENT_PHRASE:
                        handleEvent();
                        break;
                    case DELETE_PHRASE:
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
        String taskName = sc.nextLine();
        if (taskName.isBlank()) {
            throw DukeException.badToDo();
        }
        Task newTask = new ToDo(taskName);
        itemsList.add(newTask);
        Printer.displayAddTask(newTask, itemsList.size());
    }

    private static void handleDeadline() throws DukeException {
        String taskName = sc.nextLine();
        if (taskName.isBlank()) {
            throw DukeException.badDeadlineTask();
        }

        // String array whose first element is the task and second element is the deadline
        // substring(1) to remove the starting space
        String[] taskAndDeadline = taskName.substring(1).split(" /by ");

        if (taskAndDeadline[0].isBlank()) {
            throw DukeException.badDeadlineTask();
        } else if (taskAndDeadline.length < 2 || taskAndDeadline[1].isBlank()) {
            throw DukeException.badDeadlineDate();
        }

        Task newTask = new Deadline(taskAndDeadline[0], taskAndDeadline[1]);
        itemsList.add(newTask);
        Printer.displayAddTask(newTask, itemsList.size());
    }

    private static void handleEvent() throws DukeException {
        String taskName = sc.nextLine();
        if (taskName.isBlank()) {
            throw DukeException.badEventTask();
        }

        // String whose first element is task and second element is time of event
        String[] eventAndTime = taskName.substring(1).split(" /at ");
        if (eventAndTime[0].isBlank()) {
            throw DukeException.badEventTask();
        } else if (eventAndTime.length < 2 || eventAndTime[1].isBlank()) {
            throw DukeException.badEventDate();
        }

        Task newTask = new Event(eventAndTime[0], eventAndTime[1]);
        itemsList.add(newTask);
        Printer.displayAddTask(newTask, itemsList.size());
    }

    private static void handleDelete() {
        int taskIndex = sc.nextInt() - 1;
        Task removedTask = itemsList.remove(taskIndex);
        Printer.displayDeleteTask(removedTask, itemsList.size());
    }
}
