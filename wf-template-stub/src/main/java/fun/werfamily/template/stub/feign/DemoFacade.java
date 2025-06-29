package fun.werfamily.template.stub.feign;

import fun.werfamily.base.response.Result;
import fun.werfamily.template.model.request.DemoReq;
import fun.werfamily.template.model.result.DemoDTO;
import fun.werfamily.template.stub.feign.fallbcak.DemoFacadeFallBack;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Description: Demo
 *
 *  @Author Mr.WenMing
 * Created on 2021/12/06.
 */
@FeignClient(name = "wf-template", fallback = DemoFacadeFallBack.class)
public interface DemoFacade {

    /**
     * demo 接口示例
     *
     * @param demoReq 请求对象
     * @return 返回体
     */
    @ApiOperation("Demo接口")
    @PostMapping(path = "/api/demo/add")
    Result<DemoDTO> add(@RequestBody DemoReq demoReq);


}
