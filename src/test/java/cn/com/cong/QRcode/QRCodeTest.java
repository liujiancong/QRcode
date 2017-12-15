package cn.com.cong.QRcode;

import java.io.IOException;
import org.junit.Test;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import cn.com.cong.QRcode.util.QRcodeUtil;
import junit.framework.Assert;

public class QRCodeTest {
	private String content = "helloWorld";
	private String filename = "F://zxing.png";

	/**
	 * 生成二维码
	 * @throws Exception
	 */
	@Test
	public void testEncode() throws Exception {
		QRcodeUtil.createQRcode(content, filename);
		System.out.println("输出成功");
	}

	
	/**
	 * 解析二维码
	 * @throws IOException 
	 * @throws NotFoundException 
	 */
	@Test
	public void testDecode() throws NotFoundException, IOException {
		Result result = QRcodeUtil.decodeQRcode(filename);
		Assert.assertEquals(content, result.getText());
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
