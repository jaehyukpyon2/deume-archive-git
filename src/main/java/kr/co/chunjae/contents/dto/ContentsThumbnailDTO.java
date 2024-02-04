package kr.co.chunjae.contents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentsThumbnailDTO {
    private Integer idx;
    private Integer contentsIdx;
    private String original;
    private String saved;
    private String uploadPath;
    private String savedPath;
    private Integer orders;

    // 원본 콘텐츠 제목
    private String name;

    // list 페이지에서 필요한 presignedUrl
    private String presignedUrl;
}
