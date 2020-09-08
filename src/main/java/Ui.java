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

//    public void showBorder() {
//        System.out.println("\n\\   / \\   / \\   / \\   / im not very creative \\   / \\   / \\   / \\   /\n \\ /   \\ /   \\ /   \\ /      EuZin's Duke      \\ /   \\ /   \\ /   \\ /\n\n");
//    }

    public void showAddedMessage() {
        System.out.println("ok can i've added it\n");
    }

    public String showAddTaskMessage(Task thisTask, TaskList taskList) {
        return thisTask.toString() + "\n" + "Now got " + (taskList.getSize()) + " task in the list\n";
    }

    public String showOtherActionMessage(String userInput, TaskList taskList) throws DukeException {
        String processedInput = userInput.split(" ")[0];
        switch (processedInput) {
            case "done" :
                int doneIndex = Integer.parseInt(userInput.substring(5));
                return "ok sure good job i guess\n" + taskList.getList().get(doneIndex-1) + "\n";
            case "delete" :
                int indexDeleted = Integer.parseInt(userInput.replace("delete ", ""));
                return "ok deleted this task alr:\n" + taskList.getList().get(indexDeleted-1) + "\n" + "Now you left " + (taskList.getSize()-1)
                        + " task(s)";
            case "find" :
                ArrayList<Task> taskArrayList = taskList.searchFor(userInput.split(" ")[1]);
                if (taskArrayList.size() == 0) {
                    return "We were unable to find your task";
                } else {
                    String returnString = "Here is what we found \n";
                    for (Task task : taskArrayList) {
                        returnString += task.toString() + "\n";
                    }
                    return returnString;
                }
            case "duration" :
                int indexSettingDuration = Integer.parseInt(userInput.split(" ")[1]);
                return "duration has been set for:\n" + taskList.getList().get(indexSettingDuration-1);
            default :
                throw new DukeException();
        }
    }

    public String showByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String showList(String returnList) {
        String returnString = "faster do don't netflix already";
        returnString += returnList;
        return returnString + "\n";
    }

}
