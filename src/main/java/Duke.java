import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\nWhat can I do for you");
        ArrayList<Task> dataBase = new ArrayList<Task>();
        String input = sc.nextLine();
        while (!input.equals(new String("bye"))) {
        	if (input.equals(new String("list"))) {
        		int num = 1;
        		System.out.println("Here are the tasks in your list:");
        		for (Task task: dataBase) {
        			System.out.println(num + "." + task.toString());
        			num++;
        		}
        	} else if (input.substring(0, 4).equals(new String("done"))) {
        		int index = Integer.parseInt(input.substring(5));
        		Task currenTask = dataBase.get(index - 1);
        		currenTask.markAsDone();
        		System.out.println("Nice! I've marked this task as done:\n" + currenTask.toString());
        	} else if (input.substring(0,4).equals(new String("todo"))) {
        		String taskContent = input.substring(5);
        		ToDo newTask = new ToDo(taskContent);
        		dataBase.add(newTask);
        		System.out.println("Got it. I've added this task:\n" + newTask.toString() + "\nNow you have " + dataBase.size() + " tasks in the list.");
        	} else if (input.substring(0,8).equals(new String("deadline"))) {
        		String task  = input.substring(8);
        		int index = task.indexOf('/');
        		String taskContent = task.substring(0, index - 1);
        		String taskDeadline = task.substring(index + 4);
        		Deadline newTask = new Deadline(taskContent, taskDeadline);
        		dataBase.add(newTask);
        		System.out.println("Got it. I've added this task:\n" + newTask.toString() + "\nNow you have " + dataBase.size() + " tasks in the list.");
        	} else if (input.substring(0,5).equals(new String("event"))) {
        		String task  = input.substring(5);
        		int index = task.indexOf('/');
        		String taskContent = task.substring(0, index - 1);
        		String taskTime = task.substring(index + 4);
        		Event newTask = new Event(taskContent, taskTime);
        		dataBase.add(newTask);
        		System.out.println("Got it. I've added this task:\n" + newTask.toString() + "\nNow you have " + dataBase.size() + " tasks in the list.");
        	} 
        	else {
        		System.out.println("added: " + input);
        		dataBase.add(new Task(input));
        	}
        	input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}