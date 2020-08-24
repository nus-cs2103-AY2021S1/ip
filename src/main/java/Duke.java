import main.java.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {

    private Storage storage;
    private TaskList tasklist;
    //private Ui ui;

    public Duke (String filePath){
        this.storage = new Storage(filePath);
        this.tasklist = new TaskList(storage.arr);
    }

    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke("duke.txt");
        ArrayList<Task> arrList = duke.tasklist.list;

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
                    Task taskCompleted = (Task) arrList.get(taskNumber);
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
                duke.tasklist.deleteTask(userinput);
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
                            String deadlineDateString = userinput.substring(index + 4);
                            System.out.println(deadlineDateString);
                            LocalDate deadlineDate = LocalDate.parse(deadlineDateString);
                            Deadline deadline = new Deadline(userinput.substring(9, index), false, deadlineDate);
                            arrList.add(deadline);

                            String reply = "I have added this task:\n"
                                    + deadline.stringify() + "\n"
                                    + "Now you have " + arrList.size() + " task(s) in the list.";
                            System.out.println(reply);
                        }
                    }catch(DateTimeParseException e){
                        System.out.println("Please input date in the format: YYYY-MM-DD");
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
                            String eventDateString = userinput.substring(index + 4);
                            LocalDate eventDate = LocalDate.parse(eventDateString);
                            Event event = new Event(userinput.substring(6, index), false, eventDate);
                            arrList.add(event);

                            String reply = "I have added this task:"
                                    + event.stringify() + "\n"
                                    + "Now you have " + arrList.size() + " task(s) in the list.";
                            System.out.println(reply);
                        }
                    }catch(DateTimeParseException e){
                        System.out.println("Please input date in the format: YYYY-MM-DD");
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
        duke.storage.saveTasks(arrList);
        System.out.println("Bye. Hope to see you again soon!");
    }


}
