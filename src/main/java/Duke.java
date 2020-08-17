import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

   private static Commands comm;


    public static void main(String[] args) {

        String line = "____________________________________________________________";
        String introduction = "Hello! I'm Duke \nWhat can I do for you?";
        System.out.println(line);
        System.out.println(introduction);
        System.out.println(line);

        ArrayList<Task> ListOfItems = new ArrayList<Task>();
        boolean flag = false;
        String echo;
        Scanner sc = new Scanner(System.in);
        echo = sc.nextLine();
        while (!flag) {

            if (echo.equals("bye")) {
                comm = Commands.bye;
                flag = true;
            } else if (echo.equals("list")) {
                comm = Commands.list;
            } else {
                String split = echo;
                String arr[] = split.split(" ", 2);
                if (arr[0].equals("done")) {
                    comm = Commands.done;
                } else if (arr[0].equals("todo")) {
                    comm = Commands.todo;
                } else if (arr[0].equals("deadline")) {
                    comm = Commands.deadline;
                } else if (arr[0].equals("event")) {
                    comm = Commands.event;
                } else if (arr[0].equals("delete")) {
                    comm = Commands.delete;
                } else {
                    comm = Commands.gibberish;
                }
            }
            String split = echo;
            String arr[] = split.split(" ", 2);

switch (comm) {
    case bye:
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

        break;
    case list:
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < ListOfItems.size(); i++) {
            int number = i + 1;
            System.out.println(Integer.toString(number) + ". " + ListOfItems.get(i));

        }
        System.out.println(line);
        echo = sc.nextLine();
        break;
    case done:
        try {
        int index = Integer.parseInt(arr[1]) - 1;
        Task taskToChange = ListOfItems.get(index);
        taskToChange.markAsDone();
        ListOfItems.set(index, taskToChange);
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done: \n" + ListOfItems.get(index));
        System.out.println(line);
        echo = sc.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException("☹ OOPS!!! There is no task at that list number to mark as done!" , e));
            echo = sc.nextLine();
        }
        break;
    case todo:
        try {
        ToDo item = new ToDo(arr[1]);

        ListOfItems.add(item);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(item);
        System.out.println("Now you have " + ListOfItems.size() + " tasks in the list.");
        System.out.println(line);
        echo = sc.nextLine();
    } catch (ArrayIndexOutOfBoundsException e) {

        System.out.println(new DukeException("☹ OOPS!!! The description of a todo cannot be empty.", e));
        echo = sc.nextLine();
    }
    break;
    case deadline:
        try {
        String obtainDate = arr[1];
        String arr2[] = obtainDate.split("/by", 2);
        String descrip = arr2[0];
        String date = arr2[1];
        Deadline item = new Deadline(descrip, date);
        ListOfItems.add(item);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(item);
        System.out.println("Now you have " + ListOfItems.size() + " tasks in the list.");
        System.out.println(line);
        echo = sc.nextLine();
    } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println(new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.", e));
        echo = sc.nextLine();
    }
    break;
    case event:
        try {
        String obtainDate = arr[1];
        String arr2[] = obtainDate.split("/at", 2);
        String descrip = arr2[0];
        String date = arr2[1];
        Event item = new Event(descrip, date);
        ListOfItems.add(item);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(item);
        System.out.println("Now you have " + ListOfItems.size() + " tasks in the list.");
        System.out.println(line);
        echo = sc.nextLine();
    } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println(new DukeException("☹ OOPS!!! The description of a event cannot be empty.", e));
        echo = sc.nextLine();
    }
    break;
    case delete:
        try {
            int index2 = Integer.parseInt(arr[1]) - 1;
            Task taskToChange2 = ListOfItems.get(index2);

            ListOfItems.remove(index2);
            System.out.println(line);
            System.out.println("Noted. I've removed this task: \n" + taskToChange2);
            System.out.println("Now you have " + ListOfItems.size() + " tasks in the list.");
            System.out.println(line);
            echo = sc.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException("☹ OOPS!!! There is no task at that list number to delete!", e));
            echo = sc.nextLine();
        }
        break;
    case gibberish:
        System.out.println(line);
        System.out.println("DukeException: ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(line);
        echo = sc.nextLine();
}


        }


    }


}
