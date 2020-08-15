import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static List<Task> list = new ArrayList<>();

    public static void printList() {
        int i = 1;
        for(Task t : list) {
            System.out.println(i + "." + t);
            i++;
        }
    }

    public static void printTotalTask(Task task) {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + list.size() + " tasks in the list." );
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        while(sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                printList();
            } else if (command.startsWith("done")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                list.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(index));
            } else if (command.startsWith("todo")){
                Task task = new Todo(command.split(" ",2)[1]);
                list.add(task);
                printTotalTask(task);
            } else if (command.startsWith("deadline")) {
                String str = command.split("/")[0].split(" ", 2)[1];
                String date = command.split("/")[1].split(" ", 2)[1];
                Task task = new Deadline(str, date);
                list.add(task);
                printTotalTask(task);
            } else if (command.startsWith("event")) {
                String str = command.split("/")[0].split(" ", 2)[1];
                String date = command.split("/")[1].split(" ", 2)[1];
                Task task = new Event(str, date);
                list.add(task);
                printTotalTask(task);
            } else {
                list.add(new Task(command));
                System.out.println("added: " + command);
            }

        }
        sc.close();
    }
}
