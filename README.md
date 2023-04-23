# Trinome_game
2nd year academic project in java

![image](https://user-images.githubusercontent.com/123560349/233864511-4a93c2ea-4323-4cd2-909c-427807a2363c.png)


## Project Objective:
The objective of the project was to code a game with it's graphical interface of a two-player strategy game where the green player faces off against the red player. The game is played over an indefinite number of rounds, with each player taking turns until one of the conditions for winning the game is met by either player. The goal of each player is to bring three different pieces to the three red opponent's spaces or capture all pieces of one opponent's type. The players move one piece per turn and must follow the rules of movement for each type of piece. Each piece has its own movement pattern and different capture possibilities. The pyramids move diagonally one space, the cubes move vertically or horizontally one space, and the half-spheres move vertically, horizontally, or diagonally two spaces but cannot capture any piece.

## Special Variant:

A special variant of the game introduces special pieces, with one special piece per type per player. The special half-spheres can only move backward if no other move is possible. The special pyramids, on the other hand, have the ability to capture a piece by jumping over it in addition to the classic capture. The capture for all pieces is done by replacement, and only pyramids, special pyramids, and cubes are allowed to make captures.
![image](https://user-images.githubusercontent.com/123560349/233865118-be2becba-e2eb-4906-ad0e-492955bb2021.png)

## Description of Classes:
In this program, classes are divided into four categories: Processing, Graphics Display, Error Management, and Entity Management, such as pieces for the game.
### Processing Classes:
The processing classes manage most aspects of the game. Firstly, the main class `Projet_Trinome` is the Java file that will be executed to launch the game. It only serves to open the menu window, which will manage the game. The `Jeu` class is one of the most important classes of the program. It creates instances of the two players, links the processing part to the graphics part, and saves and loads the game.

### Entity Management Classes:
The primary entities are the pieces of each player. Each piece, such as the pyramid, the cube, the hemisphere, and their special variants, have a different type of movement. However, they share certain attributes, such as color. For this reason, and to facilitate the processing of the pieces, each of these pieces inherits from the `Piece class`. The other entity is the game board. The board is equivalent to the physical game board and consists of a matrix of `Piece` class. As a result, each element of this matrix is equivalent to a square on the physical game board. This class defines the position of each player's pieces and also determines if a player has won the game and, if so, which one.

### Graphics Display Classes:
There are two classes managing the graphical display. The first one displays the menu window, which acts as the main menu. 

![image](https://user-images.githubusercontent.com/123560349/233866538-7fa83fde-7aeb-4a76-adab-0744cab9a4fc.png)

This class manages the launch of the game, as well as all aspects prior to its launch. It allows either loading a game or starting a new game. If the players want to start a new game, they must enter a pseudonym each and choose whether they wish to play with special pieces or not. The `Fenetre_Menu` class then generates an instance of the class`Jeu`. 

The second graphical class creates the game window, as shown below. It consists of the game board, each square of which is symbolized by a button. When the player clicks on a square, the window communicates this information to the `Jeu` class.

![image](https://user-images.githubusercontent.com/123560349/233866632-8c4ebfe7-55db-4828-bd38-2a71fee7f21f.png)


### Error Management Classes:
These two classes mainly specify what type of error the program encounters. These classes are useful for managing errors made by the user, such as forgetting to enter or validate the names of the two players or forgetting to select the type of game (game with or without special pieces).
