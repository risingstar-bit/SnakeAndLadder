package main.java.service;

import main.java.entities.BoardPO;

public interface GamePlayService {

  BoardPO initBoard(int boardSize);

  DiceService setDice(int diceChoice);

  void setSnakes(BoardPO boardPO);

  void startGame(BoardPO boardPO, DiceService dice);


}
