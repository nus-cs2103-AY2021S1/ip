import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>();

        Scanner Sc = new Scanner(System.in);
        String line = "____________________________\n"
                +"____________________________";

        String logo = "****** ****** ****** ******\n"
                +"   *   *      *      *\n"
                +"   *   ****** ****** ******\n"
                +"*  *   *      *      *\n"
                +"***    ****** *      *\n";

        System.out.println("My name is\n" + logo);
        System.out.println("What do you want?");
        System.out.println(line);

        //noinspection InfiniteLoopStatement
        while(true){
            String[] inputs = Sc.nextLine().trim().split(" ",2);
            String command = inputs[0];
            String taskDescription = "";
            if(inputs.length > 1) {
                taskDescription = inputs[1];
            }

            switch(command){
                case "done" : {
                    int index = Integer.parseInt(taskDescription) - 1;
                    Task doneTask = tasks.get(index);
                    doneTask.complete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(String.format("  %s", doneTask.toString()));
                    break;
                }
                case "list" : {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(String.format("%d. %s", i + 1, tasks.get(i).toString()));
                    }
                    break;
                }
                case "bye" : {
                    System.out.println("Bye, Have a Great Time!");
                    break;
                }
                case "todo" : {
                    Task newTask = new Todo(taskDescription);
                    tasks.add(newTask);
                    newTask.printAddTask();
                    printNumberOfTask(tasks.size());
                    break;
                }
                case "deadline" : {
                    Task newTask = new Deadline(taskDescription.split(" /")[0]
                            ,taskDescription.split(" /")[1]);
                    tasks.add(newTask);
                    newTask.printAddTask();
                    printNumberOfTask(tasks.size());
                    break;
                }
                case "event" : {
                    Task newTask = new Event(taskDescription.split(" /")[0]
                            ,taskDescription.split(" /")[1]);
                    tasks.add(newTask);
                    newTask.printAddTask();
                    printNumberOfTask(tasks.size());
                    break;
                }
                case "delete" : {
                    int index = Integer.parseInt(taskDescription) - 1;
                    tasks.get(index).printDeleteTask();
                    tasks.remove(index);
                    printNumberOfTask(tasks.size());
                    break;
                }
                default : {
                    System.out.println("Please enter a valid command.");
                    break;
                }
            }
        }
    }

    static void printNumberOfTask(int i){
        System.out.println(String.format("Now you have %d tasks in the list.",i));
    }

}