package com.sahil.GuessGame.controller;

import com.sahil.GuessGame.model.Game;
import com.sahil.GuessGame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public String Home(Model model){
        Game game=gameService.StartNewGame();
        model.addAttribute("game",game);
        return "index";
    }

    @PostMapping("/guess")
    public String guess(@RequestParam int guess,Model model){
        String message=gameService.GuessNumber(guess);
        Game game=gameService.getGame();
        model.addAttribute("game",game);
        model.addAttribute("message",message);
        return "index";
    }
}
