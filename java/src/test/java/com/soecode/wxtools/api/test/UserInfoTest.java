package com.soecode.wxtools.api.test;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxUserList;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.bean.result.WxUserListResult;
import com.soecode.wxtools.bean.result.WxUserTagResult;
import java.util.Arrays;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
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
  public void should_update_user_remark_successfully() throws Exception {
    WxError result = iService.updateUserRemark("oROCnuNihJnO9bnKOAORDFFriPgQ", "Abel");
    System.out.println(result);
  }

  @Test
  public void should_query_user_info_successfully() throws Exception {
    WxUser result = iService.getUserInfoByOpenId(new WxUserGet("oROCnuNihJnO9bnKOAORDFFriPgQ",
        WxConsts.LANG_CHINA));
    System.out.println(result);
  }

  @Test
  public void should_batch_query_user_info_successfully() throws Exception {
    WxUserList result = iService.batchGetUserInfo(Arrays.asList(new WxUserGet("oROCnuNihJnO9bnKOAORDFFriPgQ",
        WxConsts.LANG_CHINA),new WxUserGet("oROCnuAQMnkPpEhsAYFzU-1xhKcQ",
        WxConsts.LANG_CHINA)));
    System.out.println(result);
  }

  @Test
  public void should_batch_get_user_id_successfully() throws Exception {
    WxUserListResult result = iService.batchGetUserOpenId(null);
    System.out.println(result);
  }

  @Test
  public void should_batch_add_user_to_black_list_successfully() throws Exception {
    WxError result = iService.batchAddUserToBlackList(Arrays.asList("oROCnuNihJnO9bnKOAORDFFriPgQ"));
    System.out.println(result);
  }

  @Test
  public void should_batch_remove_user_from_black_list_successfully() throws Exception {
    WxError result = iService.batchRemoveUserFromBlackList(Arrays.asList("oROCnuNihJnO9bnKOAORDFFriPgQ"));
    System.out.println(result);
  }

  @Test
  public void should_batch_get_users_from_black_list_successfully() throws Exception {
    WxUserListResult result = iService.batchGetUsersFromBlackList(null);
    System.out.println(result);
  }

}
