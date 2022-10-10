## Mars Rover Kata

### Your Task

You’re part of the team that explores Mars by sending remotely controlled vehicles to the surface of the planet. Develop
an API that translates the commands sent from earth to instructions that are understood by the rover.

### Requirements

- You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
- The rover receives a character array of commands.
- Implement commands that move the rover forward/backward (f,b).
- Implement commands that turn the rover left/right (l,r).
- Implement wrapping at edges. But be careful, planets are spheres.
- Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle,
  the rover moves up to the last possible point, aborts the sequence and reports the obstacle.

### Rules

- Hardcore TDD. No Excuses!
- Change roles (driver, navigator) after each TDD cycle. 
- No red phases while refactoring. 
- Be careful about edge cases and exceptions. We can not afford to lose a mars rover, just because the developers overlooked a null pointer.


### Implementation
I am going to build this out as an event-driven application. The concept being that the communication between the rover and the engineers driving the rover would not have instant communication and there would be a long delay between the commands being sent and the rover receiving them. It is also good practice as I can implement a messaging queue with Kafka or a similar framework.

For fun and depending on how far I get with this I could then make it into a (boring) web app with realtime mars communication as an event-driven microservice architecture application. Users enter the commands into the web app that pass the instructions onto the rover controller. The rover controller then sends an event to a rover command topic that a rover message handler listens for (add a fake delay based on the actual current time taken to communicate with mars at the given time). The rover then moves as instructed and sends an event back when it is done, a handler recieves this topic and moves the rover on the web app as the user instructed.