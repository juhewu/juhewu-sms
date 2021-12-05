package org.juhewu.sms.baidu;

import java.util.HashMap;

import org.juhewu.sms.SmsAccount;
import org.juhewu.sms.SmsChannel;
import org.juhewu.sms.SmsRequest;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.SendMessageV2Request;
import com.baidubce.services.sms.model.SendMessageV2Response;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 百度短信渠道
 *
 * @author duanjw
 */
@Slf4j
public class BaiduSmsChannelImpl implements SmsChannel {
    /**
     * 短信client
     */
    private SmsClient smsClient;

    /**
     * 初始化客户端
     *
     * @param smsAccount
     */
    private void initClient(SmsAccount smsAccount) {
        // ak、sk等config
        SmsClientConfiguration config = new SmsClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(smsAccount.getAccess(), smsAccount.getSecret()));
        config.setEndpoint(smsAccount.getUrl());

        // 实例化发送客户端
        smsClient = new SmsClient(config);
    }

    @Override
    public String getChannelCode() {
        return "3";
    }

    @Override
    @SneakyThrows
    public boolean sendSms(SmsAccount smsAccount, SmsRequest smsRequest) {
        this.initClient(smsAccount);
        log.info("我是百度云，我调用百度云的发送短信");
        final SendMessageV2Request request = new SendMessageV2Request();
        final String context = smsRequest.getContext();
        request.withInvokeId(smsRequest.getSignName())
                .withPhoneNumber(smsRequest.getMobile())
                .withTemplateCode(smsRequest.getTemplate())
                .withContentVar(new HashMap<>(0));
        final SendMessageV2Response sendMessageV2Response = smsClient.sendMessage(request);

        return true;
    }
}
