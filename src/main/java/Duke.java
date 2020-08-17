import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Initialise booleans and scanners
        boolean quitProgram = false;
        Scanner inputScanner = new Scanner(System.in);

        // Initialise Task List
        TaskList taskList = new TaskList();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, my name is\n" + logo);
        System.out.println("What can I do for you?");

        while (!quitProgram) {
            // blocks program until input is received
            String newInput = inputScanner.nextLine();
            // then prints the input
            if (newInput.equals("bye")) {
                quitProgram = true;
            } else if (newInput.equals("list")) {
                taskList.displayTasks();
            } else if (newInput.length() > 5 && newInput.substring(0, 5).equals("done ")) {
                int taskIndex = Integer.parseInt(newInput.substring(5));    // this is not corrected for 0 index
                Task completedTask = taskList.getTask(taskIndex);
                completedTask.markAsDone();

                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(completedTask);
            } else {
                taskList.addTask(newInput);
                System.out.println("added: " + newInput);
            }
        }

        // quit
        inputScanner.close();
        System.out.println("See you space cowboy!");
    }
}
