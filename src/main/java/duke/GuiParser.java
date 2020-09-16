package duke;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a parser object that interprets user inputs for the graphical user interface.
 * It is in a separate class so that both the graphical user interface and the command line interface
 * can run at the same time.
 */
public class GuiParser {

    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String HELP = "help";
    private static final String SPACE = " ";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String FIND = "find";

    /**
     * Interprets the given user input with the current task list and storage.
     *
     * @param input Current input string of the user.
     * @param taskList Current list of tasks of the user.
     * @param storage Current storage of the user.
     * @param gui Gui object.
     * @throws DukeUnknownCommandException If an unknown command is used.
     * @throws DukeInvalidTaskQueryException If an invalid task query is used.
     * @throws DukeInvalidArgumentException If an invalid argument is used.
     * @throws DukeInvalidDateException If an invalid date format is used.
     * @throws DukeEmptyDescriptionException If an empty description is used.
     * @throws FileNotFoundException
     */
    public String interpretGui(String input, TaskList taskList, Storage storage, Gui gui)
            throws DukeUnknownCommandException, DukeInvalidTaskQueryException, DukeInvalidArgumentException,
            DukeInvalidDateException, DukeEmptyDescriptionException, FileNotFoundException {
        assert taskList != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";

        if (input.equals(BYE)) {
            return gui.printExitBot();
        }

        ArrayList<Task> list = taskList.getList();

        if (input.equals(LIST)) {
            return showListGui(list);
        }

        if (input.equals(HELP)) {
            return gui.printHelpInterface();
        }

        if (input.contains(SPACE)) {
            int indexOfSpace = input.indexOf(SPACE);
            String firstWord = input.substring(0, indexOfSpace);

            switch (firstWord) {
                case DONE:
                    return setDoneGui(input, indexOfSpace, list, storage);
                case DELETE:
                    return dealWithDeleteGui(input, indexOfSpace, taskList, list, storage);
                case TODO:
                    return dealWithTodoGui(input, indexOfSpace, taskList, list, storage);
                case DEADLINE:
                    return dealWithDeadlineGui(input, indexOfSpace, taskList, list, storage);
                case EVENT:
                    return dealWithEventGui(input, indexOfSpace, taskList, list, storage);
                case FIND:
                    return dealWithFindGui(input, indexOfSpace, list, gui);
                default:
                    throw new DukeUnknownCommandException();
            }
        } else {
            switch (input) {
                case TODO:
                    throw new DukeEmptyDescriptionException(TODO);
                case DEADLINE:
                    throw new DukeEmptyDescriptionException(DEADLINE);
                case EVENT:
                    throw new DukeEmptyDescriptionException(EVENT);
                default:
                    throw new DukeUnknownCommandException();

            }
        }

    }

    /**
     * Shows the tasks in the list.
     * @param list List of tasks.
     */
    private String showListGui(ArrayList<Task> list) {
        assert list != null : "List object should not be null";

        if (list.isEmpty()) {
            return "List is empty.";
        } else {
            StringBuilder listOutput = new StringBuilder();
            for (int j = 0; j < list.size(); j++) {
                int num = j + 1;
                Task task = list.get(j);
                listOutput.append(num + "." + task.toString() + "\n");
            }
            return listOutput.toString();
        }
    }

    /**
     * Sets the specific task as done.
     * @param input Input string.
     * @param indexOfSpace Index of space.
     * @param list List of tasks.
     * @param storage Storage object.
     * @throws DukeInvalidTaskQueryException
     * @throws FileNotFoundException
     */
    private String setDoneGui(String input, int indexOfSpace, ArrayList<Task> list, Storage storage)
            throws DukeInvalidTaskQueryException, FileNotFoundException {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        int num = Integer.parseInt(input.substring(indexOfSpace + 1));
        if (list.size() < num) {
            throw new DukeInvalidTaskQueryException();
        } else {
            Task taskToSetToDone = list.get(num - 1);
            taskToSetToDone.setDone();
            storage.update(list);
            return "Nice! I've marked this task as done:" + "\n" + taskToSetToDone.toString();
        }
    }

    /**
     * Deletes a task.
     * @param input Input string
     * @param indexOfSpace Index of space.
     * @param taskList TaskList object.
     * @param list list of tasks
     * @param storage
     * @throws DukeInvalidTaskQueryException
     * @throws FileNotFoundException
     */
    private String dealWithDeleteGui(String input, int indexOfSpace, TaskList taskList, ArrayList<Task> list, Storage storage)
            throws DukeInvalidTaskQueryException, FileNotFoundException {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        int num = Integer.parseInt(input.substring(indexOfSpace + 1));
        if (list.size() < num) {
            throw new DukeInvalidTaskQueryException();
        } else {
            Task taskToDelete = list.get(num - 1);
            taskList.remove(num - 1);
            storage.update(list);
            return "Noted. I've removed this task:" + "\n" + taskToDelete.toString() + "\n" + "Now you have " +
                    list.size() + " tasks in the list.";
        }
    }

    /**
     * Creates a new todo object and stores it.
     * @param input Input string.
     * @param indexOfSpace Index of space.
     * @param taskList TaskList object.
     * @param list List of tasks.
     * @param storage Storage object.
     * @throws FileNotFoundException
     */
    private String dealWithTodoGui(String input, int indexOfSpace, TaskList taskList, ArrayList<Task> list, Storage storage)
            throws FileNotFoundException {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        String todoName = input.substring(indexOfSpace + 1);
        Todo newTodo = new Todo(todoName);
        taskList.add(newTodo);
        storage.update(list);
        String str = "Got it. I've added this task:" + "\n" + newTodo.toString() + "\n" + "Now you have " +
                list.size() + " task in the list.";
        return str;
    }

    /**
     * Creates a new deadline object and stores it.
     * @param input Input string.
     * @param indexOfSpace Index of space.
     * @param taskList TaskList object
     * @param list List of tasks.
     * @param storage Storage object.
     * @throws FileNotFoundException
     * @throws DukeInvalidDateException
     * @throws DukeInvalidArgumentException
     */
    private String dealWithDeadlineGui(String input, int indexOfSpace, TaskList taskList, ArrayList<Task> list, Storage storage)
            throws FileNotFoundException, DukeInvalidDateException, DukeInvalidArgumentException {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        int index = input.indexOf("/");
        if (index < 0 || index + 4 > input.length()) {
            throw new DukeInvalidArgumentException(DEADLINE);
        }
        String deadlineName = input.substring(indexOfSpace + 1, index);
        String deadlineTime = input.substring(index + 4);
        try {
            Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
            taskList.add(newDeadline);
            storage.update(list);
            String str = "Got it. I've added this task:" + "\n" + newDeadline.toString() + "\n" + "Now you have " +
                    list.size() + " task in the list.";
            return str;
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException(DEADLINE);
        }

    }

    /**
     * Creates a new event object and stores it.
     * @param input Input string.
     * @param indexOfSpace Index of space.
     * @param taskList TaskList object.
     * @param list List of tasks.
     * @param storage Storage object.
     * @throws FileNotFoundException
     * @throws DukeInvalidDateException
     * @throws DukeInvalidArgumentException
     */
    private String dealWithEventGui(String input, int indexOfSpace, TaskList taskList, ArrayList<Task> list, Storage storage)
            throws FileNotFoundException, DukeInvalidDateException, DukeInvalidArgumentException {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        int index = input.indexOf("/");
        if (index < 0 || index + 4 > input.length()) {
            throw new DukeInvalidArgumentException(EVENT);
        }
        String eventName = input.substring(indexOfSpace + 1, index);
        String eventTime = input.substring(index + 4);
        try {
            Event newEvent = new Event(eventName, eventTime);
            taskList.add(newEvent);
            storage.update(list);
            String str = "Got it. I've added this task:" + "\n" + newEvent.toString() + "\n" + "Now you have " +
                    list.size() + " task in the list.";
            return str;
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException(EVENT);
        }
    }

    /**
     * Finds the specific tasks based on user query.
     * @param input Input string.
     * @param indexOfSpace Index of space.
     * @param list List of tasks.
     * @param gui Gui object.
     */
    private String dealWithFindGui(String input, int indexOfSpace, ArrayList<Task> list, Gui gui) {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        String nameOfItemToBeFound = input.substring(indexOfSpace + 1);
        ArrayList<Task> newList = new ArrayList<>();
        for (Task task: list) {
            Boolean found = Arrays.asList(task.getTaskName().split(" ")).contains(nameOfItemToBeFound);
            if (found) {
                newList.add(task);
            }
        }
        if (newList.isEmpty()) {
            return gui.printNoQueryResult();
        } else {
            StringBuilder listOutput = new StringBuilder();
            for (int j = 0; j < newList.size(); j++) {
                int num = j + 1;
                Task task = newList.get(j);
                listOutput.append(num + "." + task.toString() + "\n");
            }
            String str = "Here are the matching tasks in your list:" + "\n" + listOutput;
            return str;
        }
    }
}
