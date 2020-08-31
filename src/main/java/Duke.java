package duke;

import duke.Task;
import duke.TaskType;
import duke.Ui;
import duke.TaskList;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Duke {
    private static String TASKS_PATHNAME = "data/tasks.txt";
    private Ui ui;
    private TaskList taskList;

    public Duke(String filePath) {
        ui = new Ui();

        //load tasks from file
        File file = new File(filePath);
        if(file.getParentFile() != null){
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            ui.showOutput(e.getMessage());
        }
        try {
            Scanner sc = new Scanner(file);
            ArrayList<Task> list = new ArrayList<>();
            String task, taskString;
            TaskType taskType;
            boolean isDone;
            LocalDateTime dateTime;
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
                if(taskType == TaskType.DEADLINE){
                    int index = task.indexOf("(by:");
                    dateTime = LocalDateTime.parse(task.substring(index + 4, task.length()-1), DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
                    list.add(new Task(taskType, isDone, taskString, Optional.of(dateTime)));
                }
                else {
                    list.add(new Task(taskType, isDone, taskString));
                }
            }
            taskList = new TaskList(list);
        } catch (FileNotFoundException e) {
            ui.showOutput(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke(TASKS_PATHNAME).run();
    }

    public void run() {
        ui.showWelcome();

        boolean isRunning = true;
        String input;
        while(isRunning) {
            input = ui.readCommand();
            ui.showLine();
            if(input.equals("list")){
                list();
            } else if (input.equals("bye")) {
                isRunning = bye();
            } else if (getWord(input).equals("done")) {
                done(input);
            } else if (getWord(input).equals("todo")) {
                todo(input);
            } else if (getWord(input).equals("deadline")) {
                deadline(input);
            } else if (getWord(input).equals("event")) {
                event(input);
            } else if (getWord(input).equals("delete")) {
                delete(input);
            }
            else if(getWord(input).equals("save")){
                save();
            }
            else{
                ui.showError();
            }
            ui.showLine();
        }
    }

    public String getWord(String string) {

        int firstSpaceIndex = string.indexOf(' ');
        if (firstSpaceIndex == -1) {
            return string;
        }
        return string.substring(0, firstSpaceIndex);
    }

    public void list() {
        Task task;
        ui.showOutput("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            task = taskList.get(i - 1);
            ui.showOutput(i + "." + task.getTypeString() + task.getDoneString() + task.getString());
        }
    }

    public boolean bye() {
        ui.showOutput("Bye. Hope to see you again soon!");
        return false;
    }

    public void todo(String input) {
        input = input.substring(4);
        if (input.isEmpty()) {
            ui.showOutput("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            Task task = new Task(TaskType.TODO, false, input);
            taskList.add(task);
            ui.showOutput("Got it. I've added this task: ");
            ui.showOutput(" " + task.getTypeString() + task.getDoneString() + input);
            ui.showOutput("Now you have " + taskList.size() + " tasks in the list.");
        }
    }

    public void deadline(String input) {
        input = input.substring(8);
        if (input.isEmpty()) {
            ui.showOutput("☹ OOPS!!! The description of a deadline cannot be empty.");
            return;
        }
        int index = input.indexOf("/by");
        if (index == -1) {
            ui.showOutput("☹ OOPS!!! The description of a deadline must have a indicated deadline.");
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
                    .append(")");
            input = stringBuilder.toString();
            Task task = new Task(TaskType.DEADLINE, false, input, Optional.of(localDateTime));
            taskList.add(task);
            ui.showOutput("Got it. I've added this task: ");
            ui.showOutput(" " + task.getTypeString() + task.getDoneString() + input);
            ui.showOutput("Now you have " + taskList.size() + " tasks in the list.");
        } catch(DateTimeParseException e) {
            ui.showOutput("☹ OOPS!!! The description of a deadline must have a valid date and time. (Format: /by dd/mm/yyyy tttt e.g 2/12/2019 1800");
        }
    }
    public void event(String input){
        input = input.substring(5);
        if(input.isEmpty()){
            ui.showOutput("☹ OOPS!!! The description of a event cannot be empty.");
        }else {
            int index = input.indexOf("/at");
            if (index == -1) {
                ui.showOutput("☹ OOPS!!! The description of an event must have a indicated deadline.");
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(input.substring(0, index)).append("(at:").append(input.substring(index + 3)).append(")");
                input = stringBuilder.toString();
                Task task = new Task(TaskType.DEADLINE, false, input);
                taskList.add(task);
                ui.showOutput("Got it. I've added this task: ");
                ui.showOutput(" " + task.getTypeString() + task.getDoneString() + input);
                ui.showOutput("Now you have " + taskList.size() + " tasks in the list.");
            }
        }
    }

    public void done(String input){
        input = input.substring(4);
        if(input.isEmpty()){
            ui.showOutput("☹ OOPS!!! Please indicate which task is done.");
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
                if( (tasknumber == 0) ||(tasknumber >taskList.size()) ){
                    ui.showOutput("☹ OOPS!!! Task number is not found in the list.");
                }
                else{
                    Task task =taskList.remove(tasknumber - 1);
                    Task newTask = new Task(task.taskType, true, task.toString());
                    taskList.add(tasknumber-1, newTask);
                    ui.showOutput("Nice! I've marked this task as done: ");
                    ui.showOutput(" " + newTask.getTypeString() + newTask.getDoneString() + newTask.toString());
                }
            }
            else{
                ui.showOutput("☹ OOPS!!! Incorrect entry for finished task.");
            }
        }
    }

    public void delete(String input){
        input = input.substring(6);
        if(input.isEmpty()){
            ui.showOutput("☹ OOPS!!! Please indicate which task is done.");
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
                if( (tasknumber == 0) ||(tasknumber > taskList.size()) ){
                    ui.showOutput("☹ OOPS!!! Task number is not found in the list.");
                }
                else{
                    Task task = taskList.remove(tasknumber - 1);
                    ui.showOutput("Noted. I've removed this task:");
                    ui.showOutput(" " + task.getTypeString() + task.getDoneString() + task.toString());
                    ui.showOutput("Now you have " + taskList.size() + " tasks in the list.");
                }
            }
            else{
                ui.showOutput("☹ OOPS!!! Incorrect entry for finished task.");
            }
        }
    }


    public void save() {
        try {
            FileWriter fileWriter = new FileWriter(TASKS_PATHNAME);
            Task task;

            for (int listIndex = 0; listIndex < taskList.size(); listIndex++) {
                task = taskList.get(listIndex);
                fileWriter.write(task.getTypeString() + task.getDoneString() + task.getString() + System.lineSeparator());
            }
            fileWriter.close();

            ui.showOutput("Tasks have been saved! ");
        } catch (IOException e) {
            ui.showOutput(e.getMessage());
        }
    }
}