package com.smartdigit.zalewski.gamecenter.domain;

import com.smartdigit.zalewski.gamecenter.domain.enums.GameStatus;
import com.smartdigit.zalewski.gamecenter.domain.enums.GameType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private GameType gameType;
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;
    @ManyToOne
    @JoinColumn(name = "player_one_id")
    private Player firstPlayer;
    @ManyToOne
    @JoinColumn(name = "player_two_id")
    private Player secondPlayer;
    private LocalDateTime created;




}
