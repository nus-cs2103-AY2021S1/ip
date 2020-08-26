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
        String input = "";
        while (!input.equals(new String("bye"))) {
        	input = sc.next();
        	if (input.equals(new String("list"))) {
        		int num = 1;
        		System.out.println("Here are the tasks in your list:");
        		for (Task task: dataBase) {
        			System.out.println(num + "." + task.toString());
        			num++;
        		}
        	} else if (input.equals(new String("done"))) {
        		int index = Integer.parseInt(sc.next());
        		Task currenTask = dataBase.get(index - 1);
        		currenTask.markAsDone();
        		System.out.println("Nice! I've marked this task as done:\n" + currenTask.toString());
        	} else if (input.equals(new String("todo"))) {
        		String taskContent = sc.nextLine();
        		ToDo newTask = new ToDo(taskContent);
        		try {
        			newTask.test();
        		} catch (DukeException e) {
        			System.out.println(e.getMsg());
        			continue;
        		}
    			dataBase.add(newTask);
    			System.out.println("Got it. I've added this task:\n" + newTask.toString() + "\nNow you have " + dataBase.size() + " tasks in the list.");

        	} else if (input.equals(new String("deadline"))) {
        		String task  = sc.nextLine();
        		int index = task.indexOf('/');
        		String taskContent = task.substring(0, index - 1);
        		String taskDeadline = task.substring(index + 4);
        		Deadline newTask = new Deadline(taskContent, taskDeadline);
        		dataBase.add(newTask);
        		System.out.println("Got it. I've added this task:\n" + newTask.toString() + "\nNow you have " + dataBase.size() + " tasks in the list.");
        	} else if (input.equals(new String("event"))) {
        		String task  = sc.nextLine();
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
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}