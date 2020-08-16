import java.util.*;

public class Duke {
    private static ArrayList<Task> userList = new ArrayList<>();

    private static Task processEventOrDeadline(String input) throws EventException, DeadlineException {
        String[] temp = input.split("/");
        String[] commandTemp = temp[0].split(" ");
        String command = commandTemp[0];
        if (temp.length != 2) {
            if (command.equals("deadline")) {
                throw new DeadlineException("Both time description and description of a deadline must be filled!");
            } else if (command.equals("event")) {
                throw new EventException("Both time description and description of a event must be filled!");
            }
        }

        String[] timeTemp = temp[1].split(" ");
        String timeCommand = timeTemp[0];

        String description = "";
        for (int i = 1; i < commandTemp.length; i++) {
            description += commandTemp[i] + " ";
        }

        String timeDescription = "";
        for (int i = 1; i < timeTemp.length; i++) {
            if (i == timeTemp.length - 1) {
                timeDescription += timeTemp[i];
            } else {
                timeDescription += timeTemp[i] + " ";
            }
        }

        System.out.println("Got it. I've added this task:");

        if (command.equals("deadline")) {
            Task task = new Deadline(description, timeDescription);
            userList.add(task);
            return task;
        } else if (command.equals("event")) {
            Task task = new Event(description, timeDescription);
            userList.add(task);
            return task;
        }
        return null;
    }

    private static Task processToDo(String input) throws ToDoException {
        String[] temp = input.split(" ");

        String command = temp[0];
        String description = "";

        for(int i = 1; i < temp.length; i++) {
            description += temp[i] + " ";
        }

        if (description.equals("")) {
            throw new ToDoException("The description of a todo cannot be empty!");
        }

        Task task = new ToDo(description);
        userList.add(task);

        System.out.println("Got it. I've added this task:");

        return task;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                // when user inputs bye

                System.out.println("Bye. Hope to see you again soon!");
                break;

            } else if (input.equals("list")) {
                // when user wants to print list of task

                System.out.println("Here are the tasks in your list:");
                int index = 1;
                for (Task task : userList) {
                    System.out.println(index + ". " + task.toString());
                    index++;
                }

            } else if (input.startsWith("done")) {
                // when user completes task

                String[] temp = input.split(" ");
                int listNumber = Integer.parseInt(temp[1]);
                userList.get(listNumber - 1).completedTask();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(userList.get(listNumber - 1).toString());

            } else if ((input.startsWith("deadline") || input.startsWith("event")) || input.startsWith("todo")) {
                // user trying to input to List

                if (input.startsWith("deadline") || input.startsWith("event")) {
                    // input starts with deadline or event

                    try {
                        Task task = processEventOrDeadline(input);
                        System.out.println(task);
                        System.out.println("Now you have " + userList.size() + " tasks in the list.");

                    } catch (EventException | DeadlineException e) {
                        System.out.println(e.getMessage());

                    }
                } else if (input.startsWith("todo")){
                    // input starts with todo

                    try {
                        Task task = processToDo(input);
                        System.out.println(task);
                        System.out.println("Now you have " + userList.size() + " tasks in the list.");

                    } catch (ToDoException e) {
                        System.out.println(e.getMessage());

                    }
                }
            } else {
                // invalid input

                try {
                    throw new InvalidInputException("I'm sorry, but I don't know what that means! :-(");
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}