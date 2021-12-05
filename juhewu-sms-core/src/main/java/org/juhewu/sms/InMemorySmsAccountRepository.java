package org.juhewu.sms;

import static java.util.Collections.synchronizedMap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 内存中的短信账户
 *
 * @author duanjw
 */
public class InMemorySmsAccountRepository implements SmsAccountRepository {
    public InMemorySmsAccountRepository(){

    }
    public InMemorySmsAccountRepository(SmsAccount smsAccount){
        this.add(smsAccount);
    }
    public InMemorySmsAccountRepository(List<SmsAccount> smsAccounts){
        smsAccounts.forEach(this::add);
    }
    private final Map<String, SmsAccount> smsAccounts = synchronizedMap(
            new LinkedHashMap());

    /**
     * 内存中的所有短信账户
     * @return
     */
    @Override
    public List<SmsAccount> getSmsAccounts() {
        return new ArrayList<>(smsAccounts.values());
    }

    /**
     * 根据短信账户id获取短信账户
     * @param id
     * @return
     */
    @Override
    public SmsAccount getSmsAccount(String id) {
        return smsAccounts.get(id);
    }

    /**
     * 新增短信账户
     * @param smsAccount
     */
    @Override
    public void add(SmsAccount smsAccount) {
        smsAccounts.put(smsAccount.getId(), smsAccount);
    }

    /**
     * 根据id删除短信账户
     * @param id
     */
    @Override
    public void delete(String id) {
        smsAccounts.remove(id);
    }
}
