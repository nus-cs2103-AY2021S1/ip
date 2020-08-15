import java.util.NoSuchElementException;
import java.util.Scanner;

public class checker {
    public static int inputChecker(String echo) {
        int isDone = 0;
        Scanner sc = new Scanner(echo);
        String first = sc.next();
        if(first.equals("done")){
            try{
                String index = sc.next();
                try{
                    int I = Integer.parseInt(index);
                    isDone = I;
                }
                catch(NumberFormatException e){ // next word is not an integer
                    System.out.println("Error 2: Not a complete 'Done' command, added as a task!");
                }
            }
            catch(NoSuchElementException e){
                System.out.println("Error 1: Not a complete 'Done' command, added as a task!");
            }
        }
        return isDone;
    }
}
