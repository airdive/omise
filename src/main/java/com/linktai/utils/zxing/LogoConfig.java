package com.linktai.utils.zxing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LogoConfig {
	/**
	 * ���� logo
	 * 
	 * @param matrixImage
	 *            Դ��ά��ͼƬ
	 * @return ���ش���logo�Ķ�ά��ͼƬ
	 * @throws IOException
	 * @author Administrator sangwenhao
	 */
	public BufferedImage LogoMatrix(BufferedImage matrixImage) throws IOException {
		/**
		 * ��ȡ��ά��ͼƬ����������ͼ����
		 */
		Graphics2D g2 = matrixImage.createGraphics();

		int matrixWidth = matrixImage.getWidth();
		int matrixHeigh = matrixImage.getHeight();

		/**
		 * ��ȡLogoͼƬ
		 */
		BufferedImage logo = ImageIO.read(new File("images/log.png"));

		// ��ʼ����ͼƬ
		g2.drawImage(logo, matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, null);// ����
		BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g2.setStroke(stroke);// ���ñʻ�����
		// ָ�����ȵ�Բ�Ǿ���
		RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth / 5 * 2, matrixHeigh / 5 * 2,
				matrixWidth / 5, matrixHeigh / 5, 20, 20);
		g2.setColor(Color.white);
		g2.draw(round);// ����Բ������

		// ����logo ��һ����ɫ�߿�
		BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g2.setStroke(stroke2);// ���ñʻ�����
		RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth / 5 * 2 + 2, matrixHeigh / 5 * 2 + 2,
				matrixWidth / 5 - 4, matrixHeigh / 5 - 4, 20, 20);
		g2.setColor(new Color(128, 128, 128));
		g2.draw(round2);// ����Բ������

		g2.dispose();
		matrixImage.flush();
		return matrixImage;
	}
}
