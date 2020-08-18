import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "I'm DukeForQ, your chatbot! You can enter everything you want to enter. If you want to exit, enter 'bye'!");

        ArrayList<Task> tasks = new ArrayList<>();
        String s;
        while (true) {
            s = sc.nextLine();
            if (s.equals("bye")) {
                System.out.println("Bye, hope to see you again!");
                sc.close();
                System.exit(0);
            } 

            if(s.startsWith("done")) {
                int index = Integer.parseInt(s.split(" ")[1]);
                tasks.get(index).markAsDone();
                System.out.println("Nice, I have marked this task as done: " + "\n" + tasks.get(index).toString());
            } // if starts with done, mark this task as done then print it.

            if(s.startsWith("delete")) {
                int index = Integer.parseInt(s.split(" ")[1]);
                Task t = tasks.get(index);
                tasks.remove(index);
                System.out.println("Nice, I have removed this task: " + "\n" + t.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } // if starts with delete, delete the referred task by the index.

            if(s.startsWith("todo")) {
                Todo t = new Todo(s.substring(5));
                tasks.add(t);
                System.out.println("Got it. I've added this task: " + "\n" + t.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } // if starts with todo, the string after todo should be in the task.

            if(s.startsWith("deadline")) {
                String[] divide = s.substring(9).split(" /by ");
                String description = divide[0];
                String ddl = divide[1];
                Deadline d = new Deadline(description, ddl);
                tasks.add(d);
                System.out.println("Got it. I've added this task: " + "\n" + d.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } // if starts with deadline, first split the string, then description should be the first string, ddl should be the last string.

            if(s.startsWith("event")) {
                String[] divide = s.substring(6).split(" /at ");
                String description = divide[0];
                String time = divide[1];
                Event e = new Event(description, time);
                tasks.add(e);
                System.out.println("Got it. I've added this task: " + "\n" + e.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } // if starts with event, first split the string, then description should be the first string, actual time should be the last string.               
            
            if (s.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for(int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + "." + tasks.get(i-1).toString());
                }
            }
            
        }
    }
}
