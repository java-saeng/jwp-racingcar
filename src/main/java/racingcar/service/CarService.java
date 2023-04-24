package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.domain.RacingGame;
import racingcar.entity.CarEntity;
import racingcar.service.mapper.CarMapper;

import java.util.List;

@Service
public class CarService {

    private final CarDao carDao;

    public CarService(final CarDao carDao) {
        this.carDao = carDao;
    }

    public void registerCars(final RacingGame racingGame, final Long savedId) {
        final List<CarEntity> carEntities =
                CarMapper.mapToCarEntitiesFrom(racingGame, savedId);

        carDao.save(carEntities);
    }

    public List<CarEntity> searchAllCars() {
        return carDao.findAll();
    }
}
