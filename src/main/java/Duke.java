/*
 * Duke is a retired old uncle who likes to speak in Singlish.
 */
import java.util.*;
import java.util.Scanner;

public class Duke {
    private List<String> taskList = new ArrayList<>();

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = "Eh yo, I am Duke! Some kids call me 'Lao Duke' (which means old Duke).\n" +
                "I like to speak Singlish, come talk to me now lah!";
        System.out.println(logo);
        System.out.println(intro);
    }

    public void exit() {
        String bye = "Alamak why so fast end the convo with me? Ok lah I also need to sleep now Zzzz.\n" +
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

    public void recordTasks() {
        Scanner sc = new Scanner(System.in);
        String task = sc.nextLine();
        while (!task.toLowerCase().equals("bye")) {
            if (task.toLowerCase().equals("list")) {
                System.out.println("Lao Duke not so blur like you. Tsk. I got remember your tasks one hor.");
                for (int i = 0; i < this.taskList.size(); i++) {
                    System.out.println("Task " + (i + 1) + ": " + taskList.get(i));
                }
            } else {
                this.taskList.add(task);
                System.out.println("Lao Duke has added this task for you: " + task);
            }
            task = sc.nextLine();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.recordTasks();
        duke.exit();
    }
}
