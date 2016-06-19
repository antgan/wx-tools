package wxtools.demo.init;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.soecode.wxtools.api.WxConfigStorage;
import com.soecode.wxtools.api.WxInMemoryConfigStorage;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.api.WxServiceImpl;

/**
 * 初始化配置库和微信API服务
 * 
 * 
 * @author antgan
 *
 */
public class DemoInitListener implements ServletContextListener {
	private WxConfigStorage config = null;
	private WxService wxService = null;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("------实例化config和service----------");
		config = WxInMemoryConfigStorage.getInstance();
		config.setAppId("wxb1bff1627d37417b");
		config.setAppSecret("dd037d9b9b4eea00fba14167a9f3c75d");
		config.setToken("antgan");
		config.setTmpDirFile(new File("e://test"));
		config.setMaterialDirFile(new File("e://test"));
		config.setOauth2redirectUri("http://antgan.hicp.net/demo/oauth");
		wxService = WxServiceImpl.getInstance();
		wxService.setWxConfigStorage(config);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
