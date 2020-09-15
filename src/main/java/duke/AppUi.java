package duke;

import duke.task.*;

import java.util.ArrayList;
import java.util.function.Supplier;

public class AppUi extends Ui {

    public AppUi(){}

    Supplier<String> byeMessage = () -> "Bye. Hope to see you again soon! o(=*T*=)m";
    Supplier<String> inputEmptyErrorMsg = () -> "\uD83D\uDE43 OOPS!!! input cannot be empty. o(=*T*=)m";
    Supplier<String> wrongFormatAfterDoneMsg = () -> "\uD83D\uDE43 wrong input after the word \"done\" o(=*T*=)m";
    Supplier<String> wrongFormatAfterDeleteMsg = () -> "\uD83D\uDE43 wrong input after the word \"delete\" o(=*T*=)m";
    Supplier<String> descriptionEmptyMsg = () -> "\uD83D\uDE43 OOPS!!! The description of a todo cannot be empty. o(=*T*=)m";
    Supplier<String> wrongDeadlineFormatMsg = () -> "\uD83D\uDE43 OOPS!!! The description and time of a deadline cannot be empty."
            + " Or maybe you used \"at\" instead of \"by\"? o(=*T*=)m";
    Supplier<String> wrongEventFormatMsg = () -> "\uD83D\uDE43 OOPS!!! The description and time of a event cannot be empty."
            + " Or maybe you used \"by\" instead of \"at\"? o(=*T*=)m";
    Supplier<String> wrongDateFormatMsg = () -> "Wrong date input format. Correct format should be \"YYYY-MM-DD\"\n";
    Supplier<String> wrongTimeFormatMsg = () -> "Wrong time format. Correct format should be \"HH:MM\"\n";
    Supplier<String> noSuchCommandMsg = () -> "\uD83D\uDE43 Sorry~ please specify whether this is a todo or a deadline or a event\n"
            + "put the word \"todo\" or \"deadline\" or \"event\" in front of your description o(=*T*=)m";
    Supplier<String> wrongFindFormatMsg = () -> "Wrong \"find\" command format.\nCorrect is \"find {keyword}\" "
            + "where {keyword} is the keyword you want to search o(=*T*=)m";

    /**
     * Returns the input empty error message
     * @return the input empty error message
     */
    public String getInputEmptyErrorMsg(){
        return inputEmptyErrorMsg.get();
    }

    /**
     * Returns the bye message
     * @return the bye message
     */
    public String getByeMessage(){
        return byeMessage.get();
    }

    /**
     * Returns the mark as done message
     * @param task the task to be marked as done
     * @return the mark as done message
     */
    public String getMarkAsDoneMsg(Task task){
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Returns message when task number exceed TaskList size
     * @param taskNumber the specified task number
     * @param listSize the size of the TaskList
     * @return message when task number exceed TaskList size
     */
    public String getTaskNumberExceedMsg(int taskNumber, int listSize) {
        return "no such task: task " + taskNumber + " as you only have " + listSize + " in total o(=*T*=)m";
    }

    /**
     * Returns wrong format after done message
     * @return wrong format after done message
     */
    public String getWrongFormatAfterDoneMsg() {
        return wrongFormatAfterDoneMsg.get();
    }

    /**
     * Returns wrong format after the word "delete" in delete command message
     * @return wrong format after the word "delete" in delete command message
     */
    public String getWrongFormatAfterDeleteMsg() {
        return wrongFormatAfterDeleteMsg.get();
    }

    /**
     * Returns delete message
     * @param task the Task to be deleted
     * @param list the TaskList object
     * @return delete message
     */
    public String getDeleteMessage(Task task, TaskList list) {
        return "Noted. I've removed this task:\n" + task.toString()
                + String.format("\nNow you have %d tasks in the list. o(=*T*=)m", list.getSize());
    }

    /**
     * Returns get description empty message
     * @return get description empty message
     */
    public String getDescriptionEmptyMsg() {
        return descriptionEmptyMsg.get();
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
                    + String.format("Now you have %d tasks in the list. o(=*T*=)m", listSize);
        } else if (type == DEADLINE) {
            Deadline ddl = (Deadline) task;
            return "Got it. I've added this task:\n"
                    + ddl.toString() + "\n"
                    + String.format("Now you have %d tasks in the list. o(=*T*=)m", listSize);
        } else {
            Event event = (Event) task;
            return "Got it. I've added this task:\n"
                    + event.toString() + "\n"
                    + String.format("Now you have %d tasks in the list. o(=*T*=)m", listSize);
        }
    }

    /**
     * Returns message after add multiple tasks
     * @param type type of the task
     * @param overallListSize the overall list size
     * @param list list of tasks
     * @return message after add multiple tasks
     */
    public String getAfterAddMsgVarargs(int type, int overallListSize, Task ... list){
        String res = "Got it. I've added this task:\n";
        for (int i = 0; i < list.length; i++) {
            if (type == TODO) {
                Todo todo = (Todo) list[i];
                res += (todo.toString() + "\n");
            } else if (type == DEADLINE) {
                Deadline ddl = (Deadline) list[i];
                res += (ddl.toString() + "\n");
            } else {
                Event event = (Event) list[i];
                res += (event.toString() + "\n");
            }
        }
        return res + String.format("Now you have %d tasks in the list. o(=*T*=)m", overallListSize);
    }

    /**
     * Returns wrong deadline format message
     * @return wrong deadline format message
     */
    public String getWrongDeadlineFormatMsg() {
        return wrongDeadlineFormatMsg.get();
    }

    /**
     * Returns wrong event format message
     * @return wrong event format message
     */
    public String getWrongEventFormatMsg() {
        return wrongEventFormatMsg.get();
    }

    /**
     * Returns wrong date format message
     * @return wrong date format message
     */
    public String getWrongDateFormatMsg() {
        return wrongDateFormatMsg.get();
    }

    /**
     * Returns wrong time format message
     * @return wrong time format message
     */
    public String getWrongTimeFormatMsg() {
        return wrongTimeFormatMsg.get();
    }

    /**
     * Returns no such command message
     * @return no such command message
     */
    public String getNoSuchCommandMsg() {
        return noSuchCommandMsg.get();
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
        return wrongFindFormatMsg.get();
    }

    /**
     * Returns the Duke logo
     * @return the Duke logo
     */
    public static String getLogo(){
        return "Hello from\n"
                + " ___            _        \n"
                + "|  _  \\  _   _  | |  _    ___ \n"
                + "| | |  |  | | | |   | |/ /      / _ \\\n"
                + "| |_|  |  | |_| |   |  <     |  __/\n"
                + "|___/  \\__,_|_ |  |\\_ \\ ___|\n"
                + "o(=*T*=)m\n";
    }
}
