package net.vkits.platform.handler.mulit;


import net.vkits.platform.config.WxConfig;
import net.vkits.platform.handler.UnsubscribeHandler;
import org.springframework.stereotype.Component;

@Component
public class MulitUnSubscribeHandler extends UnsubscribeHandler {

  private WxConfig wxConfig;

  @Override
  protected WxConfig getWxConfig() {
    return this.wxConfig;
  }

  public void setWxConfig(WxConfig wxConfig) {
    this.wxConfig = wxConfig;
  }
}
