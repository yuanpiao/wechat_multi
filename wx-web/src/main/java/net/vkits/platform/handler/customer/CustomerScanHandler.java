package net.vkits.platform.handler.customer;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import net.vkits.platform.application.counter.CustomerMessageService;
import net.vkits.platform.application.wechat.builder.TextBuilder;
import net.vkits.platform.application.wechat.config.WxConfig;
import net.vkits.platform.application.wechat.config.WxCustomerConfig;
import net.vkits.platform.application.wechat.handler.ScanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by duqingxiang on 17/5/22.
 */
@Component
public class CustomerScanHandler extends ScanHandler {
    @Autowired
    WxCustomerConfig wxCustomerConfig;
    @Autowired
    CustomerMessageService customerMessageService;

    @Override
    protected WxConfig getWxConfig() {
        return this.wxCustomerConfig;
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

        return new TextBuilder().build(customerMessageService.getScanQRCodeMessage(wxMessage.getFromUser(),Integer.valueOf(wxMessage.getEventKey())), wxMessage, null);
    }
}
