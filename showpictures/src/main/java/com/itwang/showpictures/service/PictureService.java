package com.itwang.showpictures.service;

import com.itwang.showpictures.model.entity.Pictures;
import com.itwang.showpictures.model.entity.PicturesType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * @ProjectName: MyLove-Vue
 * @Package: com.itwang.showpictures.service.impl
 * @ClassName: PictureService
 * @Author: wangyaqiang
 * @Description:
 * @Date: 2020/12/10 18:33
 * @Version: 1.0
 */
public interface PictureService {
    List<Pictures> getALLPictures(Integer pageSize, Integer pageNum);

    List<Pictures> getPicturesByTypeID(Integer pageSize, Integer pageNum, Integer image_type);

    List<Pictures> getPicturesByTypeName(Integer pageSize, Integer pageNum, String type);

    String getPictureTypeById(Integer id);

    List<PicturesType> getALLPicturesType();

    List<HashMap<String,Object>> getDistinctPicturesByType();

    List<Pictures> getAllBackPicturesByTypeId();
}
