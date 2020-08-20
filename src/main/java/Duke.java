import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void invalidInput() throws DukeException {
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

    public static Task createTask(String firstWord, String input) throws DukeException {
        Task newTask;
        if (firstWord.equals("todo")) {
            String[] arr = input.split("todo ");
            if (arr.length == 1) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else {
                String tsk = arr[1];
                newTask = new ToDo(tsk);
            }
        } else if (firstWord.equals("deadline")) {
            String[] arr = input.split("deadline ");
            if (arr.length == 1) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else {
                String[] split = input.split("/by ");
                if (split.length == 1) {
                    throw new DukeException("By when??? You didn't include your deadline.");
                } else {
                    String deadline = split[1];
                    String tsk = split[0].split("deadline ")[1];
                    newTask = new Deadline(tsk, deadline);
                }
            }

        } else {
            String[] arr = input.split("event ");
            if (arr.length == 1) {
                throw new DukeException("The description of an event cannot be empty.");
            } else {
                String[] split = input.split("/at ");
                if (split.length == 1) {
                    throw new DukeException("At??? You didn't include the time of the event.");
                } else {
                    String at = split[1];
                    String tsk = split[0].split("event ")[1];
                    newTask = new Event(tsk, at);
                }
            }
        }
        return newTask;
    }
    public static void mainLogic(Scanner sc, ArrayList<Task> lst) {

        while (true) {
            String input = sc.nextLine();
            String firstWord = input.split(" ")[0];
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                String listIntro = "Here are the tasks in your list:";
                System.out.println(listIntro);
                for (int i = 0; i < lst.size(); i ++) {
                    Task currentTask = lst.get(i);
                    String num = Integer.toString(i + 1);
                    System.out.println(num + "." + currentTask);
                }
            } else if (firstWord.matches("done|delete")) {
                String[] splitted = input.split("\\s+");
                int taskIndex = Integer.parseInt(splitted[1]) - 1;
                Task selectedTask = lst.get(taskIndex);
                if (firstWord.equals("done")) {
                    selectedTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n  " + selectedTask);
                } else {
                    lst.remove(taskIndex);
                    System.out.println("Noted. I've removed this task:\n  " + selectedTask);
                }
                System.out.println("Now you have " + lst.size() + " tasks in the list.");
            } else if (firstWord.matches("todo|deadline|event")) {
                Task newTask;
                try {
                    newTask = createTask(firstWord, input);
                    lst.add(newTask);
                    System.out.println("Got it. I've added this task:\n  " + newTask);
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! " + e.getMessage());
                } finally {
                    mainLogic(sc, lst);
                }
            } else {
                try {
                    invalidInput();
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! " + e.getMessage());
                } finally {
                    mainLogic(sc, lst);
                }
            }
        }

        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }
    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetings);

        ArrayList<Task> lst = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        mainLogic(sc, lst);
    }
}
