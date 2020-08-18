import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private List<Task> tasks = new ArrayList<>();

    void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    void addTodoTask(String task){
        TodoTask t = new TodoTask(task);
        tasks.add(t);
        System.out.println("Got it. I've added this task:\n " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    void addDeadlineTask(String task, String deadline) {
        DeadlineTask t = new DeadlineTask(task, deadline);
        tasks.add(t);
        System.out.println("Got it. I've added this task:\n " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    void addEventTask(String task, String timing) {
        EventTask t = new EventTask(task, timing);
        tasks.add(t);
        System.out.println("Got it. I've added this task:\n " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i ++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }

    void completeTask(int index) {
        Task completedTask = tasks.get(index -1 );
        completedTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n " + completedTask);
    }

    void deleteTask (int index) {
        Task deletedTask = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println("Noted. I've removed this task:\n" + deletedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void initializeChatbot() throws DukeException{
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            Command command = Command.valueOf(sc.next().toUpperCase());
            switch(command) {
                case BYE: {
                    exit();
                    break;
                }
                case LIST: {
                    listTasks();
                    break;
                }
                case TODO: {
                    String task = sc.nextLine().trim();
                    if (task.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    addTodoTask(task);
                    break;
                }
                case DEADLINE: {
                    String[] task = sc.nextLine().trim().split(" /by ");
                    if (task[0].isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline task cannot be empty.");
                    }
                    if (task[1].isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The deadline of a deadline task cannot be empty.");
                    }
                    addDeadlineTask(task[0], task[1]);
                    break;
                }
                case EVENT: {
                    String[] task = sc.nextLine().trim().split(" /at ");
                    if (task[0].isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of an event task cannot be empty.");
                    }
                    if (task[1].isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The timing of an event task cannot be empty.");
                    }
                    addEventTask(task[0], task[1]);
                    break;
                }
                case DONE: {
                    int index = sc.nextInt();
                    if (index > tasks.size() || index < 1) {
                        throw new DukeException("☹ OOPS!!! There is no such task.");
                    }
                    completeTask(index);
                    break;
                }
                case DELETE: {
                    int index = sc.nextInt();
                    if (index > tasks.size() || index < 1) {
                        throw new DukeException("☹ OOPS!!! There is no such task.");
                    }
                    deleteTask(index);
                    break;
                }
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        try {
            duke.initializeChatbot();
        } catch (DukeException ex) {
            System.out.println(ex);
        } catch (IllegalArgumentException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
