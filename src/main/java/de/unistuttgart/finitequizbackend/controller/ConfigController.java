package de.unistuttgart.finitequizbackend.controller;

import de.unistuttgart.finitequizbackend.data.ConfigurationDTO;
import de.unistuttgart.finitequizbackend.data.QuestionDTO;
import de.unistuttgart.finitequizbackend.data.mapper.ConfigurationMapper;
import de.unistuttgart.finitequizbackend.data.mapper.QuestionMapper;
import de.unistuttgart.finitequizbackend.repositories.ConfigurationRepository;
import de.unistuttgart.finitequizbackend.service.ConfigService;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configurations")
@Slf4j
public class ConfigController {

  @Autowired
  ConfigurationRepository configurationRepository;

  @Autowired
  ConfigService configService;

  @Autowired
  QuestionMapper questionMapper;

  @Autowired
  ConfigurationMapper configurationMapper;

  @GetMapping("")
  public List<ConfigurationDTO> getConfigurations() {
    log.debug("get all configurations");
    return configurationMapper.configurationsToConfigurationDTOs(configurationRepository.findAll());
  }

  @GetMapping("/{id}")
  public ConfigurationDTO getConfiguration(@PathVariable final UUID id) {
    log.debug("get configuration {}", id);
    return configurationMapper.configurationToConfigurationDTO(configService.getConfiguration(id));
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public ConfigurationDTO createConfiguration(@RequestBody final ConfigurationDTO configurationDTO) {
    log.debug("create configuration {}", configurationDTO);
    return configService.saveConfiguration(configurationDTO);
  }

  @PutMapping("/{id}")
  public ConfigurationDTO updateConfiguration(
    @PathVariable final UUID id,
    @RequestBody final ConfigurationDTO configurationDTO
  ) {
    log.debug("update configuration {} with {}", id, configurationDTO);
    return configService.updateConfiguration(id, configurationDTO);
  }

  @DeleteMapping("/{id}")
  public ConfigurationDTO deleteConfiguration(@PathVariable final UUID id) {
    log.debug("delete configuration {}", id);
    return configService.deleteConfiguration(id);
  }

  @PostMapping("/{id}/questions")
  @ResponseStatus(HttpStatus.CREATED)
  public QuestionDTO addQuestionToConfiguration(
    @PathVariable final UUID id,
    @RequestBody final QuestionDTO questionDTO
  ) {
    log.debug("add question {} to configuration {}", questionDTO, id);
    return configService.addQuestionToConfiguration(id, questionDTO);
  }

  @DeleteMapping("/{id}/questions/{questionId}")
  public QuestionDTO removeQuestionFromConfiguration(@PathVariable final UUID id, @PathVariable final UUID questionId) {
    log.debug("remove question {} from configuration {}", questionId, id);
    return configService.removeQuestionFromConfiguration(id, questionId);
  }

  @PutMapping("/{id}/questions/{questionId}")
  public QuestionDTO updateQuestionFromConfiguration(
    @PathVariable final UUID id,
    @PathVariable final UUID questionId,
    @RequestBody final QuestionDTO questionDTO
  ) {
    log.debug("update question {} with {} for configuration {}", questionId, questionDTO, id);
    return configService.updateQuestionFromConfiguration(id, questionId, questionDTO);
  }

  @GetMapping("/{id}/questions")
  public Set<QuestionDTO> getQuestions(@PathVariable final UUID id) {
    log.debug("get configuration {}", id);
    return configurationMapper.configurationToConfigurationDTO(configService.getConfiguration(id)).getQuestions();
  }
}
