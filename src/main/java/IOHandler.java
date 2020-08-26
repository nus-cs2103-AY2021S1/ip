import java.util.Scanner;

public class IOHandler {

    Scanner sc = new Scanner(System.in);
    String text = sc.nextLine();

    public boolean hasText(String text) {
        return text != null;
    }

    TaskManager taskManager = new TaskManager();

    public void handleIO() {

        while (!text.equals("bye")) {

            String error = DukeExceptionHandler.handleException(text);
            //System.out.println(text);
            if (error != null) {
                System.out.print(text);
                System.out.println(error);

            } else {
                if (text.equals("list")) {
                    System.out.print(taskManager);

                } else if (text.contains("done")) {
                    String[] textArray = text.split(" ", 2);
                    int taskNum = Integer.parseInt(textArray[1]);
                    taskManager.setTaskDone(taskNum);

                    System.out.println("Nice! I've marked this task as done:\n"
                            + taskManager.getTask(taskNum - 1));

                } else if (text.contains("delete")) {
                    String[] textArray = text.split(" ", 2);
                    int taskNum = Integer.parseInt(textArray[1]);
                    Task deletedTask = taskManager.getTask(taskNum - 1);
                    taskManager.removeTask(taskNum);

                    System.out.println("Noted. I've removed this task:\n"
                            + deletedTask + "\nNow you have " + taskManager.getNumTasks()
                            + " tasks in the list");

                } else if (text.length() > 0) {

                    if (text.contains("todo")) {
                        String[] textArray = text.split(" ", 2);
                        Todo todo = new Todo(textArray[1]);
                        taskManager.addTask(todo);
                        System.out.println("Got it. I've added this task:\n"
                                + todo + "\nNow you have " + taskManager.getNumTasks()
                                + " tasks in the list");

                    } else if (text.contains("deadline")) {
                        String[] textArray = text.split(" ", 2);
                        Deadline deadline = new Deadline(textArray[1]);
                        taskManager.addTask(deadline);
                        System.out.println("Got it. I've added this task:\n"
                                + deadline + "\nNow you have " + taskManager.getNumTasks()
                                + " tasks in the list");

                    } else if (text.contains("event")) {
                        String[] textArray = text.split(" ", 2);
                        Event event = new Event(textArray[1]);
                        taskManager.addTask(event);
                        System.out.println("Got it. I've added this task:\n"
                                + event + "\nNow you have " + taskManager.getNumTasks()
                                + " tasks in the list");
                    }
                }
            }

            text = sc.nextLine();
        }
        sc.close();
    }
}







