package fun.werfamily.template.biz.business.impl;

import fun.werfamily.template.biz.business.DemoBiz;
import fun.werfamily.template.model.request.DemoReq;
import fun.werfamily.template.model.result.DemoDTO;
import fun.werfamily.template.service.DemoService;
import fun.werfamily.base.exception.WfBaseException;
import fun.werfamily.base.response.SysResponseCode;
import fun.werfamily.core.util.annotation.WfLog;
import fun.werfamily.core.util.enums.WfLogLevel;
import fun.werfamily.core.util.enums.WfLogType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * <p>
 *
 *  @Author Mr.WenMing
 * Created on 2021/12/7.
 */
@Service
@Slf4j
public class DemoBizImpl implements DemoBiz {

    @Autowired
    private DemoService demoService;

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @Override
    @WfLog(value = "demobiz", type = WfLogType.BIZ, level = WfLogLevel.INFO)
    public DemoDTO add(DemoReq demoReq) {
        if (StringUtils.equals("Test", demoReq.getName())) {
            Map<String, String> params = new HashMap<>(1);
            params.put("detail", "不能包含关键字");
            throw new WfBaseException(SysResponseCode.ERROR_PARAM, params);
        }
        String result = demoService.getDemoTempDesc(demoReq.getName());
        return new DemoDTO(1L, result, "description");
    }
}
