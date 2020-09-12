package parser;

import static java.lang.Integer.parseInt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

    /**
     * Warns the user if they give a number not on their list.
     */
    protected static String printIndexOutOfBounds() {
        return "Oh no! That number is not on the list! D:";
    }

    /**
     * Warns the user if they give something other than a number as their description for delete and done.
     */
    protected static String printNumberFormat() {
        return "Oh no! Type only a number for the description!";
    }

    /**
     * Reminds the user to write a time for deadline.
     */
    protected static String printDeadlineByReminder() {
        return "Oh no! Remember to write /by [date] after your task!";
    }

    /**
     * Reminds the user to write a time for event.
     */
    protected static String printEventAtReminder() {
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
    protected static String printIncorrectTimeFormat() {
        return "Oh no! Please only type in the date in this format: yyyy-mm-dd (eg, 2019-10-15).";
    }

    protected String showHelp() {
        String result = "";
        result += "Here are the commands you can use!\n\n";
        result += "1. help\n";
        result += "2. list\n";
        result += "3. todo <task>\n";
        result += "4. event <task> /at <yyyy-mm-dd>\n";
        result += "5. deadline <task> /by <yyyy-mm-dd>\n";
        result += "6. done <list number(s)>\n";
        result += "7. delete <list number(s)>\n";
        result += "8. find <keyword>\n";
        result += "9. bye\n\n";
        result += "Hope this helped you! :D";
        return result;
    }

    protected Integer[] removeDuplicates(Integer[] array) {
        int length = array.length;
        ArrayList<Integer> tempArr = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < length; i++) {
            set.add(array[i]);
        }

        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            tempArr.add((int) iter.next());
        }
        Integer[] resultArr = new Integer[tempArr.size()];
        for (int j = 0; j < tempArr.size(); j++) {
            resultArr[j] = tempArr.get(j);
        }
        return resultArr;
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
                String[] strIndexes = command.split(" ");
                Integer[] indexes = new Integer[strIndexes.length - 1];
                for (int i = 1; i < strIndexes.length; i++) {
                    indexes[i - 1] = parseInt(strIndexes[i]);
                }

                for (int i : indexes) {
                    reply = tasks.getFromList(i).toString();
                }

                indexes = removeDuplicates(indexes);

                reply = "Completing tasks...\n";
                reply += setMultipleDoneTask(indexes);
                reply += "Task marked as done! Good job!";

            } catch (IndexOutOfBoundsException e) {
                reply = printIndexOutOfBounds();
            } catch (NumberFormatException e) {
                reply = printNumberFormat();
            } finally {
                return reply;
            }
        }
    }

    protected String setMultipleDoneTask(Integer ... indexes) {
        String reply = "";
        try {
            for (int index : indexes) {
                storage.setDoneLine(index);
                String doneTask = storage.printLine(index);
                doneTask = storage.processLine(doneTask);
                tasks.setDoneList(index);
                reply += doneTask + "\n";
            }
        } catch (FileNotFoundException e) {
            reply = printFileError();
        } catch (IOException e) {
            reply = printFileError();
        }
        return reply;
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

                String[] strIndexes = command.split(" ");
                Integer[] indexes = new Integer[strIndexes.length - 1];
                for (int i = 1; i < strIndexes.length; i++) {
                    indexes[i - 1] = parseInt(strIndexes[i]);
                }

                for (int i : indexes) {
                    reply = tasks.getFromList(i).toString();
                }

                indexes = removeDuplicates(indexes);

                Arrays.sort(indexes, Collections.reverseOrder());


                reply = "Deleting tasks...\n";
                reply += deleteMultipleTasks(indexes);
                reply += "You now have " + tasks.getNumList() + " tasks.";


            } catch (IndexOutOfBoundsException e) {
                reply = printIndexOutOfBounds();
            } catch (NumberFormatException e) {
                reply = printNumberFormat();
            } finally {
                return reply;
            }
        }
    }


    protected String deleteMultipleTasks(Integer ... indexes) {
        String reply = "";
        try {
            for (int index : indexes) {

                String deletedTask = storage.printLine(index);
                deletedTask = storage.processLine(deletedTask);
                storage.deleteFromFile(index);
                tasks.deleteList(index);

                reply += deletedTask + "\n";
            }
        } catch (FileNotFoundException e) {
            reply = printFileError();
        } catch (IOException e) {
            reply = printFileError();
        }
        return reply;
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
                reply = printDeadlineByReminder();
            } catch (DateTimeParseException e) {
                reply = printIncorrectTimeFormat();
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
                reply = printEventAtReminder();
            } catch (DateTimeParseException e) {
                reply = printIncorrectTimeFormat();
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
            taskType = taskType.toLowerCase();
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
                break;
            case "delete":
                reply = deleteTask(command);
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
            return reply;
        }
    }

}
