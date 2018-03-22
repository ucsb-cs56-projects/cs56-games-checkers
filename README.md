# cs56-games-checkers

Ryan Kroner, W12  
Graham Foster, F17  
Matthew Maatubang, F17

Fuheng Zhao, Runyu Gao W18


New version of checkers that includes the previous version of code reviews.
As well as fixes some of the issues in command line version of the game.
We added a retract history method to return to pervious game state. This feature is added for both gui and command line version, and updates the readme file.


How to play and test:
you can use ant run to play the command line version of the checkers game. In the game you can type the checkers coordinate to move your pieces. 
You can also type retract and then enter number of steps you want to get back, to return to the that state of game.

You can use ant gui to invoke the gui version of the checkers game. Then, you can click the pieces and then click a possible spot to move it. 
There is also a retract button so that you can enter the number of steps you want to get back, to return to that state of game.

To test the code, you can enter ant test.

You can type ant clean to clean the classes. 

You can also type ant compile to see if your changes is compilable.


Also will include: A basic AI and fix some issues in build.xmli (the old version of the build.xml contains many unnecessary targets and codes), and fix some jump, "king", etc. bugs;

Orginal:A GUI that allows two players to play checkers against each other, also will include ability to get a "king" to move around freely from F17.
Basic classes for Checkers, Non-GUI. Plus a J-Unit Test similar to Lab 07 from S11:
F17 Final Remarks:

Current version contains working command line and gui version.
The version we started with was very poorly written and organized. Some refactoring was done but there is much more opportunity for refactoring.
It would be helpful for a new piece/move class to be added that would remove some of the code smell from checkersGame class.
Double jumping should be added and kings should be improved a little.
The next major endeavour that could be attempted would be to create a basic ai so the player could play the computer.

We reccomend that you start by fixing state storage (create move class and store possible moves in an ArrayList of move objects). From there it should be easy to resolve the jump and double jump issues.
                                               


W18 Final Remarks:
The version that we got from F17 also has a lot issues. We reorganized the build.xml file, and fixed some logical bugs. We added a stack to record history and improved the gui messaging.

We added a basic AI. Due to the time limit, the AI is very basic. I believe there is a lot can be done for the AI part, such as saving all AI pieces's location into an arraylist and then find the possible moves for one of these pieces.
So I would suggest that you can start from Indicate Possible Moves (Issue #10)

My recommendation is that you can also get some easy points by improving the gui version, such as adding pictures and make it look better. After that you can fix the jump and double jump issues which I did not touch on.

