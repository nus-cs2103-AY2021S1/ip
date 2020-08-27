public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or cross symbols
    }

    public void markAsDone() {
        isDone = true;
    }
    
    public static Task taskify(String taskString) throws DukeException { 
        char taskType = taskString.charAt(1);
        boolean isTaskDone = taskString.charAt(4) == '\u2713';
        Task result;
        
        if (taskType == 'T') {
            String taskDesc = taskString.substring(7);
            result = new ToDo(taskDesc);
        } else {
            String[] strArr = taskString.split(taskType == 'D' ? " \\(by: " : " \\(at: ");
            String taskDesc = strArr[0].substring(7);
            String time = strArr[1].substring(0, strArr[1].length() - 1);
            result = taskType == 'D' ? new Deadline(taskDesc, time) : new Event(taskDesc, time);
        }

        if (isTaskDone) result.markAsDone();
        return result;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}