import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileManager{

    public static void add(String location, String text) throws IOException {
        FileWriter f = new FileWriter(location,true);
        f.write(text);
        f.write(System.lineSeparator());
        f.close();
    }
    public static void edit(String location, ArrayList<task> store) throws IOException {
        FileWriter fw = new FileWriter(location);
        for(task i : store){
            fw.append(i.read());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
    private static String getName(String s) throws ErrorExceptions {
        Scanner sc = new Scanner(s);
        String name = "";
        try{
            sc.next(); // skip the symbols
            String current = sc.next();
            while(current.charAt(0)!='('){
                name = name + current + " ";
                current = sc.next();
            }
        } catch(NoSuchElementException e){}
        return name;
    }
    private static String getDate(String s) throws ErrorExceptions{
        Scanner sc = new Scanner(s);
        String date = "";
        try{
            sc.next();
            String current = sc.next();
            while(current.charAt(0)!='('){
                sc.next();
            }
            date = current;
        } catch(NoSuchElementException e){}
        return date;
    }
    private static int getType(String s) throws ErrorExceptions{
        Scanner sc = new Scanner(s);
        try{
            String current = sc.next();
            char type = current.charAt(1);
            if(type == 'T'){
                return 1;
            } else if(type == 'D'){
                return 2;
            } else if(type == 'E'){
                return 3;
            } else{
                throw new ErrorExceptions("Error: Wrong item type detected, file might be corrupted!");
            }
        } catch(NoSuchElementException e){
            throw new ErrorExceptions("Failed to load saved file (Type)" + System.lineSeparator()
                    + e);
        }
    }
    private static boolean getDone(String s) throws ErrorExceptions{
        Scanner sc = new Scanner(s);
        try{
            String current = sc.next();
            char done = current.charAt(4);
            if(done == '✓'){
                return true;
            } else if (done == 'X'){
                return false;
            } else{
                throw new ErrorExceptions("Error: Cannot determine if item is done, file might be corrupted!");
            }
        } catch(NoSuchElementException e){
            throw new ErrorExceptions("Failed to load saved file (Completed?)" + System.lineSeparator()
                    + e);
        }
    }
    public static void read(File f, ArrayList<task> store){
        try{
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                String current = sc.nextLine();
//                System.out.println(current);
                try {
                    int type = FileManager.getType(current);
                    String name = FileManager.getName(current);
                    boolean done = FileManager.getDone(current);
                    if(type == 1){
                        Todo t =new Todo(name);
                        t.done();
                        store.add(t);
                    } else if(type == 2){
                        try {
                            String date = FileManager.getDate(current);
                            Deadline d = new Deadline(name);
                            d.done();
                            d.addDate(date);
                            store.add(d);
                        } catch(ErrorExceptions e){
                            System.out.println(e);
                        }
                    } else{
                        try {
                            String date = FileManager.getDate(current);
                            Event e = new Event(name);
                            e.done();
                            e.addDate(date);
                            store.add(e);
                        } catch(ErrorExceptions e){
                            System.out.println(e);
                        }
                    }
                } catch (ErrorExceptions e){
                    System.out.println(e);
                }
            }
        } catch(FileNotFoundException e){
            System.out.println("File does not exist!");
        }
    }
}
