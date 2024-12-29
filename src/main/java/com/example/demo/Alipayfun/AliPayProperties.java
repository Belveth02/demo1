package com.example.demo.Alipayfun;
import com.alipay.easysdk.kernel.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "alipay.easy")
public class AliPayProperties {
    // 请求协议
    private String protocol;
    // 请求网关
    private String gatewayHost;
    // 签名类型 RSA2
    private String signType;
    // 应用id
    private String appId;
    // 应用私钥
    private String merchantPrivateKey;
    // 支付宝公钥
    private String alipayPublicKey;
    // 异步通知接收服务器地址
    private String notifyUrl;
    // 设置AES密钥
    private String encryptKey;

    @Bean
    public Config alipayConfigNew() {
        Config config = new Config();
        config.protocol = protocol;
        config.gatewayHost = gatewayHost;
        config.signType = signType;
        config.appId = appId;
        config.merchantPrivateKey = merchantPrivateKey;
        config.alipayPublicKey = alipayPublicKey;
        config.notifyUrl = notifyUrl;
        config.encryptKey = encryptKey;
        return config;
    }
}