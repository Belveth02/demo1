package com.example.demo.Alipayfun;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@Slf4j
@AllArgsConstructor
public class EasyPayController {
    private final Config config;

    @GetMapping("/pay")
    public String pay(@RequestParam String orderName, @RequestParam String orderId, @RequestParam Double totalPrice) {
        try {
            log.info("Setting Alipay config with signType: {}", config.signType);
            Factory.setOptions(config);
            AlipayTradePrecreateResponse response = Factory.Payment.FaceToFace().preCreate(orderName, orderId, String.valueOf(totalPrice));
            String httpBody = response.getHttpBody();
            if (httpBody!= null) {
                JSONObject jsonObject = JSONObject.parseObject(httpBody);
                if (jsonObject.containsKey("alipay_trade_precreate_response")) {
                    JSONObject precreateResponse = jsonObject.getJSONObject("alipay_trade_precreate_response");
                    if (precreateResponse.containsKey("qr_code")) {
                        String qrUrl = precreateResponse.getString("qr_code");
                        if (qrUrl!= null) {
                            // 生成二维码
                            BufferedImage qrImage = QrCodeUtil.generate(qrUrl, 300, 300);
                            // 将BufferedImage转换为字节数组
                            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                            ImageIO.write(qrImage, "png", outputStream);
                            byte[] qrCodeBytes = outputStream.toByteArray();
                            outputStream.close();
                            // 将字节数组转换为Base64编码的字符串
                            String qrCodeBase64 = Base64.getEncoder().encodeToString(qrCodeBytes);
                            return qrCodeBase64;
                        } else {
                            log.error("qr_code value is null");
                        }
                    } else {
                        log.error("qr_code field not found in alipay_trade_precreate_response");
                    }
                } else {
                    log.error("alipay_trade_precreate_response not found in httpBody");
                }
            } else {
                log.error("httpBody is null");
            }
        } catch (Exception e) {
            log.error("Payment error", e);
        }
        return "支付过程中发生错误，请稍后重试";
    }
}