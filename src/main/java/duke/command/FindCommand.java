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
            if (contain(keyword, description)) {
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
    private boolean contain(String a, String b) {
        boolean hasSameWord = false;
        String[] arrayB = b.split(" ");
        for (String s : arrayB) {
            if (isPrefixOf(a, s)) {
                hasSameWord = true;
                break;
            }
        }
        return hasSameWord;
    }

    /**
     * Checks if a String is a prefix of another String.
     *
     * @param a First String as the prefix.
     * @param b Second String that wants to be checked.
     * @return true if a is prefix of b.
     */
    private boolean isPrefixOf(String a, String b) {
        if (b.length() < a.length()) {
            return false;
        }

        char[] arrayA = a.toCharArray();
        char[] arrayB = b.toCharArray();

        for (int i = 0; i < arrayA.length; i++) {
            if (arrayA[i] != arrayB[i]) {
                return false;
            }
        }
        return true;
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
                reply += "\n" + " " + (i) + "." + t.toString();
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
