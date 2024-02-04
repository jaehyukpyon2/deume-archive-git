package kr.co.chunjae.template.controller;

import kr.co.chunjae.template.dto.TemplateSaveDTO;
import kr.co.chunjae.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/template")
public class TemplateController {
    private final TemplateService templateService;

    @PostMapping(value = "/write")
    public ResponseEntity<?> templateSave(@Validated @RequestBody TemplateSaveDTO requestDTO) {
        log.info("requestDTO = {}", requestDTO);

        templateService.insertTemplate(requestDTO);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", "템플릿이 등록되었습니다");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resultMap);
    }
}
