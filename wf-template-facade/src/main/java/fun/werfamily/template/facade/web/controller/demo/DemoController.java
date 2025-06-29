package fun.werfamily.template.facade.web.controller.demo;

import fun.werfamily.template.biz.business.DemoBiz;
import fun.werfamily.template.model.request.DemoReq;
import fun.werfamily.template.model.result.DemoDTO;
import fun.werfamily.base.response.Result;
import fun.werfamily.core.util.annotation.WfLog;
import fun.werfamily.core.util.enums.WfLogLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * <p>
 *
 *  @Author Mr.WenMing
 * Created on 2021/12/7.
 */
@RestController
@RequestMapping("/api/demo")
@Api(tags = "DEMO")
public class DemoController {

    @Autowired
    private DemoBiz demoBiz;


    @ApiOperation("demo接口")
    @PostMapping("/add1")
    public Result<DemoDTO> add1(@Validated @RequestBody DemoReq demoReq) {
        return Result.success(demoBiz.add(demoReq));
    }

    @ApiOperation("demo接口")
    @PostMapping("/add")
    @WfLog
    public Result<DemoDTO> add(@Validated @RequestBody DemoReq demoReq) {
        return Result.success(demoBiz.add(demoReq));
    }

    @ApiOperation("demo接口")
    @PostMapping("/del")
    @WfLog(value = "删除数据", level = WfLogLevel.INFO)
    public Result<DemoDTO> del(@Validated @RequestBody DemoReq demoReq) {
        return Result.success(demoBiz.add(demoReq));
    }


}
