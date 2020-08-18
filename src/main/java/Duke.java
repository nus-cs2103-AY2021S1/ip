import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------\n" +
                                "Hello! I'm Duke\n" + "What can I do for you?" +
                                        "\n-------------------------------------------\n");

        Scanner sc = new Scanner(System.in);
        String input = "";
        ArrayList<Task> taskList = new ArrayList<>();
        int i = 1;
        String done = "done x";
        char[] doneArray = done.toCharArray();
        int number = 0;

        while (input != null) {
            input = sc.nextLine();
            Task t = new Task(input);
            char[] chars = t.getDescription().toCharArray();
            if (t.getDescription().equals("bye")) {
                System.out.println("-------------------------------------------\n" +
                                        "Bye. Hope to see you again soon!" +
                                                "\n-------------------------------------------\n");
                System.exit(0);

            } else if (t.getDescription().equals("list")) {
                System.out.println("-------------------------------------------\n" +
                                        "Here are the tasks in your list:\n");

                for(int j = 1; j < i; j++) {
                    System.out.println(j + ". " + "[" + t.getStatusIcon() + "] " + taskList.get(j-1).getDescription());
                }
                System.out.println("-------------------------------------------\n");
            }
            else if (chars.length == doneArray.length) {
                if (chars[0] == doneArray[0] && chars[1] == doneArray[1] && chars[2] == doneArray[2] &&
                        chars[3] == doneArray[3] && chars[4] == doneArray[4] &&
                        Character.isDigit(chars[5])) {

                    number = Character.getNumericValue(chars[chars.length - 1]);
                    taskList.get(number -1).markAsDone();
                    System.out.println("-------------------------------------------\n" +
                            " Nice! I've marked this task as done:\n"
                            + "[" + taskList.get(number -1).getStatusIcon() + "] " + taskList.get(number -1).getDescription());
                }
            } else {
                taskList.add(i-1, t);
                System.out.println("-------------------------------------------\n" +
                                        "added:" + input +
                                                "\n-------------------------------------------\n");
                i++;
            }

        }
    }
}


//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

