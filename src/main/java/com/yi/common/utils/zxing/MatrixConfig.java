package com.yi.common.utils.zxing;

import com.google.zxing.BarcodeFormat;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.Serializable;

/**
 * 生成编码图片的配置类
 *
 * @Date 2017年4月14日 下午12:04:10
 */
public class MatrixConfig implements Serializable {
	public static final long serialVersionUID = 1L;
	/** 默认编码格式 */
	public static final String DEFAULT_CHARSET = "UTF-8";
	/** 默认图片格式 */
	public static final String DEFAULT_FORMAT = "png";
	/** 默认编码图片的大小 */
	public static final int DEFAULT_SIZE = 400;
	/** 默认的logo大小，是正方形的 */
	public static final double DEFAULT_LOGO_SIZE_RATE = 0.25;
	/** 支持的所有存储类型 */
	public static final String SUPPORT_FORMAT = "|gif|jpg|jpeg|png|bmp|ioc|webp|";

	/** 生成编码图片的类型，默认二维码 */
	private BarcodeFormat type = BarcodeFormat.QR_CODE;
	/** 编码格式 */
	private String charset = DEFAULT_CHARSET;
	/** 编码图片的大小 */
	private int width = DEFAULT_SIZE;
	private int height = DEFAULT_SIZE;
	/** 边框大小 */
	private int margin = 0;
	/** logo占编码图片的比率，默认是1/4 */
	private double logoRate = DEFAULT_LOGO_SIZE_RATE;
	/** logo的大小，可以不设置，如果不设置，取值 (size * logoRate) */
	private Integer logoSize;
	/** 是否压缩logo图片 */
	private boolean logoCompress = true;

	/** 编码图片的内容 */
	private String content;

	/** 使用的logo文件 */
	private File logoFile;

	public MatrixConfig(String content) {
		this.content = content;
	}

	public MatrixConfig(String content, int size) {
		this(content);
		this.width = size;
		this.height = size;
	}

	public MatrixConfig(String content, String logoPath) {
		this(content);
		logoPath(logoPath);
	}

	public MatrixConfig(String content, String logoPath, int size) {
		this(content, size);
		logoPath(logoPath);
	}

	public MatrixConfig(String content, BarcodeFormat type, int width, int height) {
		this(content);
		this.type = type;
		this.width = width;
		this.height = height;
	}

	public MatrixConfig logoPath(String logoPath) {
		if (StringUtils.isNotBlank(logoPath))
			this.logoFile = new File(logoPath);
		return this;
	}

	public MatrixConfig content(String content) {
		this.content = content;
		return this;
	}

	public MatrixConfig width(int width) {
		this.width = width;
		return this;
	}

	public MatrixConfig height(int height) {
		this.height = height;
		return this;
	}

	public MatrixConfig logoRate(double logoRate) {
		this.logoRate = logoRate;
		return this;
	}

	public MatrixConfig logoSize(Integer logoSize) {
		this.logoSize = logoSize;
		return this;
	}

	public MatrixConfig charset(String charset) {
		this.charset = charset;
		return this;
	}

	public MatrixConfig logoCompress(boolean logoCompress) {
		this.logoCompress = logoCompress;
		return this;
	}

	public MatrixConfig logoFile(File logoFile) {
		this.logoFile = logoFile;
		return this;
	}

	public MatrixConfig margin(int margin) {
		this.margin = margin;
		return this;
	}

	public MatrixConfig type(BarcodeFormat type) {
		this.type = type;
		return this;
	}

	public boolean isLogoCompress() {
		return logoCompress;
	}

	public String getContent() {
		return content;
	}

	public String getCharset() {
		return charset;
	}

	public int getMargin() {
		return margin;
	}

	public Integer getLogoSize() {
		int min = width > height ? height : width;
		// logo 不得大于最小边的1/2
		if (logoSize == null || logoSize < 0 || logoSize > min / 2)
			logoSize = (int) (min * getLogoRate());
		return logoSize;
	}

	public double getLogoRate() {
		if (logoRate <= 0 || logoRate > 0.5)
			logoRate = DEFAULT_LOGO_SIZE_RATE;
		return logoRate;
	}

	public int getWidth() {
		if (width <= 0)
			width = DEFAULT_SIZE;
		return width;
	}

	public File getLogoFile() {
		return logoFile;
	}

	public BarcodeFormat getType() {
		return type;
	}

	public int getHeight() {
		return height;
	}

}
