
import main.java.*;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
public class Bob {
    static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetings = "Hello! I'm Bob\nWhat can I do for you?";
        String exit = "Bye! Hope to see you again.";
        System.out.println("Hello from\n" + logo);
        System.out.println(greetings);
        String command = sc.nextLine();
        while(!command.equals("bye")) {
            try {
                respond(command);
            } catch (BobNoDescriptionException e) {
                System.out.println("Please include a description for this todo!");
            } catch (BobIncompleteDeadlineDescriptionException e) {
                System.out.println("The description for this deadline is incomplete. Please remember to include a brief description alongside a due date.");
                System.out.println("Here's the format: deadline [brief description] /by [due date]");
            } catch (BobIncompleteEventDescriptionException e) {
                System.out.println("The description for this event is incomplete. Please remember to include a brief description alongside the period of this event.");
                System.out.println("Here's the format: event [brief description] /at [period]");
            }  catch(IndexOutOfBoundsException e) {
                if (list.isEmpty()) {
                    System.out.println("There aren't any tasks on the list!");
                } else {
                    System.out.println("There are no tasks on the list with the provided index. Please check the list and try again!");
                }
            } catch (NumberFormatException e) {
              System.out.println("Please provide the index of a task on the list to mark it as done or to delete it.");
              System.out.println("Here's the format: delete/done [index]");
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry, I do not understand your request. Please try again.");
            } catch (DateTimeParseException e) {
                System.out.println("Please input dates and times in the correct format. The format is: ");
                System.out.println("yyyy-MM-dd HHMM");
                System.out.println("Note: Events require a start date and time and an end date and time with the following format:");
                System.out.println("yyyy-MM-dd HHMM to yyyy-MM-dd HHMM");
            }
            command = sc.nextLine();
        }
        System.out.println(exit);
    }
    static void respond(String command) {
        if (command.equals("list")) {
            int count = 1;
            for(Task item: list) {
                System.out.println(count +"." + item.toString());
                count++;
            }
        } else if(command.contains("done")) {
            int index = Integer.parseInt(command.substring(command.length()-1));
            Task task = list.get(index-1);
            task.markAsDone();
            System.out.println("Good job! I have marked this task as done:");
            System.out.println("\t" + index + "." + task.toString());
        } else if (command.contains("todo") || command.contains("deadline") || command.contains("event")){
            Task task = null;
            if (command.contains("todo")) {
                try {
                    task = new Todo(command.substring(5));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new BobNoDescriptionException();
                }
            } else if (command.contains("deadline")) {
                try {
                    int index = command.indexOf(47);
                    if (command.substring(9,index).equals("")) {
                        throw new BobIncompleteDeadlineDescriptionException();
                    }
                    task = new Deadline(command.substring(9, index), command.substring(index + 4));
                } catch (StringIndexOutOfBoundsException e){
                    throw new BobIncompleteDeadlineDescriptionException();
                }
            } else if(command.contains("event")) {
                try {
                    int index = command.indexOf(47);
                    if (command.substring(6,index).equals("")) {
                        throw new BobIncompleteEventDescriptionException();
                    }
                    task = new Event(command.substring(6, index), command.substring(index + 4));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new BobIncompleteEventDescriptionException();
                }
            }
            list.add(task);
            System.out.println("Got it! I have added a new task to the list.");
            System.out.println("added: " + task.toString());
        } else if (command.contains("delete")) {
            int index = Integer.parseInt(command.substring(command.length()-1));
            Task removed = list.get(index-1);
            list.remove(index-1);
            System.out.println("Noted. I have removed the following task: ");
            System.out.println("\t" + removed.toString());
            System.out.println("There are now " + list.size() + " remaining tasks on the list.");
        } else {
            throw new IllegalArgumentException();
        }
    }
}
