import java.util.*;

public class Duke {
    private static ArrayList<Task> userList = new ArrayList<>();

    private static Task processEventOrDeadline(String input) {
        String[] temp = input.split("/");
        String[] commandTemp = temp[0].split(" ");
        String[] timeTemp = temp[1].split(" ");

        String command = commandTemp[0];
        String description = "";
        for (int i = 1; i < commandTemp.length; i++) {
            description += commandTemp[i] + " ";
        }

        String timeCommand = timeTemp[0];
        String timeDescription = "";
        for (int i = 1; i < timeTemp.length; i++) {
            if (i == timeTemp.length - 1) {
                timeDescription += timeTemp[i];
            } else {
                timeDescription += timeTemp[i] + " ";
            }
        }

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

    private static Task processToDo(String input) {
        String[] temp = input.split(" ");
        String command = temp[0];
        String description = "";

        for(int i = 1; i < temp.length; i++) {
            description += temp[i] + " ";
        }
        Task task = new ToDo(description);
        userList.add(task);
        return task;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("   ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can i do for you?");
        System.out.println("   ____________________________________________________________");

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                // when user inputs bye
                System.out.println("   ____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("   ____________________________________________________________");
                break;

            } else if (input.equals("list")) {
                // when user wants to print list of task
                System.out.println("   ____________________________________________________________");
                System.out.println("    Here are the tasks in your list:");
                int index = 1;
                for (Task task : userList) {
                    System.out.println("    " + index + ". " + task.toString());
                    index++;
                }
                System.out.println("   ____________________________________________________________");

            } else if (input.startsWith("done")) {
                // when user completes task
                String[] temp = input.split(" ");
                int listNumber = Integer.parseInt(temp[1]);
                userList.get(listNumber - 1).completedTask();
                System.out.println("   ____________________________________________________________");
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println(userList.get(listNumber - 1).toString());
                System.out.println("   ____________________________________________________________");
                
            } else {
                // user trying to input to List
                System.out.println("   ____________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                if (input.startsWith("deadline") || input.startsWith("event")) {
                    System.out.println("      " + processEventOrDeadline(input));
                } else {
                    // input starts with todo
                    System.out.println("      " + processToDo(input));
                }
                System.out.println("    Now you have " + userList.size() + " tasks in the list.");
                System.out.println("   ____________________________________________________________");
            }
        }
    }

}
