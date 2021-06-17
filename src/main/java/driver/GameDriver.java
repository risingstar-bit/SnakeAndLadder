package main.java.driver;

import main.java.common.Errors;
import main.java.common.ExceptionUtils;
import main.java.common.GameConstants;
import main.java.entities.BoardPO;
import main.java.entities.PlayerPO;
import main.java.exception.DiceException;
import main.java.service.DiceService;
import main.java.service.GamePlayService;
import main.java.service.GamePlayServiceImpl;

import java.util.ArrayList;
import java.util.Scanner;


public class GameDriver {
  public static void main(String[] args) {

    ArrayList<PlayerPO> playerList;
    Scanner scanner = new Scanner(System.in);
    System.out.println("Select the preferred dice: (1 or 2");
    System.out.println("1: Fair Dice \n2: Crooked Dice");

    int diceSelection = scanner.nextInt();

    if(diceSelection>2 || diceSelection<=0){
      throw new DiceException(ExceptionUtils.getMessage(Errors.INVALID_DICE_SELECTION.getMessage()));
    }

    System.out.println("Enter Number of players:");
    int noOfPlayer = scanner.nextInt();
//    playerList = new ArrayList<>(noOfPlayer);
    GamePlayService gamePlayService = new GamePlayServiceImpl();


    BoardPO boardPO = gamePlayService.initBoard(GameConstants.DEFAULT_BOARD_SIZE);
    gamePlayService.setSnakes(boardPO);
    DiceService dice = gamePlayService.setDice(diceSelection);
    //call player
    playerList = gamePlayService.initPlayer(noOfPlayer);
    gamePlayService.startGame(boardPO, dice, playerList);
  }
}
