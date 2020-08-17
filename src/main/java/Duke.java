import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    private static List<Task> taskList = new ArrayList<>();
    public static void intro() {
        System.out.println("Hi handsome! My name is Duck. What can I do for you?");
    }
    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println("added: " + task);
    }
    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, taskList.get(i)));
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
                    System.out.print("Bye. Hope to see you again soon!");
                    break;
                case "list" :
                    Duke.printList();
                    break;
                default :
                    if (task.substring(0,4).equals("done")) {
                        int taskNo = Character.getNumericValue(task.charAt(5));
                        System.out.println("Nice! I've marked this task as done:");
                        markTaskDone(taskNo);
                    } else {
                        Duke.addTask(new Task(task));
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
