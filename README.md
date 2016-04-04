#Battleship
I created a battleship game in efforts to learn scala and git essentials using intellij. This is also my first object oriented project.

The game is played by running the battleShip.scala file.
###Understanding Classes
The battleship game is made up of 5 simple classes, used for several functions related to the game.

The designs of the classes were meant to be conducted in a manor that is easily scalable.
####Boat
Holds all attributes and helper functions that a boat in battleship would need.

The Boat is constructed as an array of tuples, where the tuples act as coordinates to the ships location.

The Boat has properties such as size, status, location, ect. to allow for other classes to know where the Boat is and in what state the boat is in. When a Boat is sunk the Player is immediately notified.
####Missile
A Missile object takes desired attack coordinates from the user and ensures that the coordinates are valid.

The Missile class maintains the status of hit or miss depending on the result of whether or not a boat is in the attack coordinates.

####Player
A Player object holds all attributes related to the user. It holds the users name, the amount of hits and misses the user has, as well as a log of the shots the user has taken to ensure the user does not shoot at the same target more than once.

####Game
A Game class posses 3 two-dimensional arrays.
- GameBoard: Maintains the current state of the game ie what is displayed to the user as he or she takes shots (hits or misses)
- FleetMap: Maintains the location of the Boat objects on the map; ie each point on this map is a Boat object or "NA" if there is no boat.
- FleetDisplay: Easy way to read the FleetMap with abbreviated strings for each boat. This is only shown at the end of the game to show the users exactly where each boat was one more time.

The game class initializes the game by randomly assorting a fleet of Boats into the FleetMap.

When a Missile is fired the FleetMap is used to update the status of the game and the status is returned to the user via the GameBoard, which again only shows hits and misses.

####LeaderBoard
The LeaderBoard class takes a Player object and compares the stats of the Player to previous players on the locally stored leaderboard.csv. If the Player is in the top 10 his or her name and total shots are saved to the leaderboard.csv.