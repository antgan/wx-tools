package com.soecode.wxtools.util.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutImageMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.bean.WxXmlOutNewsMessage;
import com.soecode.wxtools.bean.WxXmlOutTextMessage;
import com.soecode.wxtools.bean.WxXmlOutVideoMessage;
import com.soecode.wxtools.bean.WxXmlOutVoiceMessage;
import com.thoughtworks.xstream.XStream;

/**
 * 指定类的专属XStream
 * @author antgan
 *
 */
public class XStreamTransformer {

	protected static final Map<Class, XStream> CLASS_2_XSTREAM_INSTANCE = configXStreamInstance();

	/**
	 * xml -> pojo
	 *
	 * @param clazz
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromXml(Class<T> clazz, String xml) {
		T object = (T) CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(xml);
		return object;
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromXml(Class<T> clazz, InputStream is) {
		T object = (T) CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(is);
		return object;
	}

	/**
	 * pojo -> xml
	 *
	 * @param clazz
	 * @param object
	 * @return
	 */
	public static <T> String toXml(Class<T> clazz, T object) {
		return CLASS_2_XSTREAM_INSTANCE.get(clazz).toXML(object);
	}

	private static Map<Class, XStream> configXStreamInstance() {
		Map<Class, XStream> map = new HashMap<Class, XStream>();
		map.put(WxXmlMessage.class, config_WxXmlMessage());
		map.put(WxXmlOutNewsMessage.class, config_WxXmlOutNewsMessage());
		map.put(WxXmlOutTextMessage.class, config_WxXmlOutTextMessage());
		map.put(WxXmlOutImageMessage.class, config_WxXmlOutImageMessage());
		map.put(WxXmlOutVideoMessage.class, config_WxXmlOutVideoMessage());
		map.put(WxXmlOutVoiceMessage.class, config_WxXmlOutVoiceMessage());
		return map;
	}

	private static XStream config_WxXmlMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxXmlMessage.class);
		xstream.processAnnotations(WxXmlMessage.ScanCodeInfo.class);
		xstream.processAnnotations(WxXmlMessage.SendPicsInfo.class);
		xstream.processAnnotations(WxXmlMessage.SendPicsInfo.Item.class);
		xstream.processAnnotations(WxXmlMessage.SendLocationInfo.class);
		return xstream;
	}

	private static XStream config_WxXmlOutImageMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxXmlOutMessage.class);
		xstream.processAnnotations(WxXmlOutImageMessage.class);
		return xstream;
	}

	private static XStream config_WxXmlOutNewsMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxXmlOutMessage.class);
		xstream.processAnnotations(WxXmlOutNewsMessage.class);
		xstream.processAnnotations(WxXmlOutNewsMessage.Item.class);
		return xstream;
	}

	private static XStream config_WxXmlOutTextMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxXmlOutMessage.class);
		xstream.processAnnotations(WxXmlOutTextMessage.class);
		return xstream;
	}

	private static XStream config_WxXmlOutVideoMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxXmlOutMessage.class);
		xstream.processAnnotations(WxXmlOutVideoMessage.class);
		xstream.processAnnotations(WxXmlOutVideoMessage.Video.class);
		return xstream;
	}

	private static XStream config_WxXmlOutVoiceMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxXmlOutMessage.class);
		xstream.processAnnotations(WxXmlOutVoiceMessage.class);
		return xstream;
	}

}
