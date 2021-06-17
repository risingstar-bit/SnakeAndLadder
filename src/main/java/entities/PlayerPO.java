package main.java.entities;

public class PlayerPO {

  int id;

  public int getPosition() {
    return position;
  }

  public void setPosition(int playerPosition) {
    this.position = playerPosition;
  }

  int position;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public PlayerPO(int id, int init){
    this.id = id;
    this.position = init;
  }

}
