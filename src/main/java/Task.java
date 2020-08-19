public class Task {
    private final String[] taskList;

    public Task() {
        taskList = new String[100];
    }

    public String[] getTasks() {
        return taskList;
    }
    
    public void addTasks(String task) {
        for (int i = 0; i < taskList.length; i++) {
            if (taskList[i] == null) {
                taskList[i] = task;
                break;
            }
        }
    }

    @Override
    public String toString() {
        String list = "";
        for (int i = 0; i < taskList.length; i++) {
            if (taskList[i] != null) {
                String task = i + 1 + ". " + taskList[i] + "\n";
                list += task;
            } else {
                break;
            }
        }
        return list;
    }
}