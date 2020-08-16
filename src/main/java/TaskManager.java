import java.util.ArrayList;
import static java.lang.Integer.parseInt;

public class TaskManager {

    private static ArrayList<Task> list = new ArrayList<>();

    public static void addToList(Task task) {
        list.add(task);
        System.out.println("New task added!");
        System.out.println(task);
        System.out.println("You now have " + list.size() + " tasks.");
    }

    public static void readList() {
        System.out.println("Here's all your tasks to complete:");
        int i = 1;
        for (Task ele : list) {
            System.out.println(i + ". " + ele);
            i++;
        }
        System.out.println("Time to get to work! :D");
    }

    public static void indexOutOfBounds() {
        System.out.println("Oh no! That number is not on the list! D:");
    }

    public static void numberFormat() {
        System.out.println("Oh no! Type only a number for the description!");
    }

    public static void deadlineByReminder() {
        System.out.println("Oh no! Remember to write /by (time) after your task!");
    }

    public static void eventAtReminder() {
        System.out.println("Oh no! Remember to write /at (time) after your task!");
    }

    public static void setDoneTask(String command) throws DukeException{
        String[] doneCommand = command.split("\\W+");
        if (doneCommand.length == 1) {
            throw new DukeException("Oh no! This can't be DONE! (The description of done can't be empty!)");
        } else {
            try {
                int i = parseInt(command.split(" ")[1]);
                Task doneTask = list.get(i - 1);
                doneTask.markAsDone();
                System.out.println("Task marked as done! Good job!");
                System.out.println(doneTask);
            } catch (IndexOutOfBoundsException e) {
                indexOutOfBounds();
            } catch (NumberFormatException e) {
                numberFormat();
            }
        }
    }

    public static void deleteTask(String command) throws DukeException{
        String[] deleteCommand = command.split("\\W+");
        if (deleteCommand.length == 1) {
            throw new DukeException("Oh no! You must DELETE this! (The description of delete can't be empty!)");
        } else {
            try {
                int i = parseInt(command.split(" ")[1]);
                Task deleteTask = list.get(i - 1);
                list.remove(i - 1);
                System.out.println("This task has been deleted from the list:");
                System.out.println(deleteTask);
                System.out.println("You now have " + list.size() + " tasks.");
            } catch (IndexOutOfBoundsException e) {
                indexOutOfBounds();
            } catch (NumberFormatException e) {
                numberFormat();
            }
        }
    }

    public static void handleTodo(String command) throws DukeException {
        String[] todoCommand = command.split("\\W+");
        if (todoCommand.length == 1) {
            throw new DukeException("Oh no! What are you trying TODO? (The description of todo can't be empty!)");
        } else {
            String taskName = command.substring(command.indexOf("todo") + 5);
            Todo todo = new Todo(taskName);
            addToList(todo);
        }
    }

    public static void handleDeadline(String command) throws DukeException {
        String[] deadlineCommand = command.split("\\W+");
        if (deadlineCommand.length == 1) {
            throw new DukeException("Oh no! This LINE has made me DEAD! (The description of deadline can't be empty!)");
        } else {
            try {
                String taskName = command.substring(command.indexOf("deadline") + 9);
                taskName = taskName.substring(0, taskName.indexOf("/by") - 1);
                String by = command.split("/by ")[1];
                Deadline deadline = new Deadline(taskName, by);
                addToList(deadline);
            } catch (StringIndexOutOfBoundsException e) {
                deadlineByReminder();
            } catch (ArrayIndexOutOfBoundsException e) {
                deadlineByReminder();
            }
        }
    }

    public static void handleEvent(String command) throws DukeException{
        String[] eventCommand = command.split("\\W+");
        if (eventCommand.length == 1) {
            throw new DukeException("Oh no! EVENTually you'll get it right! (The description of event can't be empty!)");
        } else {
            try {
                String taskName = command.substring(command.indexOf("event") + 6);
                taskName = taskName.substring(0, taskName.indexOf("/at") - 1);
                String at = command.split("/at ")[1];
                Event event = new Event(taskName, at);
                addToList(event);
            } catch (StringIndexOutOfBoundsException e) {
                eventAtReminder();
            } catch (ArrayIndexOutOfBoundsException e) {
                eventAtReminder();
            }
        }
    }

    public static void manageTask(String command) {
        try {
            String taskType = command.split(" ")[0];
            if (taskType.equals("done")) {
                setDoneTask(command);
            } else if (taskType.equals("delete")) {
                deleteTask(command);
            } else if (taskType.equals("todo")) {
                handleTodo(command);
            } else if (taskType.equals("deadline")) {
                handleDeadline(command);
            } else if (taskType.equals("event")) {
                handleEvent(command);
            } else {
                System.out.println("Sorry! I don't understand that command. Please try again!");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

}
