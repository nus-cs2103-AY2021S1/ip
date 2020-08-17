import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

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
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    }
                } else if (splitArr.length == 2 && splitArr[0].equals("done") && Integer.parseInt(splitArr[1]) > 0) {
                    int index = Integer.parseInt(splitArr[1]);
                    if (index > tasks.size() || index < 0) {
                        throw new DukeException("OOPS!!! That task number does not exist.");
                    }
                    tasks.get(index - 1).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index - 1).toString());
                } else if (splitArr.length == 2 && splitArr[0].equals("delete") && Integer.parseInt(splitArr[1]) > 0) {
                    int index = Integer.parseInt(splitArr[1]);
                    if (index > tasks.size() || index < 0) {
                        throw new DukeException("OOPS!!! That task number does not exist.");
                    }
                    Task deletedTask = tasks.remove(index - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(deletedTask.toString());
                    System.out.println("Now, you have " + tasks.size() + " tasks in the list");
                } else {
                    switch (splitArr[0]) {
                        case "todo":
                            if (splitArr.length <= 1) {
                                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                            }
                            System.out.println("Got it. I've added this task:");
                            Task newTask = new ToDo(input.substring(5));
                            tasks.add(newTask);
                            System.out.println(newTask.toString());
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
                            Task newDeadline = new Deadline(desc, date);
                            tasks.add(newDeadline);
                            System.out.println(newDeadline.toString());
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
                            Task newEvent = new Event(des, dat);
                            tasks.add(newEvent);
                            System.out.println(newEvent.toString());
                            break;
                        default:
                            System.out.println("dada");
                            throw new DukeException("OOPS!! I'm sorry, but I don't know what that means :-(");

                    }
                    System.out.println("Now, you have " + tasks.size() + " tasks in the list");
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
