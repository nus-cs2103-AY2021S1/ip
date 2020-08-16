import main.java.Task;

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
                todo.add(new Task(line));
                System.out.println(underscore + " added: " + line + "\n" + underscore);
            }
        }
        input.close();
    }
}
