import main.java.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;



public class Duke {

    public static void main(String[] args) throws DukeException, IOException {

        ArrayList<Task> arrList= new ArrayList<>();
        readTasks(arrList);

        Scanner scanner = new Scanner(System.in);

        String greeting =
                "Hello! I'm Duke. What can I do for you?";

        System.out.println(greeting);
        String userinput = "";

        while(!userinput.equals("bye")) {
            System.out.println("Input:");
            userinput = scanner.nextLine();
            //if done
            if (userinput.contains("done")){

                try {
                    if (userinput.length() < 6) {
                        throw new DukeException();
                    }
                    int taskNumber = Integer.parseInt(userinput.substring(5)) - 1;

                    if (taskNumber >= arrList.size()) {
                        throw new DukeArrayException();
                    }
                    Task taskCompleted = arrList.get(taskNumber);
                    taskCompleted.complete = true;
                    System.out.println("Nice! I've marked this task as done:\n" + "[✓] " + taskCompleted.task);
                }catch(DukeArrayException e){
                    System.out.println("Number cannot be longer than list.");
                }catch(DukeException e) {
                    System.out.println("Must include number after 'done'.");
                }
            } else if (userinput.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < arrList.size(); i++) {
                        System.out.println((i+1)+"."+arrList.get(i).stringify());
                    }
                    //add todo
            }else if(userinput.contains("delete")) {
                try {
                    if (userinput.length() <= 6) {
                        throw new DukeException();
                    }
                    int taskNumber = Integer.parseInt(userinput.substring(7)) - 1;
                    if (taskNumber > arrList.size()) {
                        throw new DukeArrayException();
                    }
                    Task taskDeleted = arrList.get(taskNumber);
                    arrList.remove(taskNumber);
                    System.out.println("I have removed the task:\n" + taskDeleted.stringify() + "\n" + "Now you have " +
                            arrList.size() + " tasks in the list.");
                } catch (DukeArrayException e) {
                    System.out.println("Number cannot be longer than the list.");
                } catch (DukeException e) {
                    System.out.println("Must include number after 'delete'");
                } catch (NumberFormatException e) {
                    System.out.println("Must include number after 'delete'");
                }
            } else if(userinput.contains("todo")){
                    try {
                        if (userinput.length() < 5) {
                            throw new DukeException();
                        }
                        String description = userinput.substring(5);

                        toDo task = new toDo(description, false);
                        arrList.add(task);

                        String reply = "I have added this task:\n"
                                + task.stringify() + "\n"
                                + "Now you have " + arrList.size() + " task(s) in the list.";
                        System.out.println(reply);
                    }catch(DukeException e){
                        System.out.println("Must include description for todo");
                    }

                }
                //add deadline
                else if(userinput.contains("deadline")){
                    try {
                        if (!userinput.contains("/by")) {
                            throw new DukeException();
                        } else {
                            int index = userinput.indexOf("/by");
                            String deadlineDate = userinput.substring(index + 4);
                            Deadline deadline = new Deadline(userinput.substring(9, index), false, deadlineDate);
                            arrList.add(deadline);

                            String reply = "I have added this task:\n"
                                    + deadline.stringify() + "\n"
                                    + "Now you have " + arrList.size() + " task(s) in the list.";
                            System.out.println(reply);
                        }
                    }catch(DukeException e){
                        System.out.println("deadline must include '/by'");
                    }
                }
                //add event
                else if(userinput.contains("event")){
                    try {
                        if (!userinput.contains("/at")) {
                            throw new DukeException();
                        } else {
                            int index = userinput.indexOf("/at");
                            String eventDate = userinput.substring(index + 4);
                            Event event = new Event(userinput.substring(6, index), false, eventDate);
                            arrList.add(event);

                            String reply = "I have added this task:"
                                    + event.stringify() + "\n"
                                    + "Now you have " + arrList.size() + " task(s) in the list.";
                            System.out.println(reply);
                        }
                    }catch(DukeException e){
                        System.out.println("event must include '/at'");
                    }
                }
                else if(!userinput.contains("bye")){
                    System.out.println("Please input:\n"+
                            "1)list - to access the list\n" +
                            "2)todo - to create a todo task\n" +
                            "3)deadline - to create a deadline\n" +
                            "4)event - to schedule an event\n" +
                            "5)done - to mark tasks as done\n" +
                            "6)delete - to delete tasks from the list");
                }
            }
        saveTasks(arrList);
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void readTasks(ArrayList<Task> arr){
        BufferedReader objReader = null;
        try {
            String strCurrentLine;

            objReader = new BufferedReader(new FileReader("duke.txt"));

            while ((strCurrentLine = objReader.readLine()) != null) {
                String taskType = strCurrentLine.substring(1,2);
                boolean taskCompletion = strCurrentLine.contains("✗")?false:true;
                String taskDetails = taskCompletion
                        ? strCurrentLine.substring(strCurrentLine.indexOf("✓")+3)
                        : strCurrentLine.substring(strCurrentLine.indexOf("✗")+3);

                switch (taskType){
                    case "T":
                        arr.add(new toDo(taskDetails,taskCompletion));
                        break;
                    case "D":
                        String deadlineString = taskDetails.substring(0,taskDetails.indexOf("("));
                        String taskDeadline = taskDetails.substring(taskDetails.indexOf("by:")+3,taskDetails.indexOf(")"));
                        arr.add(new Deadline(deadlineString,taskCompletion,taskDeadline));
                        break;
                    case "E":
                        String eventString = taskDetails.substring(0,taskDetails.indexOf("("));
                        String eventDate = taskDetails.substring(taskDetails.indexOf("at:")+3,taskDetails.indexOf(")"));
                        arr.add(new Event(eventString,taskCompletion,eventDate));



                }
            }

        } catch (IOException e) {
//            e.printStackTrace();
        } finally {

            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void saveTasks(ArrayList<Task> arr){
        try {
            // Creates a FileWriter
            FileWriter file = new FileWriter("duke.txt");

            // Creates a BufferedWriter
            BufferedWriter output = new BufferedWriter(file);

            // Writes the string to the file
            for(int i = 0 ; i < arr.size() ; i ++ ) {
                output.write(arr.get(i).stringify()+"\n");
            }
            // Closes the writer
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
