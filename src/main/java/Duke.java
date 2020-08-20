import java.util.Scanner;

public class Duke {
    public static String iterateToDo(Task[] arr) {
        String output = "";
        int counter = 1;
        for (Task task : arr) {
            if (task == null) {
                break;
            } else {
                output += Integer.toString(counter) + ". " + task.toString() + "\n";
                counter++;
            }
        }
        return output;
    }

    public static Task[] deleteTask(int index, Task[] arr) {
        arr[index - 1] = null;
        for (int x = index; x < arr.length; x++) {
            if (arr[x] == null) {
                break;
            } else {
                arr[x - 1] = arr[x];
                arr[x] = null;
            }
        }
        return arr;
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
            try {
                if (echo.equals("bye")) {
                    System.out.println(hor_line + "Bye. Hope to see you again soon!\n" + hor_line);
                    sc.close();
                    break;
                } else if (echo.equals("list")) {
                    System.out.println(hor_line + "Here are the tasks in your list: \n");
                    System.out.println(iterateToDo(todo_list) + hor_line);
                } else if (echo.startsWith("delete")) {
                    String index = echo.substring(echo.length() - 1);
                    int number = Integer.parseInt(index);
                    todo_list = deleteTask(number, todo_list);
                    System.out.println(hor_line + "Task deleted! You now have " + Integer.toString(counter - 1) + " tasks left. \n"
                        + hor_line);
                    counter --;
                } else if (echo.startsWith("done")) {
                    if (echo.equals("done")) {
                        throw new ResponseException(hor_line + "☹ OOPS!!! Please state which task is done. \n" + hor_line);
                    } else {
                        String index = echo.substring(echo.length() - 1);
                        int number = Integer.parseInt(index) - 1;
                        todo_list[number] = todo_list[number].markDone();
                        System.out.println(hor_line + "Nice! Now I will mark this as done: \n" + todo_list[number].toString() + "\n" + hor_line);
                    }
                } else if (echo.startsWith("todo")) {
                    if (echo.equals("todo")) {
                        throw new ResponseException(hor_line + "☹ OOPS!!! The description of a todo cannot be empty. \n" + hor_line);
                    } else {
                        String instructions = echo.substring(5);
                        todo_list[counter] = new Todo(false, counter + 1, instructions);
                        System.out.println(hor_line + "Got it. I've added this task: \n" + todo_list[counter].toString() + "\n" +
                                "You now have " + Integer.toString(counter + 1) + " tasks in the list.\n" + hor_line);
                        counter++;
                    }
                } else if (echo.startsWith("deadline")) {
                    if (echo.equals("deadline")) {
                        throw new ResponseException(hor_line + "☹ OOPS!!! The description of a deadline cannot be empty. \n" + hor_line);
                    } else {
                        String instructions = echo.substring(9);
                        String[] arr = instructions.split("/by");
                        instructions = arr[0].substring(0, arr[0].length() - 1);
                        String date = arr[1].substring(1);
                        todo_list[counter] = new Deadline(false, counter + 1, instructions, date);
                        System.out.println(hor_line + "Got it. I've added this task: \n" + todo_list[counter].toString() + "\n" +
                                "You now have " + Integer.toString(counter + 1) + " tasks in the list.\n" + hor_line);
                        counter++;
                    }
                } else if (echo.startsWith("event")) {
                    if (echo.equals("event")) {
                        throw new ResponseException(hor_line + "☹ OOPS!!! The description of a event cannot be empty. \n" + hor_line);
                    } else {
                        String instructions = echo.substring(5);
                        String[] arr = instructions.split("/at");
                        instructions = arr[0].substring(0, arr[0].length() - 1);
                        String time = arr[1].substring(1);
                        todo_list[counter] = new Event(false, counter + 1, instructions, time);
                        System.out.println(hor_line + "Got it. I've added this task: \n" + todo_list[counter].toString() + "\n" +
                                "You now have " + Integer.toString(counter + 1) + " tasks in the list.\n" + hor_line);
                        counter++;
                    }
                }else {
                    throw new ResponseException(hor_line + "☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n" + hor_line);
                }
            } catch (ResponseException exception){
                System.out.println(exception.getMessage());
            }

        }
    }
}
