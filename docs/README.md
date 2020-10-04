# User Guide

## Features 
The following is a summarized table of features for Duke.

| Name of feature | Description of feature |
| --------------- | ---------------------- |
| List            | Displays all added tasks |
| Add 'todo'      | Adds a Todo into your task list |
| Add 'deadline'  | Adds a Deadline into your task list |
| Add 'event'     | Adds an Event into your task list |
| Mark as 'done'  | Ticks the checkbox to indicate a completed task corresponding to index |
| 'delete' index  | Deletes task that matches the index |
| 'printbydate'   | Displays all Deadline and Event tasks based on entered date |
| 'find' keyword(s) | Displays all tasks according to the entered keyword(s) |

Duke also has other commands such as 'help' which displays all available commands and 'bye' which saves the task list into a text file. Moreover, it is able to load task list that was saved under persistent storage.

__Note__: All the available commands are case-insensitive

### Booting up Duke
When starting the program, Duke will first look for a file with the name `tasklist.txt` and load the contents of task list, if any.
#### The outcome if text file exists
```
Found tasklist.txt, contents have been read. 
```
#### The outcome if text file does not exist
```
File tasklist.txt is not found, creating new file.......
```

### Adding a task into your list
There are 3 types of tasks namely `todo`, `deadline` and `event`. The following table demonstrates how you can utilize these commands.

| Task type | Entry format | Example of entry |
| --------- | ------------ | ---------------- |
| Todo      | `todo` _[Task description]_ | `todo Read coding tutorial` |
| Deadline  | `deadline`_[Task description]_ /by _[DD/MM/YYYY Time(24-hour format)]_ | `deadline Return book /by 19/09/2020 1400` |
| Event     | `Event`_[Task description]_ /at _[DD/MM/YYYY Time(24-hour format)]_ | `event Meeting with friends /at 20/09/2020 1100` |

__Note__: When the format entered is wrong, the corresponding messages will be printed to inform the user about the mistake. Use `help` command to look at the correct examples.
Expected outcome looks like this when all the commands are executed correctly:
```
__________________________________________
todo something
Got it. I've added this task:
  [T][✘] something
Now you have 1 tasks in the list
__________________________________________
event NUS open house /at 20/09/2020 1000
Got it. I've added this task:
  [E][✘] NUS open house(at: 20 Sep 2020 10:00am)
Now you have 2 tasks in the list
__________________________________________
deadline assignment submission /by 25/09/2020 1500
Got it. I've added this task:
  [D][✘] assignment submission(by: 25 Sep 2020 3:00pm)
Now you have 3 tasks in the list
__________________________________________
```

### Printing your task list
Enter the command `list` to print out the entire task list and the expected outcome is as such:
```
__________________________________________
1. [T][✘] something
2. [E][✘] NUS open house(at: 20 Sep 2020 10:00am)
3. [D][✘] assignment submission(by: 25 Sep 2020 3:00pm)
__________________________________________
```

### Deleting a task from your list
Enter the command `delete` followed by the index of the task to be deleted.

__Command Format__: `delete` _[Task index]_

Before deleting, Duke will look for the index corresponding to the task. If the index is out of bounds or the format is wrong, an error message will be shown. 
Otherwise the specified task will be deleted from your list.

__Example__: `delete 1` removes the first task in your list.
```
__________________________________________
delete 1
Noted. I've removed this task:
  [T][✘] something
Now you have 2 tasks in the list.
__________________________________________
```

### Finding a task in your list
Enter the command `find` followed by at least a keyword to search for all the tasks containing the keyword(s).

__Command Format__: `find` _[Keyword(s)]_

__Example__: `find submission` looks for task(s) containing the word 'submission'.
```
__________________________________________
find submission
Here's what we have found: 
1. [D][✘] assignment submission(by: 25 Sep 2020 3:00pm)
__________________________________________
```

### Marking a task as done
Enter the command `done` followed by the index of the task to be marked as completed.

__Command Format__: `done` _[Task index]_

Before marking, Duke will look for the index corresponding to the task. If the index is out od bounds or the format is wrong, an error message will be shown.
Otherwise the specified task will be marked as done in your list.

__Example__: `done 2` marks the second task in your list as done.
```
__________________________________________
done 2
__________________________________________
Nice! I've marked this as done:
  [D][✓] assignment submission(by: 25 Sep 2020 3:00pm)
__________________________________________
```

### Printing tasks according to specific date and/or time
Enter the command `printbydate` followed by the desired date and/or time. 

__Command Format__: `printbydate` _[d MMM yyyy h:ma]_

Take note that the date and time does not have to be in the full form as this method checks if the task(s) contain(s) the string entered.

__Example__: `printbydate 20 Sep` prints all deadlines and events with due dates containing '20 Sep'
```
__________________________________________
printbydate 20 Sep
Here's what we have found: 
1. [E][✘] NUS open house(at: 20 Sep 2020 10:00am)
__________________________________________
```

### Exiting the program
To exit the program and save the task list, use the `bye` command. Following which, the task list will be saved into the file `tasklist.txt`.
Below is the expected outcome:
```
__________________________________________
bye
Saving to file.........
__________________________________________
Bye. Hope to see you again soon!
__________________________________________
```

## End of user guide
