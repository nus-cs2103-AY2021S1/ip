package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a parser object that interprets user inputs.
 */
public class GuiParser {

    public GuiParser() {}

    /**
     * Interprets the given user input with the current task list and storage.
     *
     * @param input Current input string of the user.
     * @param taskList Current list of tasks of the user.
     * @param storage Current storage of the user.
     * @throws DukeException
     * @throws FileNotFoundException
     */
    public String interpretGui(String input, TaskList taskList, Storage storage)
            throws DukeException, FileNotFoundException {
        assert taskList != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";

        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        }

        ArrayList<Task> list = taskList.getList();

        if (input.equals("list")) {
            return showListGui(list);
        }

        if (input.equals("help")) {
            return helpInterface();
        }

        if (input.contains(" ")) {
            int i = input.indexOf(" ");
            String firstWord = input.substring(0, i);

            switch (firstWord) {
                case "done":
                    return setDoneGui(input, i, list, storage);
                case "delete":
                    return dealWithDeleteGui(input, i, taskList, list, storage);
                case "todo":
                    return dealWithTodoGui(input, i, taskList, list, storage);
                case "deadline":
                    return dealWithDeadlineGui(input, i, taskList, list, storage);
                case "event":
                    return dealWithEventGui(input, i, taskList, list, storage);
                case "find":
                    return dealWithFindGui(input, i, list);
                default:
                    throw new DukeUnknownCommandException();
            }
        } else {
            switch (input) {
                case "todo":
                    throw new DukeEmptyDescriptionException("todo");
                case "deadline":
                    throw new DukeEmptyDescriptionException("deadline");
                case "event":
                    throw new DukeEmptyDescriptionException("event");
                default:
                    throw new DukeUnknownCommandException();
            }
        }

    }

    /**
     * Returns a help interface.
     *
     * @return
     */
    private String helpInterface() {
        String helpInterface = "List of available commands: \ntodo - creates a todo (e.g todo read book)" +
                "\ndeadline - creates a deadline with a date (e.g deadline return book /by 2019-10-15)" +
                "\nevent - creates an event with a date (e.g event go library /at 2019-10-15)" +
                "\ndone - sets item at index to done (e.g done 1)" +
                "\ndelete - deletes item at index (e.g delete 1)" +
                "\nfind - finds task (e.g find book)";
        return helpInterface;
    }

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

    private String setDoneGui(String input, int indexOfSpace, ArrayList<Task> list, Storage storage) throws DukeException, FileNotFoundException {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        int num = Integer.parseInt(input.substring(indexOfSpace + 1));
        if (list.size() < num) {
            throw new DukeInvalidTaskException();
        } else {
            Task taskToSetToDone = list.get(num - 1);
            taskToSetToDone.setDone();
            storage.update(list);
            return "Nice! I've marked this task as done:" + "\n" + taskToSetToDone.toString();
        }
    }

    private String dealWithDeleteGui(String input, int indexOfSpace, TaskList taskList, ArrayList<Task> list, Storage storage)
            throws DukeException, FileNotFoundException {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        int num = Integer.parseInt(input.substring(indexOfSpace + 1));
        if (list.size() < num) {
            throw new DukeInvalidTaskException();
        } else {
            Task taskToDelete = list.get(num - 1);
            taskList.remove(num - 1);
            storage.update(list);
            return "Noted. I've removed this task:" + "\n" + taskToDelete.toString() + "\n" + "Now you have " +
                    list.size() + " tasks in the list.";
        }
    }

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

    private String dealWithDeadlineGui(String input, int indexOfSpace, TaskList taskList, ArrayList<Task> list, Storage storage)
            throws FileNotFoundException {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        int index = input.indexOf("/");
        String deadlineName = input.substring(indexOfSpace + 1, index);
        String deadlineTime = input.substring(index + 4);
        Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
        taskList.add(newDeadline);
        storage.update(list);
        String str = "Got it. I've added this task:" + "\n" + newDeadline.toString() + "\n" + "Now you have " +
                list.size() + " task in the list.";
        return str;
    }

    private String dealWithEventGui(String input, int indexOfSpace, TaskList taskList, ArrayList<Task> list, Storage storage)
            throws FileNotFoundException {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        int index = input.indexOf("/");
        String eventName = input.substring(indexOfSpace + 1, index);
        String eventTime = input.substring(index + 4);
        Event newEvent = new Event(eventName, eventTime);
        taskList.add(newEvent);
        storage.update(list);
        String str = "Got it. I've added this task:" + "\n" + newEvent.toString() + "\n" + "Now you have " +
                list.size() + " task in the list.";
        return str;
    }

    private String dealWithFindGui(String input, int indexOfSpace, ArrayList<Task> list) {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        String nameOfItemToBeFound = input.substring(indexOfSpace + 1);
        // search the list of the item to be found, add them to a new list and print them out
        ArrayList<Task> newList = new ArrayList<>();
        for (Task task: list) {
            Boolean found = Arrays.asList(task.getTaskName().split(" ")).contains(nameOfItemToBeFound);
            if (found) {
                newList.add(task);
            }
        }
        if (newList.isEmpty()) {
            return "We are unable to find any task that match your query.";
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
