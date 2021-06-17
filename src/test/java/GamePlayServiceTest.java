package test.java;

import main.java.common.GameConstants;
import main.java.entities.BoardPO;
import main.java.exception.DiceException;
import main.java.service.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GamePlayServiceTest {

  private GamePlayService gamePlayService = new GamePlayServiceImpl();
  private static final Integer FAIR_DICE_SELECTION_VALUE = 1;
  private static final Integer CROOKED_DICE_SELECTION_VALUE = 2;
  private static final Integer WRONG_DICE_SELECTION_VALUE = 5;

  @Test
  public void boardSizeTest(){
    BoardPO snakeAndLadderBoard = gamePlayService.initBoard(GameConstants.DEFAULT_BOARD_SIZE);
    Assert.assertEquals((int) GameConstants.DEFAULT_BOARD_SIZE, snakeAndLadderBoard.getSize());
  }

//  @Test
//  public void boardInitialPositionTest(){
//    BoardPO snakeAndLadderBoard = gamePlayService.initBoard(GameConstants.DEFAULT_BOARD_SIZE);
//    Assert.assertEquals((int) GameConstants.INITIAL_PLAYER_POSITION, snakeAndLadderBoard.getPosition());
//  }

  @Test(expectedExceptions = DiceException.class,
    expectedExceptionsMessageRegExp = "--> ErrorCode: 1- Invalid dice Selection")
  public void invalidDiceSelectionTest(){
    gamePlayService.setDice(WRONG_DICE_SELECTION_VALUE);
  }

  @Test
  public void fairDiceSelectionTest(){
    DiceService dice = gamePlayService.setDice(FAIR_DICE_SELECTION_VALUE);
    Assert.assertEquals(dice.getClass(), FairDiceServiceImpl.class);
  }

  @Test
  public void crookedDiceSelectionTest(){
    DiceService dice = gamePlayService.setDice(CROOKED_DICE_SELECTION_VALUE);
    Assert.assertEquals(dice.getClass(), CrookedDiceServiceImpl.class);
  }

  @Test
  public void setSnakesTest(){
    BoardPO snakeAndLadderBoard = gamePlayService.initBoard(GameConstants.DEFAULT_BOARD_SIZE);
    gamePlayService.setSnakes(snakeAndLadderBoard);
    Assert.assertEquals((Integer) snakeAndLadderBoard.getSnakes().size(), GameConstants.NUMBER_OF_SNAKES);
  }

//  @Test
//  public void startGameTest(){
//    BoardPO snakeAndLadderBoard = gamePlayService.initBoard(GameConstants.DEFAULT_BOARD_SIZE);
//    DiceService dice = gamePlayService.setDice(FAIR_DICE_SELECTION_VALUE);
//    gamePlayService.startGame(snakeAndLadderBoard, dice);
//  }

}
