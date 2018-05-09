package com.soecode.wxtools.api.test;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.bean.result.WxUserListResult;
import com.soecode.wxtools.bean.result.WxUserTagResult;
import java.util.Arrays;
import org.junit.Test;

public class UserInfoTest {
  IService iService = new WxService();


  @Test
  public void should_create_user_tag_successfully() throws Exception {
    WxUserTagResult result = iService.createUserTag("TestTag2");
    System.out.println(result);
  }

  @Test
  public void should_query_all_user_tag_successfully() throws Exception {
    WxUserTagResult result = iService.queryAllUserTag();
    System.out.println(result);
  }

  @Test
  public void should_update_user_tag_successfully() throws Exception {
    WxError result = iService.updateUserTagName(104, "TestTag2_update");
    System.out.println(result);
  }

  @Test
  public void should_delete_user_tag_successfully() throws Exception {
    WxError result = iService.deleteUserTag(104);
    System.out.println(result);
  }

  @Test
  public void should_batch_moving_user_to_tag_successfully() throws Exception {
    WxError result = iService.batchMovingUserToNewTag(Arrays.asList("oROCnuNihJnO9bnKOAORDFFriPgQ"), 104);
    System.out.println(result);
  }

  @Test
  public void should_batch_remove_user_to_tag_successfully() throws Exception {
    WxError result = iService.batchRemoveUserTag(Arrays.asList("oROCnuNihJnO9bnKOAORDFFriPgQ"), 104);
    System.out.println(result);
  }

  @Test
  public void should_query_all_user_under_by_tag_successfully() throws Exception {
    WxUserListResult result = iService.queryAllUserUnderByTag(2, null);
    System.out.println(result);
  }

  @Test
  public void should_test() throws Exception {
    int result = iService.queryGroupIdByOpenId("oROCnuNihJnO9bnKOAORDFFriPgQ");
    System.out.println(result);
  }
}
