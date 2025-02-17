# Ella User Guide

Introducing Ella, a CLI application for managing all your tasks.
Let's get started!

> 📄 **Note** 
>
> * Words in CAPS are parameters to be filled in by the user.
>
> * DATE should be in dd/MM/yyyy HHmm (20/10/2023 1600) or dd/MM/yyyy h.mma (20/10/2023 4.00PM) format for it to be 
> parsed properly. AM/PM must be in caps
> * DATE which is not valid would be corrected to the nearest valid date eg 31/06/2002 would be corrected to 30/06/2002
> 
> * INDEX should be valid task number, it should be between 1 and the number of tasks in your list.

## View existing tasks: `list`
Lists all existing tasks including task status and dates associated with the task.

Format: `list`

## Adding todo: `todo`
Adds a todo to the list of tasks. These are tasks which do not have any date
associated with it.

Format: `todo TASK DESCRIPTION`

Example: `todo read books`


## Adding deadlines: `deadlines`

Adds a deadline to the list of tasks. These are tasks with an end date.

Format: `deadline TASK DESCRIPTION /by DATE`

Example:
`deadline cw1 /by 20/10/2022 1400`

## Adding events: `event`

Adds an event to the list of tasks. These are tasks which have a clear start and end date.

Format: `event TASK DECRIPTION /from DATE /to DATE`

Example: `event watch movie /from 20/10/2024 1400 /to 20/10/2024 1600`

> 📄 **Note**
> 
> The end date must occur after the start date

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

Format: `find WORD`

Example: `find books`
* Lists all tasks with task descriptions containing the word books

## Arrange tasks: `arrange`
Arranges all tasks which are not done by their date in ascending order.

Format: `arrange`

## Exiting the application: `bye`
Exits the application. 

Format: `bye`

## Saving the data
Ella saves all tasks in a JSON file after each update, preserving them across multiple sessions.

## Editing the JSON file
You may directly add in your tasks to the JSON file in the appropriate format. The JSON file is located
in the following path `./data/tasks.json`

> ⚠️ **Caution:** Be careful with this step.
> 
> Modifying this file incorrectly might lead to all your tasks getting overwritten.
> It is recommended not to do this unless your absolutely certain you will do so correctly.
 
**Sample Task formats**:

1) ToDo

```
{"type": "ToDo","completed": false,"name": "see stars","dates": []}
```

2) Deadline

```
{"type": "Deadline","completed": true,"name": "nap ","dates": ["2025-12-12T13:00"]}
```

3. Event

```
{"type": "Event","completed": true,"name": "watch movie ","dates": ["2022-10-13T14:00","2022-10-14T16:00"]}
```

**Organising Tasks in the file**
```
[
  {
    "type": "ToDo",
    "completed": false,
    "name": "read books",
    "dates": []
  },
  {
    "type": "Deadline",
    "completed": true,
    "name": "assignment 1 ",
    "dates": [
      "2025-10-12T00:00"
    ]
  },
  {
    "type": "Event",
    "completed": false,
    "name": "watch movie ",
    "dates": [
      "2025-10-21T16:00",
      "2025-10-21T19:00"
    ]
  }
]
```






