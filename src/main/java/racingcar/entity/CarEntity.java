package racingcar.entity;

import java.time.LocalDateTime;

public class CarEntity {

    private final Long id;
    private final String name;
    private final int position;
    private final Long raceResultId;
    private final String winnerYn;
    private final LocalDateTime createdAt;

    public CarEntity(final Long id, final String name,
                     final int position, final Long raceResultId,
                     final String winnerYn, final LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.raceResultId = raceResultId;
        this.winnerYn = winnerYn;
        this.createdAt = createdAt;
    }

    public CarEntity(final String name, final int position,
                     final Long raceResultId, final String winnerYn,
                     final LocalDateTime createdAt) {
        this(null, name, position, raceResultId, winnerYn, createdAt);
    }

    public boolean isWinner() {
        return winnerYn.equals("Y");
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Long getRaceResultId() {
        return raceResultId;
    }

    public String getWinnerYn() {
        return winnerYn;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
