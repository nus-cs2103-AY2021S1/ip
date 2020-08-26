import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String long_line = "________________________________________________________________________________";
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static String bye_key = "bye";
    private static String list_key = "list";
    private static String done_key = "done";
    private static String todo_key = "todo";
    private static String deadline_key = "deadline";
    private static String event_key = "event";
    private static String delete_key = "delete";


    public static void main(String[] args) throws IOException {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        lineFormatter("Hello!!!! I'm Duke\nWhat can I do for you?!?!?!" );
        loadTasks();
        add_input();

    }



    public static void add_input() {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            try{
                String input = scanner.nextLine();
                //splitting into list for easier comparison
                String[] inputList = input.trim().split(" ", 2);

                // case where input is bye, and a case where the inputList is of length 1
                if(inputList[0].trim().toLowerCase().equals(bye_key)){
                    byeGreetings();
                    saveTasks();
                    break;
                }

                // Case where input is list, to show the list of tasks, and case where the inputList is of length 1
                if(inputList[0].trim().toLowerCase().equals(list_key) && inputList.length > 1){
                    throw new IncompleteCommandException();
                }
                if(inputList[0].trim().toLowerCase().equals(list_key)) {
                    StringBuffer result = new StringBuffer();
                    //to add in the starting line of the section
                    result.append("Here are the tasks in your list:\n");

                    for (int i = 0; i < taskList.size(); i++) {
                        // getting the current task
                        Task currentTask = taskList.get(i);

                        // adding the current task into the tasklist
                        result.append((i + 1) + ". " + currentTask.toString() + "\n");
                    }
                    lineFormatter(result.toString());
                }

                //case where the command is incomplete, in the cases of done, todo, event, deadline and delete
                else if(inputList.length < 2) {
                    throw new IncompleteCommandException();

                }
                // case where the input is done
                else if(inputList[0].trim().toLowerCase().equals(done_key) && isNum(inputList[1])){
                    int currentIndex = Integer.parseInt(inputList[1]) - 1;
                    if(currentIndex + 1> taskList.size() || currentIndex + 1 <= 0){
                        throw new DoneException(currentIndex, taskList.size());
                    } else {
                        Task task = taskList.get(currentIndex);
                        // to check if the task is already done
                        if(task.getStatus()){
                            throw new TaskAlreadyDoneException(task);
//                            lineFormatter("This task is already done!\n" +
//                                    task.toString());
                            // if task is not done
                        } else {
                            taskList.get(currentIndex).markAsDone();
                            taskDone(taskList.get(currentIndex));
                        }
                    }
                } else if(inputList[0].trim().toLowerCase().equals(delete_key) && isNum(inputList[1])) {
                    int currentIndex = Integer.parseInt(inputList[1]) - 1;
                    if (currentIndex + 1 > taskList.size() || currentIndex + 1 <= 0) {
                        throw new DeleteException(currentIndex, taskList.size());
                    } else {
                        Task deletedTask = taskList.remove(currentIndex);
                        taskDeleted(deletedTask);
                    }
                } else {
                    added_to_List(input);


                }
            } catch (DukeException e){
                lineFormatter(e.getMessage());
            }

        }
    }

    // method that adds tasks into the list of tasks
    public static void added_to_List(String printable) throws DukeException{
        String[] nameList = printable.split(" ", 2);
        if(nameList[0].trim().toLowerCase().equals(deadline_key)){
            String[] task_deadline = nameList[1].trim().split("/by", 2);
            if(task_deadline.length != 2){
                throw new DeadlineException();
            }
            Task newTask = new Deadline(task_deadline[0].trim(), task_deadline[1].trim());
            taskList.add(newTask);
            newTaskItem(newTask, deadline_key);

        } else if(nameList[0].trim().toLowerCase().equals(event_key)){
            String[] task_event = nameList[1].trim().split("/at", 2);
            if(task_event.length != 2){
                throw new EventException();
            }
            Task newTask = new Event(task_event[0].trim(), task_event[1].trim());
            taskList.add(newTask);
            newTaskItem(newTask, event_key);

        } else if(nameList[0].toLowerCase().equals(todo_key)){
            Task newTask = new ToDo(nameList[1].trim());
            taskList.add(newTask);
            newTaskItem(newTask, todo_key);

        } else {
            lineFormatter("Please enter an appropriate command!!");
        }


    }

    //method to segment every String that is being fed into this method
    public static void lineFormatter (String printable){
        System.out.println(long_line);
        System.out.println(printable);
        System.out.println(long_line);
    }


    // standardised goodbye greeting
    public static void byeGreetings () {
        lineFormatter("Bye! Hope to see you soon again?!");
    }

    //method to mark tasks as done
    public static void taskDone(Task task) {
        lineFormatter("Nice! This task is getting done!!\n" + "[" + task.getStatusIcon() + "] " + task.getTask());
    }

    //method to mark tasks as deleted
    public static void taskDeleted(Task task) {
        lineFormatter("The following Task is removed from the TaskList!!\n" + "[" + task.getStatusIcon() + "] "
                        + task.getTask() + "\n" +
                        "You have " + taskList.size() + " tasks left!"
                );
    }

    // method to check for int in String
    public static boolean isNum(String num){
        try{
            int check = Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    //method for formatting inputs into the taskList
    public static void newTaskItem (Task task, String type){
        lineFormatter("Now you have a new " + type + "! :\n" + task.toString() +
                "\nYou currently have " + taskList.size() + " events in your list\n" +
                "Type \'list\' to check your Tasklist");
    }

    private static void createTasks() {
        try {
            File createFile = new File("/data/duke.txt");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // method to update and save the txt file with the taskList
    private static void saveTasks(){
        String directory = System.getProperty("user.dir");
        Path path =  Paths.get(directory, "data");
        try{
            // Check if the file path exists, if not, create a new directory
            if(!Files.exists(path)){
                Files.createDirectories(path);
            }

            Path filePath = Paths.get(directory,"data","taskList.txt");
            File taskFile = filePath.toFile();

            // Check if the file exists, if not, create a new file
            if(!taskFile.exists()){
                taskFile.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(taskFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < taskList.size(); i++) {
                bufferedWriter.write(taskList.get(i).getOriginal());
                bufferedWriter.write("~");
                if(taskList.get(i).getStatus()){
                    bufferedWriter.write("1");
                } else{
                    bufferedWriter.write("0");
                }
                bufferedWriter.write("-");
            }
            //System.out.println(SAVED);
            bufferedWriter.close();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    // method to load the existing file and update the taskList
    private static void loadTasks() {
        String directory = System.getProperty("user.dir");
        Path filePath = Paths.get(directory, "data", "taskList.txt");
        if(filePath.toFile().exists()){
            try{
                BufferedReader reader = Files.newBufferedReader(filePath);
                String data = reader.readLine();
                if(data != null) {
                    String[] tasks = data.split("-");
                    for (int i = 0; i < tasks.length; i++) {
                        //split the individual tasks
                        String[] doneList = tasks[i].split("~", 2);
                        String[] nameList= doneList[0].split(" ", 2);
                        if (nameList[0].trim().toLowerCase().equals(deadline_key)) {
                            String[] task_deadline = nameList[1].trim().split("/by", 2);
                            Task newTask = new Deadline(task_deadline[0].trim(),
                                        task_deadline[1].trim(), checkDone(doneList[1]));
                            taskList.add(newTask);

                        } else if (nameList[0].trim().toLowerCase().equals(event_key)) {
                            String[] task_event = nameList[1].trim().split("/at", 2);
                            Task newTask = new Event(task_event[0].trim(), task_event[1].trim(),checkDone(doneList[1]));
                            taskList.add(newTask);

                        } else if (nameList[0].toLowerCase().equals(todo_key)) {
                            Task newTask = new ToDo(nameList[1].trim(), checkDone(doneList[1]));
                            taskList.add(newTask);
                        }
                    }
                }

            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
    // check if the task was done before
    private static boolean checkDone (String checker){
        return checker.equals("1");
    }


}
