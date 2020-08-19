import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____   ____  \n"
                + "|  _ \\ |  _ \\ \n"
                + "| | | || | | | \n"
                + "| |_| || |_| | \n"
                + "|____/ |____/ \n";
        System.out.println("Hi! I'm\n" + logo + "How can I help you? :)\n"
                + "_________________________________________");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        String bye = "bye";
        String list = "list";

        Task[] taskList = new Task[100];
        int taskSize = 0;

        if (input.equals(bye)) {
            // exit
            System.out.println("You're leaving? Bye :( Come back soon!"
                    + "\n_________________________________________");
        }
        else {
            // does not exit
            while (!input.equals(bye)) {
                if (input.equals(list)) {
                    System.out.println("Here is your current list of task(s)!");
                    int curr = 0;
                    while (curr < taskSize) {
                        System.out.println((curr + 1) + ". " + taskList[curr]);
                        curr += 1;
                    }
                }
                // input tasks
                else if (input.startsWith("todo")) {
                    // to-do activity
                    input = input.substring(5);
                    taskList[taskSize] = new Todo(input);
                    System.out.println("Ok, To-do added: \n  " + taskList[taskSize]);
                    taskSize += 1;
                    System.out.println("You now have " + taskSize + " task(s) in your list!");
                }
                else if (input.startsWith("deadline")) {
                    // deadline
                    input = input.substring(9);
                    String[] temp = input.split(" /by "); // create array of [task desc, task date]

                    if (temp.length == 2) {
                        // valid
                        taskList[taskSize] = new Deadline(temp[0], temp[1]);
                        System.out.println("Ok, Deadline added: \n  " + taskList[taskSize]);
                        taskSize += 1;
                        System.out.println("You now have " + taskSize + " task(s) in your list!");
                    } else {
                        // no valid date
                        System.out.println("Due date not detected, try again!");
                    }
                }
                else if (input.startsWith("event")) {
                    // event
                    input = input.substring(6);
                    String[] temp = input.split(" /at "); // create array of [task desc, task date]

                    if (temp.length == 2) {
                        // valid
                        taskList[taskSize] = new Event(temp[0], temp[1]);
                        System.out.println("Ok, Event added: \n  " + taskList[taskSize]);
                        taskSize += 1;
                        System.out.println("You now have " + taskSize + " task(s) in your list!");
                    } else {
                        // no valid date
                        System.out.println("Event date not detected, try again!");
                    }
                }
                // check for done activity
                else if (input.startsWith("done")) {
                    String taskStr = input.substring(5);
                    int taskNum = 0;

                    try {
                        taskNum = Integer.parseInt(taskStr);
                    }
                    catch (NumberFormatException ignored) {
                    }

                    if (taskNum > 0 && taskNum <= taskSize) {
                        taskList[taskNum - 1].markAsDone();
                        System.out.println("Wow!! Good job!!\n" + taskList[taskNum - 1]);
                    }
                    else {
                        System.out.println("hmm.. I don't think thats a valid task, try again?");
                    }
                }
                else {
                    // not valid task
                    System.out.println("Sorry what?");
                }
                System.out.println("_________________________________________");
                input = sc.nextLine();
            }

            System.out.println("You're leaving? Bye :( Come back soon!"
                    + "\n_________________________________________");
        }
    }
}
