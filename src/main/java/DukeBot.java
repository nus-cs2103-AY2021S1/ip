import java.util.ArrayList;
import java.util.Scanner;

public class DukeBot {

    private ArrayList<String> tasks = new ArrayList<String>();

    public void horizontalRule(){
        System.out.println("____________________________________________________________");
    }

    public void greeting(){
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        horizontalRule();
        System.out.println("Hello from\n" + logo);
        System.out.println("What Can I do for you?");
        horizontalRule();

    }

    public void echoWithExit(String userInput){ // level 1 task
        if(userInput.equals("bye")){
            System.out.println("Bye. Hope to see you again soon!");
        }
        else{
            System.out.println(userInput);

        }
    }

    public void addTask(String task){
        this.tasks.add(task);
        System.out.println("added: "+task);
    }

    public void listener(){
        Scanner fetch = new Scanner(System.in);
        String currInput = fetch.nextLine();

        while (true){
            if(currInput.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(currInput.equals("list")){
                System.out.println(this.tasks);
            }
            else{
                addTask(currInput);
            }

            currInput=fetch.nextLine();
        }


    }




}
