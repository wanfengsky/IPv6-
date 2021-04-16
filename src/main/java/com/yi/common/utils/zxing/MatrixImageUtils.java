package com.yi.common.utils.zxing;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 编码图片生成工具
 *
 * @Date 2017年4月14日 上午10:20:06
 */
public class MatrixImageUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(MatrixImageUtils.class);

	// ---------------------------------- Decode -------------------------------------- //
	/** 解析编码文件 */
	public static String decode(File file) throws Exception {
		if (file == null || !file.exists() || !file.canRead())
			return null;
		BufferedImage image = ImageIO.read(file);
		if (image == null)
			return null;

		// 解码
		BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Map<DecodeHintType, Object> hints = new HashMap<>();
		hints.put(DecodeHintType.CHARACTER_SET, MatrixConfig.DEFAULT_CHARSET);

		Result result = new MultiFormatReader().decode(bitmap, hints);
		return result.getText();
	}

	/** 解析编码文件 */
	public static String decode(String path) throws Exception {
		return decode(new File(path));
	}

	// ---------------------------------- Encode -------------------------------------- //
	/**
	 * 对内容进行编码，生成二维码
	 *
	 * @param content 要编码的内容
	 */
	public static MatrixResult encode(String content) throws Exception {
		return encode(new MatrixConfig(content));
	}

	/**
	 * 对内容进行编码，生编码图片码
	 *
	 * @param content 要编码的内容
	 * @param size 生成编码图片的大小
	 */
	public static MatrixResult encode(String content, int size) throws Exception {
		return encode(new MatrixConfig(content, size));
	}

	/**
	 * 对内容进行编码，在生成的编码图片中插入logo
	 *
	 * @param content 要编码的内容
	 * @param logoPath 编码图片中心插入的logo图片路径
	 */
	public static MatrixResult encode(String content, String logoPath) throws Exception {
		return encode(new MatrixConfig(content, logoPath));
	}

	/**
	 * 对内容进行编码，并且在生成的编码图片中插入logo，将生成的编码图片保存在指定的路径下或者文件中
	 *
	 * @param content 要编码的内容
	 * @param logoPath 编码图片中心的logo图片路径
	 * @param size 生成的编码图片的大小
	 */
	public static MatrixResult encode(String content, String logoPath, int size) throws Exception {
		return encode(new MatrixConfig(content, logoPath, size));
	}

	/**
	 * 生成特定类型的编码图片
	 *
	 * @param content 编码内容
	 * @param type 编码类型
	 * @param width 生成图的宽度
	 * @param height 生成图的高度
	 */
	public static MatrixResult encode(String content, BarcodeFormat type, int width, int height) throws Exception {
		return encode(new MatrixConfig(content, type, width, height));
	}

	/**
	 * 生成编码的核心方法，可以灵活的配置编码图片生成的参数
	 *
	 * @param config 配置类
	 */
	public static MatrixResult encode(MatrixConfig config) throws Exception {
		// 配置生成的参数
		Map<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, config.getCharset());
		hints.put(EncodeHintType.MARGIN, config.getMargin());
		BitMatrix bitMatrix = new MultiFormatWriter().encode(config.getContent(), config.getType(), config.getWidth(), config.getHeight(), hints);
		// 使用矩阵生成编码图片图片
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}

		if (config.getLogoFile() != null) {// 有中心图片
			insertImage(image, config);
		}

		// 返回编码图片结果
		return new MatrixResult(config, image);
	}

	/** 在编码图片中间插入logo图片 */
	private static void insertImage(BufferedImage source, MatrixConfig config) throws Exception {
		if (!config.getLogoFile().exists()) {// 图片存在
			LOGGER.error("[编码图片] 配置中logo地址为：{}, 生成时未找到logo图像，生成之后将不携带logo！", config.getLogoFile().getAbsolutePath());
			return;
		}

		Image src = ImageIO.read(config.getLogoFile());
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (config.isLogoCompress()) {// 压缩LOGO
			if (width > config.getLogoSize())
				width = config.getLogoSize();
			if (height > config.getLogoSize())
				height = config.getLogoSize();

			Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (config.getWidth() - width) / 2;
		int y = (config.getHeight() - height) / 2;
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}
}