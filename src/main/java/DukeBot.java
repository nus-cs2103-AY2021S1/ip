import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeBot {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void horizontalRule() {
        System.out.println("____________________________________________________________");
    }

    public void greeting() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


    }

    public void echoWithExit(String userInput) { // level 1 task
        if (userInput.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else {
            System.out.println(userInput);

        }
    }
    public void addTask(String type, String task) throws InSuffArgsException{

        if(task.equals("")){
            throw new InSuffArgsException();
        }

        Task myTask = new Task(type,task);
        this.tasks.add(myTask);
        System.out.println("added: " + myTask);
    }

    public void addTask(String type, String task, LocalDate d1) throws InSuffArgsException{

        if(task.equals("")){
            throw new InSuffArgsException();
        }

        Task myTask = new Task(type,task,d1);
        this.tasks.add(myTask);
        System.out.println("added: " + myTask);
    }
    public void addTask(String type,String task,String deadLine) throws InSuffArgsException{

        if(task.equals("")){
            throw new InSuffArgsException();
        }

        Task myTask = new Task(type,task,deadLine);
        this.tasks.add(myTask);
        System.out.println("added: " + myTask);
    }

    public void deleteTask(int index) {
        Task myTask = this.tasks.get(index);
        this.tasks.remove(index);
        System.out.println("removed: " + myTask);
        numTask();
    }

    public void numTask() {
        int done = 0;
        int undone = 0;
        for (int i = 0; i < this.tasks.size(); i++) {
            boolean finished = tasks.get(i).finished();
            if (finished) {
                done++;
            } else {
                undone++;
            }
        }
        System.out.println(done + " finished tasks in the list.");
        System.out.println(undone + " unfinished tasks in the list.");
    }

    public void listener() {
        Scanner fetch = new Scanner(System.in);
        String currInput = fetch.nextLine();

        while (true) {
            horizontalRule();
            String[] fullArg = currInput.split("/");
            String[] args1 = fullArg[0].split(" ");


            if (currInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                horizontalRule();
                break;
            }

          if (args1[0].equals("todo")) {
                String task = "";
                for (int i = 1; i < args1.length; i++) {
                    task += args1[i] + " ";
                }
                try {
                    addTask("[T]",task);
                }
                catch (InSuffArgsException E){
                    System.out.println("☹ OOPS!!! The description of a " + args1[0] + " cannot be empty.");
                }
                numTask();
            } else if (args1[0].equals("delete")) {
                deleteTask(Integer.parseInt(args1[1]) - 1);
            } else if (args1[0].equals("deadline")) {
                String task = "";
                for (int i = 1; i < args1.length; i++) {
                    task += args1[i] + " ";
                }
                try {
                    if(fullArg.length==1){
                        throw new InSuffArgsException();
                    }
                    if(fullArg.length==4){
                        String day = Integer.parseInt(fullArg[1].substring(fullArg[1].length()-1))+"";
                        if(day.length()==1){
                            day= "0"+day;
                        }
                        String month = Integer.parseInt(fullArg[2])+"";
                        if(month.length()==1){
                            month= "0"+month;
                        }
                        int year = Integer.parseInt(fullArg[3].split(" ")[0]);
                        //int time = Integer.parseInt(fullArg[3].split(" ")[1]);

                        //System.out.println(time);

                        LocalDate d1 = LocalDate.parse(year+"-"+month+"-"+day);
                        addTask("[D]",task,d1);

                    }else{
                        addTask("[D]",task ,fullArg[1]);
                    }

                }
                catch (InSuffArgsException E){
                    System.out.println("☹ OOPS!!! The description of a " + args1[0] + " cannot be empty.");
                }

                numTask();

            } else if (args1[0].equals("event")) {

                String task = "";
                for (int i = 1; i < args1.length; i++) {
                    task += args1[i] + " ";
                }
                try {
                    if(fullArg.length==1){
                        throw new InSuffArgsException();
                    }
                    addTask("[E]",task ,fullArg[1]);
                }
                catch (InSuffArgsException E){
                    System.out.println("☹ OOPS!!! The description of a " + args1[0] + " cannot be empty.");
                }
                numTask();

            } else if (currInput.equals("list")) {
                for (int i = 0; i < this.tasks.size(); i++) {
                    System.out.println("" + (i + 1)+"." + this.tasks.get(i));
                }
            } else if (args1[0].equals("done")) {
                int index = Integer.parseInt(args1[1]) - 1;
                this.tasks.get(index).setDone();
                System.out.println("Nice I've marked this tasks as done");
                System.out.println( this.tasks.get(index));
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            horizontalRule();

            currInput = fetch.nextLine();
        }


    }


}
