package com.game.example.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
public class Player {
    private String playerName;
    private int playerId;
    private int ranking;
    private char playerSymbol;
}
