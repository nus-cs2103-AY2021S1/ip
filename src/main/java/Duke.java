import java.util.Scanner;

public class Duke {
    public static String iterateToDo(Task[] arr) {
        String output = "";
        int counter = 1;
        for (int x = 0; x < arr.length; x++) {
            if (arr[x] == null) {
                break;
            } else {
                output += Integer.toString(counter) + ". " + arr[x].toString() + "\n";
                counter++;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String hor_line = "____________________________________\n";
        System.out.println(hor_line + "Hello! I'm Duke\n" + "What can I do for you?\n" + hor_line);
        Task[] todo_list = new Task[100];
        int counter = 0;
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String echo = sc.nextLine();
            if (echo.equals("bye")) {
                System.out.println(hor_line + "Bye. Hope to see you again soon!\n" + hor_line);
                sc.close();
                break;
            } else if (echo.equals("list")) {
                System.out.println(hor_line + "Here are the tasks in your list: \n");
                System.out.println(iterateToDo(todo_list) + hor_line);
            } else if (echo.startsWith("done")) {
                String index = echo.substring(echo.length() - 1);
                int number = Integer.parseInt(index) - 1;
                todo_list[number] = todo_list[number].markDone();
                System.out.println(hor_line + "Nice! Now I will mark this as done: \n" + todo_list[number].toString() + "\n" + hor_line);
            } else if (echo.startsWith("todo")) {
                String instructions = echo.substring(5);
                todo_list[counter] = new Todo(false, counter + 1, instructions);
                System.out.println(hor_line + "Got it. I've added this task: \n" + todo_list[counter].toString() + "\n" +
                        "You now have " + Integer.toString(counter + 1) + " tasks in the list.\n" + hor_line);
                counter++;
            } else if (echo.startsWith("deadline")) {
                String instructions = echo.substring(9);
                String[] arr = instructions.split("/by");
                instructions = arr[0].substring(0, arr[0].length() - 1);
                String date = arr[1].substring(1);
                todo_list[counter] = new Deadline(false, counter + 1, instructions, date);
                System.out.println(hor_line + "Got it. I've added this task: \n" + todo_list[counter].toString() + "\n" +
                        "You now have " + Integer.toString(counter + 1) + " tasks in the list.\n" + hor_line);
                counter++;
            } else if (echo.startsWith("event")) {
                String instructions = echo.substring(5);
                String[] arr = instructions.split("/at");
                instructions = arr[0].substring(0, arr[0].length() - 1);
                String time = arr[1].substring(1);
                todo_list[counter] = new Event(false, counter + 1, instructions, time);
                System.out.println(hor_line + "Got it. I've added this task: \n" + todo_list[counter].toString() + "\n" +
                        "You now have " + Integer.toString(counter + 1) + " tasks in the list.\n" + hor_line);
                counter++;
            }else {
                
            }
        }
    }
}
