package edu.upenn.cis350.gpx;

import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class GPXcalculatorTest {
	
	public GPXtrkpt point1, point2,point3,point4,point5,point6,point7;
	Date time1;
	@Test
	public void testGPXtrkptConstructer() {
		Date time1=new Date(); 
		GPXtrkpt point1=new GPXtrkpt(0, 0, time1);
		GPXtrkpt point2=new GPXtrkpt(90, -45, time1);
		GPXtrkpt point3=new GPXtrkpt(-45, 180, time1);
		GPXtrkpt point4=new GPXtrkpt(140, 45, time1);
		GPXtrkpt point5=new GPXtrkpt(-140, 45, time1);
		GPXtrkpt point6=new GPXtrkpt(50, 200, time1);
		GPXtrkpt point7=new GPXtrkpt(50, -200, time1);
		
	}
	
	@Test
	//Calculate the total distance
	public void testNormalDistance(){
		ArrayList<GPXtrkpt> list1=new ArrayList<GPXtrkpt>();
		list1.add(point1);
		list1.add(point2);
		GPXtrkseg seg1=new GPXtrkseg(list1);
		ArrayList<GPXtrkseg> list2=new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		GPXtrk trk1=new GPXtrk("track1",list2);
		double result=GPXcalculator.calculateDistanceTraveled(trk1);
		assertEquals(Math.sqrt(10125),result, 0.00001);
		
	}
	
	@Test
	//If the GPXtrk contains no GPXtrkseg objects, the method should return -1
	public void testNoGPXtrkseg(){
		GPXtrk trk1=new GPXtrk("trk1",null);
		double result1=GPXcalculator.calculateDistanceTraveled(trk1);
		assertEquals(-1.0,result1,0.00001);	
	}
	
	@Test
	//If the GPXtrk object is null, the method should return -1
	public void testGPXtrkNull(){
		double result1=GPXcalculator.calculateDistanceTraveled(null);
		assertEquals(-1.0,result1,0.00001);			
	}
	
	@Test
	//If any GPXtrkseg in the GPXtrk is null, the distance traveled for that GPXtrkseg should be considered 0.
	public void testGPXtrksegNULL(){
		ArrayList<GPXtrkpt> list1=new ArrayList<GPXtrkpt>();
		list1.add(point1);
		list1.add(point2);
		GPXtrkseg seg1=new GPXtrkseg(list1);
		ArrayList<GPXtrkseg> list2=new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		list2.add(null);
		GPXtrk trk1=new GPXtrk("track1",list2);
		double result=GPXcalculator.calculateDistanceTraveled(trk1);
		assertEquals(0.0,result, 0.00001);
	}
	
	@Test
	//If a GPXtrkseg contains no GPXtrkpt objects, the distance traveled for that GPXtrkseg should be considered 0.
	public void testNoGPXtrkpt(){
		ArrayList<GPXtrkpt> list1=new ArrayList<GPXtrkpt>();
		GPXtrkseg seg1=new GPXtrkseg(list1);
		ArrayList<GPXtrkseg> list2=new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		GPXtrk trk1=new GPXtrk("track1",list2);
		double result=GPXcalculator.calculateDistanceTraveled(trk1);
		assertEquals(0.0,result, 0.00001);	
	}
	
	@Test
	//If a GPXtrkseg contains only one GPXtrkpt, the distance traveled for that GPXtrkseg should be considered 0;
	public void testOnePoint(){
		ArrayList<GPXtrkpt> list1=new ArrayList<GPXtrkpt>();
		list1.add(point1);
		GPXtrkseg seg1=new GPXtrkseg(list1);
		ArrayList<GPXtrkseg> list2=new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		GPXtrk trk1=new GPXtrk("track1",list2);
		double result=GPXcalculator.calculateDistanceTraveled(trk1);
		assertEquals(0.0,result, 0.00001);	
	}
	
	@Test
	//If any GPXtrkpt in a GPXtrkseg is null,the distance traveled for that GPXtrkseg should be considered 0;
	public void testNullSeg(){
		ArrayList<GPXtrkpt> list1=new ArrayList<GPXtrkpt>();
		list1.add(null);
		list1.add(point1);
		GPXtrkseg seg1=new GPXtrkseg(list1);
		ArrayList<GPXtrkseg> list2=new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		GPXtrk trk1=new GPXtrk("track1",list2);
		double result=GPXcalculator.calculateDistanceTraveled(trk1);
		assertEquals(0.0,result, 0.00001);	
	}
	
	@Test
	public void testLat(){
		ArrayList<GPXtrkpt> list1=new ArrayList<GPXtrkpt>();
		list1.add(point4);
		list1.add(point1);
		GPXtrkseg seg1=new GPXtrkseg(list1);
		ArrayList<GPXtrkseg> list2=new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		GPXtrk trk1=new GPXtrk("track1",list2);
		double result=GPXcalculator.calculateDistanceTraveled(trk1);
		assertEquals(0.0,result, 0.00001);	
	}
	
	@Test
	public void testLong(){
		ArrayList<GPXtrkpt> list1=new ArrayList<GPXtrkpt>();
		list1.add(point2);
		list1.add(point6);
		GPXtrkseg seg1=new GPXtrkseg(list1);
		ArrayList<GPXtrkseg> list2=new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		GPXtrk trk1=new GPXtrk("track1",list2);
		double result=GPXcalculator.calculateDistanceTraveled(trk1);
		assertEquals(0.0,result, 0.00001);	
	}
	
	
	
	

}
