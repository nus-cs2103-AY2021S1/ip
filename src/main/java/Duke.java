import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String longLine = "________________________________________________________________________________";
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        lineFormatter("Hello!!!! I'm Duke \nWhat can I do for you?!?!?!" );
        add_input();

    }

    public static void echo_input(){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String input = scanner.nextLine();
            if(input.toLowerCase().equals("bye")){
                byeGreetings();
                break;
            } else {
                lineFormatter(input);
            }
        }
    }


    public static void add_input(){
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            String input = scanner.nextLine();
            //splitting into list for easier comparison
            String[] inputList = input.trim().split(" ", 2);

            if(inputList[0].toLowerCase().equals("list")){
                StringBuffer result = new StringBuffer();
                //to add in the starting line of the section
                result.append("Here are the tasks in your list: \n");

                for(int i = 0; i < taskList.size(); i ++){
                    // getting the current task
                    Task currentTask = taskList.get(i);

                    // adding the current task into the tasklist
                    result.append((i + 1)+ ". [" + currentTask.getStatusIcon() + "] " + currentTask.getTask() + "\n");
                }
                lineFormatter(result.toString());

                //ending the process
            } else if(inputList[0].toLowerCase().equals("bye")){
                byeGreetings();
                break;

                //checking for done
            } else if(inputList[0].toLowerCase().equals("done") && isNum(inputList[1])){
                int currentIndex = Integer.parseInt(inputList[1]) - 1;
                if(currentIndex + 1> taskList.size() || currentIndex + 1 <= 0){
                    lineFormatter("You do not have " + (currentIndex + 1) + " tasks!\n"
                            + "Choose a number less than equals to " + taskList.size() + "!");
                } else {
                    Task task = taskList.get(currentIndex);
                    // to check if the task is already done
                    if(task.getStatus()){
                        lineFormatter("This task is already done!\n" +
                                "[" + task.getStatusIcon() + "] " + task.getTask());
                    // if task is not done
                    } else {
                        taskList.get(currentIndex).markAsDone();
                        taskDone(taskList.get(currentIndex));
                    }
                }

            }
            else {
                added_to_List(input);
            }
        }
    }

    // method that adds tasks into the list of tasks
    public static void added_to_List(String printable) {
        lineFormatter("added: " + printable);
        Task newTask = new Task(printable);
        taskList.add(newTask);


    }

    //method to segment every String that is being fed into this method
    public static void lineFormatter (String printable){
        System.out.println(longLine);
        System.out.println(printable);
        System.out.println(longLine);
    }


    // standardised goodbye greeting
    public static void byeGreetings () {
        lineFormatter("Bye! Hope to see you soon again?!");
    }

    //method to mark tasks as done
    public static void taskDone(Task task) {
        lineFormatter("Nice! This task is getting done!!\n" + "[" + task.getStatusIcon() + "] " + task.getTask());
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


}
