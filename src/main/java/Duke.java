import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Duke is a small educational project which acts
 * based on user input.
 * @author Xia Liyi
 */
public class Duke {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        Ui ui = new Ui();
        Parser parser = new Parser();
        Storage file = new Storage("../data/duke.txt");
        ui.showLine("Hello! I'm Duke\nWhat can I do for you");
        TaskList taskList = new TaskList(file.getTasks());
        while (sc.hasNext()) {
        	String input = sc.next();
        	if (input.equals(new String("bye"))) {
        		break;
        	}
        	if (input.equals(new String("list"))) {
        		int num = 1;
        		ui.showLine("Here are the tasks in your list:");
        		for (Task task: taskList.getList()) {
        			ui.showLine(num + "." + task.toString());
        			num++;
        		}
        	} else if (input.equals(new String("done"))) {
        		int index = sc.nextInt();
        		Task currentTask = taskList.get(index - 1);
        		currentTask.markAsDone();
        		ui.showLine("Nice! I've marked this task as done:\n" + currentTask.toString());
        	} else if (input.equals(new String("todo"))) {
        		String taskContent = sc.nextLine();
        		ToDo newTask = new ToDo(taskContent);
        		try {
        			newTask.test();
        		} catch (DukeException e) {
        			System.out.println(e.getMsg());
        			continue;
        		}
    			taskList.add(newTask);
    			ui.showLine("Got it. I've added this task:\n" + newTask.toString() + "\nNow you have " 
                        + taskList.size() + " tasks in the list.");
        	} else if (input.equals(new String("deadline"))) {
        		String task  = sc.nextLine();
        		int index = task.indexOf('/');
        		String taskContent = task.substring(0, index - 1);
        		String dateString = task.substring(index + 4);
        		Task newTask;
                try {
                    LocalDate taskDeadline = LocalDate.parse(dateString);
                    newTask = new Deadline(task, taskDeadline);
                } catch (Exception e) {
                    newTask = new Deadline(task, dateString);
                }
        		taskList.add(newTask);
        		ui.showLine("Got it. I've added this task:\n" + newTask.toString() + "\nNow you have " 
                        + taskList.size() + " tasks in the list.");
        	} else if (input.equals(new String("event"))) {
        		String task  = sc.nextLine();
        		int index = task.indexOf('/');
        		String taskContent = task.substring(0, index - 1);
        		String taskTime = task.substring(index + 4);
        		Event newTask = new Event(taskContent, taskTime);
        		taskList.add(newTask);
        		ui.showLine("Got it. I've added this task:\n" + newTask.toString() + "\nNow you have " 
                        + taskList.size() + " tasks in the list.");
        	} else if (input.equals(new String("delete"))) {
        		int index = sc.nextInt();
        		Task currentTask = taskList.get(index - 1);
                taskList.remove(index - 1);
        		ui.showLine("Noted. I've removed this task:\n" + currentTask.toString() + "\nNow you have " + taskList.size() + " tasks in the list.");
        	} else if (input.equals("find")) {
                String condition = sc.nextLine();
                ArrayList<Task> newTaskList = taskList.find(condition);
                int num = 1;
                for (Task task: newTaskList) {
                    ui.showLine(num + "." + task.toString());
                    num++;
                }
            } else {
        		System.out.println("added: " + input);
        		taskList.add(new Task(input));
        	}
        	file.write(taskList.getList());
        }
        ui.showLine("Bye. Hope to see you again soon!");
    }
}
