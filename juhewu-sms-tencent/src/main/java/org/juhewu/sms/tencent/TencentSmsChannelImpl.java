package org.juhewu.sms.tencent;

import java.util.ArrayList;
import java.util.List;

import org.juhewu.core.base.annotation.BeanAlias;
import org.juhewu.sms.SmsAccount;
import org.juhewu.sms.SmsChannel;
import org.juhewu.sms.SmsRequest;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;

import cn.hutool.json.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 腾讯短信渠道
 *
 * @author duanjw
 */
@Slf4j
@BeanAlias("1")
public class TencentSmsChannelImpl implements SmsChannel {

    /**
     * 阿里云短信client
     */
    SmsMultiSender smsMultiSender;

    /**
     * 初始化客户端
     *
     * @param smsAccount
     */
    private void initClient(SmsAccount smsAccount) {
        // 创建DefaultAcsClient实例并初始化
        smsMultiSender = new SmsMultiSender(Integer.parseInt(smsAccount.getAccess()), smsAccount.getSecret());
    }

    @Override
    public String getChannelCode() {
        return "1";
    }

    /**
     * 腾讯发短信
     *
     * @param smsAccount
     * @param smsRequest
     * @return
     */
    @Override
    @SneakyThrows
    public boolean sendSms(SmsAccount smsAccount, SmsRequest smsRequest) {
        this.initClient(smsAccount);
        final JSONObject jsonObject = new JSONObject(smsRequest.getContext());

        // 参数转数组格式
        List<String> paramList = new ArrayList();
        jsonObject.entrySet().forEach(item -> paramList.add(item.getValue().toString()));
        String[] params = new String[paramList.size()];

        log.info("调用腾讯云的发送短信");
        final SmsMultiSenderResult smsMultiSenderResult = smsMultiSender
                .sendWithParam("86", smsRequest.getMobile().split(","), Integer.parseInt(smsRequest.getTemplate()), paramList.toArray(params),
                        smsRequest.getSignName(), "", "");
        log.info("腾讯云发送短信返回code码：【{}】，消息：【{}】", smsMultiSenderResult.getResponse().statusCode, smsMultiSenderResult.getResponse().body);
        return true;
    }
}
