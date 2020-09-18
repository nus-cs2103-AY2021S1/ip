package duke;

import duke.task.Task;

/**
 * Creates a dialog box as a response.
 */
public class Response {
    enum Tag {
        LIST,
        ADD,
        REMOVE,
        NORMAL,
        FIND
    }

    private Task[] tasks;
    private String[] texts;
    private Tag tag;
    private int numOfTasks;

    /**
     * Creates the response of normal texts.
     *
     * @param texts An array of strings. Each string will be printed in one line.
     */
    public Response(String[] texts) {
        this.texts = texts;
        this.tag = Tag.NORMAL;
    }

    /**
     * Creates the response after receiving task related commands from the user
     * like adding task to the list.
     *
     * @param tasks      The task to be added or removed.
     * @param tag        Removal or addition of the task.
     * @param numOfTasks The number of Tasks in the list.
     */
    public Response(Task[] tasks, Tag tag, int numOfTasks) {
        this.tasks = tasks;
        this.tag = tag;
        this.numOfTasks = numOfTasks;
    }

    /**
     * Creates the response after receiving some other task related commands from the user
     * like listing the tasks in the list.
     *
     * @param tasks The list of the tasks in the list.
     */
    public Response(Task[] tasks, Tag tag) {
        this.tasks = tasks;
        this.tag = tag;
    }

    /**
     * Gets the string representation of the response.
     *
     * @return The string representation of the response from duke.
     */
    public String getResponse() {
        String linesOfText = "";
        if (this.tag == Tag.LIST) {
            for (int i = 0; i < this.tasks.length; i++) {
                linesOfText += "     " + String.format("%d. ", i + 1) + this.tasks[i] + "\n";
            }
        } else if (this.tag == Tag.ADD) {
            for (int i = 0; i < this.tasks.length; i++) {
                linesOfText += "     "
                        + "Got it. I've added this task: \n"
                        + "       "
                        + this.tasks[i]
                        + "\n"
                        + "     "
                        + String.format("Now you have %d tasks in the list. \n", numOfTasks);
            }
        } else if (this.tag == Tag.REMOVE) {
            for (int i = 0; i < this.tasks.length; i++) {
                linesOfText += "     "
                        + "Got it. I've removed this task: \n"
                        + "       "
                        + this.tasks[i]
                        + "\n"
                        + "     "
                        + String.format("Now you have %d tasks in the list. \n", numOfTasks);
            }
        } else if (this.tag == Tag.FIND) {
            try {
                linesOfText += "     "
                        + "Here are the matching tasks in your list: \n";
                for (int i = 0; i < this.tasks.length; i++) {
                    linesOfText += "       "
                            + this.tasks[i]
                            + "\n";
                }
            } catch (NullPointerException e) {
                Response msg = new Response(new String[]{"There is no matching tasks!"});
                System.out.println(msg.getResponse());
            }
        } else {
            for (int i = 0; i < this.texts.length; i++) {
                linesOfText += "     "
                        + this.texts[i] + "\n";
            }
        }
        String output = linesOfText;
        return output;
    }
}
