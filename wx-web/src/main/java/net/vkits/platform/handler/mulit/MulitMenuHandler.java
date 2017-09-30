package net.vkits.platform.handler.mulit;

import net.vkits.platform.config.WxConfig;
import net.vkits.platform.handler.MenuHandler;
import org.springframework.stereotype.Component;

@Component
public class MulitMenuHandler extends MenuHandler {

  private WxConfig wxConfig;

  @Override
  protected WxConfig getWxConfig() {
    return this.wxConfig;
  }

  public void setWxConfig(WxConfig wxConfig) {
    this.wxConfig = wxConfig;
  }
}
