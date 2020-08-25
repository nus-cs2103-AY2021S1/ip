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
    public static String parse(String input, TaskList tasks) throws DukeException {
        Ui ui = new Ui();
        String[] splitArr = input.split(" ");
        if (input.equals("bye")) {
            return ui.goodbye();
        } else if (input.equals("list")) {
            ui.list(tasks);
        } else if (splitArr.length == 2 && splitArr[0].equals("done") && Integer.parseInt(splitArr[1]) > 0) {
            int index = Integer.parseInt(splitArr[1]);
            if (index > tasks.size() || index < 0) {
                throw new DukeException("That task number does not exist.");
            }
            tasks.get(index - 1).setDone();
            Task t = tasks.get(index - 1);
            ui.done(t);
        } else if (splitArr.length == 2 && splitArr[0].equals("delete") && Integer.parseInt(splitArr[1]) > 0) {
            int index = Integer.parseInt(splitArr[1]);
            if (index > tasks.size() || index < 0) {
                throw new DukeException("That task number does not exist.");
            }
            Task deletedTask = tasks.remove(index - 1);
            ui.delete(deletedTask, tasks.size());
        } else {
            switch (splitArr[0]) {
                case "todo":
                    if (splitArr.length <= 1) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    Task newTask = new ToDo(input.substring(5));
                    tasks.add(newTask);
                    ui.add(newTask, tasks.size());
                    break;
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
                            ui.add(newDeadline, tasks.size());
                        } catch (Exception e) {
                            throw new DukeException("Please enter a valid YYYY-MM-DD date format!");
                        }
                        break;
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
                            ui.add(newEvent, tasks.size());
                        } catch (Exception e) {
                            throw new DukeException("Please enter a valid YYYY-MM-DD date format!");
                        }
                        break;
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            }
            ArrayList<Task> tasksCopy = tasks.clone();
            Storage.store(tasksCopy);
            return "";
        }
}
