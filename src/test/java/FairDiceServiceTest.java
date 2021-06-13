package test.java;

import main.java.service.DiceService;
import main.java.service.FairDiceServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;


public class FairDiceServiceTest {

  private DiceService fairDice = new FairDiceServiceImpl();

  @Test
  public void testFairDiceRollLowerValue(){
    int rollValue = fairDice.roll();
    Assert.assertTrue(rollValue>0);
  }

  @Test
  public void testFairDiceRollUpperValue(){
    int rollValue = fairDice.roll();
    Assert.assertTrue(rollValue<=6);
  }

}
