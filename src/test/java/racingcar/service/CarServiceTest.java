package racingcar.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import racingcar.dao.CarDao;
import racingcar.domain.RacingGame;
import racingcar.entity.CarEntity;
import racingcar.service.mapper.CarMapper;
import racingcar.util.RandomNumberGenerator;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("CarService Unit Test")
class CarServiceTest {

    private CarService carService;
    private static MockedStatic<CarMapper> carMapper;

    private CarDao carDao;

    @BeforeEach
    void init() {
        carDao = mock(CarDao.class);

        carService = new CarService(carDao);
    }

    @BeforeAll
    static void beforeAll() {
        carMapper = mockStatic(CarMapper.class);
    }

    @AfterAll
    static void afterAll() {
        carMapper.close();
    }

    @Test
    @DisplayName("registerCars() : 자동차들을 저장합니다.")
    void test_registerCars() throws Exception {
        //given
        final RacingGame racingGame =
                RacingGame.readyToRacingGame("a,b,c",
                                             new RandomNumberGenerator(),
                                             2);
        final Long savedRaceResultId = 1L;

        final List<CarEntity> carEntities = List.of(
                new CarEntity("a", 3, 1L, "Y", LocalDateTime.now()),
                new CarEntity("b", 3, 1L, "Y", LocalDateTime.now()),
                new CarEntity("c", 3, 1L, "Y", LocalDateTime.now())
        );

        //when
        when(CarMapper.mapToCarEntitiesFrom(any(), anyLong()))
                .thenReturn(carEntities);

        carService.registerCars(racingGame, savedRaceResultId);

        //then
        verify(carDao, times(1)).save(any());
    }
}
