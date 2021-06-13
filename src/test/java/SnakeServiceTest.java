package test.java;

import main.java.common.GameConstants;
import main.java.entities.SnakePO;
import main.java.service.PopulateSnakesService;
import main.java.service.PopulateSnakesServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SnakeServiceTest {

  private PopulateSnakesService populateSnakesService = new PopulateSnakesServiceImpl();

  @Test
  public void snakeCountTest(){
    List<SnakePO> snakesList = populateSnakesService.addSnakes();
    Assert.assertEquals((int) GameConstants.NUMBER_OF_SNAKES, snakesList.size());
  }

  @Test
  public void snakeLowerBoundTest(){
    List<SnakePO> snakesList = populateSnakesService.addSnakes();
    snakesList.forEach((snake -> Assert.assertTrue(snake.getEnd() >=7)));
  }

  @Test
  public void snakeUpperBoundTest(){
    List<SnakePO> snakesList = populateSnakesService.addSnakes();
    snakesList.forEach((snake -> Assert.assertTrue(snake.getEnd() <=99)));
  }

  @Test
  public void snakeCustomValueTest(){
    List<SnakePO> snakesList = populateSnakesService.addSnakes();
    Assert.assertEquals(snakesList.get(0).getEnd(), 7);
    Assert.assertEquals(snakesList.get(0).getStart(), 14);
  }
}
