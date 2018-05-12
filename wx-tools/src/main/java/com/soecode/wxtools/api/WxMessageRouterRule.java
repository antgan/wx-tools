package com.soecode.wxtools.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;

public class WxMessageRouterRule {

  private final WxMessageRouter routerBuilder;

  private String fromUser;

  private String msgType;

  private String event;

  private String eventKey;

  private String content;

  private String rContent;

  private WxMessageMatcher matcher;

  private boolean reEnter = false;

  private List<WxMessageHandler> handlers = new ArrayList<WxMessageHandler>();

  private List<WxMessageInterceptor> interceptors = new ArrayList<WxMessageInterceptor>();

  protected WxMessageRouterRule(WxMessageRouter routerBuilder) {
    this.routerBuilder = routerBuilder;
  }

  public WxMessageRouterRule msgType(String msgType) {
    this.msgType = msgType;
    return this;
  }

  public WxMessageRouterRule event(String event) {
    this.event = event;
    return this;
  }

  public WxMessageRouterRule eventKey(String eventKey) {
    this.eventKey = eventKey;
    return this;
  }

  public WxMessageRouterRule content(String content) {
    this.content = content;
    return this;
  }

  public WxMessageRouterRule rContent(String regex) {
    this.rContent = regex;
    return this;
  }

  public WxMessageRouterRule fromUser(String fromUser) {
    this.fromUser = fromUser;
    return this;
  }

  public WxMessageRouterRule matcher(WxMessageMatcher matcher) {
    this.matcher = matcher;
    return this;
  }

  public WxMessageRouterRule interceptor(WxMessageInterceptor interceptor) {
    return interceptor(interceptor, (WxMessageInterceptor[]) null);
  }

  public WxMessageRouterRule interceptor(WxMessageInterceptor interceptor,
      WxMessageInterceptor... otherInterceptors) {
    this.interceptors.add(interceptor);
    if (otherInterceptors != null && otherInterceptors.length > 0) {
      for (WxMessageInterceptor i : otherInterceptors) {
        this.interceptors.add(i);
      }
    }
    return this;
  }

  public WxMessageRouterRule handler(WxMessageHandler handler) {
    return handler(handler, (WxMessageHandler[]) null);
  }

  public WxMessageRouterRule handler(WxMessageHandler handler, WxMessageHandler... otherHandlers) {
    this.handlers.add(handler);
    if (otherHandlers != null && otherHandlers.length > 0) {
      for (WxMessageHandler i : otherHandlers) {
        this.handlers.add(i);
      }
    }
    return this;
  }

  public WxMessageRouter end() {
    this.routerBuilder.getRules().add(this);
    return this.routerBuilder;
  }

  public WxMessageRouter next() {
    this.reEnter = true;
    return end();
  }

  protected boolean test(WxXmlMessage wxMessage) {
    return (this.fromUser == null || this.fromUser.equals(wxMessage.getFromUserName()))
        && (this.msgType == null || this.msgType.equals(wxMessage.getMsgType()))
        && (this.event == null || this.event.equals(wxMessage.getEvent()))
        && (this.eventKey == null || this.eventKey.equals(wxMessage.getEventKey()))
        && (this.content == null
        || this.content
        .equals(wxMessage.getContent() == null ? null : wxMessage.getContent().trim()))
        && (this.rContent == null || Pattern.matches(this.rContent,
        wxMessage.getContent() == null ? "" : wxMessage.getContent().trim()))
        && (this.matcher == null || this.matcher.match(wxMessage));
  }

  protected WxXmlOutMessage service(WxXmlMessage wxMessage, IService iService,
      WxErrorExceptionHandler exceptionHandler) {
    try {
      Map<String, Object> context = new HashMap<String, Object>();
      // 如果拦截器不通过，返回null
      for (WxMessageInterceptor interceptor : this.interceptors) {
        if (!interceptor.intercept(wxMessage, context, iService)) {
          return null;
        }
      }
      // 交给handler处理
      WxXmlOutMessage res = null;
      for (WxMessageHandler handler : this.handlers) {
        // 返回最后handler的结果
        res = handler.handle(wxMessage, context, iService);
      }
      return res;
    } catch (WxErrorException e) {
      e.printStackTrace();
      exceptionHandler.handle(e);
    }
    return null;
  }

  public void setFromUser(String fromUser) {
    this.fromUser = fromUser;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public void setEventKey(String eventKey) {
    this.eventKey = eventKey;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setrContent(String rContent) {
    this.rContent = rContent;
  }

  public void setMatcher(WxMessageMatcher matcher) {
    this.matcher = matcher;
  }

  public void setHandlers(List<WxMessageHandler> handlers) {
    this.handlers = handlers;
  }

  public void setInterceptors(List<WxMessageInterceptor> interceptors) {
    this.interceptors = interceptors;
  }

  public boolean isReEnter() {
    return reEnter;
  }

  public void setReEnter(boolean reEnter) {
    this.reEnter = reEnter;
  }

}
