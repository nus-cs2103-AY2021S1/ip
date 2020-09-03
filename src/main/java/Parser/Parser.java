package parser;

import static java.lang.Integer.parseInt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import dukeexception.DukeException;
import storage.Storage;
import tasklist.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

/**
 *  Deals with making sense of the user command.
 */
public class Parser {

    /** Storage for storing user's data */
    protected Storage storage;

    /** Tasklist for dealing with the user's data */
    protected TaskList tasks;

    /**
     * Constructs a new Parser object.
     * @param tasks the list of tasks
     */
    public Parser(TaskList tasks) {
        this.storage = tasks.getStorage();
        this.tasks = tasks;
    }

    public String addLine() {
        return "\n*********************************";
    }

    /**
     * Warns the user if they give a number not on their list.
     */
    protected static String indexOutOfBounds() {
        return "Oh no! That number is not on the list! D:";
    }

    /**
     * Warns the user if they give something other than a number as their description for delete and done.
     */
    protected static String numberFormat() {
        return "Oh no! Type only a number for the description!";
    }

    /**
     * Reminds the user to write a time for deadline.
     */
    protected static String deadlineByReminder() {
        return "Oh no! Remember to write /by [date] after your task!";
    }

    /**
     * Reminds the user to write a time for event.
     */
    protected static String eventAtReminder() {
        return "Oh no! Remember to write /at [date] after your task!";
    }

    /**
     * Tells the user if there has been an error with the data file.
     */
    protected static String printFileError() {
        return "Oops! There's been an error with the data file, please try again!";
    }

    /**
     * Warns the user if they have given the wrong time format.
     */
    protected static String incorrectTimeFormat() {
        return "Oh no! Please only type in the date in this format: yyyy-mm-dd (eg, 2019-10-15).";
    }

    protected String showHelp() {
        String result = "";
        result += "Here are the commands you can use!\n\n"; //, as well as examples on how to use them!\n\n";
        result += "1. help\n"; //: list out all the commands and how to use them\n";
        result += "2. list\n"; //: list out all of your tasks\n";
        result += "3. todo <task>\n"; //: add a todo to your list of tasks (eg, todo buy eggs)\n";
        result += "4. event <task> /at <yyyy-mm-dd>\n"; //: add a event to your list of tasks at a specified date"
                //+ " (eg, event day6 concert /at 2020-10-5)\n";
        result += "5. deadline <task> /by <yyyy-mm-dd>\n"; //: add a deadline to your list of tasks at a specified date"
                //+ " (eg, deadline cs2103t assignment /by 2020-09-02\n";
        result += "6. done <list number>\n"; //: set the task at the number specified on the list to be done (eg, done 1)\n";
        result += "7. delete <list number>\n"; //: deletes the task at the number specified on the list (eg, delete 2)\n";
        result += "8. find <keyword>\n"; //: finds all the tasks on the list that has the specified word (eg, find book)\n";
        result += "9. bye\n\n"; //: closes Cait\n\n";
        result += "Hope this helped you! :D";
        return result;
    }

    /**
     * Handles the command for done.
     * @param command the user's input
     * @throws DukeException if the user doesn't give a description for done
     */
    protected String setDoneTask(String command) throws DukeException {
        String reply = "";
        String[] doneCommand = command.split("\\W+");
        if (doneCommand.length == 1) {
            throw new DukeException("Oh no! This can't be DONE! (The description of done can't be empty!)");
        } else {
            try {
                assert doneCommand[0].equals("done");
                int index = parseInt(command.split(" ")[1]);
                storage.setDoneLine(index);
                String doneTask = storage.printLine(index);
                doneTask = storage.processLine(doneTask);

                assert index > 0;
                reply += "Task marked as done! Good job!\n";
                reply += doneTask;
            } catch (IndexOutOfBoundsException e) {
                reply = indexOutOfBounds();
            } catch (NumberFormatException e) {
                reply = numberFormat();
            } catch (FileNotFoundException e) {
                reply = printFileError();
            } catch (IOException e) {
                reply = printFileError();
            } finally {
                return reply;
            }
        }
    }

    /**
     * Handles the command for delete.
     * @param command the user's input
     * @throws DukeException if the user doesn't give a description for delete.
     */
    protected String deleteTask(String command) throws DukeException {
        String reply = "";
        String[] deleteCommand = command.split("\\W+");
        if (deleteCommand.length == 1) {
            throw new DukeException("Oh no! You must DELETE this! (The description of delete can't be empty!)");
        } else {
            try {
                assert deleteCommand[0].equals("delete");
                int index = parseInt(command.split(" ")[1]);
                String deletedTask = storage.printLine(index);
                deletedTask = storage.processLine(deletedTask);
                storage.deleteFromFile(index);

                assert index > 0;
                reply += "This task has been deleted from the list:\n";
                reply += deletedTask + "\n";
                reply += "You now have " + storage.getNumOfTasks() + " tasks.";
            } catch (IndexOutOfBoundsException e) {
                reply = indexOutOfBounds();
            } catch (NumberFormatException e) {
                reply = numberFormat();
            } catch (FileNotFoundException e) {
                reply = printFileError();
            } catch (IOException e) {
                reply = printFileError();
            } finally {
                return reply;
            }
        }
    }

    /**
     * Handles the command for todo.
     * @param command the user's input
     * @throws DukeException if the user doesn't give a description for todo
     */
    protected String handleTodo(String command) throws DukeException {
        String reply = "";
        String[] todoCommand = command.split("\\W+");
        if (todoCommand.length == 1) {
            throw new DukeException("Oh no! What are you trying TODO? (The description of todo can't be empty!)");
        } else {
            assert todoCommand[0].equals("todo");
            String taskName = command.substring(command.indexOf("todo") + 5);
            Todo todo = new Todo(taskName);
            reply = tasks.addToFile(todo);
        }
        return reply;
    }

    /**
     * Handles the command for deadline.
     * @param command the user's input
     * @throws DukeException if the user doesn't give a description for deadline
     */
    protected String handleDeadline(String command) throws DukeException {
        String reply = "";
        String[] deadlineCommand = command.split("\\W+");
        if (deadlineCommand.length == 1) {
            throw new DukeException("Oh no! This LINE has made me DEAD! (The description of deadline can't be empty!)");
        } else {
            try {
                assert deadlineCommand[0].equals("deadline");
                String taskName = command.substring(command.indexOf("deadline") + 9);
                taskName = taskName.substring(0, taskName.indexOf("/by") - 1);
                String by = command.split("/by ")[1];
                Deadline deadline = new Deadline(taskName, by);
                reply = tasks.addToFile(deadline);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                reply = deadlineByReminder();
            } catch (DateTimeParseException e) {
                reply = incorrectTimeFormat();
            }
        }
        return reply;
    }

    /**
     * Handles the command for event.
     * @param command the user's input
     * @throws DukeException if the user doesn't give a description for event
     */
    protected String handleEvent(String command) throws DukeException {
        String reply = "";
        String[] eventCommand = command.split("\\W+");
        if (eventCommand.length == 1) {
            throw new DukeException("Oh no! EVENTually you'll get it right! "
                    + "(The description of event can't be empty!)");
        } else {
            try {
                assert eventCommand[0].equals("event");
                String taskName = command.substring(command.indexOf("event") + 6);
                taskName = taskName.substring(0, taskName.indexOf("/at") - 1);
                String at = command.split("/at ")[1];
                Event event = new Event(taskName, at);
                reply = tasks.addToFile(event);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                reply = eventAtReminder();
            } catch (DateTimeParseException e) {
                reply = incorrectTimeFormat();
            }
        }
        return reply;
    }

    /**
     * Handles the command for find.
     * @param command the user's input
     * @throws DukeException if the user doesn't give a description for find
     */
    public String handleFind(String command) throws DukeException {
        String reply = "";
        String[] findCommand = command.split("\\W+");
        if (findCommand.length == 1) {
            throw new DukeException("Oh no! Did you FIND out your problem? (The description of find can't be empty!)");
        } else {
            assert findCommand[0].equals("find");
            String taskName = command.substring(command.indexOf("find") + 5);
            reply = "Here's what I've found for you:\n";
            reply += tasks.findInList(taskName);

            if (!reply.equals("Here's what I've found for you:\n")) {
                reply += "Hope you found it useful!";
            } else {
                reply += "Oh! Looks like there aren't any tasks that has this word!";
            }
        }
        return reply;
    }

    /**
     * Reads the user's input and manages it according to the input.
     * @param command the input given by the user
     */
    public String manageTask(String command) {
        String reply = "";
        try {
            String taskType = command.split(" ")[0];
            switch (taskType) {
            case "help":
                reply = showHelp();
                break;
            case "bye":
                reply = "Bye! Let's talk again soon!";
                break;
            case "list":
                reply = tasks.readList();
                break;
            case "done":
                reply = setDoneTask(command);
                tasks.setDoneList(command);
                break;
            case "delete":
                reply = deleteTask(command);
                tasks.deleteList(command);
                break;
            case "todo":
                reply = handleTodo(command);
                break;
            case "deadline":
                reply = handleDeadline(command);
                break;
            case "event":
                reply = handleEvent(command);
                break;
            case "find":
                reply = handleFind(command);
                break;
            default:
                reply = "Sorry! I don't understand that command. Please try again!";
                break;
            }
        } catch (DukeException e) {
            reply = e.getMessage();
        } finally {
            //reply += addLine();
            return reply;
        }
    }

}
