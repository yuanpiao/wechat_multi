package net.vkits.platform.handler.mulit;


import net.vkits.platform.application.wechat.config.WxConfig;
import net.vkits.platform.application.wechat.handler.LocationHandler;
import org.springframework.stereotype.Component;

@Component
public class MulitLocationHandler extends LocationHandler {

  private WxConfig wxConfig;

  @Override
  protected WxConfig getWxConfig() {
    return this.wxConfig;
  }

  public void setWxConfig(WxConfig wxConfig) {
    this.wxConfig = wxConfig;
  }
}
