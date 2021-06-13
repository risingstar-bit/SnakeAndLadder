package main.java.entities;

import java.util.*;

public class BoardPO {

  private int size;
  private List<SnakePO> snakes;
  private int position;

  public BoardPO(int size) {
    this.size = size;
    this.snakes = new ArrayList<>();
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public int getSize() {
    return size;
  }

  public List<SnakePO> getSnakes() {
    return snakes;
  }

  public void setSnakes(List<SnakePO> snakes) {
    this.snakes = snakes;
  }

}
