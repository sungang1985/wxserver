package cn.saymagic.weixin.server.handler;

import java.io.IOException;

import cn.saymagic.weixin.server.bean.MsgRequest;
import cn.saymagic.weixin.server.util.TulingUtil;

public class CustomTextHandler extends BaseHandler {

	@Override
	public String doHandleMsg(MsgRequest msgRequest) {
		try {
			if (null == msgRequest.getContent()) {
				return getResponseStringByContent(TulingUtil.getContentStr(msgRequest.getRecognition(), msgRequest.getFromUserName()), msgRequest);
			} else {
				if (msgRequest.getContent().contains("百日")) {
					return getNewsResponseStringByContent(msgRequest);
				} else {
					return getResponseStringByContent(TulingUtil.getContentStr(msgRequest.getContent(), msgRequest.getFromUserName()), msgRequest);
				}
			}
		} catch (IOException e) {
			return getResponseStringByContent("很抱歉暂时无法处理您的消息，请稍后重试", msgRequest);
		}
	}
}
