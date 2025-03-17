# AI-AlphaBetaBot

### Description

This project implements a bot using the **Minimax** algorithm and **Alpha-Beta Pruning** on the Four Connect game.

#### Minimax Algorithm
The **Minimax** algorithm is a decision-making strategy used in turn-based games.  
It assumes that the opponent plays optimally (maximise the score) and tries to minimize the player's score or vice versa. The algorithm explores all possible game states up to a certain depth and selects the move that maximizes the player's minimum gain.

#### Alpha-Beta Pruning
**Alpha-Beta Pruning** is an optimization of the Minimax algorithm that reduces the number of nodes evaluated. It uses two values:
- **Alpha**: The best already explored option along the path for the maximizing player.
- **Beta**: The best already explored option along the path for the minimizing player.

By pruning branches that cannot affect the final decision, Alpha-Beta Pruning significantly improves efficiency.

### Game: Connect Four

The tests conducted for this project are based on the game **Connect Four**.

Connect Four is a two-player board game where the goal is to connect four of one's own discs in a row, column, or diagonal before the opponent does.

- Players take turns dropping colored discs into a vertical grid.
- The disc falls to the lowest available slot in the chosen column.
- The first player to align four discs in any direction wins.
- If the grid is full with no winner, the game ends in a draw.

## Project Features

This project allows you to represent five types of players:

1. **Minimax Bot**: A bot that makes decisions based on the Minimax algorithm.
2. **Alpha-Beta Bot**: A bot that makes decisions based on the Alpha-Beta Pruning algorithm.
3. **Sorted Alpha-Beta Bot**: A bot that makes decisions based on an optimized Alpha-Beta algorithm that sorts moves for better pruning efficiency.
4. **Random Bot**: A bot that takes random actions during gameplay.
5. **Human Player**: A mode where you can directly play the game.

#### Directory Structure:

TODO

```bash
src
    ├── App.java                           
    ├── MVC                                
    │   ├── Controller
    │   │   └── Controller.java
    │   ├── Model
    │   │   ├── Algorithm
    │   │   │   ├── Algorithm.java
    │   │   │   ├── AlphaBeta.java
    │   │   │   ├── AlphaBetaSort.java
    │   │   │   └── MinMax.java
    │   │   ├── Game
    │   │   │   ├── ConnectFourGame.java
    │   │   │   └── Game.java
    │   │   ├── Grid
    │   │   │   ├── ConnectFourGrid.java
    │   │   │   └── Grid.java
    │   │   ├── Player
    │   │   │   ├── Bot
    │   │   │   │   ├── AlphaBetaBot.java
    │   │   │   │   ├── AlphaBetaSortBot.java
    │   │   │   │   ├── MinMaxBot.java
    │   │   │   │   └── RandomBot.java
    │   │   │   ├── HumanPlayer.java
    │   │   │   └── Player.java
    │   │   └── Utils
    │   │       ├── Constant.java
    │   │       └── Evaluation.java
    │   └── View
    │       ├── Panel
    │       │   └── CirclePanel.java
    │       ├── PlayBotActionListener.java
    │       ├── PlayHumanActionListener.java
    │       └── View.java
    └── TerminalLauncher.java
```

## Requirement

TODO

## Execution 

TODO

## Contact Information

For inquiries or feedback, please contact me at [alexisegea@outlook.com](mailto:alexisegea@outlook.com).

## Copyright

© 2024 Alexis EGEA. All Rights Reserved.

