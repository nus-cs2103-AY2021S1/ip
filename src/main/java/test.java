import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws IOException {

//        Scanner sc = new Scanner(System.in);
//        String echo = sc.nextLine();
//        task t = new Todo(echo);
//        Deadline d = new Deadline(echo);
//        String date = "by: Monday";
//        d.addDate(date);
//        System.out.println(d.read());
//        try {
//            FileWriter myWriter = new FileWriter("filename.txt");
//            myWriter.write("Files in Java might be tricky, but it is fun enough!");
//            myWriter.close();
//            System.out.println("Successfully wrote to the file.");
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }

        String fileDir = "./DukeTodoSave.txt";
        File save = new File(fileDir);
//        if(!save.exists()){ // create the text file
//            System.out.println("Save file does not exist, creating it now!");
//            try{
//                save.createNewFile();
//            } catch(IOException e){
//                System.out.println("Error creating the save file!");
//                System.out.println(e);
//            }
//        }
//        else{
//            Scanner sc = new Scanner(save);
//            while(sc.hasNext()){
//                System.out.println(sc.nextLine());
//            }
//        FileWriter fw = new FileWriter(fileDir);
//        fw.write("test1");
//        fw.write("test2");
//        fw.close();
        ArrayList<task> store = new ArrayList<>();
        FileManager.read(save,store);
        for(task i:store){
            System.out.println(i.read());
        }
    }
}
