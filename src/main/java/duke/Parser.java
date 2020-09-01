package duke;

import java.util.ArrayList;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    /**
     * Processes each line of user input in accordance to the user's TaskList.
     * Triggers the appropriate response to the user via the Ui class methods.
     *
     * @param input Line of user input.
     * @param tasks The user's current task list.
     * @return Returns a ui.goodbye() if the user inputs "bye", which ends the program.
     * @throws DukeException  If user input is incorrect.
     */
    public static String parse(String input, TaskList tasks, boolean isRunningOnGui) throws DukeException {
        Ui ui = new Ui();
        String[] splitArr = input.split(" ");
        if (input.equals("bye")) {
            return ui.goodbye(isRunningOnGui);
        } else if (input.equals("list")) {
            return ui.list(tasks, isRunningOnGui);
        } else if (splitArr.length == 2 && splitArr[0].equals("done") && Integer.parseInt(splitArr[1]) > 0) {
            int index = Integer.parseInt(splitArr[1]);
            if (index > tasks.size() || index < 0) {
                throw new DukeException("That task number does not exist.");
            }
            tasks.get(index - 1).setDone();
            Task t = tasks.get(index - 1);
            ArrayList<Task> tasksCopy = tasks.clone();
            Storage.store(tasksCopy);
            return ui.done(t, isRunningOnGui);
        } else if (splitArr.length == 2 && splitArr[0].equals("delete") && Integer.parseInt(splitArr[1]) > 0) {
            int index = Integer.parseInt(splitArr[1]);
            if (index > tasks.size() || index < 0) {
                throw new DukeException("That task number does not exist.");
            }
            Task deletedTask = tasks.remove(index - 1);
            ArrayList<Task> tasksCopy = tasks.clone();
            Storage.store(tasksCopy);
            return ui.delete(deletedTask, tasks.size(), isRunningOnGui);
        } else if (splitArr.length == 2 && splitArr[0].equals("find")) {
            String keyWord = splitArr[1];
            ArrayList<Task> foundTasks = tasks.find(keyWord);
            return ui.find(foundTasks, isRunningOnGui);
        } else {
            switch (splitArr[0]) {
            case "todo":
                if (splitArr.length <= 1) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                Task newTask = new ToDo(input.substring(5));
                tasks.add(newTask);
                ArrayList<Task> tasksCopy = tasks.clone();
                Storage.store(tasksCopy);
                return ui.add(newTask, tasks.size(), isRunningOnGui);
            case "deadline":
                if (splitArr.length <= 1) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                int index = input.indexOf("/");
                if (index == -1) {
                    throw new DukeException("Please include the date of the deadline!");
                }
                String desc = input.substring(9, index - 1);
                String date = input.substring(index + 4);
                try {
                    Task newDeadline = new Deadline(desc, date);
                    tasks.add(newDeadline);
                    ArrayList<Task> taskCopy = tasks.clone();
                    Storage.store(taskCopy);
                    return ui.add(newDeadline, tasks.size(), isRunningOnGui);
                } catch (Exception e) {
                    throw new DukeException("Please enter a valid YYYY-MM-DD date format!");
                }
            case "event":
                if (splitArr.length <= 1) {
                    throw new DukeException("The description of an event cannot be empty.");
                }
                int ind = input.indexOf("/");
                if (ind == -1) {
                    throw new DukeException("Please include the date of the event!");
                }
                String des = input.substring(6, ind - 1);
                String dat = input.substring(ind + 4);
                try {
                    Task newEvent = new Event(des, dat);
                    tasks.add(newEvent);
                    ArrayList<Task> tasCopy = tasks.clone();
                    Storage.store(tasCopy);
                    return ui.add(newEvent, tasks.size(), isRunningOnGui);
                } catch (Exception e) {
                    throw new DukeException("Please enter a valid YYYY-MM-DD date format!");
                }
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
