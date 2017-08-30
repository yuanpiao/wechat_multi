package net.vkits.platform.manager;

import net.vkits.platform.config.WxMulitConfig;
import net.vkits.platform.dto.WxMulitConfigDto;
import net.vkits.platform.service.WxMulitConfigWebService;
import net.vkits.platform.util.SpringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class MulitConfigService {

    @Resource
    private WxMulitConfigWebService wxMulitConfigWebService;

    private ConcurrentHashMap<String, WxMulitService> hashMap = new ConcurrentHashMap<>();

    /**
     * @discription:
     * @author: chi
     * @Date: 18:59 2017/8/29/029
     */
    public WxMulitService getWxMulitService(String clientId){
        return hashMap.get(clientId);
    }

    @PostConstruct
    public void wxMulitServiceInit(){
        List<WxMulitConfigDto> wxMulitConfigDtos = wxMulitConfigWebService.getAllWxConfig();

      /*  wxMulitConfigDtos.stream()
                .peek(wxMulitConfigDto ->
                        hashMap.put(wxMulitConfigDto.getClientId(),new WxMulitService(wxMulitConfigDto)));
    */

        for (WxMulitConfigDto wxMulitConfigDto: wxMulitConfigDtos) {
            WxMulitService wxMulit = (WxMulitService)SpringUtil.getBean("wxMulitService");
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
