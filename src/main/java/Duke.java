import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks;

    private enum Commands {
        bye, list, delete, done, todo, deadline, event, invalid
    }

    private enum TaskTypes {
        todo, deadline, event
    }

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

    private static void deleteTask(int idx) {
        Task task = tasks.get(idx);
        tasks.remove(idx);
        System.out.println(">> I've eradicated the task:\n>> " + task + "\n>> You now have " + tasks.size() + " tasks to do!");
    }

    private static void descriptionError(TaskTypes type) {
        System.out.println(">> Oh no!!! A " + type + " must have a description!");
    }

    private static void conditionError(TaskTypes type) {
        System.out.println(">> Oh no!!! A " + type + " must have an associated date!");
    }

    private static void deleteError() {
        System.out.println(">> Oh no!!! That task does not exist!");
    }

    private static void converse(Scanner sc) {
        boolean running = true;
        while(running) {
            String input = sc.nextLine();
            String[] chunks = input.split(" ", 2);
            String action = chunks[0];
            Commands command = Commands.invalid;
            try {
                command = Commands.valueOf(action);
            } catch (IllegalArgumentException e) {
                System.out.println(">> Oh no!!! I don't understand this input :(" );
            }
            switch(command) {
                case bye:
                    System.out.println(">> Bye! Hope I helped!");
                    running = false;
                    break;
                case list:
                    int i = 1;
                    for (Task task : tasks) {
                        System.out.println(">> " + i++ + ". " + task);
                    }
                    break;
                case delete:
                    int deleteIndex = Integer.parseInt(chunks[1]) - 1;
                    if (deleteIndex >= tasks.size()) {
                        deleteError();
                        break;
                    }
                    deleteTask(deleteIndex);
                    break;
                case done:
                    int index = Integer.parseInt(chunks[1]) - 1;
                    tasks.get(index).complete();
                    System.out.println(">> Yay! The following task is marked as done:\n>> " + tasks.get(index));
                    break;
                case todo:
                    if (chunks.length < 2) {
                        descriptionError(TaskTypes.todo);
                        break;
                    }
                    Task todo = new Todo(chunks[1]);
                    addTask(todo);
                    break;
                case deadline:
                    if (chunks.length < 2) {
                        descriptionError(TaskTypes.deadline);
                        break;
                    }
                    String[] deadlineInfo = chunks[1].split(" /by ", 2); // [name, deadline]
                    if (deadlineInfo.length < 2) {
                        conditionError(TaskTypes.deadline);
                        break;
                    }
                    try {
                        Task deadline = new Deadline(deadlineInfo[0], LocalDate.parse(deadlineInfo[1]));
                        addTask(deadline);
                    } catch (DateTimeParseException e) {
                        System.out.println(">> Please format your date in YYYY-MM-DD format!");
                    }
                    break;
                case event:
                    if (chunks.length < 2) {
                        descriptionError(TaskTypes.event);
                        break;
                    }
                    String[] eventInfo = chunks[1].split(" /at ", 2); // [name, date]
                    if (eventInfo.length < 2) {
                        conditionError(TaskTypes.event);
                        break;
                    }
                    try {
                        Task event = new Event(eventInfo[0], LocalDate.parse(eventInfo[1]));
                        addTask(event);
                    } catch (DateTimeParseException e) {
                        System.out.println(">> Please format your date in YYYY-MM-DD format!");
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
