import main.java.Task;
import java.util.Scanner;
import java.util.ArrayList;
public class Bob {
    static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetings = "Hello! I'm Bob\nWhat can I do for you?";
        String exit = "Bye! Hope to see you again.";
        System.out.println("Hello from\n" + logo);
        System.out.println(greetings);
        String command = sc.nextLine();
        while(!command.equals("bye")) {
            if (command.equals("list")) {
                int count = 1;
                for(Task item: list) {
                    System.out.println(count +"." + item.toString());
                    count++;
                }
            } else if(command.contains("done")) {
                int index = Integer.parseInt(command.substring(command.length()-1));
                Task task = list.get(index-1);
                task.markAsDone();
                System.out.println("Good job! I have marked this task as done:");
                System.out.println("\t" + index + "." + task.toString());
            } else {
                Task task = new Task(command);
                list.add(task);
                System.out.println("added: " + command);
            }
            command = sc.nextLine();
        }
        System.out.println(exit);
    }
}
