package com.soecode.wxtools.util.xml;

import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

/**
 * XStream 自定义初始化器
 * @author antgan
 *
 */
public class XStreamInitializer {

	public static XStream getInstance() {
		XStream xstream = new XStream(new XppDriver() {
			@Override
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new PrettyPrintWriter(out, getNameCoder()) {
					protected String PREFIX_CDATA = "<![CDATA[";
					protected String SUFFIX_CDATA = "]]>";
					protected String PREFIX_MEDIA_ID = "<MediaId>";
					protected String SUFFIX_MEDIA_ID = "</MediaId>";

					@Override
					protected void writeText(QuickWriter writer, String text) {
						if (text.startsWith(PREFIX_CDATA) && text.endsWith(SUFFIX_CDATA)) {
							writer.write(text);
						} else if (text.startsWith(PREFIX_MEDIA_ID) && text.endsWith(SUFFIX_MEDIA_ID)) {
							writer.write(text);
						} else {
							super.writeText(writer, text);
						}

					}
				};
			}
		});
		xstream.ignoreUnknownElements();//忽视null的节点
		xstream.setMode(XStream.NO_REFERENCES);
		xstream.addPermission(NullPermission.NULL);
		xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
		return xstream;
	}

}
