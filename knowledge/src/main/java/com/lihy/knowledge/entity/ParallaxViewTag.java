package com.lihy.knowledge.entity;

/**
 * 视差动画播放时参数的控制
 */
public class ParallaxViewTag {
	public int index;
	public float xIn;
	public float xOut;
	public float yIn;
	public float yOut;
	public float alphaIn;
	public float alphaOut;
	
	
	@Override
	public String toString() {
		return "ParallaxViewTag [index=" + index + ", xIn=" + xIn + ", xOut="
				+ xOut + ", yIn=" + yIn + ", yOut=" + yOut + ", alphaIn="
				+ alphaIn + ", alphaOut=" + alphaOut + "]";
	}

	
}
