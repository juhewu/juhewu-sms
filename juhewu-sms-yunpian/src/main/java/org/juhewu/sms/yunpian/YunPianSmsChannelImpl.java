package org.juhewu.sms.yunpian;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.juhewu.core.base.annotation.BeanAlias;
import org.juhewu.sms.SmsAccount;
import org.juhewu.sms.SmsChannel;
import org.juhewu.sms.SmsRequest;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;

import cn.hutool.json.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 云片短信渠道
 *
 * @author duanjw
 */
@Slf4j
public class YunPianSmsChannelImpl implements SmsChannel {

    /**
     * 短信client
     */
    YunpianClient yunpianClient;

    /**
     * 初始化客户端
     *
     * @param smsAccount
     */
    private void initClient(SmsAccount smsAccount) {
        // 初始化客户端
        yunpianClient = new YunpianClient(smsAccount.getSecret()).init();
    }

    @Override
    public String getChannelCode() {
        return "4";
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
        Map params = new HashMap(2);
        params.put("mobile", smsRequest.getMobile());
        // 替换邮件内容中的占位符
        final Map map = new JSONObject(smsRequest.getContext()).toBean(Map.class);
        final String content = new StringSubstitutor(map).replace(smsRequest.getTemplate());
        params.put("text", "【" + smsRequest.getSignName() + "】" + content);
        log.info("调用云片的发送短信");
        final Result result = yunpianClient.sms().batch_send(params);
        log.info("云片发送短信返回code码：【{}】，消息：【{}】", result.getCode(), result.getMsg());
        return true;
    }
}
