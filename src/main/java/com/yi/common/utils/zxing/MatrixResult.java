package com.yi.common.utils.zxing;

import com.yi.common.utils.DateUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.Random;

/**
 * 编码图片生成结果<br>
 * 可以转换成字符串，保存到文件，输出到流，或者直接取出缓存的image
 *
 * @Date 2017年4月16日 下午9:01:23
 */
public class MatrixResult implements Serializable {
	private static final Logger LOGGER = LoggerFactory.getLogger(MatrixResult.class);
	private static final long serialVersionUID = 1L;
	private MatrixConfig config;
	private BufferedImage image;

	public MatrixResult(MatrixConfig config, BufferedImage image) {
		this.config = config;
		this.image = image;
	}

	/** 输出到流 */
	public void out(OutputStream out) throws IOException {
		ImageIO.write(image, MatrixConfig.DEFAULT_FORMAT, out);
	}

	/**
	 * 返回的是这个编码图片的imageBase64字符串<br>
	 * 前端用 <img src="data:image/png;base64,${imgString}"/> 其中${imgString}对应图片的imageBase64字符串
	 */
	public String toBase64() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		out(baos);

		// 转换为base64编码字符串
		String imageBase64QRCode = Base64Utils.encodeToString(baos.toByteArray());
		baos.close();
		return imageBase64QRCode;
	}

	/** 保存编码图片到指定文件，如果文件存在则会覆盖 */
	public File save(File saveFile) throws IOException {
		File dir = saveFile.getParentFile();
		if (!dir.exists()) { // 如果文件不攒在， 级联创建父文件夹
			try {
				FileUtils.forceMkdir(dir);
			} catch (IOException e) {
				LOGGER.error("保存编码图片，创建文件夹失败！", e);
				return null;
			}
		}
		// 校验保存格式,不支持的格式，保存文件：save.getAbsolutePath()+.png
		String format = FilenameUtils.getExtension(saveFile.getName());
		if (format == null || !MatrixConfig.SUPPORT_FORMAT.contains("|" + format.toLowerCase() + "|")) {
			return save(dir.getAbsolutePath(), saveFile.getName(), MatrixConfig.DEFAULT_FORMAT);
		} else {// 写入文件
			ImageIO.write(image, FilenameUtils.getExtension(saveFile.getName()), saveFile);
			return saveFile;
		}
	}

	/** 保存为指定位置的文件 */
	public File save(String savePath) throws IOException {
		savePath = FilenameUtils.normalize(savePath);
		return save(FilenameUtils.getFullPath(savePath), FilenameUtils.getBaseName(savePath), FilenameUtils.getExtension(savePath));
	}

	/** 保存为指定文件夹下的文件，文件名称随机 */
	public File save(String saveDir, String format) throws IOException {
		return save(saveDir, null, format);
	}

	/**
	 * 保存为文件
	 *
	 * @param saveDir 保存的文件路径
	 * @param saveName 保存的文件名称，不带后缀名的
	 * @param format 保存的文件格式
	 * @return 保存的文件
	 */
	protected File save(String saveDir, String saveName, String format) throws IOException {
		File dir = new File(FilenameUtils.normalize(saveDir));
		if (!dir.exists()) { // 如果文件不攒在， 级联创建父文件夹
			try {
				FileUtils.forceMkdir(dir);
			} catch (IOException e) {
				LOGGER.error("保存编码图片，创建文件夹失败！", e);
				return null;
			}
		}

		// 校验保存格式,不传或者不支持的格式，设置为默认的png
		if (format == null || !MatrixConfig.SUPPORT_FORMAT.contains("|" + format.toLowerCase() + "|")) {
			format = MatrixConfig.DEFAULT_FORMAT;
		}
		// 校验保存的文件名
		if (StringUtils.isBlank(saveName)) {
			saveName = DateUtils.parseDateToStr("yyyyMMddHHmmssSSS",new Date()) + new Random().nextInt(9);
		}

		File realSaveFile = new File(dir, saveName + "." + format);
		// 保存文件
		ImageIO.write(image, format, realSaveFile);
		return realSaveFile;
	}

	public MatrixConfig getConfig() {
		return config;
	}

	public BufferedImage getImage() {
		return image;
	}

}
