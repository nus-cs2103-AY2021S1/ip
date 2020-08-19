import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<>();

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greet = "\n____________________________________________________________"
                + "\n Hello! I'm Duke"
                + "\n What can I do for you?"
                + "\n____________________________________________________________\n";
        System.out.println(logo + greet);
    }

    public static void commands() {
        String commands = "   ____________________________________________________________"
                + "\n    Here are all your commands:"
                + "\n     list - show all tasks"
                + "\n     todo <your task> - add task"
                + "\n     deadline <your task> /by <your deadline> - add task with deadline"
                + "\n     event <your event> /at <event's timing> - add event"
                + "\n     done <index of task> - mark task as done"
                + "\n   ____________________________________________________________\n";
        System.out.println(commands);
    }

    public static void bye() {
        String bye = "   ____________________________________________________________"
                + "\n    Bye! Hope to see you again soon."
                + "\n   ____________________________________________________________";
        System.out.println(bye);
    }

    public static void addTask(Task task) {
        tasks.add(task);
        String str = "   ____________________________________________________________"
                + "\n    Got it. I've added this task:"
                + "\n      " + task
                + "\n    Now you have " + tasks.size() + " task(s) in the list."
                + "\n   ____________________________________________________________\n";
        System.out.println(str);
    }

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);

        greeting();
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.equals("help")) {
                commands();
            } else if (input.equals("list")) {
                Task.getListOfTasks(tasks);
            } else if (input.startsWith("todo")) {
                String task;
                try {
                    task = input.split("todo")[1];
                    Task newTask = new ToDos(task);
                    addTask(newTask);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    try {
                        throw new DukeException("", DukeExceptionType.MISSING_DESCRIPTION, DukeCommandType.TODO);
                    } catch (DukeException e) {
                        System.err.println(e);
                    }
                }
            } else if (input.startsWith("deadline")) {
                try {
                    if (input.split("deadline ").length < 2) {
                        throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DukeCommandType.DEADLINE);
                    } else if (!input.contains("/by ")) {
                        if (input.equals("deadline /by")) {
                            throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DukeCommandType.DEADLINE);
                        } else {
                            throw new DukeException("", DukeExceptionType.MISSING_TIMING, DukeCommandType.DEADLINE);
                        }
                    } else if (input.split("/by ").length < 2) {
                        throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DukeCommandType.DEADLINE);
                    } else {
                        try {
                            String task = input.split("deadline ")[1].split("/by")[0];
                            String due = input.split("deadline ")[1].split("/by")[1];
                            if (task.equals("") && due.equals("")) {
                                throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DukeCommandType.DEADLINE);
                            } else if (task.equals("")) {
                                throw new DukeException("", DukeExceptionType.MISSING_DESCRIPTION, DukeCommandType.DEADLINE);
                            } else if (due.equals("")) {
                                throw new DukeException("", DukeExceptionType.MISSING_TIMING, DukeCommandType.DEADLINE);
                            } else {
                                Task newTask = new Deadlines(task, due);
                                addTask(newTask);
                            }
                        } catch (DukeException e){
                            System.err.println(e);
                        }
                    }
                } catch (DukeException e) {
                    System.err.println(e);
                }
            } else if (input.startsWith("event")) {
                try {
                    if (input.split("event ").length < 2) {
                        throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DukeCommandType.EVENT);
                    } else if (!input.contains("/at ")) {
                        if (input.equals("event /at")) {
                            throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DukeCommandType.EVENT);
                        } else {
                            throw new DukeException("", DukeExceptionType.MISSING_TIMING, DukeCommandType.EVENT);
                        }
                    } else if (input.split("/at ").length < 2) {
                        throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DukeCommandType.EVENT);
                    } else {
                        try {
                            String task = input.split("event ")[1].split("/at")[0];
                            String due = input.split("event ")[1].split("/at")[1];
                            if (task.equals("") && due.equals("")) {
                                throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DukeCommandType.EVENT);
                            } else if (task.equals("")) {
                                throw new DukeException("", DukeExceptionType.MISSING_DESCRIPTION, DukeCommandType.DEADLINE);
                            } else if (due.equals("")) {
                                throw new DukeException("", DukeExceptionType.MISSING_TIMING, DukeCommandType.DEADLINE);
                            } else {
                                Task newTask = new Events(task, due);
                                addTask(newTask);
                            }
                        } catch (DukeException e){
                            System.err.println(e);
                        }
                    }
                } catch (DukeException e) {
                    System.err.println(e);
                }
            } else if (input.startsWith("done")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    Task.done(tasks, index);
                } catch (IndexOutOfBoundsException exception) {
                    try {
                        throw new DukeException("", DukeExceptionType.INVALID_INDEX, DukeCommandType.DONE);
                    } catch (DukeException e) {
                        System.err.println(e);
                    }
                }
                input = sc.nextLine();
                continue;
            } else if (input.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    Task.delete(tasks, index);
                } catch (IndexOutOfBoundsException exception) {
                    try {
                        throw new DukeException("", DukeExceptionType.INVALID_INDEX, DukeCommandType.DELETE);
                    } catch (DukeException e) {
                        System.err.println(e);
                    }
                }
                input = sc.nextLine();
                continue;
            } else {
                try {
                    throw new DukeException("", DukeExceptionType.UNKNOWN);
                } catch (DukeException e) {
                    System.err.println(e);
                }
                input = sc.nextLine();
                continue;
            }
            input = sc.nextLine();
        }
    }
}
