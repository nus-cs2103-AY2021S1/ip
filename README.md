# Alice - Independent Project

[![CI Status](https://github.com/jinhao-l/ip/workflows/Java%20CI/badge.svg)](https://github.com/jinhao-l/ip/actions)

Alice is a chatbot desktop app that manages your tasks for you so that you can focus on the things that really matters to you. 
It comes with a beautiful Graphical User Interface (GUI) and is also optimized for use via a Command Line Interface (CLI).

![ui](./docs/Ui.png)

## Quick Start
1. Ensure that you have the latest Java 11 installed on your computer.
1. Download our latest version of Alice from [here](https://github.com/JinHao-L/ip/releases/tag/v0.3)
1. Copy the file to the folder of your choice. This will be the home folder of Alice.
1. Double-click the file to start the app
   * If the app does not start, you can use the command prompt/terminal to start the app
      ```bash
      java -jar alice.jar
      ```
   * To start Alice as a Command-Line Program, use the following command on the command prompt/terminal:
      ```bash
      java -jar alice.jar -c 
      ```
1. Type the command in the text input field and press Enter to execute.  
Entering `help` will bring out the list of usable commands.  
Here are some example commands that you can try:
    1. `todo setup` : Adds a new task named `setup` to the task list.
    1. `event Tutorial /at now` : Adds a new `Tutorial` event that is happening at the current date and time.
    1. `list` : Lists all tasks.
    1. `done 1` : Marks the first task as done
    1. `delete 2` : Delete the second task
    1. `clear all` : Delete all tasks
    1. `bye` : Exit the app.
6. Refer to the [Usage section in the User Guide](https://jinhao-l.github.io/ip/#usage) to see more details on each command.


## Acknowledgements
* This is a independent greenfield project built using the [Duke](https://github.com/nus-cs2103-AY2021S1/ip) repo as the project template.  
* The project is built for the CS2103T module under the guidance of [Prof. Damith](https://github.com/damithc).
