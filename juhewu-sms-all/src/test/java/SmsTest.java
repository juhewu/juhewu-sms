import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.juhewu.sms.CompositeSmsAccountLocator;
import org.juhewu.sms.InMemorySmsAccountRepository;
import org.juhewu.sms.SmsAccount;
import org.juhewu.sms.SmsAccountLocator;
import org.juhewu.sms.SmsChannel;
import org.juhewu.sms.SmsChannelFactory;
import org.juhewu.sms.SmsRequest;
import org.juhewu.sms.ali.AliSmsChannelImpl;
import org.juhewu.sms.baidu.BaiduSmsChannelImpl;
import org.juhewu.sms.tencent.TencentSmsChannelImpl;
import org.juhewu.sms.yunpian.YunPianSmsChannelImpl;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmsTest {

    @Test
    public void smsAccountLocatorTest() {
        List<SmsAccount> smsAccountList = new ArrayList<>();
        SmsAccount smsAccount1 = new SmsAccount();
        SmsAccount smsAccount2 = new SmsAccount();
        SmsAccount smsAccount3 = new SmsAccount();
        SmsAccount smsAccount4 = new SmsAccount();

        smsAccount1.setId("1");
        smsAccount1.setChannelCode("1");
        smsAccountList.add(smsAccount1);

        smsAccount2.setId("2");
        smsAccount2.setChannelCode("2");
        smsAccountList.add(smsAccount2);

        smsAccount3.setId("3");
        smsAccount3.setChannelCode("3");
        smsAccountList.add(smsAccount3);

        smsAccount4.setId("4");
        smsAccount4.setChannelCode("4");
        smsAccountList.add(smsAccount4);

        SmsAccountLocator inMemorySmsAccountRepository = new InMemorySmsAccountRepository(smsAccountList);
        SmsAccountLocator smsAccountLocator = new CompositeSmsAccountLocator(Arrays.asList(inMemorySmsAccountRepository));


        // 短信渠道注册
        List<SmsChannel> smsChannels = new ArrayList<>();
        SmsChannel aliSmsChannel = new AliSmsChannelImpl();
        SmsChannel tencentSmsChannel = new TencentSmsChannelImpl();
        SmsChannel baiduSmsChannel = new BaiduSmsChannelImpl();
        SmsChannel yunPianSmsChannel = new YunPianSmsChannelImpl();
        smsChannels.add(aliSmsChannel);
        smsChannels.add(tencentSmsChannel);
        smsChannels.add(baiduSmsChannel);
        smsChannels.add(yunPianSmsChannel);
        SmsChannelFactory smsChannelFactory = new SmsChannelFactory(smsChannels);

        smsAccountLocator.getSmsAccounts().forEach(smsAccount->{
            SmsChannel smsChannel = smsChannelFactory.getSmsChannel(smsAccount.getChannelCode());

            try {
                smsChannel.sendSms(smsAccount, new SmsRequest());
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("获取到的短信账户{}"+ smsAccount);
        });
    }
}
