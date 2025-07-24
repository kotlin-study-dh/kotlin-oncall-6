# Feature Requirements

## Input
- [x] Read the start month and day
  - [x] throw an exception when the input is invalid
- [x] Read weekday on-call order of developer nicknames
  - [x] N: the number of developers, 5 <= N <= 35
  - [x] Each nickname has to be less than 6 characters
  - [x] throw an exception when there's duplicate nicknames
- [x] Read holiday on-call order of developer nicknames
  - [x] Should be same developers as weekday on-call order
  - [x] throw an exception when there's duplicate nicknames
- [x] If the input is invalid, print an error message starting with [ERROR], and prompt for the input again from the relevant step.

## Holiday

- [x] Official holiday
  * January 1: New Year’s Day
  * March 1: Independence Movement Day
  * May 5: Children’s Day
  * June 6: Memorial Day
  * August 15: Liberation Day
  * October 3: National Foundation Day
  * October 9: Hangeul Day
  * December 25: Christmas Day


## Scheduling
- [x] Assign on-call days according to the given order.
- [x] Weekday and holiday on-call orders are different. Holidays include Saturdays, Sundays, and official public holidays.
- [x] A developer must be assigned only once for each of weekday and holiday schedules.

## Output
- [x] If a weekday is also a public holiday, append (Holiday) after the weekday.