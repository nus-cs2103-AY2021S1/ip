import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeBot {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void createDirectory(String dirName){
        File file = new File(dirName);

        if(!file.exists()){
            file.mkdir();
        }

    }

    public void createToDo(String fileName){
        File file = new File(fileName);

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToFile(Task myTask,int todoNum){

        createToDo("ToDo/item"+todoNum+".txt");

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ToDo/item"+todoNum+".txt"));
            out.writeObject(myTask);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task readFromFile(String fileDir){
        Task myTask = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileDir));
            myTask = (Task) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return myTask;

    }

    public void updateDirectory(){
        // deleting all files in directory
        File dir = new File("ToDo");
        File[] myItems = dir.listFiles();
        for (File child : myItems) {
            if(child.toString().substring(0,9).equals("ToDo/item")){
                Path path = FileSystems.getDefault().getPath(child.toString());
                try {
                    Files.delete(path);
                } catch (NoSuchFileException x) {
                    System.err.format("%s: no such" + " file or directory%n", path);
                } catch (IOException x) {
                    System.err.println(x);
                }
            }
        }

        // repopulating directory with that in arraylist taks
        for(int i = 0;i<tasks.size();i++){
            writeToFile(tasks.get(i),i);
        }
    }

    public void list(){
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("" + (i + 1)+"." + this.tasks.get(i));
        }
    }

    public void populateList(){
        File dir = new File("ToDo");
        File[] myItems = dir.listFiles();
        for (File child : myItems) {

            if(child.toString().substring(0,9).equals("ToDo/item")){
                tasks.add(readFromFile(child.toString()));
            }
            // Do something with child
        }


    }




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

    public void addTask( String type,String task) throws InSuffArgsException{

        if(task.equals("")){
            throw new InSuffArgsException();
        }

        Task myTask = new Task(type,task);
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
                    addTask("[D]",task ,fullArg[1]);
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
                list();
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
