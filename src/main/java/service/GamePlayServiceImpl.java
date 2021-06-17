package main.java.service;

import main.java.common.Errors;
import main.java.common.GameConstants;
import main.java.entities.*;
import main.java.exception.DiceException;
import main.java.common.ExceptionUtils;

import java.util.ArrayList;
import java.util.List;

public class GamePlayServiceImpl implements  GamePlayService {

  private BoardPO boardPO;

  public BoardPO initBoard(int boardSize){
    this.boardPO = new BoardPO(boardSize);
//    setInitialPosition(GameConstants.INITIAL_PLAYER_POSITION);
    return boardPO;
  }
//  private void setInitialPosition(int initPosition) {
//    boardPO.setPosition(initPosition);
//  }

  public DiceService setDice(int diceChoice){
    DiceService diceService;
    if(diceChoice==1){
      diceService = new FairDiceServiceImpl();
      return diceService;
    }
    else if(diceChoice==2){
      diceService = new CrookedDiceServiceImpl();
      return diceService;
    }
    else{
      throw new DiceException(ExceptionUtils.getMessage(Errors.INVALID_DICE_SELECTION.getMessage()));
    }
  }



  public void setSnakes(BoardPO snakeAndLadderBoard) {
    PopulateSnakesService populateSnakesService = new PopulateSnakesServiceImpl();
    List<SnakePO> snakes = populateSnakesService.addSnakes();
    snakeAndLadderBoard.setSnakes(snakes);
  }

  private int getNewPosition(BoardPO snakeAndLadderBoard, int newPosition) {
      for (SnakePO snake : snakeAndLadderBoard.getSnakes()) {
        if (snake.getStart() == newPosition) {
          newPosition = snake.getEnd();
        }
      }
      return newPosition;
  }

  private void movePlayer(BoardPO snakeAndLadderBoard, int positions, PlayerPO player) {
    int oldPosition = player.getPosition();
    int newPosition = oldPosition + positions;
    int boardSize = snakeAndLadderBoard.getSize();

    if (newPosition > boardSize) {
      newPosition = oldPosition;
    } else {
      newPosition = getNewPosition(snakeAndLadderBoard, newPosition);
    }

    player.setPosition(newPosition);
    System.out.println( "Player ID: "+ player.getId()+" Dice value: " + positions + ". Position moved from " + oldPosition +" to " + newPosition);
  }

  private int getNewDiceValue(DiceService dice) {
    return dice.roll();
  }

  private boolean hasPlayerWon(BoardPO snakeAndLadderBoard, PlayerPO player) {
    int playerPosition = player.getPosition();
    int winningPosition = snakeAndLadderBoard.getSize();
    return playerPosition == winningPosition;
  }

  /*
  while game has not ended: iterate player list if it has won
   */

  public void startGame(BoardPO snakeAndLadderBoard, DiceService dice, ArrayList<PlayerPO> playerList) {
    int gameRuns = GameConstants.GAME_RUN_COUNT;
    int playerCount = playerList.size();
    int playerTracker = 0;
//    PlayerPO player = playerList.get(0);

    while(!hasPlayerWon(snakeAndLadderBoard, playerList.get(playerTracker))) {
      PlayerPO currentPlayer = playerList.get(playerTracker);
      if(gameRuns--==0){
        System.out.println("Iterations Completed");
        return;
      }
      int totalDiceValue = getNewDiceValue(dice);
      movePlayer(snakeAndLadderBoard, totalDiceValue, currentPlayer);
      playerTracker = (playerTracker+1) % playerCount;
      // (0 +1) % 2
    }
    System.out.println("Player wins the game");
  }

  public ArrayList<PlayerPO> initPlayer(Integer noOfPlayers){
    ArrayList<PlayerPO> playerList = new ArrayList<>(noOfPlayers);

    for(int i=1;i<noOfPlayers+1;i++){
      PlayerPO player = new PlayerPO(i, GameConstants.INITIAL_PLAYER_POSITION);
      playerList.add(player);
    }
    return playerList;
  }
}
