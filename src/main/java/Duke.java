import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        UserInput userinput = new UserInput();

        String path = "."+File.separator+"data"+File.separator;
        String fileName = "duke.txt";

        File file = new File(path);
        File dukeSave = new File(path + fileName);
        if (!file.exists()) {
            file.mkdirs();
            dukeSave.createNewFile();
        } else {
            if (dukeSave.exists()) {
                userinput.handleFile();
            } else {
                dukeSave.createNewFile();
            }
        }



        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Hello from\n" +
                        " ____        _        \n" +
                        "|  _ \\ _   _| | _____ \n" +
                        "| | | | | | | |/ / _ \\\n" +
                        "| |_| | |_| |   <  __/\n" +
                        "|____/ \\__,_|_|\\_\\___|\n" +
                "____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________\n");
        boolean stop = false;
        while (sc.hasNextLine()) {
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

        FileWriter fw = new FileWriter("data/duke.txt");
        for (int k = 0; k < userinput.tasks.size(); k++) {
            Task currentTask = userinput.tasks.get(k);
            String isDone = currentTask.isDone ? " 1 @ " : " 0 @ ";
            if (currentTask instanceof ToDo) {
                fw.write("T @" + isDone + currentTask.description + System.lineSeparator());
            } else if (currentTask instanceof Deadline) {
                String date = " @ " + ((Deadline) currentTask).by;
                fw.write("D @" + isDone + currentTask.description + date + System.lineSeparator());
            } else if (currentTask instanceof Event) {
                String date = " @ " + ((Event) currentTask).at;
                fw.write("D @" + isDone + currentTask.description + date + System.lineSeparator());
            }

        }
        fw.close();



    }
}
