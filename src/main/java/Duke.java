import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks;

    public static void main(String[] args) {
        tasks = new ArrayList<>();
        greet();
        Scanner sc = new Scanner(System.in);
        converse(sc);
    }

    private static void greet() {
        System.out.println(">> Beep Boop. I am Aq-bot.\n>> How can I help?");
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println(">> Added the task:\n>> " + task + "\n>> You now have " + tasks.size() + " tasks to do!");
    }

    private static void converse(Scanner sc) {
        boolean running = true;
        while(running) {
            String input = sc.nextLine();
            String[] chunks = input.split(" ", 2);
            String action = chunks[0];
            switch(action) {
                case("bye"):
                    System.out.println(">> Bye! Hope I helped!");
                    running = false;
                    break;
                case("list"):
                    int i = 1;
                    for (Task task : tasks) {
                        System.out.println(">> " + i++ + ". " + task);
                    }
                    break;
                case("done"):
                    int index = Integer.parseInt(chunks[1]) - 1;
                    tasks.get(index).complete();
                    System.out.println(">> Yay! The following task is marked as done:\n>> " + tasks.get(index));
                    break;
                case("todo"):
                    Task todo = new Todo(chunks[1]);
                    addTask(todo);
                    break;
                case("deadline"):
                    String[] deadlineInfo = chunks[1].split(" /by ", 2); // [name, deadline]
                    Task deadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                    addTask(deadline);
                    break;
                case("event"):
                    String[] eventInfo = chunks[1].split(" /at ", 2); // [name, date]
                    Task event = new Event(eventInfo[0], eventInfo[1]);
                    addTask(event);
                    break;
                default:
                    tasks.add(new Task(input));
                    System.out.println(">> added: " + input);
                    break;
            }
        }
    }
}
