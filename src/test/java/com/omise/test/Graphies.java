package com.omise.test;

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

public class Graphies {

	public static String creatFile(String zxingPath, String backPath, String no, String name) {
		BufferedImage image = new BufferedImage(1240, 1754, BufferedImage.TYPE_INT_RGB);
		try {
			BufferedImage read = ImageIO.read(new FileInputStream(new File(backPath)));
			BufferedImage bi = ImageIO.read(new FileInputStream(new File(zxingPath)));
			Graphics graphics = image.getGraphics();
			graphics.drawImage(read.getScaledInstance(1240, 1754, Image.SCALE_DEFAULT), 0, 0, null);
			graphics.drawImage(bi.getScaledInstance(200, 200, Image.SCALE_DEFAULT), 96, 900, null);
			graphics.setColor(Color.black);
			Font font = new Font("微软雅黑", Font.BOLD, 24);
			graphics.setFont(new Font("Arial Unicode MS", Font.BOLD, 40));
			graphics.drawString(no, 608, 951);// 绘制文字
			graphics.drawString(name, 636, 1000);// 绘制文字
			graphics.dispose();
			String substring = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
			ImageIO.write(image, "png", new File("D:/" + substring + ".png"));
			return "D:/" + substring + ".png";
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		BufferedImage image = new BufferedImage(1240, 1754, BufferedImage.TYPE_INT_RGB);
		try {
			BufferedImage read = ImageIO.read(new FileInputStream(new File("D:/tickets.png")));
			BufferedImage bi = ImageIO.read(new FileInputStream(new File("D:/new-1.gif")));
			Graphics graphics = image.getGraphics();
			graphics.drawImage(read.getScaledInstance(1240, 1754, Image.SCALE_DEFAULT), 0, 0, null);
			graphics.drawImage(bi.getScaledInstance(200, 200, Image.SCALE_DEFAULT), 96, 900, null);
			graphics.setColor(Color.black);
			Font font = new Font("微软雅黑", Font.BOLD, 24);
			graphics.setFont(new Font("Arial Unicode MS", Font.BOLD, 40));
			graphics.drawString("ภาษาไทย", 608, 951);// 绘制文字
			graphics.dispose();
			ImageIO.write(image, "png", new File("D:/tickets7.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
