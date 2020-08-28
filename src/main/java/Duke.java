package duke;

import duke.Task;
import duke.TaskType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String TASKS_PATHNAME = "data/tasks.txt";

    public static void main(String[] args) {
        greet();
        try {
            run();
        } catch(IOException e) {
            System.err.println(e);
        }
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

    public static void run() throws IOException, SecurityException {
        File tasks = new File(TASKS_PATHNAME);

        if(tasks.getParentFile() != null){
            tasks.getParentFile().mkdirs();
        }
        tasks.createNewFile();

        Scanner sc = new Scanner(tasks);
        ArrayList<Task> list = new ArrayList<>();
        String task, taskString;
        TaskType taskType;
        boolean isDone;
        while(sc.hasNext()) {
                task = sc.nextLine();
                if(task.charAt(1) == 'T') {
                    taskType = TaskType.TODO;
                }
                else if(task.charAt(1) == 'D') {
                    taskType = TaskType.DEADLINE;
                }
                else {
                    taskType = TaskType.EVENT;
                }

                if(task.charAt(4) == '✓') {
                    isDone = true;
                }
                else {
                    isDone = false;
                }
                taskString = task.substring(6);
                list.add(new Task(taskType, isDone, taskString));
        }

        sc = new Scanner(System.in);
        boolean isRunning = true;
        String input;
        while(isRunning) {
            input = sc.nextLine();
            printLine();
            if(input.equals("list")){
                list(list);
            }
            else if(input.equals("bye")){
                isRunning = bye();
            }
            else if(getWord(input).equals("done")){
                done(input, list);

            }
            else if(getWord(input).equals("todo")){
                todo(input, list);
            }
            else if(getWord(input).equals("deadline")) {
                deadline(input, list);
            }
            else if(getWord(input).equals("event")) {
                event(input, list);
            }
            else if(getWord(input).equals("delete")) {
                delete(input, list);
            }
            else if(getWord(input).equals("save")){
                save(list);
            }
            else{
                error();
            }
            printLine();
        }
    }

    public static String getWord(String string){

        int firstSpaceIndex = string.indexOf(' ');
        if(firstSpaceIndex == -1){
            return string;
        }
        return string.substring(0, firstSpaceIndex);
    }

    public static void list(ArrayList<Task> list){
        Task task;
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= list.size(); i++){
            task = list.get(i-1);
            System.out.println(i + "." + task.getTypeString() + task.getDoneString() + task.getString());
        }
    }

    public static boolean bye(){
        System.out.println("Bye. Hope to see you again soon!");
        return false;
    }

    public static void todo(String input, ArrayList<Task> list){
        input = input.substring(4);
        if(input.isEmpty()){
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else {
            Task task = new Task(TaskType.TODO, false, input);
            list.add(task);
            System.out.println("Got it. I've added this task: ");
            System.out.println(" " + task.getTypeString() + task.getDoneString() + input);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
    }

    public static void deadline(String input, ArrayList<Task> list){
        input = input.substring(8);
        if(input.isEmpty()){
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        }else {
            int index = input.indexOf("/by");
            if (index == -1) {
                System.out.println("☹ OOPS!!! The description of a deadline must have a indicated deadline.");
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(input.substring(0, index)).append("(by:").append(input.substring(index + 3)).append(")");
                input = stringBuilder.toString();
                Task task = new Task(TaskType.DEADLINE, false, input);
                list.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + task.getTypeString() + task.getDoneString() + input);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
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

    public static void save(ArrayList<Task> list) throws IOException {
        FileWriter fileWriter = new FileWriter(TASKS_PATHNAME);
        Task task;

        for(int listIndex = 0; listIndex < list.size(); listIndex++){
            task = list.get(listIndex);
            fileWriter.write( task.getTypeString() + task.getDoneString() + task.getString() + System.lineSeparator());
        }
        fileWriter.close();

        System.out.println("Tasks have been saved! ");
    }
}