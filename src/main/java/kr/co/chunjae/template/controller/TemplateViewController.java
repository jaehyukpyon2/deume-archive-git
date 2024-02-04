package kr.co.chunjae.template.controller;

import kr.co.chunjae.template.dto.TemplateThumbnailDTO;
import kr.co.chunjae.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;



@Slf4j
@Controller
@RequestMapping("/template")
@RequiredArgsConstructor
public class TemplateViewController {
    private final TemplateService templateService;
    @GetMapping("/write")
    public String writeForm() {
        return "template/write";
    }

    @GetMapping("/list")
    public String templateList(Model model){
        List<TemplateThumbnailDTO> templateThumbnailDTOList = templateService.getThumbnailList();
        log.info("aaaa"+templateThumbnailDTOList.toString());
        model.addAttribute("templateList", templateThumbnailDTOList);
        return "template/list";
    }
}
