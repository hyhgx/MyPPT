package com.example.graphics;




import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class MyImage extends MyComponent2D {

    public Image getImage() {
        return image;
    }

    private final Image image;

    public MyImage(int minX,int minY,int maxX,int maxY,Image image){
        super(0,0,null);
        this.minX=minX;
        this.minY=minY;
        this.maxX=maxX;
        this.maxY=maxY;
        this.image=image;
        this.setBounds(minX-5,minY-5,maxX-minX+10,maxY-minY+10);
        this.repaint();
    }
    public MyImage(int minX,int minY,int maxX,int maxY,String s) throws Exception {
        super(0,0,null);
        this.minX=minX;
        this.minY=minY;
        this.maxX=maxX;
        this.maxY=maxY;
        this.image=Base64ToImage(s);
        this.setBounds(minX-5,minY-5,maxX-minX+10,maxY-minY+10);
        this.repaint();
    }
    @Override
    public MyComponent cloneMySelf() {
        return new MyImage(minX,minY,maxX,maxY,image);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,5,5,maxX-minX,maxY-minY,new Color(255,255,255),null);
    }
    public BufferedImage Base64ToImage(String s) throws Exception {
        Base64.Decoder decoder =Base64.getDecoder();
        byte[] decode = decoder.decode(s);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decode);
        return ImageIO.read(byteArrayInputStream);

    }
    public String imageToBase64() throws IOException {//得到向文件保存字符串
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(image,0,0,null);
        graphics.dispose();//得到Bufferimage
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage,"jpg",byteArrayOutputStream);
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(byteArrayOutputStream.toByteArray());
        return new String(encode,0,encode.length);

    }
}
