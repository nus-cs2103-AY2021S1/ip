import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;
import Exception.DukeException;
import Exception.DukeInvalidArgumentException;
import Helper.DateTimeHelper;
import java.time.LocalDate;
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
    static DateTimeHelper processDateTime(String input) {
        DateTimeHelper dtHelper = new DateTimeHelper(input.strip());
        String result = dtHelper.processInput();
        String resDate = dtHelper.processDateStr();

        if (!result.equals("error") && !resDate.equals("error")) {
            return dtHelper;
        } else {
            return null;
        }
    }
    static String furtherProcessing(Commands commandType, String[] tokens, Duke dk, boolean isLoaded) throws DukeException {
        String content = "";
        String result_prefix = "Got it. I've added this task:\n      ";
        String result_subfix = "Now you have " + (dk.taskStorage.size() + 1) + " tasks in the list.";
        String main_content = "";
        String deadlineStr = "";
        SaveData saver = new SaveData();
        int tokens_limit = isLoaded ? tokens.length - 1 : tokens.length;
        boolean isDone = tokens[tokens.length - 1].equals("1");
        for(int i = 1;i < tokens_limit; i++) {
            if(tokens[i].length() == 0) continue;
            if(tokens[i].charAt(0) == '/') {
                for(int j = i + 1; j < tokens_limit;j++) {
                    deadlineStr += tokens[j] + " ";
                }
                break;
            }

            content += tokens[i] + " ";
        }
        LocalDate deadline = LocalDate.now();
        String exactTime = "";
        if(deadlineStr.equals("") && (commandType == Commands.DEADLINE || commandType == Commands.EVENT))
            throw new DukeInvalidArgumentException("NOT ENOUGH INFORMATION!!!");

        if(commandType == Commands.DEADLINE || commandType == Commands.EVENT) {
            DateTimeHelper dtHelper = processDateTime(deadlineStr);
            if (dtHelper != null) {
                deadline = dtHelper.getDate();
                String resTime = dtHelper.processTimeStr();
                if (resTime.equals("success")) exactTime = dtHelper.getTime();
            } else {
                return "Wrong format\n    Your date and time(optional) should be in this format:\n      yyyy-mm-dd HHmm\n    e.g: 2019-10-15 1800 or 2019-10-15";
            }
        }
        content = content.strip();
        if(content.equals("")) throw new DukeException("Description cannot be empty PLEASE!!!");
        if(commandType == Commands.DEADLINE) {

            Deadline deadlineTask = new Deadline(content, deadline, exactTime, deadlineStr);
            if(isLoaded && isDone) deadlineTask.markAsDone();
            dk.taskStorage.add(deadlineTask);
            saver.saveData(dk.taskStorage);
            main_content = deadlineTask.returnStringForm();

        } else if(commandType == Commands.EVENT) {

            Event eventTask = new Event(content, deadline, exactTime, deadlineStr);
            if(isLoaded && isDone) eventTask.markAsDone();
            dk.taskStorage.add(eventTask);
            saver.saveData(dk.taskStorage);
            main_content = eventTask.returnStringForm();

        } else if(commandType == Commands.TODO) {

            Todo todoTask = new Todo(content);
            if(isLoaded && isDone) todoTask.markAsDone();
            dk.taskStorage.add(todoTask);
            saver.saveData(dk.taskStorage);
            main_content = todoTask.returnStringForm();

        } else if(commandType == Commands.DELETE) {

            int mark_number = Integer.parseInt(tokens[1]);
            try {
                Task marked = dk.taskStorage.get(mark_number - 1);
                dk.deleteTask(mark_number - 1);
                saver.saveData(dk.taskStorage);
                return "Noted. I've removed this task:\n      " + marked.returnStringForm() + "\n    Now you have " + dk.taskStorage.size() + " task(s) in the list.";
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                throw new DukeInvalidArgumentException("Invalid number");
            }

        } else if(commandType == Commands.DONE) {

            int mark_number = Integer.parseInt(tokens[1]);
            try {
                Task marked = dk.taskStorage.get(mark_number - 1);
                marked.markAsDone();
                saver.saveData(dk.taskStorage);
                return "Nice! I've marked this task as done:\n      " + marked.returnStringForm();
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                throw new DukeInvalidArgumentException("Invalid number");
            }

        } else if(commandType == Commands.LIST) {
            DateTimeHelper dtHelper = processDateTime(content);
            if (dtHelper != null) {
                deadline = dtHelper.getDate();
                String res = "Here are the tasks in your list:\n    ";
                for(int i = 0;i < dk.taskStorage.size();i++) {
                    Task task = dk.taskStorage.get(i);
                    if(task instanceof Deadline) {
                        if(((Deadline)task).getDeadline().compareTo(deadline) == 0) {
                            res += ((i + 1) + "." + task.returnStringForm());
                            if (i < dk.taskStorage.size() - 1) res += "\n    ";
                        }
                    } else if(task instanceof Event) {
                        if(((Event)task).getTime().compareTo(deadline) == 0) {
                            res += ((i + 1) + "." + task.returnStringForm());
                            if (i < dk.taskStorage.size() - 1) res += "\n    ";
                        }
                    }
                }
                return res;
            } else {
                return "Wrong format\n    Your date and time(optional) should be in this format:\n      yyyy-mm-dd HHmm\n    e.g: 2019-10-15 1800 or 2019-10-15";
            }
        }
        return result_prefix + main_content + "\n    " + result_subfix;
    }

    static String processedCommand(String command, Duke dk, boolean isLoaded) throws DukeException {
        command = command.strip();
        if(command.equals("")) return "";
        String[] tokens = command.split(" ");
        try {
            return furtherProcessing(Commands.valueOf(tokens[0].toUpperCase()), tokens, dk, isLoaded);
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
        printDialog("Hello! I'm the Riddle. Type 'help' if you know nothing HAHAHA\n    What can WE do for you?");
        Scanner sc = new Scanner(System.in);
        LoadData loader = new LoadData();
        ArrayList<String> savedTasks = loader.getSavedTasks();
        if(savedTasks.size() > 0 && savedTasks.get(0).equals("000")) {
            printDialog("This is the first time you use Duke!");
        } else {
            try {
                for (String task : savedTasks) {
                    processedCommand(task, dk, true);
                    //if (!result.equals("")) printDialog(result);
                }
            } catch (DukeException e) {
                printDialog("Something wrong happened while loading saved tasks");
            }
        }
        while(true) {
            String content = sc.nextLine();
            content = content.strip();
            if(content.equals(Commands.BYE.getAction())) {
                printDialog("Bye. Hope to see you again soon!");
                break;//exit the program
            }
            if(content.equals(Commands.HELP.getAction())) {
                String res = "";
                for(Commands command: Commands.values()) {
                    res += command.getAction() + ": " + command.getDescription();
                    res += "\n    ";
                }
                printDialog(res);
            }
            else if(content.equals(Commands.LIST.getAction())) {
                dk.printStoredTasks();
            } else {
                try {
                    String result = processedCommand(content, dk, false);
                    if (!result.equals("")) printDialog(result);
                } catch (DukeException e) {
                    printDialog(e.getMessage());
                }
            }
        }
    }
}

