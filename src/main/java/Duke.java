import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Yooo, I'm Deke.\nWhat can I do for you today?"); //Greeting

        ArrayList<Task> list = new ArrayList<>();
        String input = sc.nextLine();

        while (!input.isEmpty()) {
            if (input.equalsIgnoreCase("bye")) { //if user types "bye"
                System.out.println("Bye bye!!! See you again next time :)");
                input = "";
                sc.close();
            } else if (input.equalsIgnoreCase("list")) { //if user types "list"
                System.out.println("here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
                input = sc.nextLine();
            } else if (input.toLowerCase().startsWith("done")) { //if user input starts with "done"
                int index;
                if (!input.substring(4).trim().isEmpty()
                        && input.substring(4).trim().matches("[0-9]+")) { //to make sure the input after "done" is a number
                    index = Integer.parseInt(input.substring(4).trim()); //convert string to integer
                    if (index >= 1) { //if input index is valid
                        Task newTask = list.get(index - 1).markAsDone();
                        list.set(index - 1, newTask);
                        System.out.println("Nice! I've marked this task as doneee! yayy:\n" + newTask);
                    } else {
                        throw new DukeException("Please enter a valid task number to mark as done (index is not valid)");
                    }
                } else {
                    throw new DukeException("Please enter a valid task number to mark as done (substring doesn't match regex)");
                }
                input = sc.nextLine();
            } else if (input.toLowerCase().startsWith("delete")) {
                int index;
                if (!input.substring(6).trim().isEmpty()
                        && input.substring(6).trim().matches("[0-9]+")) { //to make sure the input after "done" is a number
                    index = Integer.parseInt(input.substring(6).trim()); //convert string to integer
                    if (index >= 1) { //if input index is valid
                        Task removed = list.get(index - 1);
                        list.remove(index - 1);
                        System.out.println("I have removed this task:\n" + removed
                            + "\nNow you have " + list.size() + " task(s) in the list.");
                    } else {
                        throw new DukeException("Please enter a valid task number to delete (index is not valid)");
                    }
                } else {
                    throw new DukeException("Please enter a valid task number to delete (substring doesn't match regex)");
                }
                input = sc.nextLine();
            } else if (input.toLowerCase().startsWith("todo")) {
                if (!input.substring(4).trim().isEmpty()) { //to make sure to do task is not empty
                    String description = input.substring(4);
                    Task newTask = new Todo(description.trim());
                    list.add(newTask);
                    System.out.println("Got itt. I've added this task:\n    "
                            + newTask
                            + "\nNow you have " + list.size() + " task(s) in the list.");
                    input = sc.nextLine();
                } else {
                    throw new DukeException("Please enter a valid todo");
                }
            } else if (input.toLowerCase().startsWith("deadline")) {
                if (!input.substring(8).trim().isEmpty() //to make sure deadline is not empty
                        && input.substring(8).trim().contains("/by") //to make sure deadline contains /by
                        && !input.substring(8).trim().startsWith("/by") //to make sure deadline contains a task description
                        && !input.substring(8).trim().endsWith("/by")) { //to make sure deadline contains a deadline
                    String descriptionAndTime = input.substring(8);
                    String description = descriptionAndTime.trim().split("/by ")[0];
                    String by = descriptionAndTime.trim().split("/by ")[1];
                    Task newTask = new Deadline(description.trim(), by.trim());
                    list.add(newTask);
                    System.out.println("Got itt. I've added this task:\n    "
                            + newTask
                            + "\nNow you have " + list.size() + " task(s) in the list.");
                    input = sc.nextLine();
                } else {
                    throw new DukeException("Please enter a valid deadline");
                }
            } else if (input.toLowerCase().startsWith("event")) {
                if (!input.substring(5).trim().isEmpty() //to make sure event is not empty
                        && input.substring(5).trim().contains("/at") //to make sure event contains at
                        && !input.substring(5).trim().startsWith("/at") //to make sure event description is not empty
                        && !input.substring(5).trim().endsWith("/at")) { //to make sure event contains a time/date
                    String descriptionAndTime = input.substring(5);
                    String description = descriptionAndTime.split("/at ")[0];
                    String at = descriptionAndTime.split("/at ")[1];
                    Task newTask = new Event(description.trim(), at.trim());
                    list.add(newTask);
                    System.out.println("Got itt. I've added this task:\n    "
                            + newTask
                            + "\nNow you have " + list.size() + " task(s) in the list.");
                    input = sc.nextLine();
                } else {
                    throw new DukeException("Please enter a valid event");
                }
            } else {
                throw new DukeException("I'm sorry I don't know what that means :(");
            }
        }
    }
}
