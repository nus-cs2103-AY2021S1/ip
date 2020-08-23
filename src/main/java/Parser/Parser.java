package parser;

import DukeException.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import storage.Storage;
import tasks.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import static java.lang.Integer.parseInt;

public class Parser {

    protected Storage storage;
    protected TaskList tasks;

    public Parser(TaskList tasks) {
        this.storage = tasks.getStorage();
        this.tasks = tasks;
    }

    protected static void indexOutOfBounds() {
        System.out.println("Oh no! That number is not on the list! D:");
    }

    protected static void numberFormat() {
        System.out.println("Oh no! Type only a number for the description!");
    }

    protected static void deadlineByReminder() {
        System.out.println("Oh no! Remember to write /by (time) after your task!");
    }

    protected static void eventAtReminder() {
        System.out.println("Oh no! Remember to write /at (time) after your task!");
    }

    protected static void fileError() {
        System.out.println("Oops! There's been an error with the data file, please try again!");
    }

    protected static void incorrectTimeFormat() {
        System.out.println("Oh no! Please only type in the date in this format: yyyy-mm-dd (eg, 2019-10-15).");
    }

    protected void setDoneTask(String command) throws DukeException {
        String[] doneCommand = command.split("\\W+");
        if (doneCommand.length == 1) {
            throw new DukeException("Oh no! This can't be DONE! (The description of done can't be empty!)");
        } else {
            try {
                int index = parseInt(command.split(" ")[1]);
                storage.setDoneLine(index);
                String doneTask = storage.printLine(index);
                doneTask = storage.processLine(doneTask);

                System.out.println("Tasks.Task marked as done! Good job!");
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

    protected void deleteTask(String command) throws DukeException{
        String[] deleteCommand = command.split("\\W+");
        if (deleteCommand.length == 1) {
            throw new DukeException("Oh no! You must DELETE this! (The description of delete can't be empty!)");
        } else {
            try {
                int index = parseInt(command.split(" ")[1]);
                String deletedTask = storage.printLine(index);
                deletedTask = storage.processLine(deletedTask);
                storage.deleteFromFile(index);

                System.out.println("This task has been deleted from the list:");
                System.out.println(deletedTask);
                System.out.println("You now have " + storage.getNumOfTasks() + " tasks.");
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

    protected void handleTodo(String command) throws DukeException {
        String[] todoCommand = command.split("\\W+");
        if (todoCommand.length == 1) {
            throw new DukeException("Oh no! What are you trying TODO? (The description of todo can't be empty!)");
        } else {
            String taskName = command.substring(command.indexOf("todo") + 5);
            Todo todo = new Todo(taskName);
            tasks.addToFile(todo);
        }
    }

    protected void handleDeadline(String command) throws DukeException {
        String[] deadlineCommand = command.split("\\W+");
        if (deadlineCommand.length == 1) {
            throw new DukeException("Oh no! This LINE has made me DEAD! (The description of deadline can't be empty!)");
        } else {
            try {
                String taskName = command.substring(command.indexOf("deadline") + 9);
                taskName = taskName.substring(0, taskName.indexOf("/by") - 1);
                String by = command.split("/by ")[1];
                Deadline deadline = new Deadline(taskName, by);
                tasks.addToFile(deadline);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                deadlineByReminder();
            } catch (DateTimeParseException e) {
                incorrectTimeFormat();
            }
        }
    }

    protected void handleEvent(String command) throws DukeException{
        String[] eventCommand = command.split("\\W+");
        if (eventCommand.length == 1) {
            throw new DukeException("Oh no! EVENTually you'll get it right! (The description of event can't be empty!)");
        } else {
            try {
                String taskName = command.substring(command.indexOf("event") + 6);
                taskName = taskName.substring(0, taskName.indexOf("/at") - 1);
                String at = command.split("/at ")[1];
                Event event = new Event(taskName, at);
                tasks.addToFile(event);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                eventAtReminder();
            } catch (DateTimeParseException e) {
                incorrectTimeFormat();
            }
        }
    }

    public void manageTask(String command) {
        try {
            String taskType = command.split(" ")[0];
            switch (taskType) {
            case "list":
                tasks.readList();
                break;
            case "done":
                setDoneTask(command);
                tasks.setDoneList(command);
                break;
            case "delete":
                deleteTask(command);
                tasks.deleteList(command);
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
