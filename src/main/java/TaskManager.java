import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Integer.parseInt;

public class TaskManager {

    public static String processTasks(Task task) {
        String result = "";
        int isDone = task.getIsDone() == "[\u2713] " ? 1 : 0;
        if (task instanceof Todo) {
            result = "T | " + isDone + " | " + task.getTaskName();
        } else if (task instanceof Deadline) {
            result = "D | " + isDone + " | " + task.getTaskName() + " | " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            result = "E | " + isDone + " | " + task.getTaskName() + " | " + ((Event) task).getAt();
        } else {
            ;
        }
        return result;
    }

    public static void addToFile(Task task) {
        String taskString = processTasks(task);
        WriteFile.saveData(taskString);
        System.out.println("New task added!");
        System.out.println(task);
        System.out.println("You now have " + WriteFile.getNumOfTasks() + " tasks.");
    }

    public static void readList() {
        if (WriteFile.getNumOfTasks() == 0) {
            System.out.println("Looks like you don't have any tasks! Go on and add some!");
        } else {
            System.out.println("Here's all your tasks to complete:");
            WriteFile.readFile();
            System.out.println("Time to get to work! :D");
        }
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

    public static void fileError() {
        System.out.println("Oops! There's been an error with the data file, please try again!");
    }

    public static void setDoneTask(String command) throws DukeException{
        String[] doneCommand = command.split("\\W+");
        if (doneCommand.length == 1) {
            throw new DukeException("Oh no! This can't be DONE! (The description of done can't be empty!)");
        } else {
            try {
                int i = parseInt(command.split(" ")[1]);
                WriteFile.setDoneLine(i);
                String doneTask = WriteFile.printLine(i);
                doneTask = WriteFile.processLine(doneTask);
                System.out.println("Task marked as done! Good job!");
                System.out.println(doneTask);
            } catch (IndexOutOfBoundsException e) {
                indexOutOfBounds();
            } catch (NumberFormatException e) {
                numberFormat();
            } catch (FileNotFoundException e) {
                fileError();
            } catch (IOException e) {
                fileError();
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
                String deletedTask = WriteFile.printLine(i);
                deletedTask = WriteFile.processLine(deletedTask);
                WriteFile.deleteFromFile(i);
                System.out.println("This task has been deleted from the list:");
                System.out.println(deletedTask);
                System.out.println("You now have " + WriteFile.getNumOfTasks() + " tasks.");
            } catch (IndexOutOfBoundsException e) {
                indexOutOfBounds();
            } catch (NumberFormatException e) {
                numberFormat();
            } catch (FileNotFoundException e) {
                fileError();
            } catch (IOException e) {
                fileError();
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
            addToFile(todo);
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
                addToFile(deadline);
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
                addToFile(event);
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
            switch (taskType) {
            case "list":
                readList();
                break;
            case "done":
                setDoneTask(command);
                break;
            case "delete":
                deleteTask(command);
                break;
            case "todo":
                handleTodo(command);
                break;
            case "deadline":
                handleDeadline(command);
                break;
            case "event":
                handleEvent(command);
                break;
            default:
                System.out.println("Sorry! I don't understand that command. Please try again!");
                break;
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

}
