package fun.werfamily.template.facade.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @Author Mr.WenMing
 * @date 2021-12-10 16:16
 **/
@RestController
@Api(tags = "健康检查")
public class HealthCheckFacadeController {
    @ApiOperation(value = "SLB健康检测", notes = "只做健康检查,无任何作用")
    @GetMapping(value = "/slb/health")
    public void health() {
        //do nothing
    }
}
