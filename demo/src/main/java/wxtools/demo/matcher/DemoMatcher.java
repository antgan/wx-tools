package wxtools.demo.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.soecode.wxtools.api.WxMessageMatcher;
import com.soecode.wxtools.bean.WxXmlMessage;

/**
 * Demo 简单的匹配器，可以用于更加复杂的消息验证操作
 * @author antgan
 *
 */
public class DemoMatcher implements WxMessageMatcher{

	@Override
	public boolean match(WxXmlMessage message) {
		Pattern p = Pattern.compile("^[a-zA-Z]*$");
		Matcher matcher = p.matcher(message.getContent());
		if(matcher.matches()){
			return true;
		}
		return false;
	}
}
