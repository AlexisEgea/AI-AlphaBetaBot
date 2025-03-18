# AI-AlphaBetaBot

### Description

This project implements a bot using the **Minimax** algorithm and **Alpha-Beta Pruning** on the Four Connect game.

#### Game: Connect Four

Connect Four is a two-player board game where the goal is to connect four of one's own discs in a row, column, or diagonal before the opponent does.

- Players take turns dropping colored discs into a vertical grid.
- The disc falls to the lowest available slot in the chosen column.
- The first player to align four discs in any direction wins.
- If the grid is full with no winner, the game ends in a draw.

#### Minimax Algorithm
The **Minimax** algorithm is a decision-making strategy used in turn-based games.  
It assumes that the opponent plays optimally (maximise the score) and tries to minimize the player's score or vice versa. The algorithm explores all possible game states up to a certain depth and selects the move that maximizes the player's minimum gain.

#### Alpha-Beta Pruning
**Alpha-Beta Pruning** is an optimization of the Minimax algorithm that reduces the number of nodes evaluated. It uses two values:
- **Alpha**: The best already explored option along the path for the maximizing player.
- **Beta**: The best already explored option along the path for the minimizing player.

By pruning branches that cannot affect the final decision, Alpha-Beta Pruning significantly improves efficiency.

#### Player - Bot

This project allows you to represent five types of players:

1. **Minimax Bot**: A bot that makes decisions based on the Minimax algorithm.
2. **Alpha-Beta Bot**: A bot that makes decisions based on the Alpha-Beta Pruning algorithm.
3. **Sorted Alpha-Beta Bot**: A bot that makes decisions based on an optimized Alpha-Beta algorithm that sorts moves for better pruning efficiency.
4. **Random Bot**: A bot that takes random actions during gameplay.
5. **Human Player**: A mode where you can directly play the game.

---
### Directory Structure:

```bash
src
    ├── MVC                                    # Model-View-Controller (MVC) implementation
    │   ├── Controller
    │   │   └── Controller.java                # Main controller class handling game logic and interactions
    │   ├── Model            
    │   │   ├── Algorithm        
    │   │   │   ├── Algorithm.java             # Abstract class for AI algorithms
    │   │   │   ├── AlphaBeta.java             # Implementation of the Alpha-Beta pruning algorithm
    │   │   │   ├── AlphaBetaSort.java         # Optimized Alpha-Beta algorithm with sorting
    │   │   │   └── MinMax.java                # Implementation of the Min-Max algorithm
    │   │   ├── Game                   
    │   │   │   ├── ConnectFourGame.java       # Game implementation for Connect Four
    │   │   │   └── Game.java                  # Base Game Class
    │   │   ├── Grid      
    │   │   │   ├── ConnectFourGrid.java       # Grid implementation for Connect Four
    │   │   │   └── Grid.java                  # Base Grid Class
    │   │   ├── Player
    │   │   │   ├── Bot                        # Directory for AI players (bots)
    │   │   │   │   ├── AlphaBetaBot.java      # Bot using the Alpha-Beta algorithm
    │   │   │   │   ├── AlphaBetaSortBot.java  # Bot using the optimized Alpha-Beta algorithm
    │   │   │   │   ├── MinMaxBot.java         # Bot using the Min-Max algorithm
    │   │   │   │   └── RandomBot.java         # Bot making random moves
    │   │   │   ├── HumanPlayer.java           # Class representing a human player
    │   │   │   └── Player.java                # Abstract class for players
    │   │   └── Utils     
    │   │       ├── Constant.java              # Class storing constants of the game
    │   │       └── Evaluation.java            # Class for evaluating game states (AI heuristic)
    │   └── View             
    │       ├── Panel                          
    │       │   └── CirclePanel.java           # Panel for rendering circular game pieces
    │       ├── PlayBotActionListener.java     # Event listener for Bot moves
    │       ├── PlayHumanActionListener.java   # Event listener for Human moves
    │       └── View.java                      # Main GUI class for displaying the game
    ├── App.java                               # Main class to launch the graphical application
    └── TerminalLauncher.java                  # Main class to launch the game in terminal mode

```

## Requirement

- Having Java installed on your machine.  
Here is the configuration used for the development this project:
```bash
openjdk 21.0.6 2025-01-21
OpenJDK Runtime Environment (build 21.0.6+7-Ubuntu-124.04.1)
OpenJDK 64-Bit Server VM (build 21.0.6+7-Ubuntu-124.04.1, mixed mode, sharing)
```

## Execution 

For this project, the execution explanation is referred here: https://github.com/AlexisEgea/AI-AlphaBetaBot/tree/readme/ALX/docs#readme

## Contact Information

For inquiries or feedback, please contact me at [alexisegea@outlook.com](mailto:alexisegea@outlook.com).

## Copyright

© 2024 Alexis EGEA. All Rights Reserved.

