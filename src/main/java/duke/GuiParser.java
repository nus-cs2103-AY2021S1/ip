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
     * @throws duke.DukeException
     * @throws FileNotFoundException
     */
    public String interpretGui(String input, duke.TaskList taskList, duke.Storage storage)
            throws duke.DukeException, FileNotFoundException {
        ArrayList<duke.Task> list = taskList.getList();
        if (input.equals("list")) {
            return showListGui(list);
        } else {
            if (input.contains(" ")) {
                int i = input.indexOf(" ");
                String firstWord = input.substring(0, i);
                if (firstWord.equals("done")) {
                    return setDoneGui(input, i, list, storage);
                } else if (firstWord.equals("delete")) {
                    return dealWithDeleteGui(input, i, taskList, list, storage);
                } else if (firstWord.equals("todo")) {
                    return dealWithTodoGui(input, i, taskList, list, storage);
                } else if (firstWord.equals("deadline")) {
                    return dealWithDeadlineGui(input, i, taskList, list, storage);
                } else if (firstWord.equals("event")) {
                    return dealWithEventGui(input, i, taskList, list, storage);
                } else if (firstWord.equals("find")) {
                    return dealWithFindGui(input, i, list);
                } else {
                    throw new duke.DukeException("OOPS!!! I'm sorry, but I don't know what that means. Please enter your task " +
                            "with the starting keyword \"todo\" or \"deadline\" or \"event\".");
                }
            } else {
                if (input.equals("todo")) {
                    throw new duke.DukeException("OOPS!!! The description of a todo cannot be empty.");
                } else if (input.equals("deadline")) {
                    throw new duke.DukeException("OOPS!!! The description of a deadline cannot be empty.");
                } else if (input.equals("event")) {
                    throw new duke.DukeException("OOPS!!! The description of an event cannot be empty.");
                } else {
                    throw new duke.DukeException("OOPS!!! I'm sorry, but I don't know what that means. Please enter your task " +
                            "with the starting keyword \"todo\" or \"deadline\" or \"event\".");
                }
            }
        }
    }

    private String showListGui(ArrayList<duke.Task> list) {
        if (list.isEmpty()) {
            return "List is empty.";
        } else {
            StringBuilder listOutput = new StringBuilder();
            for (int j = 0; j < list.size(); j++) {
                int num = j + 1;
                duke.Task task = list.get(j);
                listOutput.append(num + "." + task.toString() + "\n");
            }
            return listOutput.toString();
        }
    }

    private String setDoneGui(String input, int indexOfSpace, ArrayList<duke.Task> list, duke.Storage storage) throws DukeException, FileNotFoundException {
        int num = Integer.parseInt(input.substring(indexOfSpace + 1));
        if (list.size() < num) {
            throw new duke.DukeException("List does not have that item.");
        } else {
            duke.Task taskToSetToDone = list.get(num - 1);
            taskToSetToDone.setDone();
            storage.update(list);
            return "Nice! I've marked this task as done:" + "\n" + taskToSetToDone.toString();
        }
    }

    private String dealWithDeleteGui(String input, int indexOfSpace, duke.TaskList taskList, ArrayList<duke.Task> list, duke.Storage storage)
            throws DukeException, FileNotFoundException {
        int num = Integer.parseInt(input.substring(indexOfSpace + 1));
        if (list.size() < num) {
            throw new duke.DukeException("List does not have that item.");
        } else {
            Task taskToDelete = list.get(num - 1);
            taskList.remove(num - 1);
            storage.update(list);
            return "Noted. I've removed this task:" + "\n" + taskToDelete.toString() + "\n" + "Now you have " +
                    list.size() + " tasks in the list.";
        }
    }

    private String dealWithTodoGui(String input, int indexOfSpace, duke.TaskList taskList, ArrayList<duke.Task> list, duke.Storage storage)
            throws FileNotFoundException {
        String todoName = input.substring(indexOfSpace + 1);
        Todo newTodo = new Todo(todoName);
        taskList.add(newTodo);
        storage.update(list);
        String str = "Got it. I've added this task:" + "\n" + newTodo.toString() + "\n" + "Now you have " +
                list.size() + " task in the list.";
        return str;
    }

    private String dealWithDeadlineGui(String input, int indexOfSpace, duke.TaskList taskList, ArrayList<duke.Task> list, duke.Storage storage)
            throws FileNotFoundException {
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

    private String dealWithEventGui(String input, int indexOfSpace, duke.TaskList taskList, ArrayList<duke.Task> list, duke.Storage storage)
            throws FileNotFoundException {
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
                duke.Task task = newList.get(j);
                listOutput.append(num + "." + task.toString() + "\n");
            }
            String str = "Here are the matching tasks in your list:" + "\n" + listOutput;
            return str;
        }
    }
}
