package com.bb.game.player.model;

import com.bb.game.award.model.Award;
import com.bb.game.tombola.model.Tombola;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(max = 36)
    @Column(name = "uuid", updatable = false, unique = true, nullable = false)
    private String uuid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "weight")
    private int weight;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "player", cascade = CascadeType.ALL)
    @Column(name = "award_id")
    private List<Award> awards;

    @ManyToMany(mappedBy = "players")
    private List<Tombola> tombolas;

    public Player() {
    }

    public Player(String name, String email, int weight) {
        this.name = name;
        this.email = email;
        this.weight = weight;
    }

    @PrePersist
    private void generateUuid() {
        uuid = UUID.randomUUID().toString();
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getWeight() {
        return weight;
    }

    public void setAwards(List<Award> awards) {
        this.awards = awards;
    }

    public List<Award> getAwards() {
        if (Objects.isNull(awards)) {
            return new ArrayList<>();
        }
        return awards;
    }

    public String getUuid() {
        return uuid;
    }
}
