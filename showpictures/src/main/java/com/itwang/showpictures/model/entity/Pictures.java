package com.itwang.showpictures.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @ProjectName: MyLove-Vue
 * @Package: com.itwang.showpictures.model.entity
 * @ClassName: Pictures
 * @Author: wangyaqiang
 * @Description:
 * @Date: 2020/12/10 17:12
 * @Version: 1.0
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Pictures {
    private int id;
    private String image_url;
    private int image_type;
    private String image_desc;
    private int width;
    private int height;
    private int is_main;

}
