package duke;

import java.util.ArrayList;

public class AppUi extends Ui {

    public AppUi(){}

    /**
     * Returns the input empty error message
     * @return the input empty error message
     */
    public String getInputEmptyErrorMsg(){
        return "\uD83D\uDE43 OOPS!!! input cannot be empty.";
    }

    /**
     * Returns the bye message
     * @return the bye message
     */
    public String getByeMessage(){
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the mark as done message
     * @param task the task to be marked as done
     * @return the mark as done message
     */
    public String getMarkAsDoneMsg(Task task){
        return "Nice! I've marked this task as done: " + task.toString();
    }

    /**
     * Returns message when task number exceed TaskList size
     * @param taskNumber the specified task number
     * @param listSize the size of the TaskList
     * @return message when task number exceed TaskList size
     */
    public String getTaskNumberExceedMsg(int taskNumber, int listSize) {
        return "no such task: task " + taskNumber + " as you only have " + listSize + " in total";
    }

    /**
     * Returns wrong format after done message
     * @return wrong format after done message
     */
    public String getWrongFormatAfterDoneMsg() {
        return "\uD83D\uDE43 wrong input after the word \"done\"";
    }

    /**
     * Returns wrong format after the word "delete" in delete command message
     * @return wrong format after the word "delete" in delete command message
     */
    public String getWrongFormatAfterDeleteMsg() {
        return "\uD83D\uDE43 wrong input after the word \"delete\"";
    }

    /**
     * Returns delete message
     * @param task the Task to be deleted
     * @param list the TaskList object
     * @return delete message
     */
    public String getDeleteMessage(Task task, TaskList list) {
        return "Noted. I've removed this task:\n" + task.toString()
                + String.format("\nNow you have %d tasks in the list.", list.getSize());
    }

    /**
     * Returns get description empty message
     * @return get description empty message
     */
    public String getDescriptionEmptyMsg() {
        return "\uD83D\uDE43 OOPS!!! The description of a todo cannot be empty.";
    }

    /**
     * Returns message after add task
     * @param task the Task to be added
     * @param type the type of Task
     * @param listSize the size of TaskList
     * @return message after add task
     */
    public String getAfterAddMsg(Task task, int type, int listSize) {
        if (type == TODO) {
            Todo newTodo = (Todo) task;
            return "Got it. I've added this task:\n"
                    + newTodo.toString() + "\n"
                    + String.format("Now you have %d tasks in the list.", listSize);
        } else if (type == DEADLINE) {
            Deadline ddl = (Deadline) task;
            return "Got it. I've added this task:\n"
                    + ddl.toString() + "\n"
                    + String.format("Now you have %d tasks in the list.", listSize);
        } else {
            Event event = (Event) task;
            return "Got it. I've added this task:\n"
                    + event.toString() + "\n"
                    + String.format("Now you have %d tasks in the list.", listSize);
        }
    }

    /**
     * Returns wrong deadline format message
     * @return wrong deadline format message
     */
    public String getWrongDeadlineFormatMsg() {
        return "\uD83D\uDE43 OOPS!!! The description and time of a deadline cannot be empty."
                + " Or maybe you used \"at\" instead of \"by\"?";
    }

    /**
     * Returns wrong event format message
     * @return wrong event format message
     */
    public String getWrongEventFormatMsg() {
        return "\uD83D\uDE43 OOPS!!! The description and time of a event cannot be empty."
                + " Or maybe you used \"by\" instead of \"at\"?";
    }

    /**
     * Returns wrong date format message
     * @return wrong date format message
     */
    public String getWrongDateFormatMsg() {
        return "Wrong date input format. Correct format should be \"YYYY-MM-DD\"\n";
    }

    /**
     * Returns wrong time format message
     * @return wrong time format message
     */
    public String getWrongTimeFormatMsg() {
        return "Wrong time format. Correct format should be \"HH:MM\"\n";
    }

    /**
     * Returns no such command message
     * @return no such command message
     */
    public String getNoSuchCommandMsg() {
        return "\uD83D\uDE43 Sorry~ please specify whether this is a todo or a deadline or a event\n"
                + "put the word \"todo\" or \"deadline\" or \"event\" in front of your description";
    }

    /**
     * Returns the Tasks of finding result for matching keyword
     * @param list the list oof Tasks that contains matching keyword
     * @return the Tasks of finding result for matching keyword
     */
    public String getFindResult(ArrayList<Task> list) {
        StringBuilder res = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            res.append(String.format("%d. ", i + 1)).append(list.get(i).toString()).append("\n");
        }
        return res.toString();
    }

    /**
     * Returns the wrong find format error
     * @return the wrong find format error
     */
    public String getWrongFindFormatMsg() {
        return "Wrong \"find\" command format.\nCorrect is \"find {keyword}\" "
                + "where {keyword} is the keyword you want to search";
    }

    /**
     * Returns the Duke logo
     * @return the Duke logo
     */
    public static String getLogo(){
        return "Hello from\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }
}
