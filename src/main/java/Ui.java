import java.util.ArrayList;

public class Ui {

    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello im Eu Zin's Duke, he spent thursday afternoon creating me cuz he forgot abt the iP");
    }

    public void showBorder() {
        System.out.println("\n\\   / \\   / \\   / \\   / im not very creative \\   / \\   / \\   / \\   /\n \\ /   \\ /   \\ /   \\ /      EuZin's Duke      \\ /   \\ /   \\ /   \\ /\n\n");
    }

    public void showAddedMessage() {
        System.out.println("ok can i've added it\n");
    }

    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showList(String returnList) {
        String returnString = "faster do don't netflix already";
        returnString += returnList;
        System.out.println(returnString + "\n");
    }

    public void showDoneMessage(Task task) {
        System.out.println("ok sure good job i guess\n");
        System.out.println(task);
    }

    public void showToDoMessage(Task thisTask, TaskList taskList) {
        showAddedMessage();
        System.out.println(thisTask.toString().replace("todo ","") + "\n" +
                "Now got " + (taskList.getSize() + 1) + " task in the list\n");
    }

    public void showDeadlineMessage(Task thisTask, TaskList taskList) {
        showAddedMessage();
        System.out.println(thisTask.toString() + "\n" +
                "Now got " + (taskList.getSize() + 1) + " task in the list\n");
    }

    public void showEventMessage(Task thisTask, TaskList taskList) {
        showAddedMessage();
        System.out.println(thisTask.toString() + "\n" +
                "Now got " + (taskList.getSize() + 1) + " task in the list\n");
    }

    public void showDeletedMessage(Task task, TaskList tasklist) {
        System.out.println("ok deleted this task alr:\n" + task + "\n" + "Now you left " + (tasklist.getSize() - 1)
        + " task(s)");
    }

    public void showSearchList(ArrayList<Task> taskArrayList) {
        if (taskArrayList.size() == 0) System.out.println("We were unable to find your task");
        String returnString = "Here is what we found \n";
        for (Task task : taskArrayList) {
            returnString += task.toString() + "\n";
        }
        System.out.println(returnString);
    }
}
