# Feature Requirements

## Input
- [ ] Read the start month and day
  - [ ] throw an exception when the input is invalid
- [ ] Read weekday on-call order of developer nicknames
  - [ ] N: the number of developers, 5 <= N <= 35
  - [ ] Each nickname has to be less than 6 characters
  - [ ] throw an exception when there's duplicate nicknames
- [ ] Read holiday on-call order of developer nicknames
  - [ ] Should be same developers as weekday on-call order
  - [ ] throw an exception when there's duplicate nicknames
- [ ] If the input is invalid, print an error message starting with [ERROR], and prompt for the input again from the relevant step.

## Holiday

- [ ] Official holiday
  * January 1: New Year’s Day
  * March 1: Independence Movement Day
  * May 5: Children’s Day
  * June 6: Memorial Day
  * August 15: Liberation Day
  * October 3: National Foundation Day
  * October 9: Hangeul Day
  * December 25: Christmas Day


## Scheduling
- [ ] Assign on-call days according to the given order.
- [ ] Weekday and holiday on-call orders are different. Holidays include Saturdays, Sundays, and official public holidays.
- [ ] A developer must be assigned only once for each of weekday and holiday schedules.

## Output
- [ ] If a weekday is also a public holiday, append (Holiday) after the weekday.