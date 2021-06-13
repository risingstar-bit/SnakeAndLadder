package main.java.service;

import java.util.Random;

public class FairDiceServiceImpl implements DiceService {

  @Override
  public Integer roll() {
    return new Random().nextInt(6) + 1;
  }

}
