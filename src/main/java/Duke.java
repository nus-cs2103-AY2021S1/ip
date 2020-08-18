import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void init() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\n Always at your service, \n" + logo + "\n");
        System.out.println("Your Majesty, I am your loyal Duke.");
        System.out.println("I offer a range of administrative services. Do type 'assist' to see the comprehensive list.");
        System.out.println();
    }

    public static String dashedLineBreak() {
        String dashedLine = "- ";
        return dashedLine.repeat(32);
    }
    
    public static void assist() {
        System.out.println(dashedLineBreak());
        System.out.println("Greetings, Your Majesty. \n");
        System.out.println("Use any of the commands on the left to access my quality services:");
        System.out.println("\ttodo [TASK]: Adds a todo to your scroll");
        System.out.println("\tdeadline [TASK] /by [DATE AND/OR TIME]: Adds a deadline to your scroll");
        System.out.println("\tevent [TASK] /on [DATE AND/OR TIME]: Adds an event to your scroll");
        System.out.println("\tscroll: Displays your scroll - your list of tasks");
        System.out.println("\tconquer [NUMBER]: Marks the particular item on your scroll as DONE");
        System.out.println("\tdelete [NUMBER]: Deletes the particular item from your scroll");
        System.out.println("\tdismiss: This will be my cue to leave.");
        System.out.println();
        System.out.println("Now, how may I serve you?");
        System.out.println(dashedLineBreak() + "\n");
    }
    
    public static void validateScannerInput(String input) throws DukeException {
        if (input.trim().length() == 0) {
            throw new DukeException("Come again, Your Majesty?");
        }
    }

    public static void validateAdd(String[] task) throws DukeException {
        if ((task.length == 1 || task[1].trim().length() == 0) &&
                (task[0].equalsIgnoreCase("todo") ||
                        task[0].equalsIgnoreCase("event") ||
                        task[0].equalsIgnoreCase("deadline"))
        ) {
            throw new DukeException("Do give me more details about this " + task[0] + ", Your Majesty.");
        }
    }

    public static void validateSlashCommands(String[] task) throws DukeException {
        if(task[0].equalsIgnoreCase("deadline") && task[1].split("/by ", 2).length == 1){
            throw new DukeException("Use /by for deadlines, Your Majesty.");
        } else if (task[0].equalsIgnoreCase("event") &&  task[1].split("/on ", 2).length == 1){
            throw new DukeException("Use /on for events, Your Majesty.");
        }
    }

    public static void validateIndex(int taskNumber) throws DukeException{
        if(taskNumber > tasks.size() || taskNumber <= 0) {
            throw new DukeException("Your Majesty, there's no such agenda in my detailed records.");
        }
    }

    public static void validateConquerDelete(String[] command) throws DukeException {
        try {
            Integer.parseInt(command[1]);
        } catch (Exception err) {
            throw new DukeException("Please enter valid numbers after your command, Your Majesty.");
        } 
    }

    public static void printAllTasks() {
        if (tasks.size() == 0) {
            System.out.println("Your scroll is currently empty, Your Majesty.");
        } else {
            System.out.println(dashedLineBreak());
            System.out.println("Your current scroll, Your Majesty:");
            for (Task task : tasks) {
                System.out.printf("\t%s.%s", tasks.indexOf(task) + 1, task);
                System.out.println();
            }
        }
        System.out.println(dashedLineBreak());
        System.out.println();
    }
    
    
    public static void addTask(String task) {
        try {
            
            validateScannerInput(task);
            
            String splitTask[] = task.split(" ", 2);
            Task newTask;
            validateAdd(splitTask);
            validateSlashCommands(splitTask);
            
            switch(splitTask[0].toLowerCase()) {
                case "todo":
                    newTask = new ToDo(splitTask[1]);
                    break;
                case "deadline":
                    newTask = new Deadline(splitTask[1]);
                    break;
                case "event":
                    newTask = new Event(splitTask[1]);
                    break;
                default:
                    throw new DukeException("I'm afraid I do not understand that command, Your Majesty.");
            }
            if (newTask != null) {
                tasks.add(newTask);
                System.out.println(dashedLineBreak());
                System.out.println("Your Majesty, I've added the writing:");
                System.out.println("\t" + newTask);
                System.out.printf("You have %s writing(s) on your scroll as of now. \n", tasks.size());
                System.out.println(dashedLineBreak());
                System.out.println();
            }
        } catch (DukeException err) {
            System.out.println(err.getMessage());
            System.out.println(dashedLineBreak());
            System.out.println();
        }
    }
    

    public static void conquerTask(String[] command) {
        try {
            validateConquerDelete(command);
            int taskNumber = Integer.parseInt(command[1]);
            validateIndex(taskNumber);
            
            tasks.get(taskNumber - 1).markAsDone();
            System.out.println(dashedLineBreak());
            System.out.println("As you wish, Your Majesty. I have marked this as conquered.");
            System.out.println("\t" + tasks.get(taskNumber - 1));
            System.out.println(dashedLineBreak());
        } catch (DukeException err) {
            System.out.println(err.getMessage());
            System.out.println(dashedLineBreak());
        }
        System.out.println();
    }
    
    public static void deleteTask(String[] command) {
        try {
            validateConquerDelete(command);
            int taskNumber = Integer.parseInt(command[1]);
            validateIndex(taskNumber);
            
            Task deletedTask = tasks.get(taskNumber - 1);
            tasks.remove(taskNumber - 1);
            System.out.println(dashedLineBreak());
            System.out.println("As you wish, Your Majesty. I have removed this writing.");
            System.out.println("\t" + deletedTask);
            System.out.printf("You have %s writing(s) on your scroll as of now. \n", tasks.size());
            System.out.println(dashedLineBreak());
        } catch (DukeException err) {
            System.out.println(err.getMessage());
            System.out.println(dashedLineBreak());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String[] splitUserInput = userInput.split(" ", 2);
            switch(splitUserInput[0].toLowerCase()) {
                case "dismiss":
                    System.out.println(dashedLineBreak());
                    System.out.println("Your wish is my command, Your Majesty. Till I see you again. \n");
                    sc.close();
                    System.exit(0);
                    break;
                case "scroll":
                    printAllTasks();
                    break;
                case "conquer":
                    conquerTask(splitUserInput);
                    break;
                case "delete":
                    deleteTask(splitUserInput);
                    break;
                case "assist":
                    assist();
                    break;
                default:
                    addTask(userInput);
            }
        }
    }
}
