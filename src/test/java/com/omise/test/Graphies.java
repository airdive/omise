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

import javax.imageio.ImageIO;

public class Graphies {
	public static void main(String[] args) {
		BufferedImage image = new BufferedImage(1240, 1754, BufferedImage.TYPE_INT_RGB);
		try {
			BufferedImage read = ImageIO.read(new FileInputStream(new File("D:/tickets.png")));
			Graphics graphics = image.getGraphics();
			graphics.drawImage(read.getScaledInstance(1240, 1754, Image.SCALE_DEFAULT),0,0,null);
			graphics.setColor(Color.black);
			Font font = new Font("微软雅黑", Font.BOLD, 24);
			graphics.setFont(new Font("Arial Unicode MS", Font.BOLD, 40));
			graphics.drawString("ภาษาไทย", 608, 951);//绘制文字
			graphics.dispose();
			ImageIO.write(image, "png", new File("D:/tickets1.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
