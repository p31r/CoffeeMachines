# CoffeeMachines test project

Simple project as hiring task.

## Task
Your task is to develop a monitoring application "The Caffeine Manager" which allows its users to manage and monitor their caffeine intoxication and helps them to administer their caffeine level. It does not need to have any UI, implementing the API only is sufficient. We are looking for reusable code. You are strongly encouraged to engineer this to display your software architecture and designing skills. Assume that this abstract application will be the first component of a large scale application.

## Specification:
Each POST/PUT request accepts a JSON object with keys described below

### Codes
Each request returns a JSON object with

- status 200 on success
- status 4xx with an optional JSON error object with mandatory keys
    - error_code
    - error_text

## API definition

___

**PUT /user/request**

Creates a new user.

### Arguments:
- login - mandatory, unique
- password - mandatory
- email - mandatory, unique

### Returns:
- id of the user

___

POST /machine

Registers a coffee machine.

### Arguments:
- name
- caffeine - mg per cup

### Returns:
- id of the coffee machine

___

**PUT /coffee/buy/:user-id/:machine-id**

Registers a coffee bought by the user at a current time

___

**PUT /coffee/buy/:user-id/:machine**

Registers a coffee bought by the user at a given time

### Arguments:
- timestamp - iso-8601 timestamp

___

**GET /stats/coffee**

**GET /stats/coffee/machine/:id**

**GET /stats/coffee/user/:id**

Returns history of user's transactions per user/machine or global

### Returns:
- list of objects, containing
    - machine
    - user 
    - timestamp
    
___

**GET /stats/level/user/:id**

Returns the history of the user's caffeine level. Let’s assume that caffeine level increases linearly from 0 to 100% in first hour and it is reduced linearly afterwards by half every 5 hour

###Returns:
- Return list of levels for past 24 hour using 1h resolution