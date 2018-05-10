package com.soecode.wxtools.api.test;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.bean.WxMenu.WxMenuButton;
import com.soecode.wxtools.bean.WxMenu.WxMenuRule;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class MenuTest {

  IService iService = new WxService();

  @Test
  public void should_create_normal_menu_successfully() throws Exception {
    WxMenu menu = getWxMenu();
    String result = iService.createMenu(menu, false);
    Assert.assertEquals("{\"errcode\":0,\"errmsg\":\"ok\"}", result);
  }

  @Test
  public void should_create_condition_menu_successfully() throws Exception {
    WxMenu menu = getWxMenu();
    WxMenuRule matchrule = new WxMenuRule();
    matchrule.setTag_id("103");
    matchrule.setCountry("中国");
    matchrule.setProvince("广东");
    menu.setMatchrule(matchrule);
    String result = iService.createMenu(menu, true);
    System.out.println(result);
  }

  @Test
  public void should_delete_condition_menu_successfully() throws Exception {
    String result = iService.deleteMenu("443508866");
    System.out.println(result);
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
