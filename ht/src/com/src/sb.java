package com.src;
import java.awt.Color;
import java.util.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;


public class sb {
	int version;
	String star;
	String end;
	String path;
	public static void Createqrcode(int version,String path,String star,String end) throws IOException{
		int imgSize=63+(version-1)*12;
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeVersion(version);
		BufferedImage bufferedImage = new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_RGB);
		Graphics2D gs = bufferedImage.createGraphics();
		gs.setBackground(Color.white);
		gs.clearRect(0, 0,imgSize,imgSize);
		String contest=
			"BEGIN:VCARD\r\n" + 
			"FN:姓名:洪涛\r\n"+
			"ORG:学校：科师	学院：数信\r\n"+
			"TITLE:共青团优秀团员先进分子\r\n" + 
			"TEL;WORK;VOICE:8008208820\r\n"+
			"TEL;HOME;VOICE:12345678911\r\n"+
			"TEL;CELL;VOICE:8008208821\r\n"+
			"ADR;WORK:衡水\r\n"+
			"ADR;HOME:先进村科技新区\r\n"+
			"EMAIL;HOME:532231254@qq.com\r\n"+ 
			"PHOTO;VALUE=uri:http://a0.att.hudong.com/04/63/01300000251368122500637235433.jpg" + 
			"END:VCARD";
		int starR=0,starG=0,starB=0;
		if(null!= star)
		{
			String[] startcolor=star.split(",");
			starR=Integer.valueOf(startcolor[0]);
			starG=Integer.valueOf(startcolor[1]);
			starB=Integer.valueOf(startcolor[2]);			
		}
		int endR=0,endG=0,endB=0;
		if(null!= end)
		{
			String[] endcolor=end.split(",");
			endR=Integer.valueOf(endcolor[0]);
			endG=Integer.valueOf(endcolor[1]);
			endB=Integer.valueOf(endcolor[2]);			
		}
		boolean[] []calQrcode=qrcode.calQrcode(contest.getBytes());
		for (int i= 0; i < calQrcode.length; i++) {
			for (int j = 0; j < calQrcode.length; j++) {
				if(calQrcode[i][j]){
					int r=starR+(endR-starR)*(j+1)/calQrcode.length ;
					int g=starG+(endG-starG)*(j+1)/calQrcode.length ;
					int b=starB+(endB-starB)*(j+1)/calQrcode.length;					
					Color cc = new Color(r,g,b);
					gs.setColor(cc);
					gs.fillRect(i*3,j*3,3,3);
				}
			}
		}
		BufferedImage logo=ImageIO.read(new File("D:/xixixi.png"));
		int logoSize=imgSize/3;
		int o=(imgSize-logoSize)/2;
		gs.drawImage(logo, o, o, logoSize,logoSize,null);
		qrcode.setQrcodeErrorCorrect('M');
		gs.dispose();
		bufferedImage.flush();
		ImageIO.write(bufferedImage, "png", new File(path));
		System.out.println("成功");
	}

	public static void main(String[] args) throws IOException {
			sb c=new sb();
		Scanner reader=new Scanner(System.in);	
		
		    c.version=15;
		    c.star="50,255,200";
		    c.end="0,120,255";
		    c.path ="D:/a.png";
			c.Createqrcode(c.version,c.path,c.star,c.end);


			
	}

}
