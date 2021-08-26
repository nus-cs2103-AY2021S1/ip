package duke;

/**
 * Duke is a chatbot that takes in tasks, which includes todos, deadlines and events, and compiles them
 * in a list. Tasks can be removed from the list and be marked as completed.
 *
 * @author Dominic Siew Zhen Yu
 *
 */

import java.io.*;
import java.util.Scanner;

public class Duke {

    public enum Tasks {
        DEADLINE,
        EVENT,
        TODO
    }

    public static void main(String[] args) throws IOException {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\nHello! I'm Duke\nWhat can I do for you?\n____________________________________________________________");

        boolean isEnded = false;
        List taskList = null;
        taskList = new List();

        File file = new File("src/main/memory.txt");

        if (!(file.exists())){
            System.out.println("processing!");
            try {
                PrintWriter writer = new PrintWriter("src/main/memory.txt");
                writer.print("");
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            File updated = new File("src/main/updated.txt");
            BufferedReader reader = new BufferedReader(new FileReader("src/main/memory.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(updated));
            String currentLine = "";
            int numberAdded = 1;



            while ((currentLine = reader.readLine()) != null) {
                String[] seperate1 = currentLine.toString().split(" ", 2);
                String taskTypeText = seperate1[0];
                String taskType;

                String[] seperate2 = seperate1[1].toString().split(" ", 2);
                boolean isCompleted = (seperate2[0].equals("[✓]"))? true: false;
                String eventInfo = seperate2[1];

                switch(taskTypeText) {
                    case "[T]":
                        String eventname = eventInfo;
                        taskList.addTodo(eventname, false);
                        break;
                    case "[D]":
                        String[] seperateAgain = seperate2[1].split(" \\(by: ", 2);
                        String eventName = seperateAgain[0];
                        String deadline = seperateAgain[1].split(" ", 2)[0];
                        taskList.addDeadline(eventName, deadline, false);
                        break;
                    case "[E]":
                        String[] seperateAgaining = seperate2[1].split(" \\(at: ", 2);
                        String event = seperateAgaining[0];
                        String timeline = seperateAgaining[1].split(" ", 2)[0];
                        taskList.addEvent(event, timeline, false);
                        break;
                }

                if (isCompleted){
                    taskList.updateTaskStatus(numberAdded, false);
                }
                numberAdded++;
            }

        }




        Scanner sc= new Scanner(System.in);

        while(!isEnded) {
            String userInput = sc.nextLine();
            System.out.println("____________________________________________________________");
            String[] parsedInput = userInput.split(" ", 2);


            if (parsedInput[0].equals("todo")) {
                if (parsedInput.length == 1 ) {
                    throw new DukeException("empty");
                }
                taskList.addTodo(parsedInput[1], true);

            } else if (parsedInput[0].equals("deadline")) {
                String[] furtherParsed = parsedInput[1].split(" /by ", 2);
                taskList.addDeadline(furtherParsed[0], furtherParsed[1], true);
            } else if (parsedInput[0].equals("event")){
                String[] furtherParsed = parsedInput[1].split(" /at ",2);
                taskList.addEvent(furtherParsed[0],furtherParsed[1], true);
            } else if(userInput.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                isEnded = true;
            } else if (userInput.equals("list")){
                taskList.printList();
            } else if (parsedInput[0].equals("done")) {
                int itemRank = Integer.parseInt(parsedInput[1]);
                taskList.updateTaskStatus(itemRank, true);

            } else if (parsedInput[0].equals("remove")){
                int itemRank = Integer.parseInt(parsedInput[1]);
                taskList.removeTask(itemRank - 1 );
            } else {
                System.out.println("Please give an appropriate response.");
                throw new DukeException("generic");
            }
            System.out.println("____________________________________________________________");
        }


    }
}
