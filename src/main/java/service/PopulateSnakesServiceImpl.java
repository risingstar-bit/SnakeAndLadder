package main.java.service;

import main.java.common.GameConstants;
import main.java.entities.SnakePO;

import java.util.*;

public class PopulateSnakesServiceImpl implements PopulateSnakesService {

  @Override
  public List<SnakePO> addSnakes() {

    List<SnakePO> snakes = new ArrayList<>();

    // Specified Test Case
    snakes.add(new SnakePO(14, 7));

    for (int i = 0; i < GameConstants.NUMBER_OF_SNAKES-1; i++) {
      int start = new Random().nextInt((GameConstants.DEFAULT_BOARD_SIZE-1)-10) + 10;
      int end = new Random().nextInt(start-10 +1)+ 10;
      snakes.add(new SnakePO(start, end));
    }

    System.out.println("Snakes at position:\n");
    for(SnakePO snakePO:snakes){
      System.out.println(snakePO.getStart()+"  "+ snakePO.getEnd()+"\n");
    }
    return snakes;
  }
}
