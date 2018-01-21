package com.linktai.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.junit.Test;

public class Graphies {

	public static String creatFile(String zxingPath, String backPath, String no, String name) {
		BufferedImage image = new BufferedImage(1024, 1448, BufferedImage.TYPE_INT_RGB);
		try {
			BufferedImage read = ImageIO.read(new FileInputStream(new File(backPath)));
			BufferedImage bi = ImageIO.read(new FileInputStream(new File(zxingPath)));
			Graphics graphics = image.getGraphics();
			graphics.drawImage(read.getScaledInstance(1024, 1448, Image.SCALE_DEFAULT), 0, 0, null);
			graphics.drawImage(bi.getScaledInstance(200, 200, Image.SCALE_DEFAULT), 706, 782, null);
			graphics.setColor(Color.black);
			graphics.setFont(new Font("Arial Unicode MS", Font.PLAIN, 40));
			graphics.drawString(no, 248, 827);// 绘制文字
			if(name.length()>=12) {
				String name1 = name.substring(12,name.length()>30?name.length():30);
				name = name.substring(0, 12);
				System.out.println(name1);
				graphics.drawString(name1, 56, 938);
			}
			graphics.drawString(name, 275, 878);// 绘制文字
			graphics.dispose();
			String substring = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
			String path = "images/tickets/" + substring + ".png";
//			String path = "d:/r.png";
			ImageIO.write(image, "png", new File(path));
			return path;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Test
	public void test() {
		creatFile("D:/new-2.gif", "C:\\Users\\17770\\Documents\\WeChat Files\\chenlijia1111\\Files\\归档(1)\\ticket.png", "1223", "jackjackjackjackjackjackjackjackjackjackjackjack");
	}

	
}
