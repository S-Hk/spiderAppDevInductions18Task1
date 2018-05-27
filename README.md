# spiderAppDevInductions18Task1
#### Task 1 for Spider App Dev Inductions 2018: The Rubik’s cube timer

### TASK 1: The Rubik’s cube timer
#### Basic
Solving a rubik’s cube for the first time has always been a moment of pride. Even for those who know
how to solve would feel the magic of how the pieces move on the cube. But it takes extreme practice
to master it and reduce solving times to record times. Seeing one such video of a record solve, you
get inspired to master this skill. As an app developer, you think of implementing an app to help you in
this endeavour.
Hence your task is to implement a Rubik’s cube timer. The working of this timer is a little different
than your regular timer. It will have the following features:
- Inspection time: Once the screen is tapped, the countdown timer begins from 15 seconds(the
standard inspection time) to 0.
- Solve time: Once the inspection time is completed, a stopwatch is started measuring the time
taken to solve the cube. This is a stopwatch which will begin from 0 and increase until it is
stopped by a screen tap. This will show the actual solve time.
The timer clock will start from 15:00( secs : millisecs ) and end at 00:00. As soon as the inspection
timer reaches 00:00, the solve time stopwatch should begin from 00:00:00 ( mins:secs:millisecs )
and go on until the screen is tapped again.
#### Hacker-mode
The hacker mode task will be as follows:
- If the screen is tapped before the inspection time is over, the timer will freeze with that present
time, and the stopwatch will begin from that instance itself.
- Sound a small beeping tone after 12 secs of inspection time as an indication of it coming to an
end.
