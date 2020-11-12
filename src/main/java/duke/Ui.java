package duke;

import duke.tasks.Task;

public class Ui {
    /*String logo = " _       _ \n"
            + "| |  _  | |_   _ ____ ___\n"
            + "| | | | | | |_/ |  _ \\  _ \\ \n"
            + "| |_| |_| |\\___ |    <  __/\n"
            + "\\___/\\___/ \\____|_| \\_\\___|\n";
    String border = "\n^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n";
    String availableCommands = "Available commands: bye, list, done, delete, todo, deadline, event";


    public void showWelcomeMessage() {
        //System.out.println("Hello from\n" + logo);
        System.out.println("Hullo! I Wyre, your Personal Assistant Chatbot! :>\nWhat you want me to do?");
    }*/

    public String showByeMessage() {
        return "Bye bye niece and nephews!";

    }

    public String showNewSaveFileMessage() {
        return "You new mah? Or your save file corrupted? Uncle Roger create new task list for you, don't worry lah.";
    }

    public String showDoneMessage(Task t) {
        return "Uncle Roger mark this task done:\n\t" + t;
    }

    public String showDeleteMessage(TaskList tList, Task t) {
        return "Uncle Roger remove task:\n\t" + t + "\nNow you have " + (tList.size()) + " task(s) in the list lah!";
    }

    public String showAddTaskMessage(TaskList tList, Task t) {
        return "Uncle Roger add task:\n\t" + t + "\nNow you have " + tList.size() + " task(s) in the list lah!";
    }

    public String showFilterMessage(boolean isFound, TaskList filteredTasks) {
        if (isFound) {
            return "Here the matching tasks lah:\n" + printList(filteredTasks);
        } else {
            return "Paiseh, no tasks found with that name!";
        }
    }

    public String showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Your list empty lah!";
        } else {
            return "Here the list lah:\n" + printList(tasks);
        }
    }

    public String printList(TaskList tasks) {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            s += "\t" + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return s;
    }

}
