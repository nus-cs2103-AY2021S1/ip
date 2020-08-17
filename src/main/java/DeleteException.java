package main.java;

public class DeleteException extends DukeException {

    @Override
    public String toString(){
        return super.toString() + " You have to specify which number in the list to be deleted";
    }
}
