# cs56-games-checkers

Ryan Kroner, W12

New version of checkers that includes the previous version of code reviews.
As well as fixes some of the issues in command line version of the game

Also will include: A GUI that allows two players to play checkers against each other, also will include ability to get a "king" to move around freely.

Original: Basic classes for Checkers, Non-GUI. Plus a J-Unit Test similar to Lab 07 from S11:

F17 Final Remarks:

Current version contains working command line and gui version.
The version we started with was very poorly written and organized. Some refactoring was done but there is much more opportunity for refactoring. 
It would be helpful for a new piece/move class to be added that would remove some of the code smell from checkersGame class.
Double jumping should be added and kings should be improved a little. 
The next major endeavour that could be attempted would be to create a basic ai so the player could play the computer.

We reccomend that you start by fixing state storage (create move class and store possible moves in an ArrayList of move objects). From there it should be easy to resolve the jump and double jump issues. 
