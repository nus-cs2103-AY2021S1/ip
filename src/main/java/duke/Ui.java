package duke;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Ui class is responsible for printing the necessary messages
 * such that the user would be able to view them on
 * the interface.
 */
public class Ui {

    private static final String GREETING = "Hello Boss! How can I help you?";
    private static final String SHOW_TASK = "Here are the tasks in your list:";
    private static final String BYE = "Bye Boss! Hope to see you again!";
    private static final String TAB = "   ";
    private static final String FIND_OPENING = "Alright Boss, here are the matching tasks in your list:";
    private static final String FIND_NOTHING = "Sorry Boss, duke can't find anything that matches in your list";

    private Scanner sc;

    /**
     * Constructor of Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the next sentence of user input.
     *
     * @return string sentence of user input.
     */
    public String getCommand() {
        return sc.nextLine();
    }

    /**
     * Returns greeting message.
     */
    public String greeting() {
        return GREETING;
    }

    /**
     * Shows all the list.
     */
    public String showList() {
        if (TaskList.getTaskLists().size() == 0) {
            return "Currently, you do not have any item in the list.";
        }
        String list = "";
        for (int i = 0; i < TaskList.getTaskLists().size(); i++) {
            int number = i + 1;
            list = list + number + "." + TaskList.getTaskLists().get(i) + "\n";
        }
        return SHOW_TASK + "\n" + list;
    }

    /**
     * Returns bye message.
     */
    public String bye() {
        return BYE + "\n";
    }

    /**
     * Searches and returns the keywords found in the list.
     *
     * @param description keyword.
     */
    public String printFind(String description) {
        String output = "";
        TaskList.searchKeyword(description);
        if (TaskList.getTempLists().size() == 0) {
            output = FIND_NOTHING;
        } else {
            output = FIND_OPENING;
            for (int i = 0; i < TaskList.getTempLists().size(); i++) {
                int number = i + 1;
                output += number + "." + TaskList.getTempLists().get(i) + "\n";
            }
        }
        return output;
    }

    /**
     * Returns task message when added.
     *
     * @param task task to be added.
     */
    public String printTask(Task task) {
        return "Got it. I've added this task:" + "\n"
                + TAB + task + "\n"
                + "Now you have " + TaskList.getTaskLists().size() + " tasks in the list." + "\n";
    }

    /**
     * Returns done message.
     *
     * @param index number of list item to be marked done.
     */
    public String printDone(int index) {
        return "Nice! I've marked this task as done:" + "\n" + TAB + TaskList.getTaskLists().get(index) + "\n";
    }

    /**
     * Returns message of delete and deletes the item from the list.
     *
     * @param indexToDelete number of list item to be deleted.
     */
    public String printDelete(int indexToDelete) {
        return "Noted. I've removed this task:" + "\n"
                + TAB + TaskList.getTaskLists().get(indexToDelete) + "\n"
                + TaskList.getTaskLists().remove(indexToDelete) + "\n"
                + "Now you have " + TaskList.getTaskLists().size() + " tasks in the list." + "\n";
    }

    /**
     * Prints the statistics depending on command.
     * If command is total, duke will return total number of items in the list.
     * If command is done, duke will return total number of items that
     * are done in the list and return the list itself.
     *
     * @param command command
     * @return String string
     */
    public String printStats(String command) {
        try {
            if (command.toLowerCase().equals("total")) {
                return "You have " + String.valueOf(TaskList.getTaskLists().size())
                        + " pending jobs to do in total Boss!";
            } else if (command.equals("done")) {
                List<Task> tasks = TaskList.getTaskLists();
                List<Task> tasksDone;
                tasksDone = tasks.stream().filter(task -> task.isDone).collect(Collectors.toList());
                String output = "Here are the done list: " + "\n";
                for (int i = 0; i < tasksDone.size(); i++) {
                    int number = i + 1;
                    output += number + "." + tasksDone.get(i) + "\n";
                }
                output += "Boss you have a total of " + tasksDone.size() + " things that are done";
                return output;
            } else {
                return "Sorry Boss! There is no tasks that are done.";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the error message.
     *
     * @param e error message.
     */
    public void printError(String e) {
        System.out.println(e + "\n");
    }

    /**
     * Returns the error message of date time parse error.
     *
     * @param e error message.
     */
    public void printDateTimeParseError(String e) {
        System.out.println(TAB + "Please enter date in 'yyyy-MM-dd' format");
    }

}
