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
        String t = "t";
        char[] todoChar = t.toCharArray();
        String d = "d";
        char[] deadlineChar = d.toCharArray();
        String e = "e";
        char[] eventChar = e.toCharArray();

        while (input != null) {
            input = sc.nextLine();
            Task task = new Task(input);
            char[] chars = task.getDescription().toCharArray();
            if (task.getDescription().equals("bye")) {
                System.out.println("-------------------------------------------\n" +
                                        "Bye. Hope to see you again soon!" +
                                                "\n-------------------------------------------\n");
                System.exit(0);

            } else if (task.getDescription().equals("list")) {
                System.out.println("-------------------------------------------\n" +
                                        "Here are the tasks in your list:\n");

                for(int j = 0; j < taskList.size(); j++) {
                    if (taskList.get(j).getDescription().charAt(0) == todoChar[0]){
                        Todo taskTodo = new Todo(taskList.get(j).getDescription());
                        System.out.println(j+1 + "." + taskTodo.toString());
                    } else if (taskList.get(j).getDescription().charAt(0) == deadlineChar[0]) {
                        String taskName = taskList.get(j).getDescription();
                        String[] parts = taskName.split("/by ");
                        //String[] subparts = parts[0].split("deadline");
                        Deadline taskDeadline = new Deadline(taskList.get(j).getDescription(), parts[1]);
                        System.out.println(j+1 + "." + taskDeadline.toString());
                    } else {
                        String taskName = taskList.get(j).getDescription();
                        String[] parts = taskName.split("/at ");
                        //String[] subparts = parts[0].split("event");
                        Event taskEvent = new Event(taskList.get(j).getDescription(), parts[1]);
                        System.out.println(j+1 + "." + taskEvent.toString());
                    }

                    //". " + "[" + t.getStatusIcon() + "] " + taskList.get(j-1).getDescription());
                }
                System.out.println(String.format("Now you have %d tasks in the list.\n", taskList.size()) +
                                            "-------------------------------------------\n");
            }
            // done case
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
                //if it is a todo
                if (chars[0] == todoChar[0]) {
                    taskList.add(i-1, task);
                    Todo taskTodo = new Todo(task.getDescription());
                    System.out.println("-------------------------------------------\n" +
                                            "Got it. I've added this task: \n" + taskTodo.toString() + "\n" +
                                                String.format("Now you have %d tasks in the list.\n", taskList.size())
                                                    + "-------------------------------------------\n");
                    i++;

                    // if it is a deadline
                } else if (chars[0] == deadlineChar[0]) {
                    taskList.add(i-1, task);
                    String taskName = task.getDescription();
                    String[] parts = taskName.split("/by ");

                    Deadline taskDeadline = new Deadline(task.getDescription(), parts[1]);
                    System.out.println("-------------------------------------------\n" +
                                             "Got it. I've added this task: \n" + taskDeadline.toString() + "\n" +
                                                String.format("Now you have %d tasks in the list.\n", taskList.size())
                                                    + "-------------------------------------------\n");
                    i++;
                } else {
                    // if it is an event
                    taskList.add(i-1, task);
                    String taskName = task.getDescription();
                    String[] parts = taskName.split("/at ");

                    Event taskEvent = new Event(task.getDescription(), parts[1]);
                    System.out.println("-------------------------------------------\n" +
                                            "Got it. I've added this task: \n" + taskEvent.toString() + "\n" +
                                                String.format("Now you have %d tasks in the list.\n", taskList.size())
                                                    + "-------------------------------------------\n");
                    i++;
                }
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

