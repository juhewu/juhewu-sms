package org.juhewu.sms.ali;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.juhewu.core.base.annotation.BeanAlias;
import org.juhewu.sms.SmsAccount;
import org.juhewu.sms.SmsChannel;
import org.juhewu.sms.SmsRequest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.utils.StringUtils;

import cn.hutool.json.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 阿里云短信渠道
 *
 * @author duanjw
 */
@Slf4j
@BeanAlias("2")
public class AliSmsChannelImpl implements SmsChannel {

    /**
     * 阿里云短信client
     */
    private IAcsClient iAcsClient;
    public static Pattern dynamicLimitCount = Pattern.compile("\\$\\{([a-z]+)\\}");

    /**
     * 初始化客户端
     *
     * @param smsAccount
     */
    private void initClient(SmsAccount smsAccount) {
        // 创建DefaultAcsClient实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsAccount.getAccess(), smsAccount.getSecret());
        iAcsClient = new DefaultAcsClient(profile);
    }

    @Override
    public String getChannelCode() {
        return "2";
    }

    /**
     * 阿里发送短信
     *
     * @param smsAccount
     * @param smsRequest
     * @return
     */
    @Override
    @SneakyThrows
    public boolean sendSms(SmsAccount smsAccount, SmsRequest smsRequest) {
        initClient(smsAccount);
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(smsRequest.getMobile());
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(smsRequest.getSignName());
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(smsRequest.getTemplate());
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${value},您的验证码为${code}"
        String context = smsRequest.getContext();

        Set<String> templateCodes = getTemplateCodesByTemplateContent(smsRequest.getTemplateContent());

        // 参数长度不能超过20字符
        // 模板内容中有参数，只取参数对应的值
        JSONObject jsonObject = new JSONObject(context);
        JSONObject result = new JSONObject(jsonObject.size());
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            if (templateCodes != null && !templateCodes.contains(key)) {
                continue;
            }
            if (value.length() > 20) {
                value = value.substring(0, 20);
            }
            result.append(key, value);
        }

        request.setTemplateParam(result.toString());
        request.setOutId(smsRequest.getMobile());
        log.debug("调用阿里云的发送短信，参数：{}", result);
        final SendSmsResponse acsResponse = iAcsClient.getAcsResponse(request);
        log.info("阿里云发送短信返回code码：【{}】，消息：【{}】", acsResponse.getCode(), acsResponse.getMessage());
        return true;
    }

    /**
     * 根据内容获取模板中的参数
     *
     * @param templateContent
     * @return
     */
    private Set<String> getTemplateCodesByTemplateContent(String templateContent) {
        Set<String> templateCodes = null;
        if (!StringUtils.isEmpty(templateContent)) {
            templateCodes = new HashSet<>();
            Matcher matcher = dynamicLimitCount.matcher(templateContent);
            while (matcher.find()) {
                templateCodes.add(matcher.group(1));
            }
        }
        return templateCodes;
    }
}
