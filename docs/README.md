## Execution AlphaBeta FourConnect Project

Navigate to the desired directory, open a terminal, and run the following command to clone the project:
```bash
git clone https://github.com/AlexisEgea/AI-AlphaBetaBot.git
```

Open IntelliJ and select the path where the project is located:
![](./img/1.%20open_project.png)

In the top right corner, click on `Current File`, then select `Edit Configuration`:
![](./img/2.%20config.png)

Click on the `+` button in the top left corner to add a new configuration, then select `Application`:
![](./img/3.%20select_config.png)

At this stage, you can rename the executable as you wish.  
Then, click on the icon to the right of `Main class` to select the file containing the main class to run.   
This project includes three executables:
1. `AppBotFirst`: The bot plays before the user
2. `AppUserFirst`: The user plays first
3. `TerminalLauncher`: Terminal version of the game (to be used for debugging only)  

Create two executables `AppBotFirst` and `AppUserFirst` by following this README to be able to play in each position.
![](./img/4.%20edit_config.png)

## Play Four Connect Game

Once the executables are created, you can select the desired configuration to launch the game by clicking on the green 
triangle in the top right corner: 
![](./img/5.%20game_open.png)

To play, click on the `Bot Play` button to make the bot play, or click on one of the 7 digit to play as the user.  
Once a button is clicked (whether it's the player's or the bot's turn), the opposing buttons become unclickable to prevent multiple consecutive plays.  
At the top, the player's information (color) is displayed. The last move made is marked by an unfilled green circle, making it easy to recognize.
![](./img/6.%20game_play.png)

When one of the players wins, a popup will appear, and the top of the interface will turn green, indicating that the game has ended. You can move the popup or close it to view the result of the grid.  
![](./img/7.%20game_win.png)

To play again, close the application and restart the execution by clicking on the green triangle

Note that a script/launcher will be added in a future update to simplify launching the application by offering configuration choices including:
- Type of game (user vs user, user vs AI, etc.)
- Player's color
- Bot difficulty
- Number of games to play    
and more