package test.java;

import main.java.service.CrookedDiceServiceImpl;
import main.java.service.DiceService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CrookedDiceServiceTest {

  private DiceService crookedDice = new CrookedDiceServiceImpl();


  @Test
  public void testCrookedDiceRollLowerValue(){
    int rollValue = crookedDice.roll();
    Assert.assertTrue(rollValue>1);
  }

  @Test
  public void testCrookedDiceRollUpperValue(){
    int rollValue = crookedDice.roll();
    Assert.assertTrue(rollValue<=6);
  }

  @Test
  public void testCrookedDiceRollEvenValueProperty(){
    int rollValue = crookedDice.roll();
    Assert.assertEquals(rollValue % 2, 0);
  }
}
