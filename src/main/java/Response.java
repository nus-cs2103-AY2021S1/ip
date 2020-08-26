public class Response {
    enum Tag {
        LIST,
        ADD,
        REMOVE,
        NORMAL,
        FIND
    }
    public Task[] tasks;
    public String[] texts;
    public Tag tag;
    public int numOfTasks;

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
     * @param tasks The task to be added or removed.
     * @param tag Removal or addition of the task.
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
    public Response(Task[] tasks) {
        this.tasks = tasks;
        this.tag = Tag.LIST;
    }

    public String getResponse() {
        String line = "    __________________________________________________________ \n";
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
        } else if (this.tag == Tag.FIND ) {
            for (int i = 0; i < this.tasks.length; i++) {
                linesOfText += "     "
                        + "Here are the matching tasks in your list: \n"
                        + "       "
                        + this.tasks[i]
                        + "\n";
            }
        } else {
            for (int i = 0; i < this.texts.length; i++) {
                linesOfText += "     "
                        + this.texts[i] + "\n";
            }
        }
        String output = line + linesOfText + line;
        return output;
    }
}
