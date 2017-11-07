package com.czyh.czyhwx.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.mapper.JsonMapper;

import com.czyh.czyhwx.util.HttpClientUtil;
import com.czyh.czyhwx.util.PropertiesUtil;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JavaType;

/**
 * 报表处理类
 * 
 * @author laizhiwei
 *
 */
@Controller
@RequestMapping("/czyhInterface/wechat")
public class WechatController {

	private static Logger logger = LoggerFactory.getLogger(WechatController.class);

	private static JsonMapper mapper = JsonMapper.nonDefaultMapper();

	private static JsonMapper mappers = new JsonMapper(Include.ALWAYS);

	/**
	 * 微信菜单管理
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wechatMenu", method = { RequestMethod.GET, RequestMethod.POST })
	public String sale(HttpServletRequest request, HttpServletResponse response) {
		return "czyhInterface/wechat/wechatMenuManage";
	}

	/**
	 * 微信菜单上传导入
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/wechatMenu/updateMenuXml", method = RequestMethod.POST)
	public String addUserTagExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "file") MultipartFile file) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			StringBuilder relativePath = new StringBuilder();
			// relativePath.append(PropertiesUtil.getProperty("wechatMenuPath")).append(file.getOriginalFilename());
			if (SystemUtils.IS_OS_LINUX) {
				relativePath.append(PropertiesUtil.getProperty("wechatMenuLinuxPath"));
			} else if (SystemUtils.IS_OS_WINDOWS) {
				relativePath.append(PropertiesUtil.getProperty("wechatMenuWindowsPath"));
			}
			File nfFile = new File(relativePath.toString());
			file.transferTo(nfFile);
			returnMap.put("success", true);
			returnMap.put("msg", "导入微信菜单文件成功!");
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", "导入微信菜单文件失败!");
		}
		return mapper.toJson(returnMap);
	}

	@ResponseBody
	@RequestMapping(value = "/wechatMenu/createMenu", method = RequestMethod.POST)
	public String createWeChatMenu(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("menu", "bulid"));
			String url = PropertiesUtil.getProperty("weChatMenuUrl");
			String result = HttpClientUtil.callUrlPost(url, params);
			boolean success = false;
			String msg = "";
			if (StringUtils.isBlank(result)) {
				msg = "微信创建菜单超时!";
			} else {
				JavaType jt = mappers.contructMapType(HashMap.class, String.class, Boolean.class);
				Map<String, Boolean> map = mappers.fromJson(result, jt);
				boolean b = map.get("success");
				if (b) {
					success = true;
					msg = "微信创建菜单成功!";
				} else {
					msg = "微信创建菜单超时!";
				}
			}
			returnMap.put("success", success);
			returnMap.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", "微信创建菜单失败!");
		}
		return mapper.toJson(returnMap);
	}

}
