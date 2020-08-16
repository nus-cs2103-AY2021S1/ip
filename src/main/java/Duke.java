import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> todoList = new ArrayList<>();

        chatBot:
        while (sc.hasNextLine()) {
            String[] userInput = sc.nextLine().split(" ", 2);
            String userOp = userInput[0]; //type of operation
            try {
                switch (userOp) {
                    case "bye":
                        System.out.println("Bye! Hope to see you again soon!");
                        break chatBot;
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < todoList.size(); i++) {
                            System.out.println((i + 1) + ". " + todoList.get(i));
                        }
                        break;
                    case "done": {
                        int taskId = Integer.parseInt(userInput[1]);
                        Task task = todoList.get(taskId - 1);
                        task.markDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(task);
                        break;
                    }
                    case "todo":
                    case "deadline":
                    case "event": {
                        Task task = null;
                        if (userInput.length == 1) {
                            throw new DukeException("☹ OOPS!!! The description of a " + userOp +" cannot be empty.");
                        } else if (userOp.equals("todo")) {
                            String description = userInput[1];
                            task = new ToDo(description);
                            todoList.add(task);
                        } else if (userOp.equals("deadline")) {
                            String[] description = userInput[1].split(" /by ");
                            if (description.length < 2) {
                                throw new DukeException("☹ OOPS!!! You need to give me a time.");
                            }
                            task = new Deadline(description[0], description[1]);
                            todoList.add(task);
                        } else {
                            String[] description = userInput[1].split(" /at ");
                            if (description.length < 2) {
                                throw new DukeException("☹ OOPS!!! You need to give me a time.");
                            }
                            task = new Event(description[0], description[1]);
                            todoList.add(task);
                        }
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task);
                        System.out.println("Now you have " + todoList.size() + " task(s) in the list.");
                        break;
                    }
                    case "delete":
                        if (userInput.length == 1) {
                            throw new DukeException("☹ OOPS!!! You have to tell me what to delete.");
                        }
                        int taskId = Integer.parseInt(userInput[1]);
                        if (taskId > todoList.size()) {
                            throw new DukeException("☹ OOPS!!! There's no task with this ID.");
                        }
                        Task task = todoList.get(taskId - 1);
                        todoList.remove(task);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(task);
                        System.out.println("Now you have " + todoList.size() + " task(s) in the list.");
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

