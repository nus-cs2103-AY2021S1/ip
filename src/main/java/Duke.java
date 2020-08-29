import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        run();
    }

    public static void greet() {
        String logo = "____________________________________________________________\n"
                /*+ " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"*/
                + " Hello I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(logo);
    }

    public static void run() {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        String input;
        String[] strings;
        ArrayList<Task> list = new ArrayList<>();
        Task task;
        int num, index;
        while (isRunning) {
            input = sc.nextLine();

            if (input.equals("list")) {
                printLine()
                list(list);
                printLine()
            } else if (input.equals("bye")) {
                printLine()
                isRunning = bye();
                printLine()
            } else if (getWord(input).equals("done")) {
                printLine()
                done(input, list);
                printLine()
            } else if (getWord(input).equals("todo")) {
                printLine()
                todo(input, list);
                printLine()
            } else if (getWord(input).equals("deadline")) {
                printLine();
                deadline(input, list);
                printLine();
            } else if (getWord(input).equals("event")) {
                printLine()
                event(input, list);
                printLine()
            } else if (getWord(input).equals("delete")) {
                printLine()
                delete(input, list);
                printLine()
            } else {
                printLine()
                error();
                printLine()
            }
        }
    }

    public static String getWord(String string) {

        int firstSpaceIndex = string.indexOf(' ');
        if (firstSpaceIndex == -1) {
            return string;
        }
        return string.substring(0, firstSpaceIndex);
    }

    public static void list(ArrayList<Task> list) {
        Task task;
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            task = list.get(i - 1);
            System.out.println(i + "." + task.getTypeString() + task.getDoneString() + task.getString());
        }
    }

    public static boolean bye() {
        System.out.println("Bye. Hope to see you again soon!");
        return false;
    }

    public static void todo(String input, ArrayList<Task> list) {
        input = input.substring(4);
        if (input.isEmpty()) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            Task task = new Task(TaskType.TODO, false, input);
            list.add(task);
            System.out.println("Got it. I've added this task: ");
            System.out.println(" " + task.getTypeString() + task.getDoneString() + input);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
    }

    public static void deadline(String input, ArrayList<Task> list) {
        input = input.substring(8);
        if (input.isEmpty()) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            return;
        }
        int index = input.indexOf("/by");
        if (index == -1) {
            System.out.println("☹ OOPS!!! The description of a deadline must have a indicated deadline.");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String[] strings = new String[1];
        if(input.length() >= index + 4) {
            strings = input.substring(index + 4).split("/");
        }
        if( (strings.length >= 3) && (strings[2].length() >= 9) ) {
            if(strings[0].length() < 2){
                strings[0] = "0" + strings[0];
            }
            stringBuilder.append(strings[2].substring(0, 4))
                    .append("-").append(strings[1])
                    .append("-").append(strings[0])
                    .append("T")
                    .append(strings[2].substring(5, 7))
                    .append(":")
                    .append(strings[2].substring(7, 9));
        }
        try{
            LocalDateTime localDateTime = LocalDateTime.parse(stringBuilder.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            stringBuilder = new StringBuilder();
            stringBuilder.append(input.substring(0, index))
                    .append("(by: ")
                    .append(localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)))
                    .append(input.substring(index + 18))
                    .append(")");
            input = stringBuilder.toString();
            Task task = new Task(TaskType.DEADLINE, false, input, Optional.of(localDateTime));
            list.add(task);
            System.out.println("Got it. I've added this task: ");
            System.out.println(" " + task.getTypeString() + task.getDoneString() + input);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } catch(DateTimeParseException e) {
            System.out.println("☹ OOPS!!! The description of a deadline must have a valid date and time. (Format: /by dd/mm/yyyy tttt e.g 2/12/2019 1800");

        }
    }
    public static void event(String input, ArrayList<Task> list){
        input = input.substring(5);
        if(input.isEmpty()){
            System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
        }else {
            int index = input.indexOf("/at");
            if (index == -1) {
                System.out.println("☹ OOPS!!! The description of an event must have a indicated deadline.");
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(input.substring(0, index)).append("(at:").append(input.substring(index + 3)).append(")");
                input = stringBuilder.toString();
                Task task = new Task(TaskType.DEADLINE, false, input);
                list.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + task.getTypeString() + task.getDoneString() + input);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
        }
    }

    public static void done(String input, ArrayList<Task> list){
        input = input.substring(4);
        if(input.isEmpty()){
            System.out.println("☹ OOPS!!! Please indicate which task is done.");
        }else{
            input = input.substring(1);
            boolean isInteger = true;
            for(int i = 0; i < input.length(); i++){
                if(!Character.isDigit(input.charAt(i))){
                    isInteger = false;
                    break;
                }
            }
            if(isInteger){
                int tasknumber = Integer.parseInt(input);
                if( (tasknumber == 0) ||(tasknumber > list.size()) ){
                    System.out.println("☹ OOPS!!! Task number is not found in the list.");
                }
                else{
                    Task task = list.remove(tasknumber - 1);
                    Task newTask = new Task(task.taskType, true, task.toString());
                    list.add(tasknumber-1, newTask);
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(" " + newTask.getTypeString() + newTask.getDoneString() + newTask.toString());
                }
            }
            else{
                System.out.println("☹ OOPS!!! Incorrect entry for finished task.");
            }
        }
    }

    public static void delete(String input, ArrayList<Task> list){
        input = input.substring(6);
        if(input.isEmpty()){
            System.out.println("☹ OOPS!!! Please indicate which task is done.");
        }else{
            input = input.substring(1);
            boolean isInteger = true;
            for(int i = 0; i < input.length(); i++){
                if(!Character.isDigit(input.charAt(i))){
                    isInteger = false;
                    break;
                }
            }
            if(isInteger){
                int tasknumber = Integer.parseInt(input);
                if( (tasknumber == 0) ||(tasknumber > list.size()) ){
                    System.out.println("☹ OOPS!!! Task number is not found in the list.");
                }
                else{
                    Task task = list.remove(tasknumber - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(" " + task.getTypeString() + task.getDoneString() + task.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                }
            }
            else{
                System.out.println("☹ OOPS!!! Incorrect entry for finished task.");
            }
        }
    }

    public static void printLine(){
        System.out.println("____________________________________________________________\n");
    }

    public static void error(){
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
class Task{
    TaskType taskType;
    boolean isDone;
    String string;
    Optional<LocalDateTime> dateTime;

    public Task(TaskType taskType, boolean isDone, String string, Optional<LocalDateTime> date){
        this.taskType = taskType;
        this.isDone = isDone;
        this.string = string;
        this.dateTime = dateTime;
    }
    public Task(TaskType taskType, boolean isDone, String string){
        this.taskType = taskType;
        this.isDone = isDone;
        this.string = string;
        this.dateTime = Optional.empty();
    }

    public String getString() {
        return string;
    }
    public String getDoneString(){
        String string;
        if(isDone){
            string = "[✓]";
        }
        else{
            string = "[✗]";
        }
        return string;
    }

    public Task done(){
        return new Task(taskType, true, string);
    }

    public String getTypeString(){
        String string;
        if(taskType.equals(TaskType.TODO)){
            string = "[T]";
        }
        else if(taskType.equals(TaskType.DEADLINE)){
            string = "[D]";
        }
        else{
            string = "[E]";
        }
        return string;
    }

    public String toString(){
        return string;
    }
}

enum TaskType{
    TODO,
    DEADLINE,
    EVENT;
}