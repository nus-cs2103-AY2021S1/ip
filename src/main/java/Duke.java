//public class Duke {
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
//}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws FileNotFoundException {

        File d = new File("data");
        File f = new File("data/task.txt");
        try {
            if (!d.exists()) {
                if(d.mkdir()) {
                    System.out.println("New data directory created");
                }
            }

            if(f.createNewFile()) {
                System.out.println("new task data file created");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        String welcomeMessage = (
            "____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "____________________________________________________________\n"
        );

        System.out.println(welcomeMessage);



        ArrayList<Task> inputList = new ArrayList<Task>();

        try {
            StringProcessor.readFileContents(f, inputList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println("Loading done");

        Scanner sc = new Scanner(System.in);

        String currInput = sc.nextLine();

        while (!currInput.equals("bye")) {

            String[] splitString = currInput.split(" ");

            if (currInput.equals("list")) {

                System.out.println("____________________________________________________________");
                for (int i = 0; i < inputList.size(); i++) {
                    System.out.println(inputList.get(i).toString());
                }
                System.out.println("____________________________________________________________");

            } else if (splitString[0].equals("done")) {
                // can add error handling exception in case out of bounds
                // can add error handling for exception already done
                int index = Integer.parseInt(splitString[1]);
                inputList.get(index - 1).setStatus(true);

                System.out.println("____________________________________________________________");
                for (int i = 0; i < inputList.size(); i++) {
                    System.out.println(inputList.get(i).toString());
                }
                System.out.println("____________________________________________________________");

            } else if (splitString[0].equals("delete")) {
                // can add error handling exception in case out of bounds
                int index = Integer.parseInt(splitString[1]);
                inputList.remove(index - 1);

                System.out.println("____________________________________________________________");
                for (int i = 0; i < inputList.size(); i++) {
                    System.out.println(inputList.get(i).toString());
                }
                System.out.println("____________________________________________________________");

            }else {
                try {
                    Task currTask = StringProcessor.stringProcessor(currInput);
                    inputList.add(currTask);
                    System.out.println("____________________________________________________________");
                    System.out.println(currTask.toString());
                    System.out.println("____________________________________________________________\n");
                } catch(Exception e) {
                    System.out.println("DUKE DOES NOT UNDERSTAND YOU!!!@#%#$%^!@^%\n" + e + "\nTRY AGAIN!!!");
                }
            }



            currInput = sc.nextLine();
        }

        String endMessage = (
            "____________________________________________________________\n" +
            "     Bye. Hope to see you again soon!\n" +
            "____________________________________________________________"
        );

        try {
            StringProcessor.writeToFile(f, inputList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        System.out.println(endMessage);

        sc.close();

    }
}
