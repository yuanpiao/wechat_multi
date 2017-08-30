package net.vkits.platform.handler.agent;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import net.vkits.platform.builder.TextBuilder;
import net.vkits.platform.config.WxConfig;
import net.vkits.platform.handler.ScanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @discription: TODO chi 待实现
 * @author: chi
 * @Date: 19:31 2017/8/30/030
 */
@Component
public class AgentScanHandler extends ScanHandler {
    @Override
    protected WxConfig getWxConfig() {
        return null;
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        return null;
    }
    /*@Autowired
    WxCustomerConfig wxCustomerConfig;
    @Autowired
    AgentMessageService agentMessageService;

    @Override
    protected WxConfig getWxConfig() {
        return this.wxCustomerConfig;
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

        return new TextBuilder().build(agentMessageService.getScanQRCodeMessage(Integer.valueOf(wxMessage.getEventKey()),wxMessage), wxMessage, null);
    }*/
}
