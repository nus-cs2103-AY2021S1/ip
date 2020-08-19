import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String horizontal = "________________________________" + "\n";
    public static ArrayList<Task> list = new ArrayList<>();
    public static int counter = 1;

    public static void action() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    String bye = "Bye. Hope to see you again soon!" + "\n";
                    System.out.println(horizontal + bye + horizontal);
                    break;
                } else if (input.equals("list")) {
                    System.out.println(horizontal + "Here are the tasks in your list:" + "\n");
                    for (Task task : list) {
                        System.out.println(task.getNum() + "." + task.toString());
                    }
                    System.out.println(horizontal);
                } else if (input.startsWith("done")) {
                    String[] number = input.split("done ");
                    int num = Integer.parseInt(number[1]);
                    Task task = list.get(num - 1);
                    task.markAsDone();
                    System.out.println(horizontal + "Nice! I've marked this task as done:" + "\n" +
                            task.toString() + "\n" + horizontal);
                } else {
                    addTask(input, list, counter);
                    counter++;
                }
            } catch (DukeException e) {
                System.out.println(horizontal + "Oops!!! " + e.getMessage() + "\n" + horizontal);
            }
        }
    }

    public static void greet() {
        String hello = "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n";
        System.out.println(horizontal + hello + horizontal);
    }

    public static void addTask(String input, ArrayList<Task> list, int counter) throws DukeException {
        if (input.startsWith("todo")) {
            String[] array = input.split("todo ");
            if (array.length == 1) {
                throw new DukeException("The description of a todo cannot be empty!");
            } else {
                String des = array[1];
                ToDos todo = new ToDos(des, counter);
                list.add(todo);
                System.out.println(horizontal + "Got it. I've added this task:" + "\n" + todo.toString() + "\n" +
                        "Now you have " + counter + " tasks in the list." + "\n" + horizontal);
            }
        } else if (input.startsWith("deadline")) {
            String[] array = input.split("deadline ");
            if (array.length == 1) {
                throw new DukeException("The description of a deadline cannot be empty!");
            } else {
                String[] arr = array[1].split(" /by ");
                String des = arr[0];
                if (arr.length == 1) {
                    throw new DukeException("The deadline of the task cannot be empty!");
                } else {
                    String due = arr[1];
                    Deadline dl = new Deadline(des, counter, due);
                    list.add(dl);
                    System.out.println(horizontal + "Got it. I've added this task:" + "\n" + dl.toString() + "\n" +
                            "Now you have " + counter + " tasks in the list." + "\n" + horizontal);
                }
            }
        } else if (input.startsWith("event")) {
            String[] array = input.split("event");
            if (array.length == 1) {
                throw new DukeException("The description of an event cannot be empty!");
            } else {
                String[] arr = array[1].split(" /at ");
                String des = arr[0];
                if (arr.length == 1) {
                    throw new DukeException("The date of the event cannot be empty!");
                } else {
                    String date = arr[1];
                    Events event = new Events(des, counter, date);
                    list.add(event);
                    System.out.println(horizontal + "Got it. I've added this task:" + "\n" + event.toString() + "\n" +
                            "Now you have " + counter + " tasks in the list." + "\n" + horizontal);
                }
            }
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means.");
        }
    }


    public static void main(String[] args) {
        greet();
        action();

        //String logo = " ____        _        \n"
        //      + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
    }
}

