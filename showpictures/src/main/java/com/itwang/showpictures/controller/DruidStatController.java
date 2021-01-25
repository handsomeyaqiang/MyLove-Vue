package com.itwang.showpictures.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: MyLove-Vue
 * @Package: com.itwang.showpictures.controller
 * @ClassName: DruidStatController
 * @Author: wangyaqiang
 * @Description:
 * @Date: 2020/12/26 17:02
 * @Version: 1.0
 */
@RestController
public class DruidStatController {
    private Logger logger = LoggerFactory.getLogger(DruidStatController.class);
    @GetMapping("/durid/stat")
    public Object druidStat(){
        logger.info("请求：/durid/stat");
        // DruidStatManagerFacade#getDataSourceStatDataList 该方法可以获取所有数据源的监控数据，除此之外 DruidStatManagerFacade 还提供了一些其他方法，你可以按需选择使用。
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
}
