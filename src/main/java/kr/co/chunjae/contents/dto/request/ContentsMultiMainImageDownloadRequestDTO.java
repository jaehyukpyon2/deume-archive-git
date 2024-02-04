package kr.co.chunjae.contents.dto.request;

import kr.co.chunjae.contents.validation.ValidList;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class ContentsMultiMainImageDownloadRequestDTO {
    @ValidList
    @Valid
    private List<String> contentsIdxList;
}
