package com.asum.xmediaimageselecter.selecter;

import java.io.File;

import com.asum.xmediaimageselecter.vo.MediaVO;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

/**
 * 系统图片获取操作类
 * 
 * @author Asum
 * 
 */
public class XMediaImageSelector {
	public static final int REQUESTCODE_CAMARE_IMAGE = 137;
	public static final int REQUESTCODE_GALLERY_IMAGE = 138;
	public static final int REQUESTCODE_CAMERA = 139;
	public static final int REQUESTCODE_CROP_IMAGE = 140;

	private static MediaVO mediaVO;
	private static Uri uri;
	private static XMediaImageSelectorCallBack callBack;

	/**
	 * 直接通过图片库获取
	 * 
	 * @param activity
	 * @param cachePath
	 *            缓存路径，null为使用默认路径
	 * @param needCrop
	 *            是否需要裁剪
	 * @param scaleW
	 *            宽占比
	 * @param scaleH
	 *            高占比
	 * @param outputW
	 *            缓存宽
	 * @param outputH
	 *            缓存高
	 * @param callBack
	 *            回调函数
	 */
	public static void fromPicture(Activity activity, String cachePath, boolean needCrop, double scaleW, double scaleH, double outputW, double outputH, XMediaImageSelectorCallBack callBack) {
		XMediaImageSelector.callBack = callBack;
		mediaVO = null;
		if (needCrop) {
			mediaVO = new MediaVO();
			mediaVO.setCachePath(cachePath);
			mediaVO.setScaleW(scaleW);
			mediaVO.setScaleH(scaleH);
			mediaVO.setOutputW(outputW);
			mediaVO.setOutputH(outputH);
		}
		FromPicture.selectPicture(activity);
	}

	/**
	 * 通过拍照获取
	 * 
	 * @param activity
	 * @param cachePath
	 *            缓存路径，null为使用默认路径
	 * @param needCrop
	 *            是否需要裁剪
	 * @param scaleW
	 *            宽占比
	 * @param scaleH
	 *            高占比
	 * @param outputW
	 *            缓存宽
	 * @param outputH
	 *            缓存高
	 * @param callBack
	 *            回调函数
	 * 
	 */
	public static void fromCamera(Activity activity, String cachePath, boolean needCrop, double scaleW, double scaleH, double outputW, double outputH, XMediaImageSelectorCallBack callBack) {
		XMediaImageSelector.callBack = callBack;
		mediaVO = null;
		if (needCrop) {
			mediaVO = new MediaVO();
			mediaVO.setCachePath(cachePath);
			mediaVO.setScaleW(scaleW);
			mediaVO.setScaleH(scaleH);
			mediaVO.setOutputW(outputW);
			mediaVO.setOutputH(outputH);
		}
		FromCamera.takeAPicture(activity);
	}

	/**
	 * 选取多张的图片
	 * 
	 * @param activity
	 * @param count
	 *            选取几张
	 * @param showCamera
	 *            是否显示照相机按钮
	 * @param reset
	 *            是否重新选择
	 * @param callBack
	 */
	public static void selectMore(Activity activity, int count, boolean showCamera, boolean reset, XMediaImageSelectorCallBack callBack) {
		XMediaImageSelector.callBack = callBack;
	}

	public static void disposeResult(Activity activity, int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUESTCODE_GALLERY_IMAGE && resultCode == Activity.RESULT_OK) {// 图片已选取
			uri = data.getData();
			if (mediaVO != null) {
				CropPicture.cropImageFromUri(activity, mediaVO.getCachePath(), uri, mediaVO.getScaleW(), mediaVO.getScaleH(), mediaVO.getOutputW(), mediaVO.getOutputH());
			} else {
				if (callBack != null) {
					callBack.complete(true);
				}
			}
		}

		if (requestCode == REQUESTCODE_CAMARE_IMAGE && resultCode == Activity.RESULT_OK) {// 图片已拍
			uri = FromCamera.uri;
			if (mediaVO != null) {
				CropPicture.cropImageFromUri(activity, mediaVO.getCachePath(), uri, mediaVO.getScaleW(), mediaVO.getScaleH(), mediaVO.getOutputW(), mediaVO.getOutputH());
			} else {
				if (callBack != null) {
					callBack.complete(true);
				}
			}
		}

		if (requestCode == REQUESTCODE_CROP_IMAGE) {// 裁切图片
			if (resultCode == Activity.RESULT_OK) {
				if (callBack != null) {
					callBack.complete(true);
				}
			} else {
				if (callBack != null) {
					callBack.complete(false);
				}
			}
		}
	}

	/**
	 * 以文件形式返回
	 * 
	 * @return
	 */
	public static File getFile() {
		return CropPicture.getFile();
	}

	/**
	 * 以Bitmap形式返回
	 * 
	 * @return
	 */
	public static Bitmap getBitmap() {
		return CropPicture.getBitmap();
	}

	/**
	 * 获取路径
	 * 
	 * @return
	 */
	public static String getPath() {
		return CropPicture.getPath();
	}

	/**
	 * 获取未被裁剪的图的Uri
	 * 
	 * @return
	 */
	public static Uri getUri() {
		return uri;
	}
}
