package net.vkits.platform.service;

import net.vkits.platform.dto.WxMulitConfigDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chi  2017-08-29 19:18
 **/
@Service
public class WxMulitConfigWebService {
    /**
     * 获得所有的多商户列表
     * @return
     */
    public List<WxMulitConfigDto> getAllWxConfig(){
        //TODO chi
        ArrayList<WxMulitConfigDto> list = new ArrayList<>();
        WxMulitConfigDto wxMulitConfigDto = WxMulitConfigDto.builder()
                .clientId("2")
                .appid("wx9c0a6457245a2c0e")
                .appsecret("7b6e299cef94aba338bd637c31cab6ab")
                .token("wezebra")
                .aesKey("4")
                .build();
        list.add(wxMulitConfigDto);

        WxMulitConfigDto wxMulitConfigDto2 = WxMulitConfigDto.builder()
                .clientId("1")
                .appid("wx49ab3262ed6427b8")
                .appsecret("75a9b1bd6e00aaf8b448ee11468d43a0")
                .token("wezebra")
                .aesKey("VnISrauPfxMVt3twwhErezR4Hm5eFe1rOCd9RFrON7L")
                .build();
        list.add(wxMulitConfigDto2);

        return list;
    }
}
