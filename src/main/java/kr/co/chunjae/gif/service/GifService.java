package kr.co.chunjae.gif.service;

import kr.co.chunjae.gif.dto.GifDTO;
import kr.co.chunjae.gif.dto.GifSaveRequestDTO;
import kr.co.chunjae.gif.repository.GifRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class GifService {
    private final GifRepository gifRepository;

    public Integer insertGif(GifDTO gifDTO) {
        Integer insertResult = gifRepository.insertGif(gifDTO);
        return insertResult;
    }

    public GifDTO selectGif(Integer idx) {
        return gifRepository.selectGif(idx);
    }

    public void insertGifWithThumbnailAndMainImage(GifSaveRequestDTO requestDTO) {
        GifDTO newGifDTO = GifDTO
                .builder()
                .typeIdx(requestDTO.getType())
                .display(requestDTO.getDisplay())
                .name(requestDTO.getName())
                .keyword(requestDTO.getKeyword())
                .explanation(requestDTO.getExplanation())
                .source(requestDTO.getSource())
                .subcateIdx(requestDTO.getSubCategory())
                .original(requestDTO.getMainImageSaveRequestDTO().getOriginal())
                .saved(requestDTO.getMainImageSaveRequestDTO().getSaved())
                .build();
        Integer gifInfoInsertResult = insertGif(newGifDTO);
        if (gifInfoInsertResult != 1) {
            throw new RuntimeException("Gif 정보를 저장하는 중 오류 발생");
        }

        Map<String, Object> gifThumbnailInsertMap = new HashMap<>();
        gifThumbnailInsertMap.put("gifIdx", newGifDTO.getIdx());
        gifThumbnailInsertMap.put("thumbnailList", requestDTO.getThumbnailSaveRequestList());

        Integer thumbnailInsertResult = gifRepository.insertGifThumbnailList(gifThumbnailInsertMap);
        if (requestDTO.getThumbnailSaveRequestList().size() != thumbnailInsertResult) {
            throw new RuntimeException("Gif 썸네일 정보를 저장하는 중 오류 발생");
        }
    }
    public void updateGifWithThumbnailAndMainImage(GifSaveRequestDTO requestDTO) {
        // 원래 있던 contentsThumbnail 삭제
        Integer gifThumbnailDeleteResult =
                gifRepository.deleteGifThumbnailByGifIdx(requestDTO.getIdx());

        GifDTO gifDTOForUpdate = GifDTO
                .builder()
                .idx(requestDTO.getIdx())
                .typeIdx(requestDTO.getType())
                .display(requestDTO.getDisplay())
                .name(requestDTO.getName())
                .keyword(requestDTO.getKeyword())
                .explanation(requestDTO.getExplanation())
                .source(requestDTO.getSource())
                .subcateIdx(requestDTO.getSubCategory())
                .original(requestDTO.getMainImageSaveRequestDTO().getOriginal())
                .saved(requestDTO.getMainImageSaveRequestDTO().getSaved())
                .build();

        Integer gifUpdateResult = gifRepository.updateGif(gifDTOForUpdate);

        Map<String, Object> gifThumbnailInsertMap = new HashMap<>();
        gifThumbnailInsertMap.put("gifsIdx", gifDTOForUpdate.getIdx());
        gifThumbnailInsertMap.put("thumbnailList", requestDTO.getThumbnailSaveRequestList());

        Integer gifThumbnailInsertResult = gifRepository.insertGifThumbnailList(gifThumbnailInsertMap);
    }
}
