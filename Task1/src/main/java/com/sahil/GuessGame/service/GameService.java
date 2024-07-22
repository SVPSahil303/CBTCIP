package com.sahil.GuessGame.service;

import com.sahil.GuessGame.model.Game;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private Game game;

    public Game StartNewGame(){
        this.game=new Game();
        return game;
    }

    public Game getGame(){
        return game;
    }

    public String GuessNumber(int number){
        while(game.getAttempts()<game.getMaxAttemptes()){
            if (game.isGuessed() || game.isLost()) {
                return "The game is over. Start a new game.";
            }
            game.increamentAttempts();
            if(number== game.getRandomNumber()){
                game.setGuessed(true);
                if(game.getBestScore()==0 || game.getAttempts() < game.getBestScore()){
                    game.setBestScore(game.getAttempts());
                }
                return "Congratulations! You Guessed the number ";
            } else if (number> game.getRandomNumber()) {
                return "TOO BIG!....";
            }else {
                return "TOO SMALL!....";
            }
        }
        game.setLost(true);
        game.setAllowed(false);
        return "You Lost!...";
    }

}
