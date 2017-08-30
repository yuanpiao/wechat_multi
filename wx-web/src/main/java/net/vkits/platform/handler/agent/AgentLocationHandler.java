package net.vkits.platform.handler.agent;


import net.vkits.platform.application.wechat.config.WxAgentConfig;
import net.vkits.platform.application.wechat.config.WxConfig;
import net.vkits.platform.application.wechat.handler.LocationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgentLocationHandler extends LocationHandler {
  @Autowired
  private WxAgentConfig wxConfig;

  @Override
  protected WxConfig getWxConfig() {
    return this.wxConfig;
  }

}
