package com.example.Component;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.bean.*;
import com.example.graphics.*;

import java.awt.*;
import java.io.*;
import java.util.List;


public class MyFile {
    public JsonList jsonList=new JsonList();
    //public List<JSONdata> jsondata=new LinkedList<>();
    public List<CanvasPanel> panels;
    public File file;
    public MyFile(List<CanvasPanel> panels,File file) {
        this.panels=panels;
        this.file=file;

    }
    public void getJsonData() throws Exception{
        for(int i=0;i<panels.size();i++){
            JSONdata j = new JSONdata();
            copy(panels.get(i),j);
            jsonList.jsonDataList.add(j);
        }
        saveFile();
    }
    public void getPanels() throws Exception{
        String s = readFile();
        jsonList = JSON.parseObject(s, JsonList.class);
    }
    public void saveFile() throws Exception {
        //String s = JSON.toJSONString(jsonList.jsonDataList);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jsonDataList",jsonList.jsonDataList);
        String s = jsonObject.toString();
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        writer.write(s);
        writer.flush();
        writer.close();
    }
    public String readFile() throws Exception {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file),"UTF-8");
        int ch=0;
        StringBuffer sb=new StringBuffer();
        while ((ch=inputStreamReader.read())!=-1){
            sb.append((char)ch);
        }
        inputStreamReader.close();
        return sb.toString();
    }

    public void copy(CanvasPanel panel,JSONdata jd){
        Component[] components = panel.getComponents();
        MypointsBean mypointsBean = new MypointsBean();
        mypointsBean.lines=panel.points.lines;
        for(Color c:panel.points.colors){
            mypointsBean.blue.add(c.getBlue());
            mypointsBean.green.add(c.getGreen());
            mypointsBean.red.add(c.getRed());
            mypointsBean.alpha.add((c.getAlpha()));
        }
        jd.mypointsBeans=mypointsBean;
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
                myTextBean.alpha=myText.lineColor.getAlpha();
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
                myRectBean.fillAlpha=myRect.fillColor.getAlpha();
                myRectBean.lineBorderRed=myRect.lineColor.getRed();
                myRectBean.lineBorderGreen=myRect.lineColor.getGreen();
                myRectBean.lineBorderBlue=myRect.lineColor.getBlue();
                myRectBean.lineBorderAlpha=myRect.lineColor.getAlpha();
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
                myRoundRectBean.fillAlpha=myRoundRect.fillColor.getAlpha();
                myRoundRectBean.lineBorderRed=myRoundRect.lineColor.getRed();
                myRoundRectBean.lineBorderGreen=myRoundRect.lineColor.getGreen();
                myRoundRectBean.lineBorderBlue=myRoundRect.lineColor.getBlue();
                myRoundRectBean.lineBorderAlpha=myRoundRect.lineColor.getAlpha();
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
                myCircleBean.fillAlpha=myCircle.fillColor.getAlpha();
                myCircleBean.lineBorderRed=myCircle.lineColor.getRed();
                myCircleBean.lineBorderGreen=myCircle.lineColor.getGreen();
                myCircleBean.lineBorderBlue=myCircle.lineColor.getBlue();
                myCircleBean.lineBorderAlpha=myCircle.lineColor.getAlpha();
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
                myLineBean.lineWidth=myLine.lineWidth;
                myLineBean.lineRed=myLine.lineColor.getRed();
                myLineBean.lineGreen=myLine.lineColor.getGreen();
                myLineBean.lineBlue=myLine.lineColor.getBlue();
                myLineBean.lineAlpha=myLine.lineColor.getAlpha();
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
                myArrowHeadBean.fillAlpha=myArrowHead.fillColor.getAlpha();
                myArrowHeadBean.lineBorderRed=myArrowHead.lineColor.getRed();
                myArrowHeadBean.lineBorderGreen=myArrowHead.lineColor.getGreen();
                myArrowHeadBean.lineBorderBlue=myArrowHead.lineColor.getBlue();
                myArrowHeadBean.lineBorderAlpha=myArrowHead.lineColor.getAlpha();
                jd.myArrowHeads.add(myArrowHeadBean);
            }else if(components[i] instanceof MyImage){
                MyImage myImage=(MyImage) components[i];
                MyImageBean myImageBean = new MyImageBean();
                myImageBean.minX=myImage.getMinX();
                myImageBean.maxX=myImage.getMaxX();
                myImageBean.minY=myImage.getMinY();
                myImageBean.maxY=myImage.getMaxY();
                myImageBean.componentName=myImage.getName();
                try {
                    myImageBean.s=myImage.imageToBase64();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                jd.myImages.add(myImageBean);
            }
        }
    }
}
