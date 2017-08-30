package net.vkits.platform.handler.customer;


import net.vkits.platform.application.wechat.config.WxConfig;
import net.vkits.platform.application.wechat.config.WxCustomerConfig;
import net.vkits.platform.application.wechat.handler.LocationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerLocationHandler extends LocationHandler {
  @Autowired
  private WxCustomerConfig wxConfig;

  @Override
  protected WxConfig getWxConfig() {
    return this.wxConfig;
  }

}
