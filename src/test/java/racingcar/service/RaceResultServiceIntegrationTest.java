package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import racingcar.service.dto.CarStatusResponse;
import racingcar.service.dto.GameInfoRequest;
import racingcar.service.dto.RaceResultResponse;
import racingcar.support.AbstractIntegrationTestSupporter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("RaceResultService Integration Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RaceResultServiceIntegrationTest extends AbstractIntegrationTestSupporter {

    @Autowired
    private RaceResultService raceResultService;

    @Test
    @DisplayName("createRaceResult() : 게임 정보를 통해 새로운 게임을 만들 수 있다.")
    @Order(1)
    void test_createRaceResult() throws Exception {
        //given
        final String input = "a,b,c,d";
        final List<String> splitInput = List.of(input.split(","));

        final GameInfoRequest gameInfoRequest = new GameInfoRequest(input, splitInput.size());

        //when
        final RaceResultResponse raceResult = raceResultService.createRaceResult(gameInfoRequest);

        //then
        final List<CarStatusResponse> racingCars = raceResult.getRacingCars();

        assertThat(racingCars).hasSize(splitInput.size())
                              .extracting("name")
                              .containsExactlyElementsOf(splitInput);
    }

    @Test
    @DisplayName("searchRaceResult() : 모든 경기 결과를 조회할 수 있다.")
    @Order(2)
    void test_searchRaceResult() throws Exception {
        //given
        final List<String> winnerResult = List.of("빙봉", "a,b,c,d");

        //when
        final List<RaceResultResponse> raceResultResponses = raceResultService.searchRaceResult();

        //then
        assertThat(raceResultResponses).extracting("winners")
                                       .hasSize(winnerResult.size())
                                       .containsExactlyElementsOf(winnerResult);
    }
}
