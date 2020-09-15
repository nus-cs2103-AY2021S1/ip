# User Guide
Duke is a desktop application for tasks management that is best suited for fast-typists, who prefer to do tasks management entirely using Command Line Interface (CLI). 
This application also allows user to use Graphical User Interface (GUI).

## Features 
#### There are 3 different types of task that you can put into the list, namely are: ```todo```, ```deadline``` and ```event```.
0. Load existing tasks when application launches, if any.
1. Add todo: ```todo```
2. Add deadline: ```deadline```
3. Add event: ```event```
4. Mark task as done: ```done```
5. List all tasks: ```list```
6. Delete task: ```delete```
7. Search keyword in list: ```find```
8. Show statistics: ```stats```
9. Exit the application: ```bye```

### Feature 0: Load existing tasks
Duke application will automatically saves your tasks after each command and loads up everytime you enter the application.
An example would be as shown in the image below, where the user has existing tasks that is stored in the list previously.

![Feature0](https://raw.githubusercontent.com/Wincenttjoi/ip/master/docs/feature0.JPG)

### Feature 1: Add todo
Adds a todo task to be stored in the list.
Type ```todo <task description>``` in the textbox as provided, and click Send or press Enter button.

Example usage: 
```
todo go to the gym
```

![Feature1a](https://raw.githubusercontent.com/Wincenttjoi/ip/master/docs/feature1a.JPG)

You application should register the new todo list and should look like this:
![Feature1b](https://raw.githubusercontent.com/Wincenttjoi/ip/master/docs/feature1b.JPG)

### Feature 2: Add deadline
Adds a deadline task to be stored in the list.

Example usage:
```
deadline <description> /by <date in yyyy-mm-dd format>
Eg: deadline submit google project PR /by 2020-11-11
```

Expected outcome:

![Feature2](https://raw.githubusercontent.com/Wincenttjoi/ip/master/docs/feature2.JPG)

### Feature 3: Adds event
Adds an event task to be stored in the list.

Example usage:
```
event <description> /at <date in yyyy-mm-dd format>
Eg: event shopee webinar /at 2020-10-10
```

Expected outcome:

![Feature3](https://raw.githubusercontent.com/Wincenttjoi/ip/master/docs/feature3.JPG)

### Feature 4: Mark task as done
Mark a task in the list as done. Ensure that number you input is not out of range.

Example usage:
```
done <number of item in list>
Eg: done 2
```

Expected outcome:

![Feature4](https://raw.githubusercontent.com/Wincenttjoi/ip/master/docs/feature4.JPG)

### Feature 5: List all tasks
List all the existing tasks.

Example usage:
```
list
```
Expected outcome:

![Feature5](https://raw.githubusercontent.com/Wincenttjoi/ip/master/docs/feature5.JPG)

### Feature 6: Delete task
Delete a specific task in the list.
Example usage:
```
delete <number of item in list>
Eg: delete 2
```
Expected outcome:

![Feature6](https://raw.githubusercontent.com/Wincenttjoi/ip/master/docs/feature6.JPG)

### Feature 7: Search keyword in list
You can search for a particular keyword in the list. Duke will return you that all the tasks that contain the keyword entered.
Example usage:
```
find <description>
Eg: find gym
```
Expected outcome:

![Feature7](https://raw.githubusercontent.com/Wincenttjoi/ip/master/docs/feature7.JPG)

### Feature 8: Show statistics
You can either show the total number of items in the list, or just the items that are marked as done.
Example usage:
```
stats total

stats done
```

![Feature8](https://raw.githubusercontent.com/Wincenttjoi/ip/master/docs/feature8.JPG)

### Feature 9: Exit application
Command would be ```bye```, or you can click on the X of the window to exit the application. Click yes to confirm exit.

![Feature9](https://raw.githubusercontent.com/Wincenttjoi/ip/master/docs/feature9.JPG)
