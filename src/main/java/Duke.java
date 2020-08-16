import main.java.Task;
import main.java.Todo;
import main.java.Deadline;
import main.java.Event;


import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String intro = "____________________________________________________________ \n" +
                        "Hello! I'm Duke \n" + "What can I do for you? \n" +
                        "____________________________________________________________";
        System.out.println(intro);
        Scanner input =  new Scanner(System.in);
        String underscore = "____________________________________________________________ \n";
        String line = "";
        ArrayList<Task> todo = new ArrayList<>();
        while(!line.equals("bye")) {
            line = input.nextLine();
            if (line.equals("bye")) {
                System.out.println(underscore + " Bye. Hope to see you again soon!" + "\n" + underscore);
            } else if (line.equals("list")) {
                System.out.println(underscore);
                for (int i = 0; i < todo.size(); i++) {
                    int number = i + 1;
                    System.out.println(" " + number + "." + todo.get(i));
                }
                System.out.println(underscore);
            } else if (line.indexOf("done") == 0) {
//                int number = input.nextInt();
                int number = Character.getNumericValue(line.charAt(line.length() - 1));
                System.out.println(number);
                todo.get(number - 1).checkOff();
                System.out.println(underscore + "Nice! I've marked this task as done: \n" +
                            todo.get(number - 1) + "\n" + underscore
                        );
            } else {
                if (line.indexOf("todo") == 0){
                    String[] splits = line.split("todo");
                    Todo task = new Todo(splits[1]);
                    todo.add(task);
                    System.out.println(underscore + "Got it. I've added this to task: \n" + task + "\n"
                            + "Now you have " + todo.size() + " tasks in the list \n" + underscore
                    );
                } else if (line.indexOf("deadline") == 0) {
                    String[] splits = line.split("deadline |/by ");
                    Deadline task = new Deadline(splits[1], splits[2]);
                    todo.add(task);
                    System.out.println(underscore + "Got it. I've added this to task: \n" + task + "\n"
                            + "Now you have " + todo.size() + " tasks in the list \n" + underscore
                    );
                } else if (line.indexOf("event") == 0){
                    String[] splits = line.split("event |/at");
                    Event task = new Event(splits[1], splits[2]);
                    todo.add(task);
                    System.out.println(underscore + "Got it. I've added this to task: \n" + task + "\n"
                            + "Now you have " + todo.size() + " tasks in the list \n" + underscore
                    );
                }
            }
        }
        input.close();
    }
}
