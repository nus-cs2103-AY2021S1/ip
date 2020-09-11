package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a parser object that interprets user inputs.
 */
public class Parser {

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
     * @throws DukeException
     * @throws FileNotFoundException
     */
    public void interpret(String input, TaskList taskList, Storage storage)
            throws DukeException, FileNotFoundException {
        assert taskList != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";

        ArrayList<Task> list = taskList.getList();
        if (input.equals(LIST)) {
            showList(list);
            return;
        }

        if (input.equals(HELP)) {
            showHelpInterface();
            return;
        }

        if (input.contains(SPACE)) {
            int indexOfSpace = input.indexOf(SPACE);
            String firstWord = input.substring(0, indexOfSpace);

            switch (firstWord) {
                case DONE:
                    setDone(input, indexOfSpace, list, storage);
                    break;
                case DELETE:
                    dealWithDelete(input, indexOfSpace, taskList, list, storage);
                    break;
                case TODO:
                    dealWithTodo(input, indexOfSpace, taskList, list, storage);
                    break;
                case DEADLINE:
                    dealWithDeadline(input, indexOfSpace, taskList, list, storage);
                    break;
                case EVENT:
                    dealWithEvent(input, indexOfSpace, taskList, list, storage);
                    break;
                case FIND:
                    dealWithFind(input, indexOfSpace, list);
                    break;
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
     * Prints the help interface.
     */
    private void showHelpInterface() {
        System.out.println("List of available commands: \ntodo - creates a todo (e.g todo read book)" +
                "\ndeadline - creates a deadline with a date (e.g deadline return book /by 2019-10-15)" +
                "\nevent - creates an event with a date (e.g event go library /at 2019-10-15)" +
                "\ndone - sets item at index to done (e.g done 1)" +
                "\ndelete - deletes item at index (e.g delete 1)" +
                "\nfind - finds task (e.g find book)");
    }

    /**
     * Shows the tasks in the list.
     * @param list List of tasks.
     */
    private void showList(ArrayList<Task> list) {
        assert list != null : "List object should not be null";

        if (list.isEmpty()) {
            System.out.println("List is empty.");
        } else {
            StringBuilder listOutput = new StringBuilder();
            for (int j = 0; j < list.size(); j++) {
                int num = j + 1;
                Task task = list.get(j);
                listOutput.append(num + "." + task.toString() + "\n");
            }
            System.out.println(listOutput);
        }
    }

    /**
     * Sets the specific task as done.
     * @param input Input string.
     * @param indexOfSpace Index of space.
     * @param list List of tasks.
     * @param storage Storage object.
     * @throws DukeInvalidTaskException
     * @throws FileNotFoundException
     */
    private void setDone(String input, int indexOfSpace, ArrayList<Task> list, Storage storage)
            throws DukeInvalidTaskException, FileNotFoundException {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        int num = Integer.parseInt(input.substring(indexOfSpace + 1));
        if (list.size() < num) {
            throw new DukeInvalidTaskException();
        } else {
            Task taskToSetToDone = list.get(num - 1);
            taskToSetToDone.setDone();
            System.out.println("Nice! I've marked this task as done:" + "\n" + taskToSetToDone.toString());
            storage.update(list);
        }
    }

    /**
     * Deletes a task.
     * @param input Input string
     * @param indexOfSpace Index of space.
     * @param taskList TaskList object.
     * @param list list of tasks
     * @param storage
     * @throws DukeInvalidTaskException
     * @throws FileNotFoundException
     */
    private void dealWithDelete(String input, int indexOfSpace, TaskList taskList, ArrayList<Task> list, Storage storage)
            throws DukeInvalidTaskException, FileNotFoundException {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        int num = Integer.parseInt(input.substring(indexOfSpace + 1));
        if (list.size() < num) {
            throw new DukeInvalidTaskException();
        } else {
            taskList.remove(num-1);
            storage.update(list);
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
    private void dealWithTodo(String input, int indexOfSpace, TaskList taskList, ArrayList<Task> list, Storage storage)
            throws FileNotFoundException {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        String todoName = input.substring(indexOfSpace + 1);
        Todo newTodo = new Todo(todoName);
        taskList.add(newTodo);
        storage.update(list);
    }

    /**
     * Creates a new deadline object and stores it.
     * @param input Input string.
     * @param indexOfSpace Index of space.
     * @param taskList TaskList object
     * @param list List of tasks.
     * @param storage Storage object.
     * @throws FileNotFoundException
     */
    private void dealWithDeadline(String input, int indexOfSpace, TaskList taskList, ArrayList<Task> list, Storage storage)
            throws FileNotFoundException {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        int index = input.indexOf("/");
        String deadlineName = input.substring(indexOfSpace + 1, index);
        String deadlineTime = input.substring(index + 4);
        Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
        taskList.add(newDeadline);
        storage.update(list);
    }

    /**
     * Creates a new event object and stores it.
     * @param input Input string.
     * @param indexOfSpace Index of space.
     * @param taskList TaskList object.
     * @param list List of tasks.
     * @param storage Storage object.
     * @throws FileNotFoundException
     */
    private void dealWithEvent(String input, int indexOfSpace, TaskList taskList, ArrayList<Task> list, Storage storage)
            throws FileNotFoundException {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        int index = input.indexOf("/");
        String eventName = input.substring(indexOfSpace + 1, index);
        String eventTime = input.substring(index + 4);
        Event newEvent = new Event(eventName, eventTime);
        taskList.add(newEvent);
        storage.update(list);
    }

    /**
     * Finds the specific tasks based on user query.
     * @param input Input string.
     * @param indexOfSpace Index of space.
     * @param list List of tasks.
     */
    private void dealWithFind(String input, int indexOfSpace, ArrayList<Task> list) {
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
            System.out.println("We are unable to find any task that match your query.");
        } else {
            StringBuilder listOutput = new StringBuilder();
            for (int j = 0; j < newList.size(); j++) {
                int num = j + 1;
                Task task = newList.get(j);
                listOutput.append(num + "." + task.toString() + "\n");
            }
            System.out.println("Here are the matching tasks in your list:");
            System.out.println(listOutput);
        }
    }
}
