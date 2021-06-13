package main.java.service;

import java.util.Random;

public class CrookedDiceServiceImpl implements DiceService {

  @Override
  public Integer roll() {
    return (new Random().nextInt(3)+1) * 2;
  }
}
