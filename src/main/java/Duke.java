import Task.*;
import Exception.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> taskStorage;
    Duke() {
        this.taskStorage = new ArrayList<>();
    }
    private void addTask(String task) {
        Task newTask = new Task(task);
        this.taskStorage.add(newTask);
    }
    static void printDialog(String content) {
        System.out.println("    ----------------------------------------");
        System.out.println("    " + content + "\n");
        System.out.println("    ----------------------------------------");
    }

    private void deleteTask(int index) {
        this.taskStorage.remove(index);
    }

    static String furtherProcessing(Commands commandType, String[] tokens, Duke dk) throws DukeException {
        String content = "";
        String result_prefix = "Got it. I've added this task:\n      ";
        String result_subfix = "Now you have " + (dk.taskStorage.size() + 1) + " tasks in the list.";
        String main_content = "";
        String deadline = "";
        for(int i = 1;i < tokens.length; i++) {
            if(tokens[i].length() == 0) continue;
            if(tokens[i].charAt(0) == '/') {
                for(int j = i + 1; j < tokens.length;j++) {
                    deadline += tokens[j] + " ";
                }
                break;
            }

            content += tokens[i] + " ";
        }
        deadline = deadline.strip();
        content = content.strip();
        if(content.equals("")) throw new DukeException("Description cannot be empty PLEASE!!!");
        if(deadline.equals("") && (commandType == Commands.DEADLINE || commandType == Commands.EVENT))
            throw new DukeInvalidArgumentException("NOT ENOUGH INFORMATION!!!");

        if(commandType == Commands.DEADLINE) {

            Deadline deadlineTask = new Deadline(content, deadline);
            dk.taskStorage.add(deadlineTask);
            main_content = deadlineTask.returnStringForm();

        } else if(commandType == Commands.EVENT) {

            Event eventTask = new Event(content, deadline);
            dk.taskStorage.add(eventTask);
            main_content = eventTask.returnStringForm();

        } else if(commandType == Commands.TODO) {

            Todo todoTask = new Todo(content);
            dk.taskStorage.add(todoTask);
            main_content = todoTask.returnStringForm();

        } else if(commandType == Commands.DELETE) {
            int mark_number = Integer.parseInt(tokens[1]);
            try {
                Task marked = dk.taskStorage.get(mark_number - 1);
                dk.deleteTask(mark_number - 1);
                return "Noted. I've removed this task:\n      " + marked.returnStringForm() + "\n    Now you have " + dk.taskStorage.size() + " task(s) in the list.";
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                throw new DukeInvalidArgumentException("Invalid number");
            }

        } else if(commandType == Commands.DONE) {
            int mark_number = Integer.parseInt(tokens[1]);
            try {
                Task marked = dk.taskStorage.get(mark_number - 1);
                marked.markAsDone();
                return "Nice! I've marked this task as done:\n      " + marked.returnStringForm();
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                throw new DukeInvalidArgumentException("Invalid number");
            }
        }
        return result_prefix + main_content + "\n    " + result_subfix;
    }

    static String processedCommand(String command, Duke dk) throws DukeException {
        command = command.strip();
        if(command.equals("")) return "";
        String[] tokens = command.split(" ");
        try {
            return furtherProcessing(Commands.valueOf(tokens[0].toUpperCase()), tokens, dk);
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! CAN YOU PLEASE TYPE SOMETHING MEANINGFUL?");
        }
    }
    private void printStoredTasks() {
        String result = "Here are the tasks in your list:\n    ";
        for(int i= 0; i < this.taskStorage.size(); i++) {
            result += ((i+1) + "." + this.taskStorage.get(i).returnStringForm());
            if(i < this.taskStorage.size() - 1) result += "\n    ";
        }
        printDialog(result);
    }
    public static void main(String[] args) {
        Duke dk = new Duke();
       /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        printDialog("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while(true) {
            String content = sc.nextLine();
            if(content.equals(Commands.BYE.getAction())) {
                printDialog("Bye. Hope to see you again soon!");
                break;//exit the program
            }
            if(content.equals(Commands.LIST.getAction())) {
                dk.printStoredTasks();
            } else  {
                try {
                    String result = processedCommand(content, dk);
                    if (!result.equals("")) printDialog(result);
                } catch (DukeException e) {
                    printDialog(e.getMessage());
                }
            }
        }
    }
}

