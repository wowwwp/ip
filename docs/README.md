# Ella User Guide

Introducing Ella, a CLI application for managing all your tasks.
Let's get started!

> 📄 Note 
>
> Words in CAPS are parameters to be filled in by the user.
> 
> DATE should be in dd-MM-yyyy HHmm format for it to be parsed properly.
> 
> INDEX should be valid task number, it should be between 1 and the number of tasks in your list.
## View existing tasks: `list`
Lists all existing tasks including task status and dates associated with the task

Format: `list`

## Adding todo: `todo`
Adds a todo to the list of tasks. These are tasks which do not have any date
associated to it.

Format: `todo TASK DESCRIPTION`

Example: `todo read books`


## Adding deadlines: `deadlines`

Adds a deadline to the list of tasks. This task must have a date associated to it.

Format: `deadline TASK DESCRIPTION /by DATE`

Example:
`deadline cw1 /by 20/10/2022 1400`

## Adding events: `event`

Adds an event to the list of tasks. These are tasks which have a clear start and end date.

Format: `event TASK DECRIPTION /from DATE /to DATE`

Example: `event watch movie /from 20/10/2024 1400 /to 20/10/2024 1600`

## Marking tasks: `mark`

Marks task as done. 

Format: `mark INDEX`

Example: `mark 3`

## Unmarking tasks: `unmark`

Unmarks tasks which are done to not done

Format: `unmark INDEX`

Example: `unmark 4`

## Delete task: `delete`

Deletes tasks from the task list.

Format: `delete INDEX`

Example: `delete 4`

## Finding tasks: `find`

Lists all tasks containing a phrase/word.

Format: `list WORD`

Example: `list books`
* Lists all tasks with task descriptions containing the word books

## Arrange tasks: `arrange`
Arranges all tasks which are not done by their date, earlier dates followed by later
dates.

Format: `arrange`

## Exiting the application: `bye`
Exits the application. 

Format: `bye`

## Saving the data
Ella saves all your tasks after each update in a JSON file. These tasks will be  preserved through
multiple sessions as well. 

## Editing the JSON file
You may directly add in your tasks to the JSON file in the appropriate format. The JSON file is located
in the following path `./data/tasks.json`

> ⚠️ **Caution:** Be careful with this step.
> 
> Modifying this file incorrectly might lead to all your tasks getting overwritten.
> It is recommended not to do this unless your absolutely certain you will do so correctly.





