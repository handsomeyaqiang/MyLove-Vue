package com.itwang.showpictures.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @ProjectName: MyLove-Vue
 * @Package: com.itwang.showpictures.model.entity
 * @ClassName: PicturesType
 * @Author: wangyaqiang
 * @Description:
 * @Date: 2020/12/10 22:07
 * @Version: 1.0
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class PicturesType {
    private int id;
    private String type;
}
