import java.util.Scanner;

public class Duke {
    public static final String HORIZONTAL_LINE = "____________________________________________________________" + "\n";

    private static void greet() {
        String greeting = "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n";
        System.out.println(HORIZONTAL_LINE + greeting + HORIZONTAL_LINE);
    }

    private static void echo() {
        greet();
        Task.loadTasks();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) { //exit condition
                    String goodbye = "Bye. Hope to see you again soon!";
                    System.out.println(HORIZONTAL_LINE + goodbye + "\n" + HORIZONTAL_LINE);
                    break;
                } else if (input.equals("list")) {
                    Task.showTasks();
                } else if (input.startsWith("done")) {
                    String[] number = input.split("done ");
                    Task.markDone(Integer.parseInt(number[1]));
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
                    Task.addTaskAndPrint(newDeadline);
                } else if (input.startsWith("todo")) {
                    String[] todoInput = input.split("todo ");
                    if(todoInput.length < 2) {
                        throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.TODO);
                    }
                    String todoName = todoInput[1];
                    Todo newTodo = new Todo(todoName);
                    Task.addTaskAndPrint(newTodo);
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
                    Task.addTaskAndPrint(newEvent);
                } else if (input.startsWith("delete")) {
                    String deleteNumber = (input.split("delete "))[1];
                    Task.deleteTask(Integer.parseInt(deleteNumber));
                } else {
                    throw new DukeUnknownArgumentException("");
                }
                Task.saveTasks();
            } catch (DukeException e) {
                System.out.println(HORIZONTAL_LINE + e + "\n" + HORIZONTAL_LINE);
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        echo();
    }
}