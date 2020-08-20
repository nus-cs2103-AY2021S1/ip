import java.util.Scanner;

public class Duke {

    static String line = "_________________________________________________________________";
    static Task[] list = new Task[100];
    static int index = 0;

    public static void greeting() {
        String str = ("\t" + line + "\n"
                + "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + "\t" + line);
        System.out.println(str);
    }

    public static void bye(){
        System.out.println("\t" + line + "\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t" + line);
    }

    public static void addTask(Task task) {
        list[index] = task;
        index++;
        System.out.println("\t" + line + "\n\tGot it. I've added this task:\n"
                + "\t  " + task + "\n"
                + "\tNow you have " + index + " tasks in the list.\n"
                + "\t" + line);
    }

    public static void showList(){
        System.out.println("\t" + line + "\n\tHere are the tasks in your list:");
        for (int i = 0; i < index; i++){
            System.out.println("\t" + (i+1) + ". " + list[i]);
        }
        System.out.println("\t" + line);
    }

    public static String firstWord(String str) {
        return str.split(" ")[0]; // create array of words and return 0th word
    }

    public static int getTaskNum(String str) {
        return Integer.parseInt(str.split(" ")[1]);
    }

    public static void markDone(int num) {
        if (list[num-1] == null) {
            System.out.println("Error :("); // error catch
        } else {
            list[num - 1].markDone();
            System.out.println("\t" + line + "\n\tNice! I've marked this task as done:\n\t  "
                    + list[num - 1]
                    + "\n\t" + line);
        }
    }



    public static void main(String[] args) {
        greeting();
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String input = sc.nextLine().toLowerCase(); // everything in lower case
                // possible actions: type task (todos/deadline/event), list, bye, done 2
                if (input.equals("bye")) {
                    bye();
                    sc.close();
                    break;
                } else if (input.equals("list")) {
                    showList();
                } else if (firstWord(input).equals("done")) {
                    try {
                        int taskNum = getTaskNum(input);
                        markDone(taskNum);
                    } catch (IndexOutOfBoundsException | NumberFormatException e) {
                        System.err.println("☹ OOPS!!! The task number is invalid.");
                    }
                } else if (firstWord(input).equals("todo")) {
                    try {
                        Task newTask = new Todo(input.substring(5)); // cut "todo "
                        addTask(newTask);
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (firstWord(input).equals("deadline")) {
                    try {
                        String str = input.substring(9);
                        String description = str.split(" /by ")[0]; // split the stirng by "/by ", take first half
                        String time = str.split(" /by ")[1];
                        Task newTask = new Deadline(description, time);
                        addTask(newTask);
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("☹ OOPS!!! The description or date of a deadline cannot be empty.");
                    }
                } else if (firstWord(input).equals("event")) {
                    try {
                        String str = input.substring(6);
                        String description = str.split(" /at ")[0]; // split the stirng by "/by ", take first half
                        String time = str.split(" /at ")[1];
                        Task newTask = new Event(description, time);
                        addTask(newTask);
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("☹ OOPS!!! The description or date of a event cannot be empty.");
                    }
                } else { // when input cannot be recognised
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) { // catch all the exception I manually throwed in the try block
                System.err.println(e);
            }
        }
    }
}
