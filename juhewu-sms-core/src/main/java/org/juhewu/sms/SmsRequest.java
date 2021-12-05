package org.juhewu.sms;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 短信请求参数
 * @author duanjw
 */
@Accessors(chain = true)
@Data
public class SmsRequest {
    private String mobile;
    private String context;
    private String channel;
    private String type;
    private String signName;
    private String template;
    private String templateContent;
}
