package duke.commands;

import duke.exception.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;


/**
 * A class that represents find command.
 */
public class FindCommand {

    /**
     * Executes the find command from user input.
     *
     * @param instruction the string of user input instruction.
     * @param tasks the tasklist that stores all the tasks.
     * @return a task list representing the tasks found.
     * @throws DukeException when the instruction format is incorrect.
     */
    public static TaskList executeCommand(String instruction, TaskList tasks) throws DukeException {
        if (instruction.substring(4).strip().equals("")) {
            throw new DukeException("The \"find\" command is not entered correctly");
        }
        String keyword = instruction.substring(4).strip();
        TaskList matches = findTaskByKeyword(keyword, tasks);
        return matches;
    }


    /**
     * Finds the list of tasks that match the keyword.
     *
     * @param keyword the string of keyword that the user wants to search for.
     * @param tasks the current list of tasks.
     * @return the tasklist of tasks that matches the keyword in the input.
     */
    public static TaskList findTaskByKeyword(String keyword, TaskList tasks) {
        TaskList matches = new TaskList();

        for (int i = 0; i < tasks.getSize(); i++) {
            boolean isMatch = false;
            Task temp = tasks.get(i);
            if (temp.getDescription().contains(keyword)) {
                isMatch = true;
            }
            if (isMatch) {
                matches.add(temp);
            }
        }

        return matches;
    }
}
