package fun.werfamily.template.biz.business;

import fun.werfamily.template.model.request.DemoReq;
import fun.werfamily.template.model.result.DemoDTO;

/**
 * Description:
 * <p>
 *
 *  @Author Mr.WenMing
 * Created on 2021/12/7.
 */
public interface DemoBiz {

    /**
     * 分包说明Demo
     *
     * @param demoReq 入参对象
     * @return 返回体
     */
    DemoDTO add(DemoReq demoReq);
}
