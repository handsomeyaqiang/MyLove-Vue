package com.itwang.showpictures.mapper;

import com.itwang.showpictures.model.entity.Pictures;
import com.itwang.showpictures.model.entity.PicturesType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ProjectName: MyLove-Vue
 * @Package: com.itwang.showpictures.mapper
 * @ClassName: PicturesMapper
 * @Author: wangyaqiang
 * @Description:
 * @Date: 2020/12/10 17:14
 * @Version: 1.0
 */
@Mapper
public interface PicturesMapper {
    @Select("select t1.id,t1.image_url,t1.image_type,t1.image_desc,t1.width,t1.height,t1.is_main from mylove t1 order by t1.id")
    List<Pictures> getALLPictures();

    @Select("select t1.id,t1.image_url,t1.image_type,t1.image_desc,t1.width,t1.height,t1.is_main from mylove t1 " +
            "where t1.image_type = #{image_type} order by t1.id")
    List<Pictures> getPicturesByTypeID(@Param("image_type") int image_type);

    @Select("select t1.id,t1.image_url,t1.image_type,t1.image_desc,t1.width,t1.height,t1.is_main from mylove t1" +
            "left join lovetype t2 on t2.id = t1.image_type where t2.type = #{type} order by t1.id")
    List<Pictures> getPicturesByTypeName(@Param("type") String type);

    @Select("select type from lovetype where id = #{id} order by id")
    String getPictureTypeById(@Param("id") int id);

    @Select("select id,type from lovetype")
    List<PicturesType> getALLPicturesType();

    @Select("select t1.id,t1.image_url,t1.image_type,t1.image_desc,t1.width,t1.height,t1.is_main from mylove t1\n" +
            "where t1.is_main = 1 \n" +
            "ORDER BY t1.id asc")
    List<Pictures> getDistinctPicturesByType();

    @Select("select t1.id,t1.image_url,t1.image_type,t1.image_desc,t1.width,t1.height,t1.is_main from mylove t1\n" +
            "where t1.image_type = 14")
    List<Pictures> getAllBackPicturesByTypeId();
}
