package com.sahil.GuessGame.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Getter
@Setter
public class Game {

    private boolean isLost;
    private int RandomNumber;
    private int MaxAttemptes;
    private int Attempts;
    private int BestScore;
    private boolean isGuessed;
    private boolean isAllowed;

    public Game(){
        RandomNumber=new Random().nextInt(100);
        MaxAttemptes=10;
        Attempts=0;
        BestScore=0;
        isLost=false;
        isGuessed=false;
        isAllowed=true;
    }

    public void increamentAttempts() {
        this.Attempts++;
    }


}
