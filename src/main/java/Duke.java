import task.*;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today?");

        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        List<Task> taskList = new ArrayList<>();

        while(true){
            String input = scanner.nextLine();
            if(input.contentEquals("bye")){
                System.out.println("\t" + "Bye. Hope to see you again soon!");
                break;
            }
            else if(input.contentEquals("list")){
                for(int i = 0; i < taskList.size(); i++){
                    System.out.println("\t" + (i + 1) + ". " + taskList.get(i));
                }
            }
            else if(input.startsWith("remove ")){
                String indexStr = input.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(indexStr) - 1;
                Task t = taskList.remove(index);
                System.out.println("\t" + "Noted. I've removed this task:");
                System.out.println("\t\t" + t);
                System.out.println("\t" + "Now you have " + taskList.size() + " tasks in the list.");
            }
            else if(input.startsWith("done ")){
                String indexStr = input.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(indexStr) - 1;
                taskList.get(index).setDone();
                System.out.println("\t" + "Nice! I've marked this task as done:");
                System.out.println("\t\t" + taskList.get(index));
            }
            else if(input.startsWith("todo ") || input.equals("todo")){
                input = input.substring(4).trim();
                try {
                    Task newTask = new Todo(input);
                    taskList.add(newTask);
                    System.out.println("\t" + "Got it. I've added this task:");
                    System.out.println("\t\t" + newTask);
                }
                catch(EmptyStringException e){
                    System.out.println("\t" + "Todo cannot be empty.");
                }
            }
            else if(input.startsWith("deadline ") || input.equals("deadline")){
                input = input.substring(8).trim();
                try {
                    Task newTask = new Deadline(input);
                    taskList.add(newTask);
                    System.out.println("\t" + "Got it. I've added this task:");
                    System.out.println("\t\t" + newTask);
                }
                catch(EmptyStringException e){
                    System.out.println("\t" + "Deadline cannot be empty.");
                }
            }
            else if(input.startsWith("event ") || input.equals("event")){
                input = input.substring(5).trim();
                try {
                    Task newTask = new Event(input);
                    taskList.add(newTask);
                    System.out.println("\t" + "Got it. I've added this task:");
                    System.out.println("\t\t" + newTask);
                }
                catch(EmptyStringException e){
                    System.out.println("\t" + "Event cannot be empty.");
                }
            }
            else{
                System.out.println("\t" + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
