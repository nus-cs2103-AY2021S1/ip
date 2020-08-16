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
            if (input.startsWith("done")) {
                System.out.println(" Nice! I've marked this task as done:");
                //get the integer from the string command and convert to integer
                Task theTask = toDoList.get(Integer.parseInt(input.substring(5))-1);
                theTask.setDone();
                try{
                    System.out.println("  " + theTask.getCurrentStatus());
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (!input.equals("list")){
                if (input.startsWith("todo")) {
                    Task theTask = new Todo(input.substring("todo".length()));
                    try {
                        System.out.println(theTask.getCurrentStatus());
                        toDoList.add(theTask);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        System.out.println(" "+"-----------------");
                        input = sc.nextLine();
                        continue;
                    }
                } else if (input.startsWith("deadline")){
                    int breakpoint = input.indexOf("/");
                    try{
                        String tempString = input.substring("deadline".length() + 1, breakpoint);
                        String tempString2 = "(" + input.substring(breakpoint + 1, breakpoint + 3) + ":" + input.substring(breakpoint + 3) + ")";
                        String result = tempString + tempString2;
                        Task theTask = new Deadline(result);
                        System.out.println("  " + theTask.getCurrentStatus());
                        toDoList.add(theTask);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        System.out.println(" "+"-----------------");
                        input = sc.nextLine();
                        continue;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be incomplete.");
                        System.out.println(" "+"-----------------");
                        input = sc.nextLine();
                        continue;
                    }
                } else if (input.startsWith("event")) {
                    int breakpoint = input.indexOf("/");
                    try {
                        String tempString = input.substring("event".length() + 1, breakpoint);
                        String tempString2 = "(" + input.substring(breakpoint + 1, breakpoint + 3) + ":" + input.substring(breakpoint + 3) + ")";
                        String result = tempString + tempString2;
                        Task theTask = new Event(result);
                        System.out.println("  " + theTask.getCurrentStatus());
                        toDoList.add(theTask);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The description of a event cannot be incomplete.");
                        System.out.println(" " + "-----------------");
                        input = sc.nextLine();
                        continue;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        System.out.println(" " + "-----------------");
                        input = sc.nextLine();
                        continue;
                    }
                } else if (input.startsWith("delete")){
                    System.out.println(" Noted. I've removed this task:");
                    Task theTask = toDoList.remove(Integer.parseInt(input.substring(7))-1);
                    System.out.println(theTask.getCurrentShortStatus());
                 } else {
                    System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(" "+"-----------------");
                    input = sc.nextLine();
                    continue;
                }
                System.out.println(" Now you have " + toDoList.size() + " tasks in the list.");

            } else { //it must be a list command
                int count = 1;
                System.out.println(" Here are the tasks in your list:");
                for (Task t: toDoList) {
                    System.out.println("  "+ count + ". " + t.getCurrentShortStatus());
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
