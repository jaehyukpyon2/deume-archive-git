package kr.co.chunjae.gif.mapper;

import kr.co.chunjae.gif.dto.GifDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface GifMapper {

  Integer insertGif(GifDTO gifDTO);
  GifDTO selectGif(Integer idx);

  Integer insertGifThumbnailList(Map<String, Object> gifThumbnailInsertMap);

  Integer deleteGifThumbnailByGifIdx(Integer gifIdx);

  Integer updateGif(GifDTO gifDTO);
}
