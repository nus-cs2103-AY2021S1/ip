import java.util.Scanner;
import java.util.ArrayList;

/**
 * Duke class that contains the main logic for the chat bot DUKE.
 */
public class Duke {

    public static String lineDivider = "--------------------------------------------";

    /**
     * Echos the message that the user put.
     * @param message  A String message that user wants the chat bot to echo.
     */
    public static void echo(String message) {
        System.out.println(lineDivider);
        System.out.println(message);
        System.out.println(lineDivider + "\n");
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        echo("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Arrange list of task into a more readable format.
     * @param list
     * @return A more readable String that represents the contents in the task list.
     */
    public static String showList(ArrayList<Task> list) {
        StringBuffer lst = new StringBuffer();
        lst.append("Here are the tasks in your list:\n");
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            if (i + 1 != listSize) {
                lst.append((i + 1) + ". " + list.get(i).toString() + "\n");
            } else {
                lst.append((i + 1) + ". " + list.get(i).toString());
            }
        }
        return lst.toString();
    }

    /**
     * Check if an integer is represented in the String
     * @param s  String input
     * @return   True or False
     */
    public static boolean isInt(String s) {
        try{
            int num = Integer.parseInt(s);
            // is an integer!
            return true;
        } catch (NumberFormatException e) {
            // not an integer!
            return false;
        }
    }

    /**
     * Main logic for the chat bot
     * @param args User input
     */
    public static void main(String[] args) {

        greet();

        Scanner sc = new Scanner(System.in);

        // Arraylist used to store all tasks.
        ArrayList<Task> list = new ArrayList<>();

        while (sc.hasNext()) {
            String msg = sc.nextLine();

            try {
                if (msg.equals("bye")) {
                    echo("Bye. Hope to see you again soon!");
                    break;
                }

                if (msg.equals("list")) {
                    echo(showList(list));
                    continue;
                }

                String[] msgArray = msg.split(" ");
                String taskS = " task ";

                if (msgArray[0].equals("done")) {
                    if ((msgArray.length != 2) || (!isInt(msgArray[1]))) {
                        throw new InvalidDoneInputException();
                    }
                    int index = Integer.parseInt(msgArray[1]) - 1;
                    if (index < 0 || index >= list.size()) {
                        throw new InvalidDoneInputException();
                    } else {
                        list.get(index).markAsDone();
                        echo("Nice! I've marked this task as done:\n" + list.get(index).toString());
                    }
                    continue;
                }

                if (msgArray[0].equals("delete")) {
                    if ((msgArray.length != 2) || (!isInt(msgArray[1]))) {
                        throw new InvalidDeleteInputException();
                    }
                    int index = Integer.parseInt(msgArray[1]) - 1;
                    if (index < 0 || index >= list.size()) {
                        throw new InvalidDeleteInputException();
                    } else {
                        String taskInfo = list.get(index).toString();
                        list.remove(index);
                        if (list.size() < 2) {
                            taskS = " task ";
                        }
                        echo("Noted. I've removed this task:\n"
                                + taskInfo
                                + "\nNow you have "
                                + list.size()
                                + taskS
                                + "in the list.");
                    }
                    continue;
                }

                if (msgArray[0].equals("todo")) {
                    if (msgArray.length < 2) {
                        throw new InvalidTodoInputException();
                    } else {
                        String todoTask = msg.substring(5);
                        ToDo todo = new ToDo(todoTask);
                        list.add(todo);
                        if (list.size() > 1) {
                            taskS = " tasks ";
                        }
                        echo("Got it. I've added this task:\n"
                                + todo.toString()
                                + "\nNow you have "
                                + list.size()
                                + taskS
                                + "in the list.");
                    }
                    continue;
                }

                if (msgArray[0].equals("deadline")) {
                    if (msgArray.length < 2) {
                        throw new NullDeadlineInputException();
                    }
                    String deadlineTask = msg.substring(9);
                    String[] deadlineTaskArray = deadlineTask.split(" /by ");
                    if (deadlineTaskArray.length != 2) {
                        throw new InvalidDeadlineInputException();
                    } else {
                        Deadline deadline = new Deadline(deadlineTaskArray[0], deadlineTaskArray[1]);
                        list.add(deadline);
                        if (list.size() > 1) {
                            taskS = " tasks ";
                        }
                        echo("Got it. I've added this task:\n"
                                + deadline.toString()
                                + "\nNow you have "
                                + list.size()
                                + taskS
                                + "in the list.");
                    }
                    continue;
                }

                if (msgArray[0].equals("event")) {
                    if (msgArray.length < 2) {
                        throw new NullEventInputException();
                    }
                    String eventTask = msg.substring(6);
                    String[] eventTaskArray = eventTask.split(" /at ");
                    if (eventTaskArray.length != 2) {
                        throw new InvalidEventInputException();
                    } else {
                        Event event = new Event(eventTaskArray[0], eventTaskArray[1]);
                        list.add(event);
                        if (list.size() > 1) {
                            taskS = " tasks ";
                        }
                        echo("Got it. I've added this task:\n"
                                + event.toString()
                                + "\nNow you have "
                                + list.size()
                                + taskS
                                + "in the list.");
                    }
                    continue;
                }
                throw new InvalidInputException();
            } catch (DukeException e) {
                echo(e.getMessage());
            }
        }
    }
}
