import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String border = "----------------------------------------------------------------------------\n";
        String greeting = "Sorry :( Duke is getting some upgrades at the moment.\n"
                + "This is Tron, temporarily standing in for Duke, how may I assist you ?\n";
        String farewell = "Adios, pleasure to serve you!\n";
        String logo =
                  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        System.out.println(border + greeting + border);

        Scanner sc = new Scanner(System.in); //scans for input
        TaskList taskList = new TaskList();
        String command = sc.next();
        String parameters = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println(border + taskList.toString() + border);
            } else if (command.equals("done")) {
                int index = Integer.parseInt(parameters.strip()) - 1;
                taskList.completeTask(index);
                System.out.println(border
                        + "Making great progress master.\n"
                        + taskList.getTask(index) + "\n"
                        + border
                );
            } else {
                Task newTask = taskList.addTask(command, parameters);
                System.out.println(border
                    + "Yes master. I've added the task to the list: \n"
                    + newTask.toString() + "\n"
                    + "You now have " + taskList.getNoTask() + " task in the list master.\n"
                    + border);
            }
            command = sc.next();
            parameters = sc.nextLine();
        }

        System.out.println(border + farewell + border);


    }


}
