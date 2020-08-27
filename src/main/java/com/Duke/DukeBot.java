package com.Duke;

import com.Duke.DataManager.Storage;
import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;
import com.Duke.Tasks.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DukeBot{
    //This class handles the simulation of the Duke chat bot
    public static void simulate(){
        TaskList ls = new TaskList(Storage.read());
        boolean hasBye = false;
        String input;
        String[] splitList;

        String line = "     ___________________________________________________________________";

        UI.dukeInit();
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();


        while(!hasBye){
            splitList = input.split(" ", 2);
            if(input.equals("bye")){
                try {
                    UI.byeCalled(ls);
                    hasBye = true;
                }catch (DukeException e){
                    UI.printError(e.toString());
                    input = sc.nextLine();
                }
            }else if(input.equals("list")){
                try {
                    UI.listCalled(ls);
                }catch(DukeException e){
                    UI.printError(e.toString());
                }finally {
                    input = sc.nextLine();
                }
            }else if(splitList[0].equals("done")){
                try{
                    UI.doneCalled(ls,Integer.parseInt(splitList[1]) - 1);
                }catch (DukeException e){
                    UI.printError(e.toString());
                }catch(Exception e){
                    UI.printError("     ☹ OOPS!!! There arent that many tasks in your list");
                }finally {
                    input = sc.nextLine();
                }
            }else if(splitList[0].equals("todo")){
                try {
                    ToDo todo = new ToDo(splitList[1], false);
                    UI.toDoCalled(ls,todo);
                }catch (Exception e){
                    UI.printError("     \u2639 OOPS!!! The description of a todo cannot be empty.");
                }finally {
                    input = sc.nextLine();
                }
            }else if(splitList[0].equals("deadline")){
                try {
                    String[] splitList2 = splitList[1].split("/by ", 2);
                    Deadline deadline = new Deadline(splitList2[0], LocalDate.parse(splitList2[1]), false);
                    UI.deadlineCalled(ls,deadline);
                } catch (DateTimeParseException e){
                    UI.printError("     \u2639 OOPS!!! The deadline is not of the proper format, make sure you enter it as YYYY-MM-dd");
                } catch (Exception e){
                    UI.printError("     \u2639 OOPS!!! The description is empty or you have not entered a proper deadline.");
                }finally {
                    input = sc.nextLine();
                }
            }else if(splitList[0].equals("event")){
                try {
                    String[] splitList2 = splitList[1].split("/at ", 2);
                    String[] splitList3 = splitList2[1].split("-", 2);
                    Event event = new Event(splitList2[0], LocalTime.parse(splitList3[0]), LocalTime.parse(splitList3[1]), false);
                    UI.eventCalled(ls,event);
                }catch(DateTimeParseException e){
                    UI.printError("     \u2639 OOPS!!! The format of your start or end time is not correct, format it as HH:mm");
                } catch(Exception e){
                    UI.printError("     \u2639 OOPS!!! The description or the time duration of a event cannot be empty.");
                }finally {
                    input = sc.nextLine();
                }
            }else if(input.equals("blah")){
                try{
                    UI.blahCalled();
                }catch (DukeException e){
                    UI.printError(e.toString());
                }finally {
                    input = sc.nextLine();
                }
            }else if(splitList[0].equals("delete")){
                try{
                    ls.delete(Integer.parseInt(splitList[1]));
                    UI.deleteCalled(ls);
                }catch(DukeException e){
                    UI.printError(e.toString());
                }finally{
                    input = sc.nextLine();
                }
            }else{
                try {
                    Task task = new Task(input, false);
                    UI.taskCalled(ls,task);
                }catch(DukeException e){
                    UI.printError(e.toString());
                }finally {
                    input = sc.nextLine();
                }
            }
        }
    }
}
