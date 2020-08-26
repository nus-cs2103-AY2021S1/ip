import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArrayList;

    private static String horizontalLine = "    ____________________________________________________________\n";
    TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public String addTask(Task newTask) {
        taskArrayList.add(newTask);

        return String.format("%s     Got it. I've added this task:%n       %s%n     " +
                             "Now you have %d tasks in the list.%n%s",
                                    horizontalLine, newTask,
                                    taskArrayList.size(), horizontalLine);
    }

    public String deleteTask(int taskNum) {
        Task toDelete = taskArrayList.get(taskNum - 1);
        taskArrayList.remove(taskNum - 1);
        String deleteMsg = String.format("     Noted. I've removed this task:%n       %s%n", toDelete);
        String countMsg = String.format("     Now you have %d tasks in the list.%n", taskArrayList.size());
        return horizontalLine + deleteMsg + countMsg + horizontalLine;
    }

    public Task getTask(int taskNum) {
        return this.taskArrayList.get(taskNum - 1);
    }

    public String finishTask(int taskNum) {
        Task toComplete = taskArrayList.get(taskNum - 1);
        toComplete.complete();
        String completion = "     Nice! I've marked this task as done:\n" +
                String.format("       %s%n", toComplete);
        return horizontalLine + completion + horizontalLine;
    }

    public String printList() {
        int counter = 0;
        String printedListString = horizontalLine + "     Here are the tasks in your list:\n";
        for (Task task: taskArrayList) {
            counter++;
            printedListString += String.format("     %d.%s%n", counter, task);
        }
        printedListString += horizontalLine;
        return printedListString;
    }

    public int getSize() {
        return this.taskArrayList.size();
    }
}