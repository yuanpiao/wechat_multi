package net.vkits.platform.wxService;

import net.vkits.platform.config.WxMulitConfig;
import net.vkits.platform.dto.WxMulitConfigDto;
import net.vkits.platform.service.WxMulitConfigWebService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class MulitConfigService {

    @Resource
    private WxMulitConfigWebService wxMulitConfigWebService;

    @Resource
    private ApplicationContext applicationContext;

    private ConcurrentHashMap<String, WxMulitService> hashMap = new ConcurrentHashMap<>();

    /**
     * @discription: 根据不同的clientId获得不同的wxservice
     * @author: chi
     * @Date: 18:59 2017/8/29/029
     */
    public WxMulitService getWxMulitService(String clientId){
        return hashMap.get(clientId);
    }

    @PostConstruct
    public void wxMulitServiceInit(){
        List<WxMulitConfigDto> wxMulitConfigDtos = wxMulitConfigWebService.getAllWxConfig();

        for (WxMulitConfigDto wxMulitConfigDto: wxMulitConfigDtos) {
            WxMulitService wxMulit = (WxMulitService)applicationContext.getBean("wxMulitService");
            WxMulitConfig wxConfig = new WxMulitConfig();
            wxConfig.setClientId(wxMulitConfigDto.getClientId());
            wxConfig.setAesKey(wxMulitConfigDto.getAesKey());
            wxConfig.setAppid(wxMulitConfigDto.getAppid());
            wxConfig.setAppsecret(wxMulitConfigDto.getAppsecret());
            wxConfig.setBillTemplateId(wxMulitConfigDto.getBillTemplateId());
            wxConfig.setToken(wxMulitConfigDto.getToken());
            wxMulit.setServerConfig(wxConfig);
            wxMulit.init();

            hashMap.put(wxMulitConfigDto.getClientId(),wxMulit);
        }



    }


}
