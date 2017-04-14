package com.soecode.wxtools.api.demo;

import java.util.ArrayList;
import java.util.List;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxUserList;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.bean.result.WxUserGroupResult;
import com.soecode.wxtools.bean.result.WxUserListResult;
import com.soecode.wxtools.exception.WxErrorException;

/**
 * 用户相关API示例
 * @author antgan
 *
 */
public class UserAPI {
	IService iService = new WxService();
		
	/**
	 * 创建用户分组
	 */
	public void createUserGroup(){
		try {
			WxUserGroupResult result = iService.createUserGroup("组名");
			System.out.println(result.getGroup().getId());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询用户分组
	 */
	public void queryAllUserGroup(){
		try {
			WxUserGroupResult result = iService.queryAllUserGroup();
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询用户在哪个分组
	 */
	public void queryGroupIdByOpenId(){
		try {
			int groupId = iService.queryGroupIdByOpenId("openid");
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改分组名
	 */
	public void updateUserGroupName(){
		try {
			//组ID，新组名
			iService.updateUserGroupName(1, "new group name");
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *  移动用户在某组
	 */
	public void movingUserToNewGroup(){
		try {
			WxError result = iService.movingUserToNewGroup("openid", 1);
			System.out.println(result.getErrcode());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量移动用户在某组
	 */
	public void batchMovingUserToNewGroup(){
		List<String> openidList = new ArrayList<>();
		openidList.add("openid1");
		openidList.add("openid2");
		
		try {
			iService.batchMovingUserToNewGroup(openidList, 2);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除用户分组
	 */
	public void deleteUserGroup(){
		try {
			iService.deleteUserGroup(2);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改用户备注
	 */
	public void updateUserRemark(){
		try {
			iService.updateUserRemark("openid", "备注名");
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取用户基本信息
	 */
	public void getUserInfoByOpenId(){
		try {
			WxUser user = iService.getUserInfoByOpenId(new WxUserGet("openid", WxConsts.LANG_CHINA));
			System.out.println(user.toString());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量获取用户信息
	 */
	public void batchGetUserInfo(){
		List<WxUserGet> list = new ArrayList<>();
		WxUserGet userGet1 = new WxUserGet("openid", WxConsts.LANG_CHINA);
		WxUserGet userGet2 = new WxUserGet("openid", WxConsts.LANG_CHINA);
		list.add(userGet1);
		list.add(userGet2);
		try {
			WxUserList userList = iService.batchGetUserInfo(list);
			System.out.println(userList.toString());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量获取关注者openid
	 */
	public void batchGetUserOpenId(){
		try {
			//第一个openid之后拉取
			WxUserListResult result = iService.batchGetUserOpenId("next openid");
			System.out.println(result.getNext_openid());
			System.out.println(result.getData());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Oauth2.0 认证获取用户信息--第一步：构造URL获取Code
	 */
	public void oauth2buildAuthorizationUrl(){
		
		try {
			String oauthUrl = iService.oauth2buildAuthorizationUrl("回调URL",WxConsts.OAUTH2_SCOPE_USER_INFO, "自定义携带参数");
			
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Oauth2.0 认证获取用户信息--第二步：拿code换token和openid
	 */
	public void oauth2ToGetAccessToken(){
		try {
			WxOAuth2AccessTokenResult result = iService.oauth2ToGetAccessToken("code");
			System.out.println(result.getAccess_token());
			System.out.println(result.getOpenid());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Oauth2.0 认证获取用户信息--第三步：拿token换用户信息
	 */
	public void oauth2ToGetUserInfo(){
		try {
			WxUser user = iService.oauth2ToGetUserInfo("token", new WxUserGet("openid", WxConsts.LANG_CHINA));
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
