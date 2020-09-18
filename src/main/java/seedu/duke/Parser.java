package seedu.duke;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {


    /**
     * Parse a line from storage file and generate the corresponding task object.
     * @param taskString String retrieved from storage file.
     * @return The task item that was recorded.
     * @throws DukeException If the String is of invalid format.
     */

    static Task parseFileItemToTask (String taskString) throws DukeException {
        char status = taskString.charAt(5);
        boolean isTodo = taskString.startsWith("[T]");
        boolean isEvent = taskString.startsWith("[E]");
        boolean isDeadline = taskString.startsWith("[D]");
        assert (status == '\u2713' || status == '\u2718')
                : "Data Storage Error: Task status not recognizable";
        assert (isTodo || isDeadline || isEvent)
                : "Data Storage Error: Task type not recognizable";
        if (taskString.isBlank()) {
            return null;
        }
        if (isTodo) {
            String name = taskString.substring(8);
            if (status == '\u2713') {
                return new Todo(name, Task.Status.DONE);
            }
            if (taskString.charAt(5) == '\u2718') {
                return new Todo(name, Task.Status.PENDING);
            }
        }
        if (isDeadline) {
            String name = taskString.split(" by: ")[0].substring(8);
            String dueDate = taskString.split(" by: ")[1];
            if (status == '\u2713') {
                return new Deadline(name, Task.Status.DONE, dueDate);
            }
            if (status == '\u2718') {
                return new Deadline(name, Task.Status.PENDING, dueDate);
            }
        }
        if (isEvent) {
            String name = taskString.split(" at: ")[0].substring(8);
            String dueDate = taskString.split(" at: ")[1];
            if (status == '\u2713') {
                return new Event(name, Task.Status.DONE, dueDate);
            }
            if (status == '\u2718') {
                return new Event(name, Task.Status.PENDING, dueDate);
            }
        }
        return null;
    }


    /**
     * Parse user's input.
     * Store the task created by the user into the storage file if applicable.
     * @param userMessage User input.
     * @param storage Storage item that contains information of data storage file.
     * @throws DukeException If user input is of invalid format.
     * @throws IOException For error with files.
     */
    static void parseInput (String userMessage, Storage storage) throws DukeException, IOException {
        ArrayList<Task> itemList = storage.load();


        if (userMessage.startsWith("find")) {
            ArrayList<Task> selectedTasks = new ArrayList<>();
            String searchedItem = userMessage.substring(5);
            for (Task task: itemList) {
                if (task.name.contains(searchedItem)) {
                    selectedTasks.add(task);
                }
            }
            System.out.println("Here are the matching tasks in your list: ");
            for (int i = 0; i < selectedTasks.size(); i++) {
                Task task = selectedTasks.get(i);
                System.out.println((i + 1) + " " + task.toString());
            }
            return;
        }

        //list down the contents in the list
        if (userMessage.equals("list")) {
            itemList = storage.load();
            System.out.println("Here is your list: ");
            for (int i = 0; i < itemList.size(); i++) {
                Task task = itemList.get(i);
                System.out.println((i + 1) + " " + task.toString());
            }
            return;
        }

        //mark something as done
        if (userMessage.contains("done")) {
            int index = Character.getNumericValue(userMessage.charAt(5)) - 1;
            Task task = itemList.get(index);
            task.markAsDone();
            storage.modifyWithList(itemList);
            System.out.println("Good job! You have finished this task!");
            System.out.println(task.toString());
            return;
        }

        //delete task
        if (userMessage.contains("delete")) {
            int index = Character.getNumericValue(userMessage.charAt(7)) - 1;
            Task task = itemList.get(index);
            itemList.remove(index);
            storage.modifyWithList(itemList);
            System.out.println("I have deleted this task for you: ");
            System.out.println(task.toString());
            System.out.println("You now have " + itemList.size() + " tasks in your list!");
            return;
        }

        //valid task entries
        Task newItem;
        if (userMessage.startsWith("todo")) {
            String name = userMessage.substring(5);
            if (!name.isEmpty() && !name.isBlank()) {
                newItem = new Todo(name, Task.Status.PENDING);
            } else {
                throw new DukeException("Oops, tasks cannot be empty");
            }
        } else if (userMessage.startsWith("deadline")) {
            String name = userMessage.split("/by")[0].substring(9);
            if (!userMessage.contains("/by")) {
                throw new DukeException("Sorry, incorrect format for Deadlines. \n Please specify a Due Date "
                        + "(and task name)");
            }

            if (name.isEmpty() || name.isBlank()) {
                throw new DukeException("Oops, tasks cannot be empty");
            }

            String dueDate = userMessage.split("/by")[1].substring(1);
            newItem = new Deadline(name, Task.Status.PENDING, dueDate);
        } else if (userMessage.startsWith("event")) {
            String name = userMessage.split("/at ")[0].substring(5);
            if (!userMessage.contains("/at")) {
                throw new DukeException("Sorry, incorrect format for Events. \n Please specify a time "
                        +
                        "(and task name)");
            }
            if (name.isEmpty() || name.isBlank()) {
                throw new DukeException("Oops, tasks cannot be empty");
            }

            String time = userMessage.split("/at ")[1];
            newItem = new Event(name, Task.Status.PENDING, time);

        } else {
            throw new DukeException("Sorry, I do not understand this command");
        }
        Storage.todoToFile(newItem);
        System.out.println("new task added: " + newItem.toString());
        System.out.println("You now have " + (itemList.size() + 1) + " tasks in your list!");
    }
}
