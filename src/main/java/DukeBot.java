import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class DukeBot {

    private ArrayList<String> tasks = new ArrayList<String>();
    private ArrayList<String> taskstype = new ArrayList<String>();
    private ArrayList<Boolean> tasksDone = new ArrayList<Boolean>();

    public void horizontalRule(){
        System.out.println("____________________________________________________________");
    }

    public void greeting(){
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
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

    public void addTask(String task,String type){
        this.tasks.add(task);
        this.taskstype.add(type);
        this.tasksDone.add(false);
        System.out.println("added: "+task);
    }

    public void numTask(){
        int done=0;
        int undone=0;
        for(int i = 0;i<tasksDone.size();i++){
            if(tasksDone.get(i)){
                done++;
            }
            else {
                undone++;
            }
        }
        System.out.println(done+ " finished tasks in the list.");
        System.out.println(undone+ " unfinished tasks in the list.");
    }

    public void listener() {
        Scanner fetch = new Scanner(System.in);
        String currInput = fetch.nextLine();

        while (true){
            horizontalRule();
            String[] fullArg= currInput.split("/");
            String[] args1= fullArg[0].split(" ");



            if(currInput.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                horizontalRule();
                break;
            }

            if((args1[0].equals("todo") || args1[0].equals("deadline")||args1[0].equals("event")) && args1.length==1){
                System.out.println("☹ OOPS!!! The description of a "+ args1[0] +" cannot be empty.");
            }

            else if(args1[0].equals("todo")){
                String task = "";
                for(int i = 1;i<args1.length;i++){
                    task+=args1[i]+" ";
                }
                addTask(task,"[T]");
                numTask();
            }
            else if(args1[0].equals("deadline")){
                String task = "";
                for(int i = 1;i<args1.length;i++){
                    task+=args1[i]+" ";
                }
                addTask(task+"--- "+ fullArg[1],"[D]");
                numTask();

            }
            else if(args1[0].equals("event") ){

                String task = "";
                for(int i = 1;i<args1.length;i++){
                    task+=args1[i]+" ";
                }
                addTask(task+"--- "+ fullArg[1],"[E]");
                numTask();

            }

            else if(currInput.equals("list")){
                for (int i = 0;i< this.tasks.size();i++){
                    String checkBox = "[✗]";
                    if(this.tasksDone.get(i)){
                        checkBox = "[✓]";
                    }
                    System.out.println("" + (i+1) + "." + this.taskstype.get(i) +checkBox + " "+ this.tasks.get(i));
                }
            }
            else if(args1[0].equals("done")){
                int index = Integer.parseInt(args1[1])-1;
                this.tasksDone.set(index,true);
                System.out.println("Nice I've marked this tasks as done");
                System.out.println( "[✓] "+ this.tasks.get(index));
            }
            else{
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            horizontalRule();

            currInput=fetch.nextLine();
        }


    }




}
