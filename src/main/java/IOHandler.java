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







