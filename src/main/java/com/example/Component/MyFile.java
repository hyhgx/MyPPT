package com.example.Component;

import com.alibaba.fastjson2.JSON;
import com.example.bean.*;
import com.example.graphics.*;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class MyFile {
    public List<JSONdata> jsondata=new LinkedList<>();
    public List<CanvasPanel> panels;
    public MyFile(List<CanvasPanel> panels) throws Exception {
        this.panels=panels;
        for(int i=0;i<panels.size();i++){
            JSONdata j = new JSONdata();
            copy(panels.get(i),j);
            jsondata.add(j);
        }
        saveFile();
    }
    public void saveFile() throws Exception {
        String s = JSON.toJSONString(jsondata);
        File file = new File("src/main/resources/data.json");
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        writer.write(s);
        writer.flush();
        writer.close();
    }
    public void copy(CanvasPanel panel,JSONdata jd){
        Component[] components = panel.getComponents();
        for(int i=0;i<components.length;i++){
            if(components[i] instanceof MyText){
                MyText myText=(MyText) components[i];
                MyTextBean myTextBean = new MyTextBean();
                myTextBean.minX=myText.getMinX();
                myTextBean.maxX=myText.getMaxX();
                myTextBean.minY=myText.getMinY();
                myTextBean.maxY=myText.getMaxY();
                myTextBean.componentName=myText.getName();
                myTextBean.fontName=myText.font.getFontName();
                myTextBean.fontStyle=myText.font.getStyle();
                myTextBean.fontSize=myText.font.getSize();
                myTextBean.red=myText.lineColor.getRed();
                myTextBean.green=myText.lineColor.getGreen();
                myTextBean.blue=myText.lineColor.getBlue();
                myTextBean.contentText=myText.text.getText();
                jd.myTexts.add(myTextBean);
            }else if(components[i] instanceof MyRect){
                MyRect myRect=(MyRect) components[i];
                MyRectBean myRectBean = new MyRectBean();
                myRectBean.minX=myRect.getMinX();
                myRectBean.maxX=myRect.getMaxX();
                myRectBean.minY=myRect.getMinY();
                myRectBean.maxY=myRect.getMaxY();
                myRectBean.componentName=myRect.getName();
                myRectBean.fillBlue=myRect.fillColor.getBlue();
                myRectBean.fillRed=myRect.fillColor.getRed();
                myRectBean.fillGreen=myRect.fillColor.getGreen();
                myRectBean.lineBorderRed=myRect.lineColor.getRed();
                myRectBean.lineBorderGreen=myRect.lineColor.getGreen();
                myRectBean.lineBorderBlue=myRect.lineColor.getBlue();
                jd.myRects.add(myRectBean);
            }else if(components[i] instanceof MyRoundRect){
                MyRoundRect myRoundRect=(MyRoundRect) components[i];
                MyRoundRectBean myRoundRectBean = new MyRoundRectBean();
                myRoundRectBean.minX=myRoundRect.getMinX();
                myRoundRectBean.maxX=myRoundRect.getMaxX();
                myRoundRectBean.minY=myRoundRect.getMinY();
                myRoundRectBean.maxY=myRoundRect.getMaxY();
                myRoundRectBean.componentName=myRoundRect.getName();
                myRoundRectBean.fillBlue=myRoundRect.fillColor.getBlue();
                myRoundRectBean.fillRed=myRoundRect.fillColor.getRed();
                myRoundRectBean.fillGreen=myRoundRect.fillColor.getGreen();
                myRoundRectBean.lineBorderRed=myRoundRect.lineColor.getRed();
                myRoundRectBean.lineBorderGreen=myRoundRect.lineColor.getGreen();
                myRoundRectBean.lineBorderBlue=myRoundRect.lineColor.getBlue();
                jd.myRoundRects.add(myRoundRectBean);
            }else if(components[i] instanceof MyCircle){
                MyCircle myCircle=(MyCircle) components[i];
                MyCircleBean myCircleBean = new MyCircleBean();
                myCircleBean.minX=myCircle.getMinX();
                myCircleBean.maxX=myCircle.getMaxX();
                myCircleBean.minY=myCircle.getMinY();
                myCircleBean.maxY=myCircle.getMaxY();
                myCircleBean.componentName=myCircle.getName();
                myCircleBean.fillBlue=myCircle.fillColor.getBlue();
                myCircleBean.fillRed=myCircle.fillColor.getRed();
                myCircleBean.fillGreen=myCircle.fillColor.getGreen();
                myCircleBean.lineBorderRed=myCircle.lineColor.getRed();
                myCircleBean.lineBorderGreen=myCircle.lineColor.getGreen();
                myCircleBean.lineBorderBlue=myCircle.lineColor.getBlue();
                jd.myCircles.add(myCircleBean);
            }else if(components[i] instanceof MyLine){
                MyLine myLine=(MyLine) components[i];
                MyLineBean myLineBean = new MyLineBean();
                myLineBean.x1=myLine.getX1();
                myLineBean.x2=myLine.getX2();
                myLineBean.y1=myLine.getY1();
                myLineBean.y2=myLine.getY2();
                myLineBean.componentName=myLine.getName();
                myLineBean.lineT=myLine.lineT;
                myLineBean.lineRed=myLine.lineColor.getRed();
                myLineBean.lineGreen=myLine.lineColor.getGreen();
                myLineBean.lineBlue=myLine.lineColor.getBlue();
                jd.myLines.add(myLineBean);
            }else if(components[i] instanceof MyArrowHead){
                MyArrowHead myArrowHead=(MyArrowHead) components[i];
                MyArrowHeadBean myArrowHeadBean = new MyArrowHeadBean();
                myArrowHeadBean.minX=myArrowHead.getMinX();
                myArrowHeadBean.maxX=myArrowHead.getMaxX();
                myArrowHeadBean.minY=myArrowHead.getMinY();
                myArrowHeadBean.maxY=myArrowHead.getMaxY();
                myArrowHeadBean.componentName=myArrowHead.getName();
                myArrowHeadBean.fillBlue=myArrowHead.fillColor.getBlue();
                myArrowHeadBean.fillRed=myArrowHead.fillColor.getRed();
                myArrowHeadBean.fillGreen=myArrowHead.fillColor.getGreen();
                myArrowHeadBean.lineBorderRed=myArrowHead.lineColor.getRed();
                myArrowHeadBean.lineBorderGreen=myArrowHead.lineColor.getGreen();
                myArrowHeadBean.lineBorderBlue=myArrowHead.lineColor.getBlue();
                jd.myArrowHeads.add(myArrowHeadBean);
            }else if(components[i] instanceof MyImage){
                MyImage myImage=(MyImage) components[i];
                MyImageBean myImageBean = new MyImageBean();
                myImageBean.minX=myImage.getMinX();
                myImageBean.maxX=myImage.getMaxX();
                myImageBean.minY=myImage.getMinY();
                myImageBean.maxY=myImage.getMaxY();
                myImageBean.componentName=myImage.getName();
                myImageBean.image=myImage.getImage();
                jd.myImages.add(myImageBean);
            }
        }
    }
}
