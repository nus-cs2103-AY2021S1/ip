import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArrayList;

    private static String horizontalLine = "    ____________________________________________________________\n";
    TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        taskArrayList.add(newTask);
    }

    public void deleteTask(int taskNum) {
        taskArrayList.remove(taskNum - 1);
    }

    public Task getTask(int taskNum) {
        return this.taskArrayList.get(taskNum - 1);
    }

    public void completeTask(int taskNum) throws CartonaException {
        Task toComplete = taskArrayList.get(taskNum - 1);
        if (toComplete.checkIfDone()) {
            throw new CartonaException(String.format("Error: Task %d is already done.", taskNum));
        }

        toComplete.complete();
    }

    @Override
    public String toString() {
        int counter = 0;
        String printedListString = "";
        for (Task task: taskArrayList) {
            counter++;
            printedListString += String.format("     %d.%s%n", counter, task);
        }
        return printedListString;
    }

    public String getListForStorage() {
        String stringToWrite = "";
        for (int i = 1; i <= this.getSize(); i++) {
            Task task = this.getTask(i);
            stringToWrite += task.getAbbreviatedString() + "\n";
        }

        return stringToWrite;
    }


    public int getSize() {
        return this.taskArrayList.size();
    }
}