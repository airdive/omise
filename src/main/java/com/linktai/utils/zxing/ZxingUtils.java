package com.linktai.utils.zxing;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.UUID;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ZxingUtils {
	public static String Encode_QR_CODE(String contents) throws IOException, WriterException {
		int width = 430; // ��ά��ͼƬ��� 300
		int height = 430; // ��ά��ͼƬ�߶�300

		String format = "gif";// ��ά���ͼƬ��ʽ gif

		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		//容错
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// ������ʹ���ַ�������
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		// hints.put(EncodeHintType.MAX_SIZE, 350);ֵ
		// hints.put(EncodeHintType.MIN_SIZE, 100);ֵ
		hints.put(EncodeHintType.MARGIN, 1);

		BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, 
				BarcodeFormat.QR_CODE, width, 
				height, 
				hints);

		// ���ɶ�ά��
		String substring = UUID.randomUUID().toString().replaceAll("-", "");
		String path = "images"+ File.separator +"zxing"+ File.separator +substring+".gif";
		File outputFile = new File(path);// ָ�����·��

		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		return path;
	}
}
