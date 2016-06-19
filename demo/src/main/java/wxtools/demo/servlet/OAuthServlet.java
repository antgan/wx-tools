package wxtools.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxServiceImpl;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.exception.WxErrorException;

/**
 * OAuth回调处理
 * @author antgan
 *
 */
@WebServlet("/oauth")
public class OAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		System.out.println("code:"+code+" state:"+state);
		PrintWriter out = response.getWriter();
		WxOAuth2AccessTokenResult result = null ;
		try {
			result = WxServiceImpl.getInstance().oauth2ToGetAccessToken(code);
			WxUser user = WxServiceImpl.getInstance().oauth2ToGetUserInfo(result.getAccess_token(), new WxUserGet(result.getOpenid(),WxConsts.LANG_CHINA));
			System.out.println(user.toString());
			out.print(user.toString());
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
