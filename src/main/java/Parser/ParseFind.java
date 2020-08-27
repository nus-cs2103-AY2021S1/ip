package Parser;

import Command.FindCommand;
import Errors.ErrorExceptions;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ParseFind extends Parse {
    public static void execute(String input) throws ErrorExceptions {
        Scanner sc = new Scanner(input);
        sc.next();
        String name = "";
        try {
            String current = sc.next();
            while (sc.hasNext()) {
                name = name + current + " ";
                current = sc.next();
            }
            name = name + current + " ";
//        System.out.println(name);
            FindCommand.execute(name);
        } catch (NoSuchElementException e){
            throw new ErrorExceptions("Wrong format! Missing name!");
        }
    }
}
