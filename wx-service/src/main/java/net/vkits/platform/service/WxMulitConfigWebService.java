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
                .appid("wx9c0a64*****")
                .appsecret("7b6e299cef94a******")
                .token("token3")
                .aesKey("4")
                .build();
        list.add(wxMulitConfigDto);

        WxMulitConfigDto wxMulitConfigDto2 = WxMulitConfigDto.builder()
                .clientId("1")
                .appid("wx49ab326*****")
                .appsecret("75a9b1bd6e00aa*****")
                .token("token4")
                .aesKey("VnISrauPfxMVt3twwhErez******")
                .build();
        list.add(wxMulitConfigDto2);

        return list;
    }
}
