import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static List<Task> toDoList = new ArrayList<Task>();
    public static String seperator = "\t____________________________________________________________";

    public static void listTask() {
        System.out.println(seperator);
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 1; i <= toDoList.size(); i++) {
            Task current = toDoList.get(i - 1);
            System.out.println("\t" + i + "."+current.toString());
        }
        System.out.println(seperator);
    }

    public static void completeTask(String newLine) {
        int completed = Integer.parseInt(newLine.substring(5));
        Task current = toDoList.get(completed - 1);
        current.completeTask();
        System.out.println(seperator);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t[" + current.getIcon() + "] " + current.taskDesc);
        System.out.println();
    }

    public static void addTask(String newLine) {
        System.out.println(seperator);
        System.out.println("\tadded: "+newLine);
        System.out.println(seperator);
        toDoList.add(new Task(newLine));
    }
    private static void deadlineTask(String newLine) {
        String[] splitWord = newLine.split("/");
        Deadlines task = new Deadlines(splitWord[0].substring(9),splitWord[1]);
        toDoList.add(task);
        System.out.println(seperator);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t"+task.toString());
        System.out.println("\tNow you have " + toDoList.size() + " tasks in the list.");
        System.out.println(seperator);
    }
    private static void eventTask(String newLine) {
        String[] splitWord = newLine.split("/");
        Events task = new Events(splitWord[0].substring(6),splitWord[1]);
        toDoList.add(task);
        System.out.println(seperator);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t"+task.toString());
        System.out.println("\tNow you have " + toDoList.size() + " tasks in the list.");
        System.out.println(seperator);
    }

    private static void todoTask(String newLine) {
        ToDos task = new ToDos(newLine.substring(5));
        toDoList.add(task);
        System.out.println(seperator);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t"+task.toString());
        System.out.println("\tNow you have " + toDoList.size() + " tasks in the list.");
        System.out.println(seperator);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(seperator);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println(seperator);
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String newLine = sc.nextLine();
            if(newLine.equals("bye")) {
                System.out.println(seperator);
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(seperator);
                break;

            } else if (newLine.equals("list")) {
                listTask();
            } else if (newLine.length() >= 5 && newLine.substring(0,4).equals("done")) {
                completeTask(newLine);
            } else if (newLine.length() >= 5 && newLine.substring(0,4).equals("todo")) {
                todoTask(newLine);
            }else if (newLine.length() >= 9 && newLine.substring(0,8).equals("deadline")) {
                deadlineTask(newLine);
            }else if (newLine.length() >= 6 && newLine.substring(0,5).equals("event")) {
                eventTask(newLine);
            } else {
                addTask(newLine);
            }
        }
    }


}
