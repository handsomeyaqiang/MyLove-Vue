package com.itwang.showpictures.controller;

import com.itwang.showpictures.common.enums.ResultStatusEnum;
import com.itwang.showpictures.model.dto.Result;
import com.itwang.showpictures.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @ProjectName: MyLove-Vue
 * @Package: com.itwang.showpictures.controller
 * @ClassName: PicturesController
 * @Author: wangyaqiang
 * @Description:
 * @Date: 2020/12/10 16:07
 * @Version: 1.0
 */
@RequestMapping(value = "/pictures")
@RestController
public class PicturesController {
    private Logger logger = LoggerFactory.getLogger(PicturesController.class);
    @Autowired
    PictureService pictureService;

    @RequestMapping("/getPicturesByTypeID")
    public Result getPicturesByTypeID(@RequestParam(required = false, defaultValue = "8", value = "pageSize")Integer pageSize, @RequestParam(required = false, defaultValue = "1")Integer pageNum, Integer image_type){
        logger.info("请求：/pictures/getPicturesByTypeID, Params: pageSize={},pageNum={},image_type={}",pageSize,pageNum,image_type);
        Result result;
        try{
            if (pageSize <= 0)
                pageSize = 8;
            if (pageNum <= 0 )
                pageNum = 1;
            result = Result.success(pictureService.getPicturesByTypeID(pageSize,pageNum,image_type));
        }catch (Exception pe){
            logger.error("请求出错信息：" + pe.getMessage());
            result = Result.error(ResultStatusEnum.ERROR.getCode(), ResultStatusEnum.ERROR.getMessage());
        }
        logger.info("返回结果：" + result);
        return result;
    }
    @RequestMapping("/getPicturesByTypeName")
    public Result getPicturesByTypeName(Integer pageSize, Integer pageNum, String type){
        logger.info("请求：/pictures/getPicturesByTypeName, Params: pageSize={},pageNum={},type={}",pageSize,pageNum,type);
        Result result;
        try {
            if (pageSize <= 0)
                pageSize = 8;
            if (pageNum <= 0 )
                pageNum = 1;
            result =  Result.success(pictureService.getPicturesByTypeName(pageSize, pageNum, type));
        }catch (Exception pe){
            logger.error("请求出错信息：" + pe.getMessage());
            result = Result.error(ResultStatusEnum.ERROR.getCode(), ResultStatusEnum.ERROR.getMessage());
        }
        logger.info("返回结果：" + result);
        return result;
    }
    @GetMapping("/getPictureTypeById")
    public Result getPictureTypeById(Integer id){
        logger.info("请求：/pictures/getPictureTypeById, Params: id={}", id);
        Result result;
        try {
            result  =  Result.success(pictureService.getPictureTypeById(id));
        }catch (Exception pe){
            logger.error("请求出错信息：" + pe.getMessage());
            result = Result.error(ResultStatusEnum.ERROR.getCode(), ResultStatusEnum.ERROR.getMessage());
        }
        logger.info("返回结果：" + result);
        return result;
    }
    @RequestMapping("/getAllPicturesType")
    public Result getALLPicturesType(){
        logger.info("请求：/pictures/getAllPicturesType");
        Result result;
        try{
            result =  Result.success(pictureService.getALLPicturesType());
        }catch (Exception e){
            logger.error("请求出错信息：" + e.getMessage());
            result =  Result.error(ResultStatusEnum.ERROR.getCode(), ResultStatusEnum.ERROR.getMessage());
        }
        logger.info("返回结果：" + result);
        return result;
    }
    @RequestMapping("/getDistinctPicturesByType")
    public Result getDistinctPicturesByType(){
        logger.info("请求：/pictures/getDistinctPicturesByType");
        Result result;
        List<HashMap<String, Object>> pictures = null;
        try {
            pictures = pictureService.getDistinctPicturesByType();
            result =  Result.success(pictures);
        }catch (Exception e){
            logger.error("请求出错信息：" + e.getMessage());
            result = Result.error(ResultStatusEnum.ERROR.getCode(), ResultStatusEnum.ERROR.getMessage());
        }
        logger.info("返回结果：" + result);
        return result;
    }

    @RequestMapping("/getAllBackPictures")
    public Result getAllBackPictures(){
        logger.info("请求：/pictures/getAllBackPictures");
        Result result;
        try {
            result = Result.success(pictureService.getAllBackPicturesByTypeId());
        }catch (Exception e){
            logger.error("请求出错信息："+ e.getMessage());
            result = Result.error(ResultStatusEnum.ERROR.getCode(),ResultStatusEnum.ERROR.getMessage());
        }
        logger.info("返回结果："+ result);
        return result;

    }
}
