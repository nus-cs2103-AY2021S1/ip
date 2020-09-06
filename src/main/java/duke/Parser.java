package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a parser object that interprets user inputs.
 */
public class Parser {

    public Parser() {}

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
        if (input.equals("list")) {
            showList(list);
            return;
        }

        if (input.contains(" ")) {
            int i = input.indexOf(" ");
            String firstWord = input.substring(0, i);

            switch (firstWord) {
                case "done":
                    setDone(input, i, list, storage);
                    break;
                case "delete":
                    dealWithDelete(input, i, taskList, list, storage);
                    break;
                case "todo":
                    dealWithTodo(input, i, taskList, list, storage);
                    break;
                case "deadline":
                    dealWithDeadline(input, i, taskList, list, storage);
                    break;
                case "event":
                    dealWithEvent(input, i, taskList, list, storage);
                    break;
                case "find":
                    dealWithFind(input, i, list);
                    break;
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

    private void setDone(String input, int indexOfSpace, ArrayList<Task> list, Storage storage)
            throws DukeException, FileNotFoundException {
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

    private void dealWithDelete(String input, int indexOfSpace, TaskList taskList, ArrayList<Task> list, Storage storage)
            throws DukeException, FileNotFoundException {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        int num = Integer.parseInt(input.substring(indexOfSpace + 1));
        if (list.size() < num) {
            throw new DukeInvalidTaskException();
        } else {
            taskList.remove(num-1);
            storage.update(list);
        }
    }

    private void dealWithTodo(String input, int indexOfSpace, TaskList taskList, ArrayList<Task> list, Storage storage)
            throws FileNotFoundException {
        assert indexOfSpace > -1 : "Index of space should not be negative";

        String todoName = input.substring(indexOfSpace + 1);
        Todo newTodo = new Todo(todoName);
        taskList.add(newTodo);
        storage.update(list);
    }

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
