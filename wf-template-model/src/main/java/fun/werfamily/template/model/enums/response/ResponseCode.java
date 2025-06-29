package fun.werfamily.template.model.enums.response;

import fun.werfamily.base.response.IResponseCode;

/**
 * Description: 应用统一返回Code码
 * <p>
 *
 *  @Author Mr.WenMing
 * Created on 2021/12/10.
 */
public enum ResponseCode implements IResponseCode {
    /**
     * 处理成功
     */
    OK(200, "处理成功", "处理成功");

    private final int code;
    private final String friendlyMsg;
    private final String logMsg;

    ResponseCode(int code, String friendlyMsg) {
        this.code = code;
        this.friendlyMsg = friendlyMsg;
        this.logMsg = friendlyMsg;
    }

    ResponseCode(int code, String friendlyMsg, String logMsg) {
        this.code = code;
        this.friendlyMsg = friendlyMsg;
        this.logMsg = logMsg;
    }

    public static ResponseCode codeOf(int code) {
        for (ResponseCode value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid ResponseCode code: " + code);
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String friendlyMsg() {
        return friendlyMsg;
    }

    @Override
    public String logMsg() {
        return logMsg;
    }

}
