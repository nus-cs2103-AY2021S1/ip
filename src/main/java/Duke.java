import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String thisTaskname;
        String thisTime;
        Task thisTask;
        String input;
        int number;

        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
        System.out.println("    Hello from\n" + logo);
        System.out.println("    Hey there! This is Duke here~");
        System.out.println("    How may I help you today?");
        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");

        input = sc.nextLine();
        while(!input.equals("bye")) {
            System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
            try {
                if (input.equals("list")) {
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 1; i <= list.size(); i++) {
                        thisTask = list.get(i - 1);
                        System.out.println("     " + i + "." + thisTask.toString());
                    }
                } else if (input.startsWith("done")) {
                    number = Character.getNumericValue(input.charAt(input.length() - 1));
                    System.out.println("     Nice! I've marked this task as done:");
                    thisTask = list.get(number - 1);
                    thisTask.markAsDone();
                    list.set(number - 1, thisTask);
                    System.out.println("       " + thisTask.toString());
                } else {
                    if (input.startsWith("deadline")) {
                        if (input.length() < 10) {
                            throw new EmptyTaskNameException("     OOPS! The taskname of a deadline cannot be empty.");
                        }
                        System.out.println("     Got it. I've added this task:");
                        thisTaskname = input.substring(9, input.indexOf('/') - 1);
                        thisTime = input.substring(input.indexOf('/') + 4);
                        list.add(new Deadline(thisTaskname, thisTime));
                    } else if (input.startsWith("event")) {
                        if (input.length() < 7) {
                            throw new EmptyTaskNameException("     OOPS! The taskname of a event cannot be empty.");
                        }
                        System.out.println("     Got it. I've added this task:");
                        thisTaskname = input.substring(6, input.indexOf('/') - 1);
                        thisTime = input.substring(input.indexOf('/') + 4);
                        list.add(new Event(thisTaskname, thisTime));
                    } else if (input.startsWith("todo")) {
                        if (input.length() < 6) {
                            throw new EmptyTaskNameException("     OOPS! The taskname of a todo cannot be empty.");
                        }
                        System.out.println("     Got it. I've added this task:");
                        thisTaskname = input.substring(5);
                        list.add(new Todo(thisTaskname));
                    } else {
                        throw new UnknownInstructionException("     OOPS! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("       " + list.get(list.size() - 1));
                    System.out.println("     Now you have " + list.size() + " tasks in the list.");
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
            input = sc.nextLine();
        }

        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
        System.out.println("    Bye bye~ See ya!");
        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
    }
}