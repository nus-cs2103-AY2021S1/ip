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

            try {
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
                    // use indexOf() method to find substring
                    Task newTask = new Task("");    // this is to make the compiler happy

                    if (newInput.indexOf("event ") == 0) {
                        int indexOfAtKeyword = newInput.indexOf(" /at ");
                        String eventDesc = newInput.substring(6, indexOfAtKeyword);
                        String eventTime = newInput.substring(indexOfAtKeyword + 5);

                        newTask = new Event(eventDesc, eventTime);
                        taskList.addTask(newTask);

                    } else if (newInput.indexOf("deadline ") == 0) {
                        int indexOfByKeyword = newInput.indexOf(" /by ");
                        String deadlineDesc = newInput.substring(9, indexOfByKeyword);
                        String deadlineTime = newInput.substring(indexOfByKeyword + 5);

                        newTask = new Deadline(deadlineDesc, deadlineTime);
                        taskList.addTask(newTask);
                    } else if (newInput.indexOf("todo") == 0){
                        if (newInput.length() <= 4 || newInput.substring(5).trim().length() == 0) {
                            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            newTask = new Todo(newInput.substring(5));
                            taskList.addTask(newTask);
                        }
                    } else {
                        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newTask);
                    System.out.println("Now you have " + taskList.numTasks() + " tasks in the list.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }

        // quit
        inputScanner.close();
        System.out.println("See you space cowboy!");
    }
}
