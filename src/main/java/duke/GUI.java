package duke;

public class GUI {
    private static final String Message_Bye = "I am happy to serve you. See you soon!";
    GUI() {
    }

    public String printBye() {
        return Message_Bye;
    }



    /**
     * Prints out the current task list
     *
     * @param taskList Task List to be read and printed
     */
    public String printTaskList(TaskList taskList) {
        String output = "This is your task list: \n";

        for (int i = 1; i < taskList.taskCounts + 1; i++) {
            Task task = taskList.tasks.get(i - 1);
            String oneLine = "" + i + "." + task + "\n";
            output = output + oneLine;
        }
        return output;
    }

    /**
     * Prints out the message of empty list
     */
    public String printEmptyList() {
        return "Oops, the task list is empty";
    }

    /**
     * Prints out the current task list
     *
     * @param added Task added successfully
     * @param list  Task List to be added into
     */
    public String addTaskSuccessful(Task added, TaskList list) {
        return  "Congratulations! This is added! \n"
                + added + "\n You have " + list.getTaskCounts() + " tasks in the list";
    }

    /**
     * Deletes the task from the list
     *
     * @param deleted Task deleted successfully
     * @param list    Task List to be deleted from
     */
    public  String deleteTaskSuccessful(Task deleted, TaskList list) {
        return  " Kay, this task now is deleted: \n"
                + deleted + "\n You have " + list.getTaskCounts() + " tasks in the list";
    }

    /**
     * Marks the task as done
     *
     * @param done Task to be changed status into Done
     */
    public String markTaskDone(Task done) {
        return  "Congratulations! This is now marked as done:\n" + done;
    }

    public String failToMarkDone() {
        return "Oops, please specify the task index to be marked";
    }

    /**
     * Warns the user to include task index when finding task to mark done
     */
    public String failToFindTask() {
        return "Oops, please ensure the task index to be marked is correct";
    }

    /**
     * Warns the user to include task index when deleting task
     */
    public String failToDelete() {
        return "Oops, please specify the task index to be deleted";
    }

    /**
     * Warns the user to include task details
     */
    public String failToFindDetails() {
        return "Oops, please specify your task";
    }

    /**
     * Warns the user to include task intended date
     */

    public String failToFindTime() {
        return "Oops, please specify the time for the task";
    }

    /**
     * Warns the user to key in correct commands
     */

    public String failToUnderstand() {
        return "Sorry, I could not answer that ..";
    }

    /**
     * Prints the relevant task to the key word
     */

    public String printFound(String keyWord, TaskList taskList) {
        String output = "This is what I found: \n";
        for (int i = 1; i < taskList.taskCounts + 1; i++) {
            Task task = taskList.tasks.get(i - 1);
            if (task.toString().contains(keyWord)) {
                output = output + "" + i + "." + task + "\n";
            }
        }
        return output;
    }
}
