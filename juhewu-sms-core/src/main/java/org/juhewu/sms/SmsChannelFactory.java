package org.juhewu.sms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.juhewu.core.base.annotation.BeanAlias;
import org.juhewu.sms.exception.SmsChannelCodeDuplicateException;
import org.juhewu.sms.exception.SmsChannelNotFoundException;

/**
 * 短信渠道工厂
 *
 * @author duanjw
 */
public class SmsChannelFactory {

    private Map<String, SmsChannel> smsChannelMap = new HashMap<>(3);

    /**
     * 初始化所有短信渠道
     *
     * @param smsChannels
     */
    public SmsChannelFactory(List<SmsChannel> smsChannels) {
        smsChannels.forEach(smsChannel -> {
            // 获取 code，否则使用类名作为 code
            String name = Optional.ofNullable(smsChannel.getChannelCode())
                    .orElse(smsChannel.getClass().getSimpleName());

            if (smsChannelMap.containsKey(name)) {
                throw new SmsChannelCodeDuplicateException();
            }
            smsChannelMap.put(name, smsChannel);
        });
    }

    /**
     * 根据key获取短信渠道
     *
     * @param key
     * @return
     */
    public SmsChannel getSmsChannel(String key) {
        // 如果取不到对应渠道，抛出短信渠道不存在异常
        return Optional.ofNullable(smsChannelMap.get(key)).orElseThrow(SmsChannelNotFoundException::new);
    }
}
