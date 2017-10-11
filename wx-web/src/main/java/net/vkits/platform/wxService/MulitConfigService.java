package net.vkits.platform.wxService;

import net.vkits.platform.config.WxMulitConfig;
import net.vkits.platform.dto.WxMulitConfigDto;
import net.vkits.platform.service.WxMulitConfigWebService;
import net.vkits.platform.util.SpringUtil;
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

    /**
     * 缓存商户id和对应的wxmulitService,如果集群条件下可以使用redis等缓存
     */
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

            WxMulitConfig wxConfig = WxMulitConfig.builder()
                    .clientId(wxMulitConfigDto.getClientId())
                    .aesKey(wxMulitConfigDto.getAesKey())
                    .appid(wxMulitConfigDto.getAppid())
                    .appsecret(wxMulitConfigDto.getAppsecret())
                    .token(wxMulitConfigDto.getToken())
                    .billTemplateId(wxMulitConfigDto.getBillTemplateId())
                    .templateId(wxMulitConfigDto.getTemplateId())
                    .build();

            wxMulit.setServerConfig(wxConfig).init();
            hashMap.put(wxMulitConfigDto.getClientId(),wxMulit);
        }
    }


}
