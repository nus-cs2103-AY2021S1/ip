import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException, DukeException {

        ArrayList<Task> myList = new ArrayList<>();

        File taskFile = new File("duke.txt");

        if(!taskFile.exists()){

        }else{

            Scanner List = new Scanner(taskFile);

            while(List.hasNextLine()){
                String task = List.nextLine();
                if(task.charAt(1) == 'T') {

                    myList.add(new ToDo("todo "+task.substring(7)));

                }else if(task.charAt(1) == 'D'){

                    myList.add(new Deadline("deadline "+task.substring(7)));

                }else if(task.charAt(1) == 'E') {

                    myList.add(new Event("event "+task.substring(7)));

                }

            }

        }



        Scanner scan = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
//        System.out.println(
//                "_______________________________"+
//                        "Hello! I'm Duke\n"+
//                        "What can I do for you?\n"+
//                        "_______________________________"
//        );
        String input = scan.nextLine();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < myList.size(); i++) {

                    Task currentTask = myList.get(i);

                    System.out.println((i + 1) + ". " + currentTask.toString());
                }
            }else if(input.startsWith("done")){
                int taskNum = Character.getNumericValue(input.charAt(5));
                Task currentTask = myList.get(taskNum-1);

                currentTask.finishTask();

                System.out.println(
                        "_______________________________\n" +
                                "Nice! I've marked this task as done:\n"+
                                currentTask.toString()+"\n"+
                        "_______________________________");

            }else if(input.startsWith("todo")){
                try {
                    ToDo currentTask = new ToDo(input);
                    myList.add(currentTask);

                    System.out.println(
                            "_______________________________\n" +
                                    "Got it. I've added this task:\n" +
                                    currentTask.toString() + "\n" +
                                    "Now you have " + myList.size() + " tasks in the list.\n" +
                                    "_______________________________");
                }catch(DukeException e){
                    System.out.println(e);
                }


            }else if(input.startsWith("deadline")){
                try {
                    Deadline currentTask = new Deadline(input);
                    myList.add(currentTask);

                    System.out.println(
                            "_______________________________\n" +
                                    "Got it. I've added this task:\n" +
                                    currentTask.toString() + "\n" +
                                    "Now you have " + myList.size() + " tasks in the list.\n" +
                                    "_______________________________");
                }catch(DukeException e){
                    System.out.println(e);
                }

            }else if(input.startsWith("event")){
                try {
                    Event currentTask = new Event(input);
                    myList.add(currentTask);


                    System.out.println(
                            "_______________________________\n" +
                                    "Got it. I've added this task:\n" +
                                    currentTask.toString() + "\n" +
                                    "Now you have " + myList.size() + " tasks in the list.\n" +
                                    "_______________________________");
                }catch(DukeException e){
                    System.out.println(e);
                }
            }else if(input.startsWith("delete")){
                int taskNum = Character.getNumericValue(input.charAt(7));
                Task currentTask = myList.get(taskNum-1);
                myList.remove(taskNum);
                System.out.println(
                        "_______________________________\n" +
                                "Noted. I've removed this task:\n" +
                                currentTask.toString()+"\n"+
                                "Now you have "+myList.size()+" tasks in the list.\n"+
                                "_______________________________");


            }else {
//                System.out.println(
//                        "_______________________________\n" +
//                                "added: " + input + "\n" +
//                                "_______________________________");
//                myList.add(new Task(input));
                System.out.println(
                                                "_______________________________\n" +
                                " OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                                "_______________________________");



            }

//            File duke = new File(".\\src\\save\\duke.txt");
//            duke.mkdir();
//            duke.createNewFile();
            PrintWriter file = new PrintWriter("duke.txt", "UTF-8");
            BufferedWriter writer = new BufferedWriter(file);
            for(int i = 0; i<myList.size();i++) {

                writer.write(myList.get(i).toString());
                writer.newLine();

            }
            writer.close();
            input = scan.nextLine();

        }
        System.out.println("_______________________________\n"+
                "Bye. Hope to see you again soon!");

    }
}
