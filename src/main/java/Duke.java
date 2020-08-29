import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Duke {
    //for pr creation
    // A-trial run
    // program to be added
    // Find out the position of the first '/'
    public int firstSlashTracker (String string){
        int location = 0;
        boolean exist = false;
        for (int i = 0; i < string.length(); i++){
            if(string.charAt(i) == '/'){
                exist = true;
                location = i;
                break;
            }
        }
        if(exist) {
            return location;
        }else {
            return -1;
        }
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        String order = sc.nextLine();
        ArrayList<Task> orders = new ArrayList<>();

        //take in and process the order
        while (!order.equals("bye")) {
            var correct = true;
            var deleted = false;
            var done = order.length() >= 4 && order.substring(0 , 4).equals("done");
            var delete = order.length() >= 6 && order.substring(0 , 6).equals("delete");
            var todo = order.length() >= 4 && order.substring(0 , 4).equals("todo");
            var deadline = order.length() >= 8  && order.substring(0, 8).equals("deadline");
            var event = order.length() >= 5 && order.substring(0, 5).equals("event");
            if (order.equals("food left")) {
                System.out.println("Ka To:");
                System.out.println(" ");
                System.out.println("Only an apple pie");
            } else if (order.equals("allowance left")) {
                System.out.println("Ka To:");
                System.out.println(" ");
                System.out.println("I have checked, it is 20000 SGD");
            } else if (order.equals("who is the most handsome guy")) {
                System.out.println("Ka To:");
                System.out.println(" ");
                System.out.println("Of Course You!");
            } else if (order.equals("task list")) {
                System.out.println("Ka To:");
                System.out.println(" ");
                if(orders.size() == 0){
                    System.out.println(" You have not given me any task yet ");
                }else {
                    for (int i = 0; i < orders.size(); i++) {
                        if(orders.get(i).status == 0) {
                            if(orders.get(i).category == 0){
                                System.out.println(i + 1 + ". " + " [T][x] "+ orders.get(i).command);
                            }else if(orders.get(i).category == 1){
                                System.out.println(i + 1 + ". " + " [D][x] " + orders.get(i).command);
                            }else if(orders.get(i).category == 2){
                                System.out.println(i + 1 + ". " + " [E][x] " + orders.get(i).command);
                            }else {
                                System.out.println(i + 1 + ". " + " [x] " + orders.get(i).command);
                            }
                        }else {
                            if(orders.get(i).category == 0){
                                System.out.println(i + 1 + ". " + " [T][v] "+ orders.get(i).command);
                            }else if(orders.get(i).category == 1){
                                System.out.println(i + 1 + ". " + " [D][v] " + orders.get(i).command);
                            }else if(orders.get(i).category == 2){
                                System.out.println(i + 1 + ". " + " [E][v] " + orders.get(i).command);
                            }else {
                                System.out.println(i + 1 + ". " + " [v] " + orders.get(i).command);
                            }
                        }
                    }
                }
            }else if (done) {
                int number = Character.getNumericValue(order.charAt(5));
                if(number > 0) {
                    orders.get(number - 1).status = 1;
                }
                System.out.println("Ka To:");
                System.out.println(" ");
                System.out.println("Wow, this is now solved! " + " [v] " +  orders.get(number - 1).command);
            }else if (delete) {

                int number = Character.getNumericValue(order.charAt(7));
                if(number > 0) {
                    if(orders.get(number - 1).status == 0){
                        System.out.println("Ka To:");
                        System.out.println(" ");
                        System.out.println("Noted, this is now deleted! " + " [x] " +  orders.get(number - 1).command);
                    }else{
                        System.out.println("Ka To:");
                        System.out.println(" ");
                        System.out.println("Noted, this is now deleted! " + " [v] " +  orders.get(number - 1).command);
                    }
                    orders.remove(number - 1);
                    deleted = true;
                }

                ;
            }else if(todo){
                if(order.length() > 4){
                    System.out.println("Ka To:");
                    System.out.println();
                    System.out.println("Yup, this is now added! " + " [T][x] " +  order.substring(4));
                    int size = orders.size() + 1;
                    System.out.println(" You have " + size + " task added now! ");
                }
            }
            else if(deadline){
                if(order.length() > 8){
                    System.out.println("Ka To:");
                    System.out.println();
                    int number = firstSlashTracker(order);
                    System.out.println("Yup, this is now added! " + " [D][x] " +  order.substring(8, number) + "(by: " +
                            order.substring(number + 3) + ")");
                    int size = orders.size() + 1;
                    System.out.println(" You have " + size + " task added now! ");
                }
            }
            else if(event){
                if(order.length() > 5){
                    System.out.println("Ka To:");
                    System.out.println();
                    int number = firstSlashTracker(order);
                    System.out.println(" Yup, this is now added! " + " [E][x] " +  order.substring(5, number) + "(at: " +
                            order.substring(number + 3) + ")");
                    int size = orders.size() + 1;
                    System.out.println(" You have " + size + " task added now! ");
                }
            }
            else {
                correct = false;
                System.out.println("Ka To:");
                System.out.println(" ");
                System.out.println("Sorry, I could not answer that");
            }
            if(correct && !done && !deleted && !order.equals("task list")) {
                if(todo){
                    if(order.length() <= 4){
                        System.out.println("oops, the todo description cannot be empty");
                    }else {
                        Task item = new Task(0, 0, order.substring(4) );
                        orders.add(item);
                    }
                }else if(deadline){
                    if(order.length() <= 8){
                        System.out.println("oops, the deadline description cannot be empty");
                    }else {
                        int number = firstSlashTracker(order);
                        Task item = new Task(1, 0,order.substring(8, number) + "(by: " +
                                order.substring(number + 3) + ")");
                        orders.add(item);
                    }
                }else if(event){
                    if(order.length() <= 5){
                        System.out.println("oops, the event description cannot be empty");
                    }else {
                        int number = firstSlashTracker(order);
                        Task item = new Task(2, 0, order.substring(5, number) + "(at: " +
                                order.substring(number + 3) + ")");
                        orders.add(item);
                    }

                }else {
                    Task item = new Task(4, 0, order);
                    orders.add(item);
                }

            }
            order = sc.nextLine();
        }
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("I am happy to serve you. See you soon!");
    }

    public static void main(String[] args) {
        String filePath = "./data/duke.txt";

        try {
            File myFile = new File(filePath);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Creating the file to save");
            try {
                String folderPath = "./data";
                Path path = Paths.get(folderPath);

                Files.createDirectories(path);

                System.out.println("Directory is created!");

                //Files.createDirectory(path);

            } catch (IOException error) {
                System.err.println("Failed to create directory!" + error.getMessage());
            }
            try {
                File fileCreator = new File(filePath);
                if (fileCreator.createNewFile()) {
                    System.out.println("File created: " + fileCreator.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException ex) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        try {
            File myFile = new File(filePath);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Creating the file to save");
        }

        try {
            FileWriter myWriter = new FileWriter(filePath);
            //myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("All changes successfully saved to the storage!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println( "____________________________________________________________");
        System.out.println("Hello :))!  I'm your daily manager, Ka To! Welcome Back!");
        System.out.println("how could I serve you now? ");
        System.out.println("You could ask me any question if you like!");
        System.out.println( "____________________________________________________________");
        Duke manager = new Duke();
        manager.run();
    }

}
