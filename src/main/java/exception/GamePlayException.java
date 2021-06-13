package main.java.exception;

abstract class GamePlayException extends RuntimeException {

  GamePlayException(String message){
    super(message);
  }
}
