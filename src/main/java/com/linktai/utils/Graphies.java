package com.linktai.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.junit.Test;

public class Graphies {
	private static String enBack = "images/ticket.png";
	private static String cnBack = "images/tickets-cn.jpg";
//	private static String enBack = "C:\\Users\\17770\\Desktop\\ticket.png";
//	private static String cnBack = "C:\\Users\\17770\\Desktop\\tickets-cn.jpg";

	public static String creatFile(String zxingPath, String lang, String no, String name) {
		if (lang.equals("en")) {
			BufferedImage image = new BufferedImage(1024, 1448, BufferedImage.TYPE_INT_RGB);
			try {
				BufferedImage read = ImageIO.read(new FileInputStream(new File(enBack)));
				BufferedImage bi = ImageIO.read(new FileInputStream(new File(zxingPath)));
				Graphics2D graphics = image.createGraphics();
				graphics.drawImage(read.getScaledInstance(1024, 1448, Image.SCALE_DEFAULT), 0, 0, null);
				graphics.drawImage(bi.getScaledInstance(200, 200, Image.SCALE_DEFAULT), 700, 782, null);
				graphics.setColor(Color.black);
				graphics.setFont(new Font("Arial Unicode MS", Font.PLAIN, 40));
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
	                    RenderingHints.VALUE_ANTIALIAS_ON);
				graphics.drawString(no, 256, 827);// 绘制文字
				if (name.length() > 15) {
					if(name.charAt(15)==' ') {
						String name1 = name.substring(15, name.length() > 30 ? 30 : name.length());
						name = name.substring(0, 15);
						graphics.drawString(name1, 56, 938);
					}else {
						int lastIndexOf = name.lastIndexOf(" ", 15);
						String name1 = name.substring(lastIndexOf, name.length());
						name = name.substring(0, lastIndexOf);
						graphics.drawString(name1, 56, 938);
					}
				}
				graphics.drawString(name, 275, 878);// 绘制文字
				graphics.dispose();
				String substring = UUID.randomUUID().toString().replaceAll("-", "");
				String path = "images/tickets/" + substring + ".png";
//				 String path = "d:/"+substring+".png";
				ImageIO.write(image, "png", new File(path));
				return path;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			BufferedImage image = new BufferedImage(1024, 1448, BufferedImage.TYPE_INT_RGB);
			try {
				BufferedImage read = ImageIO.read(new FileInputStream(new File(cnBack)));
				BufferedImage bi = ImageIO.read(new FileInputStream(new File(zxingPath)));
				Graphics2D graphics = image.createGraphics();
				graphics.drawImage(read.getScaledInstance(1024, 1448, Image.SCALE_DEFAULT), 0, 0, null);
				graphics.drawImage(bi.getScaledInstance(200, 200, Image.SCALE_DEFAULT), 693, 786, null);
				graphics.setColor(Color.black);
				graphics.setFont(new Font("Arial Unicode MS", Font.PLAIN, 40));
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
	                    RenderingHints.VALUE_ANTIALIAS_ON);
				graphics.drawString(no, 267, 838);// 绘制文字
				
				if (name.length() > 15) {
					if(name.charAt(15)==' ') {
						String name1 = name.substring(15, name.length() > 30 ? 30 : name.length());
						name = name.substring(0, 15);
						graphics.drawString(name1, 56, 938);
					}else {
						int lastIndexOf = name.lastIndexOf(" ", 15);
						String name1 = name.substring(lastIndexOf, name.length());
						name = name.substring(0, lastIndexOf);
						graphics.drawString(name1, 146, 943);
					}
				}
				graphics.drawString(name, 306, 890);// 绘制文字
				graphics.dispose();
				String substring = UUID.randomUUID().toString().replaceAll("-", "");
				String path = "images/tickets/" + substring + ".png";
//				 String path = "d:/"+substring+".png";
				ImageIO.write(image, "png", new File(path));
				return path;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Test
	public void test() {
		BufferedImage image = new BufferedImage(1024, 1448, BufferedImage.TYPE_INT_RGB);
		try {
			BufferedImage read = ImageIO.read(new FileInputStream(new File("D:\\tickets-cn.jpg")));
			BufferedImage bi = ImageIO.read(new FileInputStream(new File("D:/new-2.gif")));
			Graphics2D graphics = image.createGraphics();
			graphics.drawImage(read.getScaledInstance(1024, 1448, Image.SCALE_DEFAULT), 0, 0, null);
			graphics.drawImage(bi.getScaledInstance(200, 200, Image.SCALE_DEFAULT), 706, 782, null);
			graphics.setColor(Color.black);
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
                    RenderingHints.VALUE_ANTIALIAS_ON);
			graphics.setFont(new Font("Arial Unicode MS", Font.PLAIN, 40));
			graphics.drawString("12345", 266, 836);// 绘制文字
			
			graphics.drawString("陈礼佳", 305, 890);// 绘制文字
			graphics.dispose();
			String substring = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
//			String path = "images/tickets/" + substring + ".png";
			 String path = "d:/f.png";
			ImageIO.write(image, "png", new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void test2() {
		String string = "Tom Saysanan na Ayudhya";
		System.out.println(string.charAt(15));
		System.out.println(string.length());
	}

}
