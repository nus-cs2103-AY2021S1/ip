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

    public Response(String[] texts) {
        this.texts = texts;
        this.tag = Tag.NORMAL;
    }

    public Response(Task[] tasks, Tag tag, int numOfTasks) {
        this.tasks = tasks;
        this.tag = tag;
        this.numOfTasks = numOfTasks;
    }

    public Response(Task[] tasks, Tag tag) {
        this.tasks = tasks;
        this.tag = tag;
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
