package org.juhewu.sms;

import java.util.List;

/**
 * @author duanjw
 */
public interface SmsAccountLocator {
    List<SmsAccount> getSmsAccounts();

    SmsAccount getSmsAccount(String id);
}
