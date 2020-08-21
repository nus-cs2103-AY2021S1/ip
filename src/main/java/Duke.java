import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

public class Duke {
    static String bot = "Dave says:\n";
    //static String user = "Me: \n";
    //static String addedText = "added: ";
    static String line = "_______________________________________________________________";

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        //Initial greetings
        System.out.println(line);
        System.out.println(bot + "Greetings from me, Dave!\n" + "How can I help you? ^_^");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        //Looping and echoing user inputs
        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            int len = userInput.length();

            if (userInput.equals("bye")) {
                System.out.println(line);
                System.out.println(bot + "Goodbye! Hope to see you again soon! ^_^");
                System.out.println(line);
                break;
            } else if (userInput.equals("list")) {
                showTaskList(tasks);
            } else if (userInput.substring(0,4).equals("done")) { //mark as done
                int pos = Integer.parseInt(userInput.substring(5, len));
//                if(pos <= tasks.size() && pos > 0) {
//                    taskIsDone(tasks, pos);
//                } else {
//                    System.out.println("You have keyed in an invalid number!");
//                }
                try { //if(pos <= tasks.size() && pos > 0)
                    taskIsDone(tasks, pos);
                } catch (DukeException ex) {
                    System.out.println(line);
                    System.out.print(bot);
                    System.out.println(ex.getMessage());
                    System.out.println(line);
                }

            } else if (userInput.startsWith("todo")) { //added
                try {
                    if (!userInput.substring(4).isBlank()) { //if got space behind, it will add also
                        ToDo todo = new ToDo(userInput.substring(5));
                        tasks.add(todo); //adds into tasks list
                        System.out.println(line);
                        System.out.print(bot);
                        System.out.println("Got it! I've added this task:");
                        //System.out.println(addedText + userInput);
                        System.out.println(todo);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(line);
                    } else {
                        throw new DukeException("The description of a todo cannot be empty!");
                    }

                } catch (DukeException ex){
                    System.out.println(line);
                    System.out.print(bot);
                    System.out.println(ex.getMessage());
                    System.out.println(line);
                }

            } else if (userInput.startsWith("deadline")) {
                try {
                    if (!userInput.substring(8).isBlank()) {
                        int indexOfSlash = userInput.indexOf('/');
                        String description = userInput.substring(9, indexOfSlash - 1);
                        String date =  userInput.substring(indexOfSlash + 4);
                        Deadline deadline = new Deadline(description, date);
                        tasks.add(deadline);
                        System.out.println(line);
                        System.out.print(bot);
                        System.out.println("Got it! I've added this task:");
                        System.out.println(deadline);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(line);
                    } else {
                        throw new DukeException("The description of a deadline cannot be empty!");
                    }
                } catch (DukeException ex){
                    System.out.println(line);
                    System.out.print(bot);
                    System.out.println(ex.getMessage());
                    System.out.println(line);
                }
            } else if (userInput.startsWith("event")) {
                try {
                    if (!userInput.substring(5).isBlank()) {
                        int indexOfSlash = userInput.indexOf('/');
                        String description = userInput.substring(6, indexOfSlash - 1);
                        String dateAndTime =  userInput.substring(indexOfSlash + 4);
                        Event event = new Event(description, dateAndTime);
                        tasks.add(event);
                        System.out.println(line);
                        System.out.print(bot);
                        System.out.println("Got it! I've added this task:");
                        System.out.println(event);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(line);
                    } else {
                        throw new DukeException("The description of an event cannot be empty!");
                    }
                } catch (DukeException ex){
                    System.out.println(line);
                    System.out.print(bot);
                    System.out.println(ex.getMessage());
                    System.out.println(line);
                }
            } else if (userInput.startsWith("delete")) {
                try {
                    if (!userInput.substring(6).isBlank()) {
                        String toDelete =  userInput.substring(7);
                        int index = Integer.parseInt(toDelete);
                        System.out.println(line);
                        System.out.print(bot);
                        System.out.println("Noted! I've deleted this task:");
                        System.out.println(tasks.get(index - 1));
                        tasks.remove(index - 1);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(line);
                    } else {
                        throw new DukeException("The description of an delete cannot be empty!");
                    }
                } catch (DukeException ex){
                    System.out.println(line);
                    System.out.print(bot);
                    System.out.println(ex.getMessage());
                    System.out.println(line);
                }
            } else { //invalid command
                try {
                    throw new DukeException("You have keyed in an invalid command!\n(Valid commands: todo, deadline, event, list, delete, bye)");
                } catch (DukeException ex) {
                    System.out.println(line);
                    System.out.print(bot);
                    System.out.println(ex.getMessage());
                    System.out.println(line);
                }
            }
        }

        scanner.close();
    }

    static void showTaskList(ArrayList<Task> tasks) {
        if(tasks.isEmpty()) {
            System.out.println("There are no tasks in your list yet! >_<");
        } else {
            System.out.println(line);
            System.out.print(bot);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                //System.out.println(i + 1 + "." + " " + "[" + tasks.get(i).getStatusIcon() + "]" + " " + tasks.get(i).getTask());
                System.out.println(i + 1 + "." + " " + tasks.get(i));
            }
            System.out.println(line);
        }
    }

    static void taskIsDone(ArrayList<Task> tasks, int pos) throws DukeException {
        if (pos <= tasks.size() && pos > 0) {
            tasks.get(pos - 1).markAsDone(); //marking task as done
            System.out.println(line);
            System.out.print(bot);
            System.out.println("Great work! I've marked this task as done:");
            //System.out.println("[" + tasks.get(pos - 1).getStatusIcon() + "]" + " " + tasks.get(pos - 1).getTask());
            System.out.println(tasks.get(pos - 1));
            System.out.println("Keep the ticks going! ^_^");
            System.out.println(line);
        } else {
            throw new DukeException("You have keyed in an invalid number!");
        }
    }
}




