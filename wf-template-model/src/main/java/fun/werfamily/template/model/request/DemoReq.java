package fun.werfamily.template.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Description:
 * <p>
 *
 *  @Author Mr.WenMing
 * Created on 2021/12/7.
 */
@Data
public class DemoReq {

    @NotBlank(message = "名称不能为空！")
    private String name;

}
