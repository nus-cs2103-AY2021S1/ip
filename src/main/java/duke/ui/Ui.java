package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class Ui {

    private TaskList taskList;
    private static final String LINE_DIVIDER = "     _____________________________________";
    private static final String LOGO = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    public void messageFormat(String... message) {
        System.out.println(LINE_DIVIDER);
        for (String i : message) {
            System.out.println(i);
        }
        System.out.println(LINE_DIVIDER);
    }

    public void displayGreeting() {
        messageFormat("     Hi, I am\n", LOGO,
         "     Is there anything I could help with?");
    }

    public void displayExit() {
        messageFormat("     Bye! I look forward to meeting you next time!");
    }

    public void getList() {
        ArrayList<Task> listOfTasks = taskList.getListOfTasks();
        int noOfTasks = listOfTasks.size();
        if (noOfTasks == 0) {
            messageFormat("     There is no task in the list yet!");
        } else {
            System.out.println(LINE_DIVIDER);
            System.out.println("     Here are the tasks in the list:");
            for (int i = 0; i < noOfTasks; i++) {
                Task task = listOfTasks.get(i);
                System.out.println("     " + (i + 1) + "." + task);
            }
            System.out.println(LINE_DIVIDER);
        }
    }

    public void displayError(String message) {
        messageFormat("     " + message);
    }

    public void displayDone(Task task) {
        messageFormat("     Great! The task below is marked as done:\n"
                + "        " + task.toString());
    }

    public void displayDeletion(Task task) {
        ArrayList<Task> listOfTasks = taskList.getListOfTasks();
        int noOfTasks = listOfTasks.size();
        System.out.println(LINE_DIVIDER);
        System.out.println("     Okay. The task below is deleted from your list:\n"
                + "        " + task.toString());
        if (noOfTasks == 1) {
            System.out.println("     Now there is 1 task in total in your list.");
        } else {
            System.out.println("     Now there are " + listOfTasks.size() + " tasks "
                    + "in total in your list.");
        }
        System.out.println(LINE_DIVIDER);
    }

    public void displayAddition(Task task) {
        ArrayList<Task> listOfTasks = taskList.getListOfTasks();
        int noOfTasks = listOfTasks.size();
        System.out.println(LINE_DIVIDER);
        System.out.println("     Noted! The task below is added into the list:\n"
                + "        " + task);
        if (noOfTasks == 1) {
            System.out.println("     There is 1 task in total in your list.");
        } else {
            System.out.println("     There are " + listOfTasks.size()
                    + " tasks in total in your list.");
        }
        System.out.println(LINE_DIVIDER);
    }

    public void displayFinding(ArrayList<Task> matchingtasks) {
        int noOfTasks = matchingtasks.size();
        if (noOfTasks == 0) {
            messageFormat("     There is no task that match with this keyword!");
        } else {
            System.out.println(LINE_DIVIDER);
            System.out.println("     Here are the task or tasks that match with this keyword:");
            for (int i = 0; i < noOfTasks; i++) {
                Task task = matchingtasks.get(i);
                System.out.println("     " + (i + 1) + "." + task);
            }
            System.out.println(LINE_DIVIDER);
        }
    }



}
