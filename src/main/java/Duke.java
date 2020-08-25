import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {
        String filePath = "data/duke.txt";
        String fileName = "duke.txt";
        String dirPath = "data";

        try {
            FileReading.printFileContents(filePath);
        } catch (FileNotFoundException e) { // if the required file/path directory is not yet created
            System.out.println("Creating the storage file...");

            // creating new directory(s)
            File parent = new File("dirPath");
            if (!parent.mkdirs()) {
                System.err.println("Could not create parent directories ");
            } try { //creating the new file
                File newFile = new File(parent, fileName);
                boolean b = newFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                FileReading.printFileContents(filePath);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        String line = "____________________________________________________________\n";
        String greeting = "Hello! I'm Duke from the chat bot universe ~ \n" +
                          "Nice to meet you! \n" +
                          "I'll be your task manager from now onwards.\n";
        String bye = "Awwww, I guess you are gonna leave... \n" +
                "I'll keep track of your tasks nicely. \n" +
                "Text me if you wanna talk again! Have a nice day!\n";

        System.out.println(line + greeting + line);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String s = "";
        String[] array = new String[1];
        Task t = new Task("");
        int count = 0;

        while (sc.hasNextLine()) {
            String temp = sc.nextLine();
            InvalidInputException ex = new InvalidInputException("");
            //String[] pieces = temp.split("\\s+");
            String [] pieces = temp.split(" ", 2);
            if (temp.equals("bye")) {
                System.out.println(line + bye + line);
                return;
            } else if (temp.equals("list")) {
                if (list.isEmpty()) {
                    ex = new InvalidInputException("Oops, your list is currently empty. Add some tasks first!");
                    System.err.println(line + ex.getMessage() + "\n" + line);
                } else {
                    System.out.println(line + "Here are the tasks in your list: \n");
                    for (int i = 1; i < count + 1; i++) {
                        Task cur = list.get(i - 1);
                        System.out.println("" + i + "." + "[" + cur.getType() + "][" + cur.getStatusIcon() + "] " + cur);
                    }
                    System.out.println(line);
                }


            } else if (pieces[0].equals("done")){
                if (pieces.length == 1) {
                    ex = new InvalidInputException("Hey, you forgot to tell me which task is done!");
                    System.err.println(line + ex.getMessage() + "\n" + line);
                } else {
                    int task = Integer.parseInt(pieces[1]);
                    if (list.get(task-1) == null) {
                        ex = new InvalidInputException("Oops, this task has not been created yet!");
                        System.err.println(line + ex.getMessage() + "\n" + line);
                    } else {
                        Task cur = list.get(task - 1);
                        cur.markAsDone();
                        System.out.println(line);
                        System.out.println("Nice! I've marked this task as done: \n" +
                                "[" + cur.getStatusIcon() + "] " + cur);
                        System.out.println(line);
                    }
                }
            }  else if (pieces[0].equals("delete")){
            if (pieces.length == 1) {
                ex = new InvalidInputException("Hey, you forgot to tell me which task to delete");
                System.err.println(line + ex.getMessage() + "\n" + line);
            } else {
                int num = Integer.valueOf(pieces[1]);
                if (list.get(num-1) == null) {
                    ex = new InvalidInputException("Oops, this task has not been created yet!");
                    System.err.println(line + ex.getMessage() + "\n" + line);
                } else {
                    Task removed = list.get(num-1);
                    count --;

                    s = "[" + removed.getType() + "][\u2718] " + removed;
                    System.out.println(line);
                    System.out.println("Got it. I've removed this task: \n" + s);
                    System.out.println("Now you have " + count + " tasks in the list. ");
                    System.out.println(line);
                }
            }
        }else {
                if (pieces.length == 1) {
                    switch (pieces[0]) {
                        case "todo":
                            ex = new InvalidInputException("Heyy, you forget the description for your todo!");
                            break;

                        case "deadline":
                            ex = new InvalidInputException("Heyy, you forget the description for your deadline!");
                            break;

                        case "event":
                            ex = new InvalidInputException("Heyy, you forget the description for your event!");
                            break;

                        default:
                            ex = new InvalidInputException("Ah oh! I didn't know what that means >n<, sorry! ");
                            break;
                    }
                    System.err.println(line + ex.getMessage() + "\n" + line);
                } else {
                    switch (pieces[0]) {
                        case "todo":
                            t = new ToDo(pieces[1]);
                            s = "[T][\u2718] " + t;
                            break;

                        case "deadline":
                            array = pieces[1].split("/by");
                            if (array.length == 1) {
                                ex = new InvalidInputException("Sorry, but I can't help if you don't tell me the exact deadline!");
                                System.err.println(line + ex.getMessage() + "\n" + line);
                            } else {
                                t = new Deadline(array[0], array[1]);
                                s = "[D][\u2718] " + t;
                            }
                            break;


                        case "event":
                            array = pieces[1].split("/at");
                            if (array.length == 1) {
                                ex = new InvalidInputException("I see...But what time is this event at?");
                                System.err.println(line + ex.getMessage() + "\n" + line);
                            } else {
                                t = new Deadline(array[0], array[1]);
                                s = "[E][\u2718] " + t;
                            }

                            break;

                        default:
                            break;
                    }
                    if (t.description != "") {
                        list.add(t);
                        count++;
                        System.out.println(line);
                        System.out.println("Got it. I've added this task: \n" + s);
                        System.out.println("Now you have " + count + " tasks in the list. ");
                        System.out.println(line);
                    }
                }
            }

        }
        String output = "";
        for (int i = 1; i < count + 1; i++) {
            Task cur = list.get(i - 1);
            String currentTask = "" + i + "." + "[" + cur.getType() + "][" + cur.getStatusIcon() + "] " + cur + "\n";
            output = output + currentTask;
        }
        try {
            FileWriting.writeToFile(filePath, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

