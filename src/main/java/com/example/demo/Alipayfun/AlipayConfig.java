package com.example.demo.Alipayfun;


import com.alipay.easysdk.kernel.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.Data;


@Configuration
@Data
@Slf4j
public class AlipayConfig {
    @Bean
    public Config config(AliPayProperties payProperties) {
        Config config = new Config();
        config.protocol = payProperties.getProtocol();
        config.gatewayHost = payProperties.getGatewayHost();
        config.signType = payProperties.getSignType();
        config.appId = payProperties.getAppId();
        config.alipayPublicKey = payProperties.getAlipayPublicKey();
        config.merchantPrivateKey = payProperties.getMerchantPrivateKey();
        config.notifyUrl = payProperties.getNotifyUrl();
        config.encryptKey = "";
        log.info("Loaded signType: {}", config.signType);
        return config;
    }
}