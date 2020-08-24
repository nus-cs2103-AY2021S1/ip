import java.util.List;
import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = "Eh yo, I am Duke! Some kids call me 'Lao Duke' (which means old Duke).\n" +
                "I like to speak Singlish but I also can remember your tasks one. Come try la! ";
        System.out.println(logo);
        System.out.println(intro);
    }

    public void byeMessage() {
        String bye = "Alamak, you sure you finished all your tasks? Ok lah I also need to sleep anyway Zzzz.\n" +
                "Goodbye!";
        System.out.println(bye);
    }

    public void showListTasks(List<Task> taskList) {
        System.out.println("Lao Duke not so blur like you. Tsk. I got remember your tasks one hor.");
        for (int i = 0; i < taskList.size(); i++) {
            Task tsk = taskList.get(i);
            System.out.println("Task " + (i + 1) + ": "  + tsk);
        }
    }

    public void showRequiredTasks(List<Task> taskList) {
        if (taskList.size() > 0) {
            System.out.println("I how smart...I have matched your queries as below:");
            for (Task tsk : taskList) {
                System.out.println(tsk);
            }
        } else {
            System.out.println("Wah cannot find any matching tasks leh...");
        }
    }
    public void showAddTask(Task tsk) {
        System.out.println("Lao Duke has added this task for you:\n" + tsk);
    }

    public void showDoneTask(Task tsk) {
        System.out.println("Wah very good! I am proud that you got do your task!\n" + tsk);
    }

    public void showDeleteTask(Task tsk) {
        System.out.println("Can can I removed this task for you:\n" + tsk);
    }

    public void showTotalTasks(int num) {
        System.out.println("You have " + num + " task(s) in your list!");
    }

    public void showLoadingError() {
        System.out.println("Error with reading / writing file!");
    }

    public void showDukeError(DukeException e) {
        System.out.println(e);
    }
}
