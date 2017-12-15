package cn.com.cong.QRcode.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRcodeUtil {
	/**
	 * 创建二维码
	 * @param content
	 * @param filename [optional]
	 * @param filepath [optional]
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void createQRcode(String content, String filename) 
			throws WriterException, IOException {
		if(null == filename || "".equals(filename)) {
			filename = "F://default.png";
		}
		int width = 300;
		int height = 300;
		String format = "png";
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//纠错等级
		hints.put(EncodeHintType.MARGIN, 2);//边距
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, 
				BarcodeFormat.QR_CODE, width, height, hints);
		Path path = new File(filename).toPath();
		MatrixToImageWriter.writeToPath(bitMatrix, format, path);
	}
	/**
	 * 解析二维码
	 * @param filename
	 * @throws IOException 
	 * @throws NotFoundException 
	 */
	public static Result decodeQRcode(String filename) throws IOException, NotFoundException {
		BufferedImage image = ImageIO.read(new File(filename));
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		Binarizer binarizer = new HybridBinarizer(source);
		BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
		Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
		//优化精度
		hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
		//复杂模式，开启PURE_BARCODE模式
		hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
		return new MultiFormatReader().decode(binaryBitmap, hints);
	}
}
