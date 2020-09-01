package duke;

import duke.tasks.Task;

public class Ui {
    String logo = " _       _ \n"
            + "| |  _  | |_   _ ____ ___\n"
            + "| | | | | | |_/ |  _ \\  _ \\ \n"
            + "| |_| |_| |\\___ |    <  __/\n"
            + "\\___/\\___/ \\____|_| \\_\\___|\n";
    String border = "\n^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n";
    String availableCommands = "Available commands: bye, list, done, delete, todo, deadline, event";


    public void showWelcomeMessage() {
        System.out.println("Hello from\n" + logo);
        System.out.println(border + "Hullo! I Wyre, your Personal Assistant Chatbot! :>\nWhat you want me to do?"
                + border);
    }

    public void showByeMessage() {
        System.out.println(border + "Bye bye hooman friend!" + border);

    }

    public void showNewSaveFileMessage() {
        System.out.println("You new to Wyre mah? (Or your save file corrupted :/) " +
                "I create new task list for you, don't worry lah.");
    }

    public void showDoneMessage(TaskList tList, int index) {
        System.out.println(border + "I mark this task done:");
        System.out.println("\t" + tList.get(index) + border);
    }

    public void showDeleteMessage(TaskList tList, int index) {
        System.out.println(border + "I remove task:");
        System.out.println("\t" + tList.get(index));
        System.out.println("Now you have " + (tList.size() - 1) + " task(s) in the list lah!" + border);
    }

    public void showAddTaskMessage(TaskList tList, Task t) {
        System.out.println(border + "Wyre at your service. I add task:\n\t" + t);
        System.out.println("Now you have " + tList.size() + " task(s) in the list lah!" + border);
    }

    public void showFilterMessage(boolean isFound, TaskList filteredTasks) {
        if (isFound) {
            System.out.println(border + "Here the matching tasks lah:\n");
            printList(filteredTasks);
            System.out.println(border);
        } else {
            System.out.println(border + "Paiseh, no tasks found with that name!" + border);
        }
    }

    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println(border + "Your list empty lah!" + border);
        } else {
            System.out.println(border + "Here the list lah:\n");
            printList(tasks);
            System.out.println(border);

        }
    }

    public void printList(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i));
        }
    }

}
