package org.juhewu.sms;

/**
 * 短信账户修改
 *
 * @author duanjw
 */
public interface SmsAccountWriter {
    /**
     * 新增短信账户
     * @param smsAccount
     */
    void add(SmsAccount smsAccount);

    /**
     * 根据id删除短信账户
     * @param id
     */
    void delete(String id);
}
