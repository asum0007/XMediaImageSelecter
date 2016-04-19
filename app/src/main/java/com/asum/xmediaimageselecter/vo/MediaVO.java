package com.asum.xmediaimageselecter.vo;

public class MediaVO {
	private String cachePath;
	private boolean needCrop;
	private double scaleW;
	private double scaleH;
	private double outputW;
	private double outputH;

	public String getCachePath() {
		return cachePath;
	}

	public boolean isNeedCrop() {
		return needCrop;
	}

	public double getScaleW() {
		return scaleW;
	}

	public double getScaleH() {
		return scaleH;
	}

	public double getOutputW() {
		return outputW;
	}

	public double getOutputH() {
		return outputH;
	}

	public void setCachePath(String cachePath) {
		this.cachePath = cachePath;
	}

	public void setNeedCrop(boolean needCrop) {
		this.needCrop = needCrop;
	}

	public void setScaleW(double scaleW) {
		this.scaleW = scaleW;
	}

	public void setScaleH(double scaleH) {
		this.scaleH = scaleH;
	}

	public void setOutputW(double outputW) {
		this.outputW = outputW;
	}

	public void setOutputH(double outputH) {
		this.outputH = outputH;
	}
}
