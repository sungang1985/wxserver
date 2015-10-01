package cn.saymagic.weixin.server.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.saymagic.weixin.server.bean.Article;
import cn.saymagic.weixin.server.bean.MsgRequest;
import cn.saymagic.weixin.server.bean.MsgResponseNews;
import cn.saymagic.weixin.server.bean.MsgResponseText;
import cn.saymagic.weixin.server.util.MsgXmlUtil;
import cn.saymagic.weixin.server.util.MsicUtil;

public abstract class BaseHandler<T extends MsgRequest> {

	public abstract String doHandleMsg(T msgRequest);

	protected String getResponseStringByContent(String content, MsgRequest msgRequest) {
		MsgResponseText reponseText = new MsgResponseText();
		reponseText.setToUserName(msgRequest.getFromUserName());
		reponseText.setFromUserName(msgRequest.getToUserName());
		reponseText.setMsgType("text");
		reponseText.setCreateTime(new Date().getTime());
		reponseText.setContent(content);
		msgRequest.setMsgResponse(reponseText);
		return MsicUtil.formatString(MsgXmlUtil.textToXml(reponseText));
	}

	protected String getNewsResponseStringByContent(MsgRequest msgRequest) {
		MsgResponseNews reponseText = new MsgResponseNews();
		reponseText.setToUserName(msgRequest.getFromUserName());
		reponseText.setFromUserName(msgRequest.getToUserName());
		reponseText.setMsgType("news");
		reponseText.setCreateTime(new Date().getTime());
		reponseText.setArticleCount(1);
		List<Article> articles = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("百日照");
		article.setDescription("这是我一百天的时候照的全家福，把我都弄哭了！");
		article.setPicUrl("http://7s1rlc.com1.z0.glb.clouddn.com/img2.jpg");
		article.setUrl("http://7s1rlc.com1.z0.glb.clouddn.com/img1.jpg");
		articles.add(article);
		reponseText.setArticles(articles);
		msgRequest.setMsgResponse(reponseText);
		return MsicUtil.formatString(MsgXmlUtil.newsToXml(reponseText));
	}
}
