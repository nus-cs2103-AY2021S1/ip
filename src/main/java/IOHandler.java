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

            if (text.equals("list")) {
                System.out.print(taskManager);

            } else if (text.contains("done")) {
                String[] textArray = text.split(" ", 2);
                int taskNum = Integer.parseInt(textArray[1]);
                taskManager.setTaskDone(taskNum);

                System.out.println("Nice! I've marked this task as done:\n"
                        + taskManager.getTask(taskNum - 1));

            } else if (text.length() > 0) {

                Task task = new Task(text);
                taskManager.addTask(task);
                System.out.println("added: " + text);
            }

            text = sc.nextLine();
        }
        sc.close();
    }
}







