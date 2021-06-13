package main.java.service;

import main.java.common.Errors;
import main.java.common.GameConstants;
import main.java.entities.*;
import main.java.exception.DiceException;
import main.java.common.ExceptionUtils;

import java.util.List;

public class GamePlayServiceImpl implements  GamePlayService {

  private BoardPO boardPO;

  public BoardPO initBoard(int boardSize){
    this.boardPO = new BoardPO(boardSize);
    setInitialPosition(GameConstants.INITIAL_PLAYER_POSITION);
    return boardPO;
  }
  private void setInitialPosition(int initPosition) {
    boardPO.setPosition(initPosition);
  }

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

  private void movePlayer(BoardPO snakeAndLadderBoard, int positions) {
    int oldPosition = snakeAndLadderBoard.getPosition();
    int newPosition = oldPosition + positions;
    int boardSize = snakeAndLadderBoard.getSize();

    if (newPosition > boardSize) {
      newPosition = oldPosition;
    } else {
      newPosition = getNewPosition(snakeAndLadderBoard, newPosition);
    }

    snakeAndLadderBoard.setPosition(newPosition);
    System.out.println( "Dice value: " + positions + ". Position moved from " + oldPosition +" to " + newPosition);
  }

  private int getNewDiceValue(DiceService dice) {
    return dice.roll();
  }

  private boolean hasPlayerWon(BoardPO snakeAndLadderBoard) {
    int playerPosition = snakeAndLadderBoard.getPosition();
    int winningPosition = snakeAndLadderBoard.getSize();
    return playerPosition == winningPosition;
  }


  public void startGame(BoardPO snakeAndLadderBoard, DiceService dice) {
    int gameRuns = GameConstants.GAME_RUN_COUNT;
    while(!hasPlayerWon(snakeAndLadderBoard)) {
//      if(gameRuns--==0){
//        System.out.println("Iterations Completed");
//        return;
//      }
      int totalDiceValue = getNewDiceValue(dice);
      movePlayer(snakeAndLadderBoard, totalDiceValue);
    }
    System.out.println("Player wins the game");
  }

}
