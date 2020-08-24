import java.util.Scanner;

public class test {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String echo = sc.nextLine();
        task t = new Todo(echo);
        Deadline d = new Deadline(echo);
        String date = "by: Monday";
        d.addDate(date);
        System.out.println(d.read());
    }
}
