package com.omise.test;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.linktai.utils.zxing.MatrixToImageWriter;

public class QrCodeDemo {
	public static void Encode_QR_CODE() throws IOException, WriterException {
		String contents = "process.start(http://www.sina.com.cn)"; // ��ά������
		int width = 430; // ��ά��ͼƬ��� 300
		int height = 430; // ��ά��ͼƬ�߶�300

		String format = "gif";// ��ά���ͼƬ��ʽ gif

		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// ָ������ȼ�,������L 7%��M 15%��Q 25%��H 30%��
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// ������ʹ���ַ�������
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		// hints.put(EncodeHintType.MAX_SIZE, 350);//����ͼƬ�����ֵ
		// hints.put(EncodeHintType.MIN_SIZE, 100);//����ͼƬ����Сֵ
		hints.put(EncodeHintType.MARGIN, 1);// ���ö�ά��ߵĿնȣ��Ǹ���

		BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, // Ҫ���������
				// �������ͣ�Ŀǰzxing֧�֣�Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,
				// Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,
				// MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E
				// 1D,UPC/EAN extension,UPC_EAN_EXTENSION
				BarcodeFormat.QR_CODE, width, // ������Ŀ��
				height, // ������ĸ߶�
				hints);// ����������ʱ��һЩ����,�����ѡ

		// ���ɶ�ά��
		File outputFile = new File("d:/new-4.gif");// ָ�����·��

		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
	}

	public static void main(String[] args) throws Exception {
		try {
			Encode_QR_CODE();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
