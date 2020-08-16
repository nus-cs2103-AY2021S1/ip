import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    private static List<String> taskList = new ArrayList<>();
    public static void intro() {
        System.out.println("Hi handsome! My name is Duck. What can I do for you?");
    }
    public static void addTask(String task) {
        taskList.add(task);
        System.out.println("added: " + task);
    }
    public static void printList() {
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, taskList.get(i)));
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke.intro();
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            if (task.equals("bye")) {
                System.out.print("Bye. Hope to see you again soon!");
                break;
            } else {
                if (task.equals("list")) {
                    Duke.printList();
                } else {
                    Duke.addTask(task);
                }
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
