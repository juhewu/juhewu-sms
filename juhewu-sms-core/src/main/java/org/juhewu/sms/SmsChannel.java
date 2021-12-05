package org.juhewu.sms;

/**
 * 短信
 * @author duanjw
 */
public interface SmsChannel {
    String getChannelCode();
    /**
     * 发送短信
     * @param smsAccount
     * @param smsRequest
     * @return
     */
    boolean sendSms(SmsAccount smsAccount, SmsRequest smsRequest);
}
