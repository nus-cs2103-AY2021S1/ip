import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String HORIZONTAL_LINE = "____________________________________________________________" + "\n";
    private static ArrayList<Task> List = new ArrayList<>();

    private static void greet() {
        String greeting = "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n";
        System.out.println(HORIZONTAL_LINE + greeting + HORIZONTAL_LINE);
    }

    private static void addTask(Task task) {
        List.add(task);
        System.out.println(HORIZONTAL_LINE
                + "Got it. I've added this task: " + "\n"
                + task.toString() + "\n"
                + String.format("Now you have %s tasks in the list.", List.size()) + "\n"
                + HORIZONTAL_LINE);
    }
    
    private static void deleteTask(int i) {
        Task task = List.get(i - 1);
        List.remove(i - 1);
        System.out.println(HORIZONTAL_LINE
                + "Noted. I've removed this task: " + "\n"
                + task.toString() + "\n"
                + String.format("Now you have %s tasks in the list.", List.size()) + "\n"
                + HORIZONTAL_LINE);
    }

    private static void echo() {
        greet();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) { //exit condition
                    String goodbye = "Bye. Hope to see you again soon!";
                    System.out.println(HORIZONTAL_LINE + goodbye + "\n" + HORIZONTAL_LINE);
                    break;
                } else if (input.equals("list")) {
                    System.out.println(HORIZONTAL_LINE + "Here are the tasks in your list:");
                    int i = 1;
                    for (Task task : List) {
                        //print out task with numbering
                        System.out.println(String.format("%s.", i) + task.toString());
                        i++;
                    }
                    System.out.println(HORIZONTAL_LINE);
                } else if (input.startsWith("done")) {
                    String[] number = input.split("done ");
                    markDone(Integer.parseInt(number[1]));
                } else if (input.startsWith("deadline")) {
                    //whatever is after deadline
                    String[] deadlineInput = input.split("deadline ");
                    if(deadlineInput.length < 2) {
                        throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.DEADLINE);
                    }
                    if((deadlineInput[1].split(" /by ")).length < 2) {
                        throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.DEADLINE);
                    }
                    String deadlineName = (deadlineInput[1].split(" /by "))[0];
                    String deadlineTime = (deadlineInput[1].split(" /by "))[1];
                    Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
                    addTask(newDeadline);
                } else if (input.startsWith("todo")) {
                    String[] todoInput = input.split("todo ");
                    if(todoInput.length < 2) {
                        throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.TODO);
                    }
                    String todoName = todoInput[1];
                    Todo newTodo = new Todo(todoName);
                    addTask(newTodo);
                } else if (input.startsWith("event")) {
                    if(input.split("event ").length < 2) {
                        throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.EVENT);
                    }
                    String eventInput = (input.split("event "))[1];
                    if(eventInput.split(" /at ").length < 2) {
                        throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.EVENT);
                    }
                    String eventName = (eventInput.split(" /at "))[0];
                    String eventTime = (eventInput.split(" /at "))[1];
                    Event newEvent = new Event(eventName, eventTime);
                    addTask(newEvent);
                } else if (input.startsWith("delete")) {
                    String deleteNumber = (input.split("delete "))[1];
                    deleteTask(Integer.parseInt(deleteNumber));
                } else {
                    throw new DukeUnknownArgumentException("");
                }
            } catch (Exception e) {
                System.out.println(HORIZONTAL_LINE + e + "\n" + HORIZONTAL_LINE);
            }
        }
        sc.close();
    }

    private static void markDone(int i) {
        Task task = List.get(i - 1);
        task.setDone();
        System.out.println(HORIZONTAL_LINE + "Nice! I've marked this task as done:" +  "\n" +
                task.toString() + "\n" + HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        echo();
    }
}