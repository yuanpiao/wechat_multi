package net.vkits.platform.wxService;

import net.vkits.platform.config.WxConfig;
import net.vkits.platform.config.WxMulitConfig;
import net.vkits.platform.handler.*;
import net.vkits.platform.handler.mulit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author chi  2017-08-29 18:33
 **/
@Component
@Scope("prototype")//设置为多例
public class WxMulitService extends BaseWxService {

    private WxMulitConfig wxConfig = new WxMulitConfig();

    protected WxMulitService setServerConfig(WxMulitConfig wxConfig) {
        this.wxConfig = wxConfig;
        return this;
    }

    @Autowired
    private MulitLocationHandler locationHandler;

    @Autowired
    private MulitMenuHandler menuHandler;

    @Autowired
    private MulitMsgHandler msgHandler;

    @Autowired
    private MulitUnSubscribeHandler unSubscribeHandler;

    @Autowired
    private MulitSubscribeHandler subscribeHandler;
    @Autowired
    private MulitScanHandler scanHandler;

    @Override
    protected WxConfig getServerConfig() {
        return this.wxConfig;
    }

    @Override
    protected MenuHandler getMenuHandler() {
        this.menuHandler.setWxConfig(wxConfig);
        return this.menuHandler;
    }

    @Override
    protected SubscribeHandler getSubscribeHandler() {
        this.subscribeHandler.setWxConfig(wxConfig);
        return this.subscribeHandler;
    }

    @Override
    protected UnsubscribeHandler getUnsubscribeHandler() {
        this.unSubscribeHandler.setWxConfig(wxConfig);
        return this.unSubscribeHandler;
    }

    @Override
    protected AbstractHandler getLocationHandler() {
        this.locationHandler.setWxConfig(wxConfig);
        return this.locationHandler;
    }

    @Override
    protected MsgHandler getMsgHandler() {
        this.msgHandler.setWxConfig(wxConfig);
        return this.msgHandler;
    }

    @Override
    protected AbstractHandler getScanHandler() {
        this.scanHandler.setWxConfig(wxConfig);
        return this.scanHandler;
    }

}
