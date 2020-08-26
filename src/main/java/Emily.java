package main.java;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Emily {
    static ArrayList<Task> store = new ArrayList<>(100);

    static public void readData() throws DukeException{

        File f = new File("data/emily.txt");
        f.getParentFile().mkdirs();
        try {
            if (f.exists()) {
                //read data from the text
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String line = sc.nextLine();
                    char type = line.charAt(0);
                    boolean isCompleted = line.charAt(1)=='1';
                    String[] temp;

                    switch(type){
                        case 'T':
                            temp = line.split(",", 2);
                            Task t = new ToDos(temp[1]);
                            t.finished = isCompleted;
                            store.add(t);
                            break;

                    }


                }


            } else {
                f.createNewFile();

            }
        } catch(FileNotFoundException e){
            throw new DukeException("file is not found");
        } catch(IOException e){
            throw new DukeException("file already existed");
        }

    }


    static public void interacting() throws DukeException {

        Task current;

        String divider = "    ---------------";
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();


            if(input.trim().isEmpty()){
                throw new DukeException("input cannot be empty");
            }

            while (!input.equals("bye")) {
                System.out.println(input);


                if (input.equals("list")) {

                    System.out.println(divider);

                    int num = 1;
                    for (Task c: store) {
                        String item = "    " + num + ". " + c;
                        num++;
                        System.out.println(item);
                    }
                    System.out.println(divider);

                } else if (input.contains("done")) {

                    int index = Character.getNumericValue(input.charAt(5)) - 1;
                    current = store.get(index);
                    current.finished = true;

                    String item = "Nice work! I have marked the task as done:\n" +
                            "     " + current;

                    System.out.println(divider +
                            "\n    " + item +
                            "\n" + divider);


                } else if(input.contains("todo") || input.contains("deadline") || input.contains("event")
                || input.contains("delete")) {

                    String shorten = input.trim();

                    //empty description
                    if(shorten.equals("todo") || shorten.equals("deadline") || shorten.equals("event")
                    || shorten.equals("deadline/by") || shorten.equals("event/at")){
                        System.out.println(divider);
                        throw new DukeException("The description of " + input + " cannot be empty");
                    }

                    //deleting task
                    if(shorten.contains("delete")){
                        if(shorten.equals("delete")){
                            throw new DukeException("Missing index");
                        } else{

                            int index = Character.getNumericValue(input.charAt(7)) - 1;

                            if((index+1) > store.size()){
                                throw new DukeException("Invalid index");
                            }

                            current = store.get(index);
                            store.remove(index);

                            System.out.println(divider +
                                    "\n    Got it! I have removed this task: " +
                                    "\n    "+ current);

                            System.out.println("    Now you have " + (store.size()) + " tasks in the list" +
                                    "\n" + divider);
                        }
                    }else { //normal addition

                        Task item = new Task("");
                        String type = "";

                        try {
                                if (input.contains("todo")) {
                                    String describe = input.substring(5);
                                    item = new ToDos(describe);

                                } else if (input.contains("deadline") && input.contains("/by")) {
                                    type = "deadline";
                                    String describe = input.substring(9);
                                    String[] temp = describe.split("/by ");
                                    item = new Deadline(temp[0], temp[1]);
                                } else if (input.contains("event")) {
                                    type = "event";
                                    String describe = input.substring(6);
                                    String[] temp = describe.split("/at ");
                                    item = new Event(temp[0], temp[1]);
                                } else{
                                    throw new DukeException("invalid task");
                                }

                            store.add(item);

                            System.out.println(divider
                                    + "\n    Got it! I have added this task:");
                            System.out.println("        " + item);
                            System.out.println("    Now you have " + (store.size()) + " tasks in the list" +
                                    "\n" + divider);
                        }catch(ArrayIndexOutOfBoundsException e){
                            throw new DukeException("missing or invalid timestamp for " + type + " task");
                        }


                    }
                } else{
                    //invalid input
                    System.out.println(divider);
                    throw new DukeException("I'm not sure what you mean");
                }

                input = sc.nextLine();
            }

    }


    public static void main(String[] args) throws DukeException, IOException {
       /* String divider = "-------------------";
        boolean end = false;


        System.out.println("Hello, I am Emily\n" +
                "What can i do for you?\n"+
                divider);

        while(!end){
            try{
                interacting();
                end = true;
            } catch(DukeException e){
                System.out.println("    OOPS! " + e.getMessage() + "\n" + divider);
            }
        }

        System.out.println("bye\n" + divider + "\nBye~, hope to see you again!");

        */

        readData();
        System.out.println(store.toString());

    }
}
