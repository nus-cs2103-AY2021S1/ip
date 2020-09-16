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
            return input.substring(Math.min(start + 5, input.length() - 1));
        } else if (type == TaskType.DEADLINE) {
            int start = 0;
            while (!input.substring(start, start + 8).equals("deadline")) {
                start++;
            }
            return input.substring(Math.min(start + 9, input.length() - 1));
        } else {
            int start = 0;
            while (!input.substring(start, start + 5).equals("event")) {
                start++;
            }
            return input.substring(Math.min(start + 6, input.length() - 1));
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
        return input.substring(0, Math.max(0, end - 1));
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
        if (start + 4 > end - 1 || start > end) {
            return "";
        }
        return input.substring(start + 4, (input.charAt(end) != '#' ? end + 1 : end - 1));
    }

    /**
     * Changes DD-MM-YYYY to YYYY-MM-DD
     * @param input the input date
     * @return date string in YYYY-MM-DD
     */
    public static String reverseDateFormatOrder(String input) {
        if (input == null) {
            return null;
        }

        String[] parts = input.split("-");
        if (parts.length != 3) {
            return null;
        }
        if (parts[0].length() == 4) {
            return input;
        } else if (parts[0].length() == 1) {
            return parts[2] + "-" + parts[1] + "-0" + parts[0];
        } else if (parts[0].length() == 2) {
            return parts[2] + "-" + parts[1] + "-" + parts[0];
        } else {
            return input;
        }
    }

    /**
     * Inserts semi-colon in between hours and minutes
     * @param input the input time
     * @return the formatted time String
     */
    public static String insertSemicolon(String input) {
        if (input.length() == 4) {
            return input.substring(0, 2) + ":" + input.substring(2);
        } else {
            return input;
        }
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
        return "[" + getStatusIcon() + "] " + description + (tagList == null || tagList.getSize() == 0 ? "" : "\n" + tagList.toString());
    }

}