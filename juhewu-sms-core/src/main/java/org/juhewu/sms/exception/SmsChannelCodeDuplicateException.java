package org.juhewu.sms.exception;

import org.juhewu.core.base.exception.BusinessException;
import org.juhewu.core.base.exception.ICodeMessage;

/**
 * 短信渠道不存在异常
 * @author duanjw
 */
public class SmsChannelCodeDuplicateException extends BusinessException {
    private static final long serialVersionUID = 1L;

    /**
     * 检查异常返回的错误吗
     */
    public static String CHECKED_EXCEPTION_CODE = "10";

    public SmsChannelCodeDuplicateException(){
        super("短信渠道 key 重复");
    }
    public SmsChannelCodeDuplicateException(String message) {
        super(message);
    }

    /**
     * 异常，有错误码和消息
     *
     * @param code
     */
    public SmsChannelCodeDuplicateException(String code, String message) {
        super(code, message);
    }

    public SmsChannelCodeDuplicateException(Throwable cause) {
        super(cause);
    }

    public SmsChannelCodeDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmsChannelCodeDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * 异常，错误对象
     *
     * @param codeMessage
     */
    public SmsChannelCodeDuplicateException(ICodeMessage codeMessage) {
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
