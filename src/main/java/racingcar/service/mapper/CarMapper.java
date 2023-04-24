package racingcar.service.mapper;

import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.entity.CarEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CarMapper {

    public static List<CarEntity> mapToCarEntitiesFrom(final RacingGame racingGame,
                                                       final Long savedId) {
        return racingGame.getParticipantAllCar()
                         .stream()
                         .map(it -> mapToCarEntityFrom(racingGame, savedId, it))
                         .collect(Collectors.toList());
    }

    private static CarEntity mapToCarEntityFrom(final RacingGame racingGame,
                                                final Long savedId,
                                                final Car car) {
        return new CarEntity(
                car.getName(),
                car.getPosition(),
                savedId,
                mapToWinner(racingGame.isWinner(car)),
                LocalDateTime.now()
        );
    }

    private static String mapToWinner(boolean isWinner) {
        if (isWinner) {
            return "Y";
        }

        return "N";
    }
}
