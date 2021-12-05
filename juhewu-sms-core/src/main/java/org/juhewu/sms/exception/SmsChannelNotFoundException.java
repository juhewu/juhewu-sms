package org.juhewu.sms.exception;

import org.juhewu.core.base.exception.BusinessException;
import org.juhewu.core.base.exception.ICodeMessage;

/**
 * 短信渠道不存在异常
 * @author duanjw
 */
public class SmsChannelNotFoundException extends BusinessException {
    private static final long serialVersionUID = 1L;

    /**
     * 检查异常返回的错误吗
     */
    public static String CHECKED_EXCEPTION_CODE = "10";

    public SmsChannelNotFoundException(){
        super("短信渠道不存在");
    }
    public SmsChannelNotFoundException(String message) {
        super(message);
    }

    /**
     * 异常，有错误码和消息
     *
     * @param code
     */
    public SmsChannelNotFoundException(String code, String message) {
        super(code, message);
    }

    public SmsChannelNotFoundException(Throwable cause) {
        super(cause);
    }

    public SmsChannelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmsChannelNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * 异常，错误对象
     *
     * @param codeMessage
     */
    public SmsChannelNotFoundException(ICodeMessage codeMessage) {
        super(codeMessage);
    }

    /**
     * 错误码
     *
     * @return
     */
    @Override
    public String getCode() {
        return CHECKED_EXCEPTION_CODE;
    }
}
