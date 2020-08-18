import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    private static List<Task> taskList = new ArrayList<>();
    public static void intro() {
        System.out.println("\tHi handsome! My name is Duck. What can I do for you?");
    }
    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println("\tGot it. I've added this task:" + "\n\t\t" + task);
        System.out.println(String.format("\tNow you have %d tasks in the list.", taskList.size()));
    }
    public static void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("\t%d. %s", i + 1, taskList.get(i)));
        }
    }

    public static void markTaskDone(int i) {
        int index = i - 1;
        Task task = taskList.remove(index).doneTask();
        System.out.println("\t" + task);
        taskList.add(index, task);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke.intro();
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            switch(task) {
                case "bye" :
                    System.out.print("\tBye. Hope to see you again soon!");
                    return;
                case "list" :
                    Duke.printList();
                    break;
                default :
                    if (task.startsWith("done")) {
                        int taskNo = Character.getNumericValue(task.charAt(5));
                        System.out.println("\tNice! I've marked this task as done:");
                        markTaskDone(taskNo);
                    } else if (task.startsWith("todo")) {
                        task = task.replace("todo ", "");
                        Duke.addTask(new ToDos(task));
                    } else if (task.startsWith("deadline")) {
                        task = task.replace("deadline ", "");
                        String[] stringArr = task.split("/by", 2);
                        task = stringArr[0];
                        String by = stringArr[1];
                        Duke.addTask(new Deadlines(task, by));
                    } else if (task.startsWith("event")) {
                        task = task.replace("event ", "");
                        String[] stringArr = task.split("/at", 2);
                        task = stringArr[0];
                        String at = stringArr[1];
                        Duke.addTask(new Events(task, at));
                    } else {
                        System.out.println("\tSorry handsome but I'm not sure about this command :)");
                    }
                    break;
            }
        }
        sc.close();
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
    }
}
