Introduction:

This is the example solution of Task 3 from Exercise 3. It follows the C/D model of the example Solution of Task 3 from Exercise 2.
It is by no means the optimal or only solution to the task and you should always try to implement your own C/D model.

The example solution was implemented to showcase different solutions and concepts that can be used when working with EJB or general component systems.
The Concepts are as follows:
Customer Component:     Date storage with database and Entity Beans; Interface with self-defined data objects.
Order Component:     	In-Memory storage with Stateful Beans; Interface with self-defined data objects and data copies.
Stock Component:    	In-Memory storage with Stateful Beans; Interface with general data objects (String encoded data).

Please note that in production you should, not use In-Memory databases but only persistent storage with Entity Beans.

Setup:

1. Follow the tutorial mentioned in Task 3a) of Exercise 3. You need to complete this step to run the example implementation.
2. Start the application server in Eclipse.
3. Import the 2 projects into your Eclipse workspace.

4. Deploy the EJB Modul on the server by (Right-clicking on the PrintingShopCRM project)->Run As->Run on Server
5. Check in the server list of Eclipse that you find your server with the PrintingShopCRM module both "Started".

6. Add the JBoss-client.jar to the TestClient projects build path. This is described in the tutorial of Footnote 7 in Task 3b) of Exercise 3.
7. Run the TestClient project as "Java Application". The output of the test scenarios should appear in the eclipse console.

Troubleshooting:

1. Please look at the JavaDoc of the project´s classes. Problem sources have comments for troubleshooting attached.
2. You can always send an E-Mail to "Markus.Hamann1@tu-dresden.de" or ask Questions in the OPAL forum of the course.