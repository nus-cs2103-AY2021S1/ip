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
        		for (Task task: dataBase) {
        			System.out.println(num + "." + task.toString());
        			num++;
        		}
        	} else if (input.substring(0, 4).equals(new String("done"))) {
        		int index = Integer.parseInt(input.substring(5));
        		Task currenTask = dataBase.get(index - 1);
        		currenTask.markAsDone();
        		System.out.println("Nice! I've marked this task as done:\n" + currenTask.toString());
        	} else {
        		System.out.println("added: " + input);
        		dataBase.add(new Task(input));
        	}
        	input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}