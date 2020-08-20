import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) throws DukeException, IOException {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        System.out.println("-------------------------------------------\n" +
                "Hello! I'm Duke\n" + "What can I do for you?\n" +
                "-------------------------------------------\n");

        Scanner sc = new Scanner(System.in);
        String input = "";
        ArrayList<Task> taskList = new ArrayList<>();
        int i = 1;
        String done = "done x";
        char[] doneArray = done.toCharArray();
        String delete = "delete x";
        char[] deleteArray = delete.toCharArray();
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
                        "-------------------------------------------");
                System.exit(0);

            } else if (task.getDescription().equals("list")) {
                System.out.println("-------------------------------------------\n" +
                        "Here are the tasks in your list:\n");

                for (int j = 0; j < taskList.size(); j++) {
                    if (taskList.get(j).getDescription().charAt(0) == todoChar[0]) {
                        Todo taskTodo = new Todo(taskList.get(j).getDescription());
                        System.out.println(j + 1 + "." + taskTodo.toString());
                    } else if (taskList.get(j).getDescription().charAt(0) == deadlineChar[0]) {
                        String taskName = taskList.get(j).getDescription();
                        String[] parts = taskName.split("/by ");
                        //String[] subparts = parts[0].split("deadline");
                        Deadline taskDeadline = new Deadline(taskList.get(j).getDescription(), parts[1]);
                        System.out.println(j + 1 + "." + taskDeadline.toString());
                    } else {
                        String taskName = taskList.get(j).getDescription();
                        String[] parts = taskName.split("/at ");
                        //String[] subparts = parts[0].split("event");
                        Event taskEvent = new Event(taskList.get(j).getDescription(), parts[1]);
                        System.out.println(j + 1 + "." + taskEvent.toString());
                    }
                }
                System.out.println(String.format("Now you have %d tasks in the list.\n", taskList.size()) +
                        "-------------------------------------------");
            }
            // done case
            else if (chars.length == doneArray.length) {
                if (chars[0] == doneArray[0] && chars[1] == doneArray[1] &&
                        chars[2] == doneArray[2] && chars[3] == doneArray[3] &&
                        chars[4] == doneArray[4] && Character.isDigit(chars[5])) {

                    number = Character.getNumericValue(chars[chars.length - 1]);
                    taskList.get(number - 1).markAsDone();
                    System.out.println("-------------------------------------------\n" +
                            " Nice! I've marked this task as done:\n" + "[" +
                            taskList.get(number - 1).getStatusIcon() + "] " +
                            taskList.get(number - 1).getDescription());
                }
            }
                // delete method
                else if (chars.length == deleteArray.length) {
                    if (chars[0] == deleteArray[0] && chars[1] == deleteArray[1] &&
                            chars[2] == deleteArray[2] && chars[3] == deleteArray[3] &&
                            chars[4] == deleteArray[4] && chars[5] == deleteArray[5] &&
                            chars[6] == deleteArray[6] && Character.isDigit(chars[7])) {

                        number = Character.getNumericValue(chars[chars.length - 1]);

                        System.out.println("-------------------------------------------\n" +
                                " Noted. I've removed this task:\n" + "[" +
                                taskList.get(number - 1).getStatusIcon() + "] " +
                                taskList.get(number - 1).getDescription() + "\n" +
                                String.format("Now you have %d tasks in the list.\n", taskList.size() -1)
                                + "-------------------------------------------------------------");

                        taskList.remove(number - 1);
                    }
                } else {
                    //if it is a todo
                    try {
                        if (chars[0] == todoChar[0]) {
                            try {
                                taskList.add(i - 1, task);
                                Todo taskTodo = new Todo(task.getDescription());
                                System.out.println("-------------------------------------------\n" +
                                        "Got it. I've added this task:\n" + taskTodo.toString() + "\n" +
                                        String.format("Now you have %d tasks in the list.\n", taskList.size())
                                        + "-------------------------------------------");
                                i++;
                            } catch (ArrayIndexOutOfBoundsException ex) {
                                System.out.println("-------------------------------------------\n" +
                                        "☹ OOPS!!! The description of a todo cannot be empty. Try again!\n" +
                                        "-------------------------------------------");
                            }
                            // if it is a deadline
                        } else if (chars[0] == deadlineChar[0]) {
                            try {
                                taskList.add(i - 1, task);
                                String taskName = task.getDescription();
                                String[] parts = taskName.split("/by ");

                                Deadline taskDeadline = new Deadline(task.getDescription(), parts[1]);
                                System.out.println("-------------------------------------------------------------\n" +
                                        "Got it. I've added this task:\n" + taskDeadline.toString() + "\n" +
                                        String.format("Now you have %d tasks in the list.\n", taskList.size())
                                        + "-------------------------------------------------------------");
                                i++;
                                if (chars.length == 8) {
                                    throw new ArrayIndexOutOfBoundsException("Cannot be empty");
                                }
                            } catch (ArrayIndexOutOfBoundsException ex) {
                                System.out.println("-------------------------------------------------------------\n" +
                                        "☹ OOPS!!! The description of a deadline cannot be empty. Try again!\n" +
                                        "-------------------------------------------------------------");
                            }
                        } else if (chars[0] == eventChar[0]) {
                            try {
                                // if it is an event
                                taskList.add(i - 1, task);
                                String taskName = task.getDescription();
                                String[] parts = taskName.split("/at ");

                                Event taskEvent = new Event(task.getDescription(), parts[1]);
                                System.out.println("-------------------------------------------------------------\n" +
                                        "Got it. I've added this task:\n" + taskEvent.toString() + "\n" +
                                        String.format("Now you have %d tasks in the list.\n", taskList.size())
                                        + "-------------------------------------------------------------");
                                i++;
                            } catch (ArrayIndexOutOfBoundsException ex) {
                                System.out.println("-------------------------------------------------------------\n" +
                                        "☹ OOPS!!! The description of an event cannot be empty. Try again!\n" +
                                        "-------------------------------------------------------------");
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println("-------------------------------------------------------------\n" +
                                "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                                "-------------------------------------------------------------");
                        System.exit(0);
                    }
                }
            }
        }
    }










