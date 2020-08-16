import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<Task> toDoList = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Omo!! hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(" "+"-----------------");
            if (input.startsWith("done ")) {
                System.out.println(" Nice! I've marked this task as done:");
                //get the integer from the string command and convert to integer
                Task theTask = toDoList.get(Integer.parseInt(input.substring(5))-1);
                theTask.setDone();
                System.out.println("  " + theTask.getCurrentStatus());
            } else if (!input.equals("list")){
                System.out.println("    added: "+ input);
                toDoList.add(new Task(input));
            } else {
                int count = 1;
                System.out.println(" Here are the tasks in your list:");
                for (Task t: toDoList) {
                    System.out.println("  "+ count + ". " + t.getCurrentStatus());
                    count++;
                }
            }
            System.out.println(" "+"-----------------");
            input = sc.nextLine();
        }
        System.out.println(" "+"-----------------");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(" "+"-----------------");
    }
}
