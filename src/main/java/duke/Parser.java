package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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
     * @throws duke.DukeException
     * @throws FileNotFoundException
     */
    public void interpret(String input, duke.TaskList taskList, duke.Storage storage) throws duke.DukeException, FileNotFoundException {
        ArrayList<duke.Task> list = taskList.getList();
        if (input.equals("list")) {
            if (list.isEmpty()) {
                System.out.println("List is empty.");
            } else {
                StringBuilder listOutput = new StringBuilder();
                for (int j = 0; j < list.size(); j++) {
                    int num = j + 1;
                    duke.Task task = list.get(j);
                    listOutput.append(num + "." + task.toString() + "\n");
                }
                System.out.println(listOutput);
            }
        } else {
            if (input.contains(" ")) {
                int i = input.indexOf(" ");
                String firstWord = input.substring(0, i);
                if (firstWord.equals("done")) {
                    // what if task is not in the list
                    int num = Integer.parseInt(input.substring(i+1));
                    if (list.size() < num) {
                        throw new duke.DukeException("List does not have that item.");
                    } else {
                        duke.Task taskToSetToDone = list.get(num-1);
                        taskToSetToDone.setDone();
                        System.out.println("Nice! I've marked this task as done:" + "\n" + taskToSetToDone.toString());
                        storage.update(list);
                    }
                } else if (firstWord.equals("delete")) {
                    // what if task is not in the list
                    int num = Integer.parseInt(input.substring(i+1));
                    if (list.size() < num) {
                        throw new duke.DukeException("List does not have that item.");
                    } else {
                        taskList.remove(num-1);
                        storage.update(list);
                    }
                } else if (firstWord.equals("todo")) {
                    String todoName = input.substring(i+1);
                    Todo newTodo = new Todo(todoName);
                    taskList.add(newTodo);
                    storage.update(list);
                } else if (firstWord.equals("deadline")) {
                    int index = input.indexOf("/");
                    String deadlineName = input.substring(i+1, index);
                    String deadlineTime = input.substring(index+4);
                    Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
                    taskList.add(newDeadline);
                    storage.update(list);
                } else if (firstWord.equals("event")) {
                    int index = input.indexOf("/");
                    String eventName = input.substring(i+1, index);
                    String eventTime = input.substring(index+4);
                    Event newEvent = new Event(eventName, eventTime);
                    taskList.add(newEvent);
                    storage.update(list);
                } else {
                    // throw exception
                    throw new duke.DukeException("OOPS!!! I'm sorry, but I don't know what that means. Please enter your task with the " +
                            "starting keyword \"todo\" or \"deadline\" or \"event\".");
                }
            } else {
                // throw exception
                if (input.equals("todo")) {
                    // throw empty todo exception
                    throw new duke.DukeException("OOPS!!! The description of a todo cannot be empty.");
                } else if (input.equals("deadline")) {
                    // throw empty deadline
                    throw new duke.DukeException("OOPS!!! The description of a deadline cannot be empty.");
                } else if (input.equals("event")) {
                    // throw empty deadline
                    throw new duke.DukeException("OOPS!!! The description of an event cannot be empty.");
                } else {
                    // throw idk what it means exception
                    throw new duke.DukeException("OOPS!!! I'm sorry, but I don't know what that means. Please enter your task with the " +
                            "starting keyword \"todo\" or \"deadline\" or \"event\".");
                }
            }
        }
    }
}
