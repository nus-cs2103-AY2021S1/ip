import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int count = 0;

    public static void start() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        try {
            Scanner userInput = new Scanner(System.in);
            while (userInput.hasNext()) {
                String input = userInput.nextLine();
                String[] splitArr = input.split(" ");
                if (input.equals("bye")) {
                    System.out.println("Bye! Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.length; i++) {
                        if (tasks[i] != null) {
                            System.out.println((i + 1) + ". " + tasks[i].toString());
                        } else {
                            break;
                        }
                    }
                } else if (splitArr.length == 2 && splitArr[0].equals("done") && Integer.parseInt(splitArr[1]) > 0) {
                    int index = Integer.parseInt(splitArr[1]);
                    if (index > count || index < 0) {
                        throw new DukeException("OOPS!!! That task number does not exist.");
                    }
                    tasks[index - 1].setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[index - 1].toString());
                } else {
                    switch (splitArr[0]) {
                        case "todo":
                            if (splitArr.length <= 1) {
                                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                            }
                            System.out.println("Got it. I've added this task:");
                            tasks[count] = new ToDo(input.substring(5));
                            count++;
                            System.out.println(tasks[count - 1].toString());
                            break;
                        case "deadline":
                            if (splitArr.length <= 1) {
                                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                            }
                            int index = input.indexOf("/");
                            if (index == -1) {
                                throw new DukeException("Please include the date of the deadline!");
                            }
                            System.out.println("Got it. I've added this task:");
                            String desc = input.substring(9, index - 1);
                            String date = input.substring(index + 4);
                            tasks[count] = new Deadline(desc, date);
                            count++;
                            System.out.println(tasks[count - 1].toString());
                            break;
                        case "event":
                            if (splitArr.length <= 1) {
                                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                            }
                            int ind = input.indexOf("/");
                            if (ind == -1) {
                                throw new DukeException("Please include the date of the event!");
                            }
                            System.out.println("Got it. I've added this task:");
                            String des = input.substring(6, ind - 1);
                            String dat = input.substring(ind + 4);
                            tasks[count] = new Event(des, dat);
                            count++;
                            System.out.println(tasks[count - 1].toString());
                            break;
                        default:
                            System.out.println("dada");
                            throw new DukeException("OOPS!! I'm sorry, but I don't know what that means :-(");

                    }
                    System.out.println("Now, you have " + count + " tasks in the list");
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        start();
    }
}
