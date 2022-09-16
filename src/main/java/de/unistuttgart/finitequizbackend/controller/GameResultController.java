package de.unistuttgart.finitequizbackend.controller;

import de.unistuttgart.finitequizbackend.data.GameResultDTO;
import de.unistuttgart.finitequizbackend.service.GameResultService;
import de.unistuttgart.gamifyit.authentificationvalidator.JWTValidatorService;
import de.unistuttgart.gamifyit.authentificationvalidator.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/results")
@Import({JWTValidatorService.class})
@Slf4j
public class GameResultController {

  @Autowired
  GameResultService gameResultService;

  @Autowired
  private JWTValidatorService jwtValidatorService;

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public GameResultDTO saveGameResult(@CookieValue("access_token") final String accessToken, @RequestBody final GameResultDTO gameResultDTO) {
    final String userId = jwtValidatorService.validate(accessToken).getSubject();
    log.info("save game result for userId {}: {}", userId, gameResultDTO);
    gameResultService.saveGameResult(gameResultDTO, userId);
    return gameResultDTO;
  }
}
