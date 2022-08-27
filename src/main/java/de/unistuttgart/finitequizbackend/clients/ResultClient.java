package de.unistuttgart.finitequizbackend.clients;

import de.unistuttgart.finitequizbackend.data.OverworldResultDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "resultClient", url = "${overworld.url}/internal")
public interface ResultClient {
  @PostMapping("/submit-game-pass")
  @Headers("Content-Type: application/json")
  void submit(OverworldResultDTO resultDTO);
}