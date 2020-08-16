import java.util.ArrayList;
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

    public void listener(){
        Scanner fetch = new Scanner(System.in);
        String currInput = fetch.nextLine();

        while (true){
            horizontalRule();
            String[] args= currInput.split(" ");
            if(currInput.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                horizontalRule();
                break;
            }

            else if(args[0].equals("todo")){
                String task = "";
                for(int i = 1;i<args.length;i++){
                    task+=args[i]+" ";
                }
                addTask(task,"[T]");
            }
            else if(args[0].equals("deadline")){}
            else if(args[0].equals("event")){}

            else if(currInput.equals("list")){
                for (int i = 0;i< this.tasks.size();i++){
                    String checkBox = "[✗]";
                    if(this.tasksDone.get(i)){
                        checkBox = "[✓]";
                    }
                    System.out.println("" + (i+1) + "." + this.taskstype.get(i) +checkBox + " "+ this.tasks.get(i));
                }
            }
            else if(args[0].equals("done")){
                int index = Integer.parseInt(args[1])-1;
                this.tasksDone.set(index,true);
                System.out.println("Nice I've marked this tasks as done");
                System.out.println( "[✓] "+ this.tasks.get(index));
            }
            else{
                addTask(currInput,"[T]");
            }
            horizontalRule();

            currInput=fetch.nextLine();
        }


    }




}
