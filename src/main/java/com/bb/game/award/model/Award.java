package com.bb.game.award.model;

import com.bb.game.player.model.Player;
import com.bb.game.tombola.model.Tombola;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "awards")
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(max = 36)
    @Column(name = "uuid", updatable = false, unique = true, nullable = false)
    private String uuid;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tombola_id", referencedColumnName = "id")
    private Tombola tombola;

    public Award() {
    }

    public Award(String name) {
        this.name = name;
    }

    @PrePersist
    private void generateUuid() {
        uuid = UUID.randomUUID().toString();
    }

    public void setTombola(Tombola tombola) {
        this.tombola = tombola;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Tombola getTombola() {
        return tombola;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }
}
