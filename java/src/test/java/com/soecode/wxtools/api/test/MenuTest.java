package com.soecode.wxtools.api.test;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.bean.WxMenu.WxMenuButton;
import com.soecode.wxtools.bean.WxMenu.WxMenuRule;
import com.soecode.wxtools.exception.WxErrorException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class MenuTest {
  IService iService = new WxService();

  @Test
  public void should_create_normal_menu_successfully() throws Exception {
    WxMenu menu = getWxMenu();
    //调用API即可
    try {
      //参数1--menu  ，参数2--是否是个性化定制。如果是个性化菜单栏，需要设置MenuRule
      String result = iService.createMenu(menu, false);
      Assert.assertEquals("{\"errcode\":0,\"errmsg\":\"ok\"}", result);
    } catch (WxErrorException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


//TODO
  @Test
  public void should_create_condition_menu_successfully() throws Exception {
    WxMenu menu = getWxMenu();
    WxMenuRule matchrule = new WxMenuRule();
//    matchrule.setTag_id();
    menu.setMatchrule(matchrule);
  }


  private WxMenu getWxMenu() {
    WxMenu menu = new WxMenu();
    List<WxMenuButton> btnList = new ArrayList<>();

    //设置CLICK类型的按钮1
    WxMenuButton btn1 = new WxMenuButton();
    btn1.setType(WxConsts.MENU_BUTTON_CLICK);
    btn1.setKey("btn1_key");
    btn1.setName("CLICK按钮1");

    //设置VIEW类型的按钮2
    WxMenuButton btn2 = new WxMenuButton();
    btn2.setType(WxConsts.MENU_BUTTON_VIEW);
    btn2.setUrl("http://www.baidu.com");
    btn2.setName("VIEW按钮2");

    //设置含有子按钮的按钮3
    List<WxMenuButton> subList = new ArrayList<>();
    //子按钮
    WxMenuButton btn3_1 = new WxMenuButton();
    btn3_1.setType(WxConsts.MENU_BUTTON_VIEW);
    btn3_1.setUrl("http://www.baidu.com");
    btn3_1.setName("子按钮3_1");
    WxMenuButton btn3_2 = new WxMenuButton();
    btn3_2.setType(WxConsts.MENU_BUTTON_VIEW);
    btn3_2.setUrl("http://www.baidu.com");
    btn3_2.setName("子按钮3_2");
    subList.add(btn3_1);
    subList.add(btn3_2);
    //把子按钮列表设置进按钮3
    WxMenuButton btn3 = new WxMenuButton();
    btn3.setName("子按钮3");
    btn3.setSub_button(subList);

    //将三个按钮设置进btnList
    btnList.add(btn1);
    btnList.add(btn2);
    btnList.add(btn3);
    //设置进菜单类
    menu.setButton(btnList);
    return menu;
  }
}
