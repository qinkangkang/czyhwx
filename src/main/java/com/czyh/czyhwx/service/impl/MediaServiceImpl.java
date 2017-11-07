package com.czyh.czyhwx.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.JsonMapper;

import com.czyh.czyhwx.service.MediaService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;

@Service
@Transactional
public class MediaServiceImpl implements MediaService {

	private static final Logger logger = LoggerFactory.getLogger(MediaServiceImpl.class);
	private static JsonMapper mapper = new JsonMapper(Include.ALWAYS);

	@Autowired
	private WxMpService wxMpService;

	@Override
	public String uploadMediaId(String author, String thumbMediaId, String title, String count, String contentUrl,
			String digest, String url) {
		String mediaId = null;
		// 单图文消息的例子
		WxMpMaterialNews wxMpMaterialNewsSingle = new WxMpMaterialNews();
		WxMpMaterialNews.WxMpMaterialNewsArticle mpMaterialNewsArticleSingle = new WxMpMaterialNews.WxMpMaterialNewsArticle();
		mpMaterialNewsArticleSingle.setAuthor(author);
		mpMaterialNewsArticleSingle.setThumbMediaId(thumbMediaId);
		mpMaterialNewsArticleSingle.setTitle(title);
		mpMaterialNewsArticleSingle.setContent("图文消息页面的内容，支持HTML标签图文消息页面的内容，支持HTML标签图文消息页面的内容，支持HTML标签");
		mpMaterialNewsArticleSingle.setContentSourceUrl(contentUrl);
		mpMaterialNewsArticleSingle.setShowCoverPic(true);
		mpMaterialNewsArticleSingle.setDigest(digest);
		mpMaterialNewsArticleSingle.setUrl(url);
		wxMpMaterialNewsSingle.addArticle(mpMaterialNewsArticleSingle);
		WxMpMaterialUploadResult resSingle;
		try {
			resSingle = wxMpService.getMaterialService().materialNewsUpload(wxMpMaterialNewsSingle);
			mediaId = resSingle.getMediaId();
		} catch (WxErrorException e) {
			e.printStackTrace();
		}

		return mediaId;
	}

}
