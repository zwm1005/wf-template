package fun.werfamily.template.stub.feign.fallbcak;

import fun.werfamily.template.model.request.DemoReq;
import fun.werfamily.template.model.result.DemoDTO;
import fun.werfamily.template.stub.feign.DemoFacade;
import com.alibaba.fastjson.JSON;
import fun.werfamily.base.response.Result;
import fun.werfamily.base.response.SysResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Description: 降级回调
 * <p>
 *
 *  @Author Mr.WenMing
 * Created on 2021/12/6.
 */
@Component
@Slf4j
public class DemoFacadeFallBack implements DemoFacade {

    @Override
    public Result<DemoDTO> add(DemoReq demoReq) {
        log.error("feign fail param {} code {} ", JSON.toJSONString(demoReq), SysResponseCode.TRIGGER_FUSE.logMsg());
        return Result.error(SysResponseCode.TRIGGER_FUSE, null);
    }
}
