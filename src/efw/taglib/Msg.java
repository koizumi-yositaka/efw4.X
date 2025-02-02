/**** efw4.X Copyright 2019 efwGrp ****/
package efw.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import efw.i18n.I18nManager;

public final class Msg extends TagSupport{

	private String key = "";
	private String defaultValue=null;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = Util.translateAttr(pageContext,key);
	}

	public String getDefault() {
		return defaultValue;
	}

	public void setDefault(String defaultValue) {
		this.defaultValue = Util.translateAttr(pageContext,defaultValue);
	}

	@Override
	public int doStartTag(){
		JspWriter out = pageContext.getOut();
		// 多国語対応のため、指定言語をattrに設定する。
		String lang = pageContext.getAttribute(Client.EFW_I18N_LANG,PageContext.REQUEST_SCOPE).toString();
		try {
			if (defaultValue==null)defaultValue=key;
			out.print(I18nManager.get(lang, key, defaultValue));
		} catch (IOException e) {
			e.printStackTrace();
		}
		key = "";
		defaultValue = null;
		return SKIP_BODY;
	}
}
