package main.java.service;

import main.java.entities.BoardPO;
import main.java.entities.PlayerPO;

import java.util.ArrayList;

public interface GamePlayService {

  BoardPO initBoard(int boardSize);

  DiceService setDice(int diceChoice);

  void setSnakes(BoardPO boardPO);

  void startGame(BoardPO boardPO, DiceService dice, ArrayList<PlayerPO> playerList);

  ArrayList<PlayerPO> initPlayer(Integer noOfPlayers);

}
