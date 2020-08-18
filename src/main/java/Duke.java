import javax.annotation.processing.SupportedSourceVersion;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Duke, more commonly known as Duck, is a Personal Assistant Chat Bot that
 * helps a person to keep track of various tasks.
 * Contains static attribute stored_task which stores task input from user.
 **/
public class Duke {

    public static List<Task> stored_task = new ArrayList<>();
    public static String line = "____________________________________________________________";

    /**
     * Lists stored task by looping through stored_task, along with their status.
     **/
    public static void listStoredTasks() {
        if (stored_task.isEmpty()) {
            System.out.println("No tasks stored...");
        } else {
            System.out.println("Quack! Here are the tasks in your list:");
            int count = 1;
            for (Task task : stored_task) {
                System.out.println(count + ". " + task);
                count++;
            }
        }
    }

    /**
     * Adds input task into stored_task.
     *
     * @param newTask Input task from user to be stored.
     **/
    public static void addTask(Task newTask) {
        stored_task.add(newTask);
        System.out.println("Quack! I have added: " + newTask);
        int numOfTasks = stored_task.size();
        if (numOfTasks == 1) {
            System.out.println("My duck senses tell me you have 1 task in the list.");
        } else {
            System.out.println("My duck senses tell me you have " + numOfTasks + " tasks in the list.");
        }
    }

    /**
     * Marks task as done.
     *
     * @param taskNumber Task number of task to be marked as done.
     **/
    public static void markTaskAsDone(int taskNumber) {
        try {
            if (taskNumber <= 0 || taskNumber > stored_task.size()) {
                throw new DukeException("Wrong task number!");
            } else {
                Task t = stored_task.get(taskNumber - 1);
                if (t.checkIfDone()) {
                    throw new DukeException("This task is already done: " + t.getDescription());
                } else {
                    t.markAsDone();
                    System.out.println("Quack! I have marked this task as done: \n" + t);
                }
            }
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints greeting message.
     * Scans for commands entered by the user, then stores input task into stored_task for 3 types of tasks:
     * ToDo, Deadline and Event.
     * Upon user command input "done " followed by the task number, task will be marked as done.
     * Upon user command input "list", stored task will be listed.
     * Upon user command input "bye", system is exited.
     **/
    public static void main(String[] args) {
        String greeting_message = line +
                "\n Quack! I am Duck" +
                "\n How can I help you today?\n" + line;
        String exit_message = line +
                "\n Waddling off now. See you soon! \n" + line;
        Scanner sc = new Scanner(System.in);

        System.out.println(greeting_message);
        while (true) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    System.out.println(exit_message);
                    break;
                } else if (input.equals("list")) {
                    System.out.println(line);
                    listStoredTasks();
                    System.out.println(line);
                } else if (input.startsWith("done")) {
                    if (input.length() > 4) {
                        if (!input.substring(4, 5).equals(" ")){
                            throw new DukeException("My duck instincts tell me your input makes no sense...");
                        } else {
                            int taskNumber = Integer.parseInt(input.substring(5));
                            System.out.println(line);
                            markTaskAsDone(taskNumber);
                            System.out.println(line);
                        }
                    } else {
                        throw new DukeException("You need to include your task number to mark done...");
                    }
                } else if (input.startsWith("todo")) {
                    if (input.length() > 4) {
                        if (!input.substring(4, 5).equals(" ")) {
                            throw new DukeException("My duck instincts tell me your input makes no sense...");
                        } else {
                            ToDo newTask = new ToDo(input.substring(5));
                            System.out.println(line);
                            addTask(newTask);
                            System.out.println(line);
                        }
                    } else {
                        throw new DukeException("Your todo description can't be empty...");
                    }
                } else if (input.startsWith("deadline")) {
                    if (input.length() > 8) {
                        if (!input.substring(8, 9).equals(" ")) {
                            throw new DukeException("My duck instincts tell me your input makes no sense...");
                        } else {
                            int indexOfBy = input.indexOf("/by");
                            if (indexOfBy == -1) {
                                throw new DukeException("Did you include /by?");
                            } else {
                                String description = input.substring(9, indexOfBy);
                                String by = input.substring(indexOfBy + 3);
                                Deadline newTask = new Deadline(description, by);
                                System.out.println(line);
                                addTask(newTask);
                                System.out.println(line);
                            }
                        }
                    } else {
                        throw new DukeException("Your deadline description can't be empty...");
                    }
                } else if (input.startsWith("event")) {
                    if (input.length() > 5) {
                        if (!input.substring(5, 6).equals(" ")) {
                            throw new DukeException("My duck instincts tell me your input makes no sense...");
                        } else {
                            int indexOfAt = input.indexOf("/at");
                            if (indexOfAt == -1) {
                                throw new DukeException("Did you include /at?");
                            } else {
                                String description = input.substring(6, indexOfAt);
                                String at = input.substring(indexOfAt + 3);
                                Event newTask = new Event(description, at);
                                System.out.println(line);
                                addTask(newTask);
                                System.out.println(line);
                            }
                        }
                    } else {
                        throw new DukeException("Your event description can't be empty...");
                    }
                } else {
                    throw new DukeException("My duck instincts tell me your input makes no sense...");
                }
            } catch (DukeException e){
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            } catch (NumberFormatException e){ //when user does not input a number after "done" command
                System.out.println(line);
                System.out.println("You need to input the task number!");
                System.out.println(line);
            }
        }
        sc.close();
    }
}
