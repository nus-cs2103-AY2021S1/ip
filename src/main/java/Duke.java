import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Hello from\n" +
                        " ____        _\n" +
                        "|  _ \\ _   _| | _____\n" +
                        "| | | | | | | |/ / _ \\\n" +
                        "| |_| | |_| |   <  __/\n" +
                        "|____/ \\__,_|_|\\_\\___|\n" +
                "____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________\n");
        boolean stop = false;
        Userinput userinput = new Userinput();

        try {
            File f = new File("data/duke.txt");
           
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        while(sc.hasNextLine()){
            try {
                String input = sc.nextLine();
                String output = userinput.getDukeResponse(input);
                System.out.println(output);
                stop = userinput.getTerminate();
                if (stop) {
                    sc.close();
                    break;
                }
            } catch (EmptyInputException ex) {
                System.out.println(ex);
            } catch (NoResponseException excep) {
                System.out.println(excep);
            }
        }

        FileWriter fw = new FileWriter("data/duke.txt",true);
        for (int k = 0; k < userinput.tasks.size(); k++) {
            Task currentTask =  userinput.tasks.get(k);
            String taskAssignment = currentTask.description;
            String taskIsDone = currentTask.isDone ? "1" : "0";
            String taskType = currentTask.status;
            String date = "";
            if (currentTask instanceof Deadline) {
                date = " | " + ((Deadline) currentTask).by;
            } else if (currentTask instanceof Event) {
                date = " | " + ((Event) currentTask).at;
            }

            fw.write("\n" + taskType + " | " + taskIsDone + " | " + taskAssignment);
        }
        fw.close();

    }
}
