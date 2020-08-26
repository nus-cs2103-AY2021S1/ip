package duke;

public class Task {
    enum TaskType{
        TODO,
        DEADLINE,
        EVENT
    }
    protected String description;
    protected boolean isDone;

    /**
     * Gets the description of the Task input by user
     * @param s the input command by user
     * @param type the type of Task
     * @return the description of the Task input by user
     */
    public static String getDescription(String s, TaskType type){
        if(type == TaskType.TODO){
            int start = 0;
            while(!s.substring(start, start + 4).equals("todo")) start++;
            System.out.println(start + 4);
            return s.substring(start + 4);
        }
        String firstWord = type == TaskType.EVENT ? "event" : "deadline", secondWord = type == TaskType.EVENT ? "/at" : "/by";
        int start = 0, firstWordLen = type == TaskType.EVENT ? 5 : 8, len = s.length();
        while(!s.substring(start, start + firstWordLen).equals(firstWord)) start++;
        start += (firstWordLen + 1);
        if(start >= len) return s.substring(len);
        int end = start + 1;
        while(end + 3 < len && !s.substring(end, end + 3).equals(secondWord)) end++;
        if(end + 3 >= len) end = len + 1;
        return s.substring(start, end - 1);
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
    public Task(String description, boolean isDone){
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
    public void markAsDone(){
        isDone = true;
    }

    /**
     * Returns the description of the Task
     * @return the description of the Task
     */
    public String getDescription(){
        return description;
    }

    /**
     * Overrides the toString method
     * @return the String
     */
    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

}