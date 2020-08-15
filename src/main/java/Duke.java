/*
 * Duke is a retired old uncle who likes to speak in Singlish.
 */
import java.util.*;
import java.util.Scanner;

public class Duke {
    private List<Task> taskList = new ArrayList<>();

    private void greet() {
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

    private void exit() {
        String bye = "Alamak, you sure you finished all your tasks? Ok lah I also need to sleep anyway Zzzz.\n" +
                "Goodbye!";
        System.out.println(bye);
    }

    public void echoDuke() {
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.toLowerCase().equals("bye")) {
            System.out.println(cmd);
            cmd = sc.nextLine();
        }
    }

    private void addTask(Task tsk) {
        taskList.add(tsk);
        System.out.println("Lao Duke has added this task for you: " + tsk);
    }

    private void listTask() {
        System.out.println("Lao Duke not so blur like you. Tsk. I got remember your tasks one hor.");
        for (int i = 0; i < taskList.size(); i++) {
            Task tsk = taskList.get(i);
            System.out.println("Task " + (i + 1) + ": " + tsk.getStatusIcon() + " " + tsk);
        }
    }

    private void markDone(int taskNum) {
        if (taskNum < 1 || taskNum > taskList.size()) {
            System.out.println("Aiyo, don't have this task number leh...");
        } else {
            Task tsk = taskList.get(taskNum - 1);
            tsk.markAsDone();
            System.out.println("Wah very good! I am proud that you got do your task!");
            System.out.println(tsk.getStatusIcon() + " " + tsk);
        }
    }

    public void processTask() {
        greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.toLowerCase().equals("bye")) {
            //to list?
            if (input.toLowerCase().equals("list")) {
                listTask();
            } else {
                //could it be a done task?
                String[] cmd = input.split(" ");
                if (cmd[0].toLowerCase().equals("done")) {
                    try {
                        Integer taskNum = Integer.parseInt(cmd[1]);
                        markDone(taskNum);
                    } catch (NumberFormatException e) {
                        System.out.println("Eh I not so smart one la..." +
                                "Can you format your done task properly or not? One spacing only leh!");
                    }
                } else {
                    //simply add tasks
                    addTask(new Task(input));
                }
            }
            input = sc.nextLine();
        }
        exit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.processTask();
    }
}
