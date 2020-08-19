public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return description;
    }
    
    // public void addTasks(String task) {
    //     for (int i = 0; i < taskList.length; i++) {
    //         if (taskList[i] == null) {
    //             taskList[i] = task;
    //             break;
    //         }
    //     }
    // }

    // @Override
    // public String toString() {
    //     String list = "";
    //     for (int i = 0; i < taskList.length; i++) {
    //         if (taskList[i] != null) {
    //             String task = i + 1 + ". " + taskList[i] + "\n";
    //             list += task;
    //         } else {
    //             break;
    //         }
    //     }
    //     return list;
    // }
}