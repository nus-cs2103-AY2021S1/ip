import java.util.Scanner;

public class Duke {

    private static void processInput(String input, TaskList list) {
        System.out.println("");
        try {
            if (input.equals("list")) {
                System.out.println("Here are your tasks:");
                System.out.println(list);
            } else if (input.startsWith("done")) {
                list.markTaskDone(input);
            } else if (input.startsWith("todo")) {
                ToDo toDoTask = new ToDo(input);
                list.addTask(toDoTask);
            } else if (input.startsWith("deadline")) {
                Deadline deadlineTask = new Deadline(input);
                list.addTask(deadlineTask);
            } else if (input.startsWith("event")) {
                Event eventTask = new Event(input);
                list.addTask(eventTask);
            } else if (input.startsWith("delete")) {
                list.deleteTask(input);
            }
            else {
                //unrecognised command
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means.\n");
            }
        } catch (DukeException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TaskList list = new TaskList();
        String input;
        System.out.println("Hi there! I'm Peanut.\nHow can I be of assistance?\n");
        input = sc.nextLine();
        while (!input.equals("bye")) {
           processInput(input, list);
            input = sc.nextLine();
        }
        System.out.println("\nBye! Sad to see you go :(");

    }

}



