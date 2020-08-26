import java.util.ArrayList;
import java.util.Scanner;

public class PandaBot {
    private static ArrayList<Task> tasks = new ArrayList<>();
    
    //TODO: afterwards, can abstract out the commands 
    // and change the error messages into actual exceptions
    // according to timeline [for handling errors]
    
    public static void main(String[] args) {
        String logo =
                 " ____                    _\n"
               + "|  _ \\                  | |\n"
               + "| |_| |___  _ _  __  ___| | ___  _\n"
               + "| ___/  _ \\| | |/  |/ _   |/ _ \\| |\n"
               + "| |  | |_|   |  _  | |_|  | |_|   |\n"
               + "|_|  \\____,__|_| |_|\\___,_|\\___,__|" + " bot\n\n";

        System.out.println(logo + "Hello! I'm PandaBot.\n" + "What can I do for you?\n");
        
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] cmd = input.split(" ", 2); // obtain first word and the rest of the string
            switch(cmd[0]) {
                case "bye":
                    System.out.println("Bye! Remember to finish the rest of your work! See you soon~");
                    break;
                case "list":
                    System.out.println("These are the tasks you have: ");
                    for (int i = 0; i < tasks.size(); i++) {
                        if (tasks.get(i) != null) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        } else {
                            break;
                        }
                    }
                    System.out.print("\n");
                    break;
                case "done":
                    int taskNum = -1;
                    try {
                        // verify the num of arguments first
                        if (cmd.length == 2) {
                            taskNum = Integer.parseInt(cmd[1]) - 1;
                            if (taskNum < tasks.size()) {
                                // mark the task as done
                                tasks.get(taskNum).markTaskDone();
                                System.out.println("Great! I've marked this task as done:");
                                System.out.println(tasks.get(taskNum) + "\n");
                            } else {
                                System.out.println("Task number given is not in range. Please try again.\n");
                                break;
                            }
                        } else {
                            System.out.println("Insufficient arguments given. What task have you done?\n");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Format: " + cmd[1] + "\nWhat task number are you done with?\n");
                    } 
                    break;
                case "delete":
                    int taskToDel = -1;
                    try {
                        // verify the num of arguments first
                        if (cmd.length == 2) {
                            taskToDel = Integer.parseInt(cmd[1]) - 1;
                            if (taskToDel < tasks.size()) {
                                // store the task to delete
                                String toDel = tasks.get(taskToDel).toString();
                                tasks.remove(taskToDel);
                                System.out.println("Will do! I've removed this task:");
                                System.out.println(toDel);
                                System.out.println("Now you have " + tasks.size() + " tasks in this list.\n");
                            } else {
                                System.out.println("Task number given is not in range. Please try again.\n");
                                break;
                            }
                        } else {
                            System.out.println("Insufficient arguments given. What task do you want to delete?\n");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Format: " + cmd[1] + "\nWhat task number are you deleting?\n");
                    }
                    break;
                case "todo":
                    if (cmd.length == 2) {
                        try {
                            tasks.add(new ToDo(cmd[1]));
                            System.out.println("Noted! I've added this task: ");
                            System.out.println(tasks.get(tasks.size() - 1).toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in this list.\n");
                        } catch (PandaBotEmptyTaskDescriptionException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Insufficient arguments given. What todo do you have?\n");
                    }
                    break;
                case "deadline":
                    if (cmd.length == 2) {
                        String[] deadlineDes = cmd[1].split("/by ", 2);
                        try {
                            tasks.add(new Deadline(deadlineDes[0], deadlineDes[1]));
                            System.out.println("Noted! I've added this task: ");
                            System.out.println(tasks.get(tasks.size() - 1).toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in this list.\n");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Insufficient arguments given.\n" +
                                    "Are you missing a description or due by time?\n");
                        } catch (PandaBotException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Insufficient arguments given. What deadline do you have?\n");
                    }
                    break;
                case "event":
                    if (cmd.length == 2) {
                        String[] taskDes = cmd[1].split("/at ", 2);
                        try {
                            tasks.add(new Event(taskDes[0], taskDes[1]));
                            System.out.println("Noted! I've added this task: ");
                            System.out.println(tasks.get(tasks.size() - 1).toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in this list.\n");
                        } catch (PandaBotEmptyTaskDescriptionException e) {
                            System.out.println(e.getMessage());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Insufficient arguments given.\n" +
                                    "Are you missing a description or duration?\n");
                        }
                    } else {
                        System.out.println("Insufficient arguments given. What event do you have?\n");
                    }
                    break;
                default:
                    System.out.println("OOPS! I'm sorry, but I don't know what that means :c\n");
                    break;
            }
            
        }
        sc.close();
    }
}
