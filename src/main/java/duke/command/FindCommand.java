package duke.command;

import duke.main.TaskList;
import duke.task.Task;

/**
 * FindCommand is a Command to find a specific Task based on the given keyword.
 * Currently, it only supports to search Task based on its description by partial word
 * and is not able to find a Task that contains the keyword as a substring.
 */
public class FindCommand extends Command {
    /** Keyword for finding the Task. **/
    private String keyword;
    /** TaskList that is related to this command. **/
    private TaskList tasks;

    /**
     * Constructs a FindCommand.
     *
     * @param keyword The keyword to find the Task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
        tasks = new TaskList();
    }

    /**
     * Finds a Task based on the description with the given keyword.
     *
     * @param t The related TaskList.
     */
    @Override
    public void perform(TaskList t) {
        for (int i = 1; i <= t.getSize(); i++) {
            Task task = t.get(i);
            String description = task.getInfo()[1];
            if (containSameWord(keyword, description)) {
                tasks.add(task);
            }
        }
    }

    /**
     * Checks if a String is contained in another String.
     *
     * @param a First String.
     * @param b Second String that wants to be checked.
     * @return true if String a is contained in String b, otherwise false.
     */
    private boolean containSameWord(String a, String b) {
        boolean hasSameWord = false;
        String[] stringArr = b.split(" ");
        for (String s : stringArr) {
            if (s.equals(a)) {
                hasSameWord = true;
                break;
            }
        }
        return hasSameWord;
    }

    /**
     * Gets the reply after performing the Command.
     *
     * @return A reply as a String based on the perform method.
     **/
    @Override
    public String getReply() {
        String reply;
        if (tasks.getSize() > 0) {
            reply = " Here are the matching tasks in your list:";
            for (int i = 1; i <= tasks.getSize(); i++) {
                Task t = tasks.get(i);
                reply += "\n" + " " + (i + 1) + "." + t.toString();
            }
            return reply;
        } else {
            return " There are no tasks that match the keyword.";
        }
    }

    /**
     * Checks if this is a termination Command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
