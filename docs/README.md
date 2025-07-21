
## Request
- make the workday, holiday oncall order
  - order is can be different between wordkay and holiday
- worker must be in each workday and holiday order one time 
- cannot work continuous
- if worker need to work continuous because of order, change the order to next worker

name
- unique
- max 5 length
worker
- max 35 people

## Input
- read month and start day
  - if wrong input -> read month and start day again
- End of February is 28
- if wrong input of workday order or holiday order -> read from workday order again 
- if wrong input, print error start with [ERROR]


## Output
- if day is both of weekday and holiday -> print (휴일)
