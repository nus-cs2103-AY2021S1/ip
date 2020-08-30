package duke;

import java.util.Scanner;

/**
 * duke.Ui class is responsible for printing the necessary messages
 * such that the user would be able to view them on
 * the interface.
 */
public class Ui {

    private static final String GREETING = "Hello Boss! How can I help you?";
    private static final String HORIZONTAL_LINE = "--------------------------------------";
    private static final String SHOW_TASK = "Here are the tasks in your list:";
    private static final String BYE = "Bye Boss! Hope to see you again!";
    private static final String TAB = "   ";
    private static final String FIND_OPENING = "Alright Boss, here are the matching tasks in your list:";
    private static final String FIND_NOTHING = "Sorry Boss, duke can't find anything that matches in your list";

    Scanner sc;

    /**
     * Constructor of duke.Ui.
     */
    Ui() {
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
        String list = "";
        for (int i = 0; i < TaskList.taskLists.size(); i++) {
            int number = i + 1;
            list = list + number + "." + TaskList.taskLists.get(i) + "\n";
        }
        return SHOW_TASK + "\n" + list + HORIZONTAL_LINE;
    }

    /**
     * Returns bye message.
     */
    public String bye() {
        return BYE + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Searches and returns the keywords found in the list.
     * @param description keyword.
     */
    public String printFind(String description) {
        String output = "";
        TaskList.searchKeyword(description);
        if (TaskList.tempLists.size() == 0) {
            output = FIND_NOTHING;
        } else {
            output = FIND_OPENING;
            for (int i = 0; i < TaskList.tempLists.size(); i++) {
                int number = i + 1;
                output += number + "." + TaskList.tempLists.get(i) + "\n";
            }
        }
        output += HORIZONTAL_LINE;
        return output;
    }

    /**
     * Returns task message when added.
     *
     * @param task task to be added.
     */
    public String printTask(Task task) {
        String output = "";
        output = "Got it. I've added this task:" + "\n";
        output += TAB + task + "\n";
        output += "Now you have " + TaskList.taskLists.size() + " tasks in the list." + "\n";
        output += HORIZONTAL_LINE;
        return output;
    }

    /**
     * Returns done message.
     *
     * @param index number of list item to be marked done.
     */
    public String printDone(int index) {
        return "Nice! I've marked this task as done:" + "\n" + TAB + TaskList.taskLists.get(index) + "\n"
                + HORIZONTAL_LINE;
    }

    /**
     * Returns message of delete and deletes the item from the list.
     *
     * @param indexToDelete number of list item to be deleted.
     */
    public String printDelete(int indexToDelete) {
        return "Noted. I've removed this task:" + "\n"
                + TAB + TaskList.taskLists.get(indexToDelete) + "\n"
                + TaskList.taskLists.remove(indexToDelete) + "\n"
                + "Now you have " + TaskList.taskLists.size() + " tasks in the list." + "\n"
                + HORIZONTAL_LINE;
    }

    /**
     * Returns the error message.
     *
     * @param e error message.
     */
    public void printError(String e) {
        System.out.println(e + "\n" + HORIZONTAL_LINE);
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
