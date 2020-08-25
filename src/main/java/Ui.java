package main.java;

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
        System.out.println(border + "Hi! I'm Wyre, your Personal Assistant Chatbot! :>\nWhat can I do for you today?"
                + border);
    }

    public void showByeMessage() {
        System.out.println(border + "Bye. Hope to see you again!" + border);

    }
    public void showLoadingError() {
        System.out.println("You seem to be a newbie to Wyre (Or your save file is corrupted, oops)! Anyway, " +
                "I created a new task list for you. :>");
    }

    public void showUnexpectedLoadingError() {
        System.out.println("Couldn't load the data due to an unexpected error. To reload the original data, do NOT " +
                "edit the list during this session and try again. :<");
    }

    public void showUnexpectedSavingError() {
        System.out.println("Couldn't save the data due to an unexpected error. Please try again. :<");
    }


    public void showDoneMessage(TaskList tList, int index) {
        System.out.println(border + "Well done! I've marked this task as done:");
        System.out.println("\t" + tList.get(index) + border);
    }

    public void showIndexErrorMessage() {
        System.out.println(border + "Sorry fam, you can't operate on a nonexistent index!" + border);
    }

    public void showDeleteMessage(TaskList tList, int index) {
        System.out.println(border + "Ooookay! I've removed this task:");
        System.out.println("\t" + tList.get(index));
        System.out.println("Now you have " + (tList.size() - 1) + " task(s) in the list." + border);
    }

    public void showAddTaskMessage(TaskList tList, Task t) {
        System.out.println(border + "Wyre at your service. I've added the task:\n\t" + t);
        System.out.println("Now you have " + tList.size() + " task(s) in the list." + border);
    }

    public void showFormatErrorMessage() {
        System.out.println(border + "Sorry fam, that doesn't seem like the right format to me!" + border);
    }

    public void showDateFormatErrorMessage() {
        System.out.println(border + "Sorry fam, the date needs to be in yyyy-mm-dd format!" + border);
    }

    public void showUnexpectedCommandMessage() {
        System.out.println(border + "Naw, this isn't an accepted command!\n" + availableCommands + border);
    }

    public void printList(TaskList tList) {
        System.out.println(border + "Here are the task(s) in your list:\n");
        for (int i = 0; i < tList.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tList.get(i));
        }
        System.out.println(border);
    }


}
