# MI6_Concurrency_project
SPL 2nd project - a system for the MI6 (James Bond) , practicing multiple threads program

***The essence of the assignemnt is to learn how to work with threads.***

## Summary
In this assignment, we have implemented an program which demonstrates a good knowledge of concurrency programming and test driven development (TDD).
Each "agent" / "manager" in the system is a thread which executes his "role" in the system, and thus it involves a lot of communication between the different threads which operates simultaneously.

## The Program logic and Frame story
We have implemented an MI6 force (as described in James Bond series), which has multiple missions, arrive from the intelligence.
Once a mission's time to be issued has arrived, the intelligence sends it to the Message Broker (a singleton).
The Message Broker sends it to an M instance that will be incharge of the mission.
The M instance needs to arrange the agents by using Moneypenny (for agents), and Q (single instance, but not a singleton - as specified in the assignment orders) for a the gadgets repository.
if a mission can be prosecuted, the agents will be sent to the mission (simulated ny calling sleep on the threads), and will be released otherwise.
The time is managed by the Time Service - creates  tick each 0.1 seconds and broadcasts it to all of the other components.

## The program creates 2 new json files:
- diaryOutputFile - Describes the completed missions, and counts the number of total missions (completed and aborted) received by M instances.
- inventoryOutputFile - Lists the gadgets reamained in the inventory at the end of the run. Once a gadget is taken (whether the mission was prosecuted or not) it is not returned to the inventory.

## The MI6Runner input:
<input file> <diary output file name> <inventory output file name>

- the diary and inventory output files will be created if do not exist.
- The and input files are an example of a run.
