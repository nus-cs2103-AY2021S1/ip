package duke.task;

public class Task {
    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    protected String description;
    protected boolean isDone;
    protected TagList tagList;

    /**
     * Trims the first word
     * @param input user input
     * @param type type of task
     * @return the trimmed input
     */
    public static String trim(String input, TaskType type) {
        if (type == TaskType.TODO) {
            int start = 0;
            while (!input.substring(start, start + 4).equals("todo")) {
                start++;
            }
            return input.substring(start + 5);
        } else if (type == TaskType.DEADLINE) {
            int start = 0;
            while (!input.substring(start, start + 8).equals("deadline")) {
                start++;
            }
            return input.substring(start + 9);
        } else {
            int start = 0;
            while (!input.substring(start, start + 5).equals("event")) {
                start++;
            }
            return input.substring(start + 6);
        }
    }

    /**
     * Gets the description of the task
     * @param input trimmed user input
     * @return the description of the task
     */
    public static String getDescription(String input, TaskType type) {
        String word = type == TaskType.DEADLINE ? "/by" : "/at";
        int end = 0, len = input.length();
        while (end + 3 < len && !input.substring(end, end + 3).equals(word)) {
            end++;
        }
        if (end + 3 >= len) {
            end = len + 1;
        }
        return input.substring(0, end - 1);
    }

    /**
     * Gets the time of the deadline or event
     * @param input trimmed user input
     * @return the time of the deadline ot event
     */
    public static String getTime(String input, TaskType type) {
        String word = type == TaskType.DEADLINE ? "/by" : "/at";
        int start = 0, len = input.length();
        while (start + 3 < len && !input.substring(start, start + 3).equals(word)) {
            start++;
        }
        int end = start + 1;
        while (end < len && input.charAt(end) != '#') {
            end++;
        }
        if (end >= len) {
            end = len - 1;
        }
        if (start + 4 < end - 1) {
            return "";
        }
        return input.substring(start + 4, (input.charAt(end) != '#' ? end + 1 : end - 1));
    }

    /**
     * Returns a String array of tags of a task
     * @param input description of a task
     * @return a String array of tags of a task
     */
    public static String[] getTags(String input){
        int i = 0, len = input.length();
        while (i < len && input.charAt(i) != '#') {
            i++;
        }

        if (i + 1 >= len) {
            return null;
        }

        String trimmed = input.substring(i + 1);
        return trimmed.split("#");
    }

    /**
     * Constructs a Task object
     * @param description the description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task object
     * @param description the description of the Task
     * @param isDone whether the task is done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the Task
     * @return the status icon of the Task
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the Task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the description of the Task
     * @return the description of the Task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the isDone of the task
     * @return the isDone of the task
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Returns the TagList
     * @return the TagList
     */
    public TagList getTagList() {
        return tagList;
    }

    /**
     * Sets the tagList
     * @param tagList the TagList to be set
     */
    public void setTagList(TagList tagList) {
        this.tagList = tagList;
    }

    /**
     * Overrides the toString method
     * @return the String
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description + (tagList == null ? "" : "\n" + tagList.toString());
    }

}