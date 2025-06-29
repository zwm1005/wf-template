package fun.werfamily.template.service.impl;

import fun.werfamily.template.service.DemoService;
import fun.werfamily.core.util.annotation.WfLog;
import fun.werfamily.core.util.enums.WfLogLevel;
import fun.werfamily.core.util.enums.WfLogType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 * <p>
 *
 *  @Author Mr.WenMing
 * Created on 2021/12/7.
 */
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    @WfLog(value = "receive service ===>", type = WfLogType.SERVICE, level = WfLogLevel.INFO)
    public String getDemoTempDesc(String name) {
        return name + "(完成)";
    }
}
