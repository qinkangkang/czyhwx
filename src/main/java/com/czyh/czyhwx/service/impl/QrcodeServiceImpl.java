package com.czyh.czyhwx.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.czyh.czyhwx.config.MainConfig;
import com.czyh.czyhwx.dto.m.ResponseDTO;
import com.czyh.czyhwx.service.QrcodeService;
import com.google.common.collect.Maps;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;

@Service
public class QrcodeServiceImpl implements QrcodeService {

	@Override
	public ResponseDTO getQrCodeCreateTmpTicket(Integer scene, Integer expireSeconds) {
		ResponseDTO responseDTO = new ResponseDTO();

		MainConfig mainConfig = new MainConfig();
		WxMpService wxMpService = mainConfig.wxMpService();
		Map<String, Object> returnData = Maps.newHashMap();
		// StringBuilder httpUrl = null;
		try {
			if (scene != null) {
				WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateTmpTicket(scene, expireSeconds);
				String TmpQrCodeUrl = wxMpService.getQrcodeService().qrCodePictureUrl(ticket.getTicket());// 这是微信提供的二维码httpurl
				returnData.put("TmpQrCodeUrl", TmpQrCodeUrl);
				// // 获得一个在系统临时目录下的文件，是jpg格式的
				// File file = wxMpService.qrCodePicture(ticket);
				// // 上传二维码到服务器
				// String filePathVar =
				// PropertiesUtil.getProperty("qrCodePath");// qrCodePath
				// StringBuilder relativePath = new StringBuilder(filePathVar)
				// .append(DateFormatUtils.format(new Date(),
				// "yyyy-MM-dd")).append("/");
				// StringBuilder rootPath = new StringBuilder(Constant.RootPath)
				// .append(PropertiesUtil.getProperty("imageRootPath")).append(relativePath);
				// // 判断如果没有该目录则创建一个目录
				// File destDir = new File(rootPath.toString());
				// if (!destDir.exists()) {
				// destDir.mkdirs();
				// }
				//
				// String storeFileName = Identities.uuid2() + ".jpg";
				// File tmpFile = new
				// File(rootPath.append(storeFileName).toString());
				// FileUtils.copyFile(file, tmpFile);
				// relativePath.append(storeFileName);
				//
				// httpUrl = new
				// StringBuilder(PropertiesUtil.getProperty("fileServerUrl"))
				// .append(PropertiesUtil.getProperty("imageRootPath")).append(relativePath.toString());

			}
		} catch (WxErrorException e) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getError().getErrorMsg());
			return responseDTO;
		}
		// } catch (IOException e) {
		// responseDTO.setSuccess(false);
		// responseDTO.setStatusCode(203);
		// responseDTO.setMsg("上传二维码到服务器时发生错误");
		// return responseDTO;
		// }

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("生成临时二维码成功");
		// returnData.put("qrCodeUrl", httpUrl);
		responseDTO.setData(returnData);
		return responseDTO;
	}

	@Override
	public ResponseDTO getQrCodeCreateLastTicket(String scene) {
		ResponseDTO responseDTO = new ResponseDTO();

		MainConfig mainConfig = new MainConfig();
		WxMpService wxMpService = mainConfig.wxMpService();
		// StringBuilder httpUrl = null;
		Map<String, Object> returnData = Maps.newHashMap();
		try {
			if (scene != null) {
				WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateLastTicket(scene);

				String qrCodeUrl = wxMpService.getQrcodeService().qrCodePictureUrl(ticket.getTicket());// 这是微信提供的二维码httpurl
				returnData.put("qrCodeUrl", qrCodeUrl);
				// 获得一个在系统临时目录下的文件，是jpg格式的
				// File file = wxMpService.qrCodePicture(ticket);
				// StringBuilder relativePath = new StringBuilder();
				// relativePath.append("D:/QrCodeCreateLast/").append(scene).append(".jpg");
				//
				// // 判断如果没有该目录则创建一个目录
				// File destDir = new
				// File(relativePath.append("D:/QrCodeCreateLast/").toString());
				// if (!destDir.exists()) {
				// destDir.mkdirs();
				// }
				//
				// File nfFile = new File(relativePath.toString());
				// FileUtils.copyFile(file, nfFile);
				// 上传二维码到服务器
				// String filePathVar =
				// PropertiesUtil.getProperty("qrCodePath");// qrCodePath
				// StringBuilder relativePath = new StringBuilder(filePathVar)
				// .append(DateFormatUtils.format(new Date(),
				// "yyyy-MM-dd")).append("/");
				// StringBuilder rootPath = new StringBuilder(Constant.RootPath)
				// .append(PropertiesUtil.getProperty("imageRootPath")).append(relativePath);
				// // 判断如果没有该目录则创建一个目录
				// File destDir = new File(rootPath.toString());
				// if (!destDir.exists()) {
				// destDir.mkdirs();
				// }
				//
				// String storeFileName = Identities.uuid2() + ".jpg";
				// File tmpFile = new
				// File(rootPath.append(storeFileName).toString());
				// FileUtils.copyFile(file, tmpFile);
				// relativePath.append(storeFileName);
				//
				// httpUrl = new
				// StringBuilder(PropertiesUtil.getProperty("fileServerUrl"))
				// .append(PropertiesUtil.getProperty("imageRootPath")).append(relativePath.toString());

			}
		} catch (WxErrorException e) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getError().getErrorMsg());
			return responseDTO;
		} /*
			 * catch (IOException e) { responseDTO.setSuccess(false);
			 * responseDTO.setStatusCode(203);
			 * responseDTO.setMsg("上传二维码到服务器时发生错误"); return responseDTO; }
			 */

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("生成永久二维码成功");
		// returnData.put("qrCodeUrl", qrCodeUrl);
		responseDTO.setData(returnData);
		return responseDTO;
	}

}
