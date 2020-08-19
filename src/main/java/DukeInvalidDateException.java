package main.java;

public class DukeInvalidDateException extends DukeException{
    public DukeInvalidDateException(){
        super("Date is invalid, date cannot be left blank :(");
    }
}
