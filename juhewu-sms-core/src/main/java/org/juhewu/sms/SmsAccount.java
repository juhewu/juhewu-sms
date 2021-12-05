package org.juhewu.sms;

import lombok.Data;

/**
 * 短信账户
 * @author duanjw
 */
@Data
public class SmsAccount {
    /**
     * 数据id，唯一
     */
    private String id;
    /**
     * 用户
     */
    private String access;
    /**
     * 密码
     */
    private String secret;
    /**
     * url，可不写
     */
    private String url;
    /**
     * 认证字符，如果渠道商只有一个认证参数，将参数写到此处
     */
    private String token;
    /**
     * 渠道名称（腾讯、阿里等）
     */
    private String channelCode;
    /**
     * 是否删除
     */
    private Integer deleted;
    /**
     * 租户id
     */
    private Long tenantId;
    /**
     * json格式参数
     */
    private String params;
}
