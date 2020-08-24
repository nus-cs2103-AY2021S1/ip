import java.util.Scanner;
import java.util.ArrayList;
import java.util.Optional;

import java.io.File;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Duke {
    // file path
    public final static String FILEPATH = System.getProperty("user.dir") 
            + (System.getProperty("user.dir").endsWith("text-ui-test") 
            ? "/test_data/duke.txt"
            : "/data/duke.txt");
    
    // list storage
    private static ArrayList<Task> tasks;

    // list of commands
    private static String exitCmd = "bye";
    private static String doneCmd = "done";
    private static String deleteCmd = "delete";
    private static String todoCmd = Task.Command.TODO.getCmd();
    private static String deadlineCmd = Task.Command.DEADLINE.getCmd();
    private static String eventCmd = Task.Command.EVENT.getCmd();
    private static String findCmd = "find";
    
    // time specifier
    private static String deadlineTimeSpecifier = Task.Command.DEADLINE.getTimeSpecifier();
    private static String eventTimeSpecifier = Task.Command.EVENT.getTimeSpecifier();

    // list of messages
    private static String exitMsg = "Bye human. See you again soon!";
    private static String doneMsg = "Good job bud! I've marked this task as done:";
    private static String deleteMsg = "Noted. I've deleted this task:";
    private static String showTasksMsg = "Here are the task(s) in your lists:";
    private static String addSuccessfulMsg = "Got it. I've added this task:";
    private static String horizontalLine = "________________________________________";
    private static String cmdReq = "Your command:";
    private static String lazyHumanBash = "You have nothing in your list. Find something to do you human!";
    private static String unrecognizedCmdMsg = "I don't understand a single word you say, human\n"
            + "Speak robot language pls";
    private static String invalidIdxMsg() {
        return "Invalid index. Please input a number between 1 and " + tasks.size();
    }
    private static String incorrectDateFormatMsg = "Sorry, I cannot understand the date, "
            + "try to format it in the format: yyyy-MM-dd";
    private static String findResultMsg = "Here are the tasks happening on that date:";

    private static String taskReport() {
        return "You have " + tasks.size() + " tasks in your list";
    }

    // logo and greeting
    private static String logo = " ____        _\n"
                                + "|  _ \\ _   _| | _____\n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";
    private static String greeting = "Hello human, I'm Duke the supporting chatbot\n"
            + "What can I do for you?";

    // main
    public static void main(String[] args) {
        // read file
        tasks = readFile(FILEPATH);
        
        // greeting
        System.out.println(logo + "\n" + horizontalLine);
        System.out.println(greeting + "\n" + horizontalLine);
        System.out.println(cmdReq);

        // getting command
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] inputSplitted = input.split("\\s+", 2);
        while (!input.equals(exitCmd)) {
            System.out.println(horizontalLine);
            try {
                if (input.equals("list")) { // command is "list"
                    if (tasks.size() == 0) {
                        System.out.println(lazyHumanBash);
                    } else {
                        System.out.println(showTasksMsg);
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i).toString());
                        }
                    }
                } else if (inputSplitted[0].equals(deleteCmd)) { // command is "delete [index]"
                    try {
                        int idx = Integer.parseInt(inputSplitted[1]) - 1;
                        System.out.println(deleteMsg);
                        System.out.println(tasks.remove(idx));
                        System.out.println(taskReport());
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        throw new InvalidIndexException();
                    }
                } else if (inputSplitted[0].equals(doneCmd)) { // command is "done [index]"
                    try {
                        int idx = Integer.parseInt(inputSplitted[1]) - 1;
                        tasks.set(idx, tasks.get(idx).markAsDone());
                        System.out.println(doneMsg);
                        System.out.println(tasks.get(idx));
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        throw new InvalidIndexException();
                    }
                } else if (inputSplitted[0].equals(todoCmd)) { // command is "todo [description]"
                    if (inputSplitted.length == 1 || inputSplitted[1].equals("")) {
                        throw new InadequateCommandException("todo",
                                new String[] {"description"});
                    } else {
                        Task newTask = new ToDo(inputSplitted[1]);
                        tasks.add(newTask);
                        System.out.println(addSuccessfulMsg);
                        System.out.println(newTask);
                        System.out.println(taskReport());
                    }
                } else if (inputSplitted[0].equals(deadlineCmd) // command is "deadline [description] /by [time]"
                        || inputSplitted[0].equals(eventCmd)) { // or "event [description] / at [time]"
                    String type, timeSpecifier;
                    boolean isDeadline;
                    if (inputSplitted[0].equals(deadlineCmd)) {
                        type = "deadline";
                        timeSpecifier = deadlineTimeSpecifier;
                        isDeadline = true;
                    } else {
                        type = "event";
                        timeSpecifier = eventTimeSpecifier;
                        isDeadline = false;
                    }
                    if (inputSplitted.length == 1) {
                        String[] missing = {"description", "time"};
                        throw new InadequateCommandException(type, missing);
                    } else {
                        String content = inputSplitted[1];
                        String[] split2Test = content.split("\\s+");
                        int timeIdx = content.indexOf(" " + timeSpecifier);
                        if (split2Test.length == 0 ||
                                (split2Test.length == 1 &&
                                        (split2Test[0].equals(timeSpecifier) || split2Test[0].equals(""))
                                )
                        ) {
                            String[] missing = {"description", "time"};
                            throw new InadequateCommandException(type, missing);
                        }
                        if (timeIdx == 0 || content.indexOf(timeSpecifier) == 0) {
                            throw new InadequateCommandException(type,
                                    new String[]{"description"});
                        }
                        if (timeIdx == -1 || timeIdx + 5 >= content.length()) {
                            throw new InadequateCommandException(type,
                                    new String[]{"time"});
                        }
                        String description = content.substring(0, timeIdx);
                        String time = content.substring(timeIdx + 5);
                        if (time.split("\\s+").length == 0) {
                            throw new InadequateCommandException(type,
                                    new String[]{"time"});
                        }
                        Task newTask = isDeadline
                                ? new Deadline(description, time)
                                : new Event(description, time);
                        tasks.add(newTask);
                        System.out.println(addSuccessfulMsg);
                        System.out.println(newTask);
                        System.out.println(taskReport());
                    }
                } else if (inputSplitted[0].equals(findCmd)){
                    Optional<LocalDate> optDate = ParseDate.parse(inputSplitted[1]);
                    if (optDate.isPresent()) {
                        LocalDate date = optDate.get();
                        System.out.println(findResultMsg);
                        for (int i = 0; i < tasks.size(); i++) {
                            Task task = tasks.get(i);
                            if ((task instanceof Deadline && ((Deadline) task).isDueOn(date))
                                    || (task instanceof Event && ((Event) task).isOccuringOn(date))) {
                                System.out.println(task);
                            }
                        }
                    } else {
                        System.out.println(incorrectDateFormatMsg);
                    }
                    
                } else { // any other commands
                    throw new IncorrectCommandException();
                }
            } catch (InadequateCommandException e) {
                System.out.println(e.getMessage());
            } catch (InvalidIndexException e) {
                System.out.println(invalidIdxMsg());
            } catch (IncorrectCommandException e) {
                System.out.println(unrecognizedCmdMsg);
            }
            System.out.println(horizontalLine);
            System.out.println(cmdReq);
            input = sc.nextLine();
            inputSplitted = input.split("\\s+", 2);
        }

        // exit
        writeFile(FILEPATH, tasks);
        System.out.println(horizontalLine + "\n" +  exitMsg + "\n" + horizontalLine);
    }
    
    // get the data from the file and put it into an array list
    private static ArrayList<Task> readFile(String filePath) {
        File f = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String nextTask = sc.nextLine();
                String[] splitted = nextTask.split("\\s+", 3);
                String type = splitted[0];
                boolean isDone = splitted[1].equals("D");
                Task newTask;
                if (type.equals("T")) {
                    String description = splitted[2];
                    newTask = new ToDo(description);
                } else {
                    int timeIdx = splitted[2].indexOf(" /time");
                    String description = splitted[2].substring(0, timeIdx);
                    String time = splitted[2].substring(timeIdx + 7);
                    if (type.equals("D")) {
                        newTask = new Deadline(description, time);
                    } else {
                        newTask = new Event(description, time);
                    }
                }
                if (isDone) {
                    newTask.markAsDone();
                }
                tasks.add(newTask);
            }
            sc.close();
        } catch (FileNotFoundException e){
            try {
                String DIRECTORY_PATH = FILEPATH.substring(0, FILEPATH.length() - 8);
                File directory = new File(DIRECTORY_PATH);
                if (!directory.exists()) {
                    directory.mkdir();
                }
                f.createNewFile();
            } catch (IOException e1) {
                
            }
        }
        
        return tasks;
    }
    
    // store data to the file when the program exits
    private static void writeFile(String filePath, ArrayList<Task> tasks) {
        try {
            File f = new File(filePath);
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();
            FileWriter fw = new FileWriter(filePath, true);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String type, description, time, status;
                description = task.getDescription();
                status = task.isDone() ? "D" : "ND";
                time = task.getTime().equals("") ? "" : "/time " + task.getTime();
                if (task instanceof ToDo) {
                    type = "T";
                } else if (task instanceof Deadline) {
                    type = "D";
                } else {
                    type = "E";
                }
                String dataPresentation = type + " " + status + " " + description + " " + time + "\n";
                fw.write(dataPresentation);
            }
            fw.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
