package com.bb.game.tombola.model;

import com.bb.game.award.model.Award;
import com.bb.game.player.model.Player;
import com.bb.game.tombola.enums.GameState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

import static com.bb.game.tombola.enums.GameState.WAITING;

@Entity
@Table(name = "tombolas")
public class Tombola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(max = 36)
    @Column(name = "uuid", updatable = false, unique = true, nullable = false)
    private String uuid;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "maxPlayers")
    private int maxPlayers;

    @Column(name = "maxAwards")
    private int maxAwards;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_state")
    private GameState gameState = WAITING;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tombola", cascade = CascadeType.ALL)
    @Column(name = "award_id")
    private List<Award> awards;

    @ManyToMany
    @JoinTable(
            name = "tombolas_players",
            joinColumns = @JoinColumn(name = "tombola_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> players;

    public Tombola() {
    }

    public Tombola(String name, int maxPlayers, int maxAwards) {
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.maxAwards = maxAwards;
    }

    @PrePersist
    private void generateUuid() {
        uuid = UUID.randomUUID().toString();
    }

    public void addAward(@NotNull Award award) {
        awards.add(award);
    }

    public void addPlayer(@NotNull Player player) {
        players.add(player);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMaxAwards() {
        return maxAwards;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameState getGameState() {
        return gameState;
    }

}
