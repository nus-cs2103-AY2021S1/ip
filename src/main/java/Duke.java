import java.util.Scanner;

public class Duke {
    private static TaskList taskList = new TaskList("data/", "duke.txt");

    public static void main(String[] args) {

        greeting();
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String input = scanner.nextLine();  // Read user input
        while(true) {
            if(input.equals("bye")){
                break;
            } else {
                try{
                    executeCommand(input);
                } catch(DukeException e) {
                    System.out.println(e.getMessage());
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    input = scanner.nextLine();
                }
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

    }

    public static void executeCommand(String command) throws DukeException {
        if(command.equals("list")) {
            taskList.printList();
        } else if(command.length() >= 6 && command.substring(0, 5).equals("done ")) {
            System.out.println("Nice! I've marked this task as done:");
            int num = Integer.parseInt(command.split(" ")[1]);
            taskList.markAsDone(num - 1);
            System.out.println(taskList.get(num - 1));

        } else if(command.length() >= 8 && command.substring(0, 7).equals("delete ")) {
            int num = Integer.parseInt(command.split(" ")[1]);
            System.out.println(" Noted. I've removed this task:");
            System.out.println(taskList.get(num - 1));
            taskList.remove(num - 1);
            System.out.println("Now you have " + taskList.getNumberOfTask() + " tasks in the list.");
        } else {
            String[] parts = command.split(" ", 2);
            String taskType = parts[0];
            if(TaskType.isTask(taskType)) {
                if(parts.length < 2 || parts[1].isEmpty()){
                    throw new DukeException(String.format("☹ OOPS!!! The description of %s cannot be empty.", parts[0]));
                } else {
                    addTask(parts[0], parts[1]);
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private static void addTask(String taskType, String rest) throws DukeException {
        System.out.println("Got it. I've added this task:");
        Task task;

        switch(taskType){
            case "todo":
                task = new ToDo(rest);
                break;
            case "deadline":
                String[] deadlineParts = rest.split("/by");
                task = new Deadline(deadlineParts[0], deadlineParts[1]);
                break;
            case "event":
                String[] eventParts = rest.split("/at");
                task = new Event(eventParts[0], eventParts[1]);
                break;
            default:
                 throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        taskList.addTask(task);
        System.out.println(task);
        System.out.println("Now you have " + taskList.getNumberOfTask() + " tasks in the list.");
    }

    private static void greeting() {
        String logo = "      _      _\n"
                    + "| | / /| | / /\n"
                    + "| |/ / | |/ /\n"
                    + "|   <  |   <\n"
                    + "|_|\\_\\ |_|\\_\\\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm KK\n" +
                " What can I do for you?");
    }



}
