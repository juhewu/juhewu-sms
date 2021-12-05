package org.juhewu.sms;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 聚合短信账户，获取短信账户从此获取
 *
 * @author duanjw
 */
public class CompositeSmsAccountLocator implements SmsAccountLocator {
    private final List<SmsAccountLocator> smsAccountLocators;

    public CompositeSmsAccountLocator(List<SmsAccountLocator> smsAccountLocators) {
        this.smsAccountLocators = smsAccountLocators;
    }

    /**
     * 所有短信账户
     *
     * @return^
     */
    @Override
    public List<SmsAccount> getSmsAccounts() {
        return smsAccountLocators.stream().flatMap(item -> item.getSmsAccounts().stream()).collect(Collectors.toList());
    }

    /**
     * 根据短信账户id获取短信账户
     *
     * @param id
     * @return
     */
    @Override
    public SmsAccount getSmsAccount(String id) {
        return smsAccountLocators.stream().map(item -> item.getSmsAccount(id)).filter(Objects::nonNull).findFirst().orElseThrow(NullPointerException::new);
    }
}
