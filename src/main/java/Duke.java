import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        sayHello();

        List<Task> taskList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                displayTaskList(taskList);
            } else if (command.startsWith("done")) {
                String[] commandArray = command.split(" ", 2);
                int taskNumber = Integer.parseInt(commandArray[1]);
                Task doneTask = taskList.get(taskNumber - 1);
                doneTask.markDone();
                displayMarkDoneMessage(doneTask);
            } else {
                echoAddCommand(command);
                Task task = new Task(command);
                taskList.add(task);
            }
            command = scanner.nextLine();
        }
        sayBye();
    }

    private static void displayMarkDoneMessage(Task task) {
        System.out.println(wrapDivider(
                        "     Nice! I've marked this task as done: \n" +
                        "       "+ task.toString() + "\n"
        ));
    }

    private static void echoAddCommand(String command) {
        System.out.println(wrapDivider("     added: " + command + "\n"));
    }

    private static void sayHello() {
        System.out.println(wrapDivider(
                "      ____        _        \n" +
                "     |  _ \\ _   _| | _____ \n" +
                "     | | | | | | | |/ / _ \\\n" +
                "     | |_| | |_| |   <  __/\n" +
                "     |____/ \\__,_|_|\\_\\___|\n" +
                "     Hello! I'm Thuya\n" +
                "     What may I do for you, sir/madam?\n")
        );
    }

    private static void sayBye() {
        System.out.println(wrapDivider("     Bye. Hope to see you again soon!\n"));
    }

    private static void displayTaskList(List<Task> textList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < textList.size(); i++) {
            stringBuilder.append("     " + (i+1) + "."+ textList.get(i) +"\n");
        }
        System.out.println(wrapDivider(stringBuilder.toString()));
    }

    private static String wrapDivider(String text) {
        return  "    ____________________________________________________________\n" +
                text +
                "    ____________________________________________________________\n" ;
    }
}
