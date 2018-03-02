Fuheng Zhao, Runyu Gao

a. In this checkers project, we are going to simulate two players playing checker game with each other. Potentially, we will add an AI so that the player can play by him/herself. We are also going to fix some bug and make this checker game more fun.

b. At current state, there is a gui and command line version of this game in some poor condition. 

  As a user, I can play checker game with my firend at gui so that I can gain friendship.

  As a user, I can play checker game with my firend at command line so that I can improve my skills.

c. It can run, and there are two options command line and gui. In command line you have two use coordinates to move checkers. In gui, you can click the checker to play with yourself or a friend.

d. As a user, I can play checker game with computer so that I can practice with the AI.

  As a user, I can play checker game with better graphics so that I can have a better experience.
  
e. I think current readme contains most of the information and is well written. However, I think it can create some bullet points so that it would be easier to comprehend. I need to tell the reader how to play this game (command line and gui versions) and I am going to add where we are going next in this project.

f. It is in good shape but there is some old targets needed to be cleaned up. It has clear descriptions.

g. Yes, this project has enough issues to earn 1000 points.

h. No issues to be added at this stage.

i. I think most of code are in good condition. The build.xml and readme need to be updated. There are nine classes, CheckersBoard, CheckersComponent, CheckersGame, CheckersGUI, CheckersIllegalMoveException, CheckersTest, JTextAreaMessageDestination, MessageDestination, and TextCheckers.

   CheckerGame is a interface which contains most important methods to make this game work.
   CheckersBoard implements the CheckerGame and has some its own instances variable to serve the game   TextChecker contains the main function to play the commandline version of this game.
   CheckersComponent implements Jcomponent for GUI to run.
   CheckersGUI has the main function to run the GUI version of this game.
I think we need to add one more class (cell/pieces class) to make each piece an object of this class,so that it would be easier to add new features and fix bugs. 

j. The test code covered most of the logical issues for the command line versions and nothing for the gui version. We could add some test for double jump, retract history, etc. I think we also need to test the gui version too. 
