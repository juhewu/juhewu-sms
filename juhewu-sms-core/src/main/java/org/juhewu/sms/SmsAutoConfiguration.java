//package org.juhewu.sms;
//
//import java.util.List;
//
//import javax.sql.DataSource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
///**
// * 短信自动配置
// *
// * @author duanjw
// */
//@Configuration
//public class SmsAutoConfiguration {
//
//    /**
//     * 内存中的短信账户
//     *
//     * @return
//     */
//    @Bean
//    public InMemorySmsAccountRepository inMemorySmsAccountStore() {
//        return new InMemorySmsAccountRepository();
//    }
//
//    /**
//     * jdbc的短信账户初始化到存储器里
//     *
//     * @param dataSource
//     * @param smsAccountRepository
//     * @return
//     */
//    @Bean
//    public InitJdbcSmsAccount2Repository initJdbcSmsAccount2Repository(DataSource dataSource, SmsAccountRepository smsAccountRepository) {
//        return new InitJdbcSmsAccount2Repository(dataSource, smsAccountRepository);
//    }
//
//    /**
//     * 配置文件中的短信账户
//     *
//     * @param smsAccessProperties
//     * @return
//     */
//    @Bean
//    public PropertiesSmsAccountLocator propertiesSmsAccountLocator(SmsAccessProperties smsAccessProperties) {
//        return new PropertiesSmsAccountLocator(smsAccessProperties);
//    }
//
//
//    /**
//     * 短信账户定位器，包括所有的短信定位器
//     *
//     * @param smsAccountLocators
//     * @return
//     */
//    @Bean
//    @Primary
//    public SmsAccountLocator smsAccountLocator(
//            List<SmsAccountLocator> smsAccountLocators) {
//        return new CompositeSmsAccountLocator(smsAccountLocators);
//    }
//
//    /**
//     * 短信渠道
//     * @param smsChannels
//     * @return
//     */
//    @Bean
//    public SmsChannelFactory smsChannelStrategy(List<SmsChannel> smsChannels) {
//        return new SmsChannelFactory(smsChannels);
//    }
//}
