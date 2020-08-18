import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int index = 0;

    public static void init() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Always at your service, \n" + logo);
        System.out.println("Your Majesty, I am your loyal Duke.");
        System.out.println("How may I serve you?");
        System.out.println();
    }

    public static String dashedLineBreak() {
        String dashedLine = "- ";
        return dashedLine.repeat(32);
    }

    public static void printAllTasks() {
        if (index == 0) {
            System.out.println("Your scroll is currently empty, Your Majesty.");
        } else {
            System.out.println(dashedLineBreak());
            System.out.println("Your current scroll, Your Majesty:");
            for (int i = 0; i < index; i++) {
                System.out.printf("\t%s.%s", i + 1, tasks[i]);
                System.out.println();
            }
        }
        System.out.println(dashedLineBreak());
        System.out.println();
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

    public static void addTask(String task) {
        try {
            String splitTask[] = task.split(" ", 2);
            Task newTask = null;
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
                tasks[index] = newTask;
                index++;
                
                System.out.println(dashedLineBreak());
                System.out.println("Your Majesty, I've added the writing:");
                System.out.println("\t" + newTask);
                System.out.printf("You have %s writing(s) on your scroll as of now. \n", index);
                System.out.println(dashedLineBreak());
                System.out.println();
            }
        } catch (DukeException err) {
            System.out.println(err);
            System.out.println(dashedLineBreak());
            System.out.println();
        }
    }
    
    public static void validateConquer(int taskNumber) throws DukeException{
        if (taskNumber - 1 < 0 || taskNumber - 1 > 99 || tasks[taskNumber - 1] == null) {
            throw new DukeException("Your Majesty, there's no such agenda in my detailed records.");
        }
    }

    public static void conquerTask(int taskNumber) {
        try {
            validateConquer(taskNumber);
            tasks[taskNumber - 1].markAsDone();
            System.out.println(dashedLineBreak());
            System.out.println("As you wish, Your Majesty. I have marked this as conquered.");
            System.out.println("\t" + tasks[taskNumber - 1]);
            System.out.println(dashedLineBreak());
        } catch (DukeException err) {
            System.out.println(err);
            System.out.println(dashedLineBreak());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String[] splitUserInput = userInput.split(" ");
            switch(splitUserInput[0].toLowerCase()) {
                case "dismiss":
                    System.out.println(dashedLineBreak());
                    System.out.println("Your wish is my command, Your Majesty. Till I see you again.");
                    sc.close();
                    System.exit(0);
                    break;
                case "scroll":
                    printAllTasks();
                    break;
                case "conquer":
                    int taskNumber = Integer.parseInt(splitUserInput[1]);
                    conquerTask(taskNumber);
                    break;
                default:
                    addTask(userInput);
            }
        }
    }
}
