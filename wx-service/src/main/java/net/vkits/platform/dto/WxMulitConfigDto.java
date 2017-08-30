package net.vkits.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @discription: 传递微信多商户的dto
 * @author: chi
 * @Date: 19:17 2017/8/29/029
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WxMulitConfigDto {

    private String clientId;

    private String token;

    private String appid;

    private String appsecret;

    private String aesKey;

    private String templateId;

    private String billTemplateId;

}
