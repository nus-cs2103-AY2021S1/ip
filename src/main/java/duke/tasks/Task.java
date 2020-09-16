package duke.tasks;

/**
 * A task has a name/description and a status of whether it is done or not.
 */
public class Task {
    public String name;
    public boolean isDone;

    /**
     * Constructs a new Task
     *
     * @param name Take in the name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public String markAsDone() {
        this.isDone = true;
        assert this.isDone: "OOPS! Failed to mark this task as done.";
        String result = "Nice! I've marked this task as done:"+"\n";
//        System.out.println("Nice! I've marked this task as done:");
//        System.out.println(this.toString());
        result = result + this.toString() + "\n";
        return result;
    }


    /**
     * Sets the status of the task.
     * @param status Whether the task is done or not. "true" means is done and "false" means not done yet
     */
    public void setStatus(String status) {
        isDone = status.equals("true");
    }
    public void setStatus(boolean status) {
        isDone = status;
    }

    /**
     * Prints out the name of the task.
     */
    public void showName() {
        System.out.println(this.name);
    }

    /**
     * Prints out the description of the task.
     *
     * @param index The position index of the task in a list of tasks.
     */
    public String showTask(int index) {
        String result = "";
        result = result + index + ". " + this.toString() + "\n";
//        System.out.println(index + ". " +this.toString());
        return result;
    }

    /**
     * Gets the tyoe of the task.
     * It will be overridden by child classes.
     * @return The general type which is "Task"
     */
    public String getType() {
        return "Task";
    }
    /**
     * Gets the time of the task.
     * It will be overridden by child classes.
     * @return ""
     */
    public String getTime() {
        return "";
    }

    /**
     * Shows the content of the task, which will be stored and displayed in duke.txt.
     * @return The content of the task.
     */
    public String showContent() {
        int status;
        if (isDone) {
            status = 1;
        } else {
            status = 0;
        }
        String commonContent = this.getType() + " | " + status + " | " + this.name;
        String content;
        if (this instanceof Deadline || this instanceof Event) {
            content = commonContent + " | " + this.getTime();
        } else {
            content = commonContent;
        }

        return content + "\n";
    }

    @Override
    /**
     * The toString method of Task
     * Print the correct format of a task.
     */
    public String toString() {

        String isDoneSymbol;

        if (isDone) {
            isDoneSymbol = "\u2713";
        } else {
            isDoneSymbol = "\u2718";
        }

        return "[" + isDoneSymbol + "] " + name;

    }
}
