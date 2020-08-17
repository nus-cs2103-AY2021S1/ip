import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> todoList = new ArrayList<>();

    public static void main(String[] args) throws DukeException {
        sayHi();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String input = sc.nextLine();
            processInput(input);
        }
    }

    private static void sayHi() {
        String logo = ",--.                           ,--. \n" +
                "|  |-.  ,---. ,--.--. ,---.  ,-|  | \n" +
                "| .-. '| .-. ||  .--'| .-. :' .-. | \n" +
                "| `-' |' '-' '|  |   \\   --.\\ `-' | \n" +
                " `---'  `---' `--'    `----' `---'  \n\n";
        StringBuilder message = new StringBuilder("Hi I'm\n").append(logo).append("Please give me something to do.");
        botOutput(message);
    }

    private static void processInput(String input) {
        String[] inputArr = input.split(" ", 2);
        Operation op;

        try {
            op = Operation.valueOf(inputArr[0].toUpperCase()); //type of operation
        } catch (IllegalArgumentException e) {
            op = Operation.INVALID;
        }

        try {
            switch (op) {
                case BYE:
                    handleBye();
                    break;
                case LIST:
                    handleList();
                    break;
                case DONE: {
                    int taskId = Integer.parseInt(inputArr[1]);
                    handleDone(taskId);
                    break;
                }
                case TODO:
                case DEADLINE:
                case EVENT: {
                    if (inputArr.length < 2) {
                        throw new DukeException("You have to tell me what's your task!");
                    }
                    String taskBody = inputArr[1];
                    handleAddTask(op, taskBody);
                    break;
                }
                case DELETE:
                    if (inputArr.length < 2) {
                        throw new DukeException("You have to tell me what to delete!");
                    }
                    int taskId = Integer.parseInt(inputArr[1]);
                    if (taskId > todoList.size()) {
                        throw new DukeException("There's no task with this ID!");
                    }
                    handleDelete(taskId);
                    break;
                case INVALID:
                    throw new DukeException("Sorry I don't know what this means!");
            }
        } catch (DukeException e) {
            botOutput(e.getMessage());
        }
    }

    private static void handleBye() {
        botOutput("Come back soon!! I'm always bored...");
        System.exit(0);
    }

    private static void handleList() {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < todoList.size(); i++) {
            message.append(i + 1) .append(". ").append(todoList.get(i));
            if (i != todoList.size() - 1) {
                message.append("\n");
            }
        }
        botOutput(message);
    }

    private static void handleAddTask(Operation op, String taskBody) throws DukeException {
        Task task = null;

        switch(op) {
            case TODO: {
                task = new ToDo(taskBody);
                break;
            }
            case DEADLINE: {
                String[] taskBodyArr = taskBody.split(" /by ");
                if (taskBodyArr.length < 2) {
                    throw new DukeException("You need to tell me when this task is due!");
                }
                task = new Deadline(taskBodyArr[0], taskBodyArr[1]);
                break;
            }
            case EVENT: {
                String[] taskBodyArr = taskBody.split(" /at ");
                if (taskBodyArr.length < 2) {
                    throw new DukeException("You need to tell me when this event is happening!");
                }
                task = new Event(taskBodyArr[0], taskBodyArr[1]);
                break;
            }
        }
        todoList.add(task);
        StringBuilder message = new StringBuilder("Alright! I've added this task:\n");
        message.append(task);
        message.append("\nNow you have ").append(todoList.size()).append(" task(s) in your list.");
        botOutput(message);
    }

    private static void handleDone(int taskId) {
        Task task = todoList.get(taskId - 1);
        task.markDone();
        StringBuilder message = new StringBuilder("Nice! I've marked this task as done:\n").append(task);
        botOutput(message);
    }

    private static void handleDelete(int taskId) {
        Task task = todoList.get(taskId - 1);
        todoList.remove(task);
        StringBuilder message = new StringBuilder("Noted. I've removed this task:\n");
        message.append(task);
        message.append("\nNow you have ").append(todoList.size()).append(" task(s) in the list.");
        botOutput(message);
    }

    private static void botOutput(String message) {
        String divider = "____________________________________________________________";
        System.out.println(divider);
        System.out.println(message);
        System.out.println(divider);
    }

    private static void botOutput(StringBuilder message) {
        botOutput(message.toString());
    }

}