package com.itwang.showpictures.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itwang.showpictures.mapper.PicturesMapper;
import com.itwang.showpictures.model.entity.Pictures;
import com.itwang.showpictures.model.entity.PicturesType;
import com.itwang.showpictures.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @ProjectName: MyLove-Vue
 * @Package: com.itwang.showpictures.service
 * @ClassName: PicturesServiceImpl
 * @Author: wangyaqiang
 * @Description:
 * @Date: 2020/12/10 18:32
 * @Version: 1.0
 */
@Service
public class PicturesServiceImpl implements PictureService {
    @Autowired
    PicturesMapper picturesMapper;

    private Logger logger = LoggerFactory.getLogger(PicturesServiceImpl.class);
    /**
     * 获取所有图片
     * @return
     */
    @Override
    public List<Pictures> getALLPictures(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Pictures> pictures = picturesMapper.getALLPictures();
        PageInfo<Pictures> pageInfo = new PageInfo<>(pictures);
        logger.info("返回结果："+ pageInfo);
        return pictures;
    }

    /**
     * 根据图片分类id获取该分类下的所有图片
     * @param image_type
     * @return
     */
    @Override
    public List<Pictures> getPicturesByTypeID(Integer pageSize, Integer pageNum, Integer image_type) {
        List<Pictures> pictures = null;
        PageHelper.startPage(pageNum,pageSize);
        if (Objects.isNull(image_type) || image_type == 0){
            pictures = picturesMapper.getALLPictures();
        }else{
            pictures = picturesMapper.getPicturesByTypeID(image_type);
        }
        PageInfo<Pictures> pageInfo = new PageInfo<>(pictures);
        logger.info("返回结果："+ pageInfo);
        return pictures;
    }

    /**
     * 根据图片分类名称获取该分类下的所有图片
     * @param type
     * @return
     */
    @Override
    public List<Pictures> getPicturesByTypeName(Integer pageSize, Integer pageNum, String type) {
        PageHelper.startPage(pageNum,pageSize);
        List<Pictures> pictures = picturesMapper.getPicturesByTypeName(type);
        PageInfo<Pictures> pageInfo = new PageInfo<>(pictures);
        logger.info("返回结果："+ pageInfo);
        return pictures;
    }

    /**
     * 根据图片分类id获取该分类的名称
     * @param id
     * @return
     */
    @Override
    public String getPictureTypeById(Integer id) {
        String pictureType = picturesMapper.getPictureTypeById(id);
        logger.info("返回结果："+ pictureType);
        return pictureType;
    }

    /**
     * 获取所有图片类型
     * @return
     */
    @Override
    public List<PicturesType> getALLPicturesType() {
        List<PicturesType> allPicturesType = picturesMapper.getALLPicturesType();
        logger.info("返回结果：" + allPicturesType);
        return allPicturesType;
    }

    /**
     * 获取所有图片中每个分类中的一条图片记录
     * @return
     */
    @Override
    public List<HashMap<String,Object>> getDistinctPicturesByType() {
        List<HashMap<String,Object>> result = new ArrayList<>();
        List<Pictures> pictures = picturesMapper.getDistinctPicturesByType();
        for (int i = 0 ;i < pictures.size();i++){
            HashMap<String,Object> item = new HashMap<>();
            item.put("is_main", pictures.get(i).getIs_main());
            item.put("height",pictures.get(i).getHeight());
            item.put("widht",pictures.get(i).getWidth());
            item.put("id",pictures.get(i).getId());
            item.put("image_desc",pictures.get(i).getImage_desc());
            item.put("image_url",pictures.get(i).getImage_url());
            int image_type = pictures.get(i).getImage_type();
            String typeName = picturesMapper.getPictureTypeById(image_type);
            item.put("typeId",image_type);
            item.put("typeName",typeName);
            result.add(item);
        }
        logger.info("返回结果："+ result);
        return result;
    }

    @Override
    public List<Pictures> getAllBackPicturesByTypeId() {
        List<Pictures> pictures = picturesMapper.getAllBackPicturesByTypeId();
        logger.info("返回结果："+ pictures);
        return pictures;
    }
}
