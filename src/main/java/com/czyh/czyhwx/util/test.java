package com.czyh.czyhwx.util;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;

public class test {

	public static void main(String[] args) {
		// int[] src = new int[] {1, 3, 5, 7, 8, 9};
		// System.out.println(binarySearch(src, 9));
		// System.out.println(binarySearch(src,9,0,src.length-1));

		// jdk
//		Calendar start = Calendar.getInstance();
//		start.set(2012, Calendar.NOVEMBER, 14);
//
//		Calendar end = Calendar.getInstance();
//		end.set(2012, Calendar.NOVEMBER, 15);
//
//		long startTim = start.getTimeInMillis();
//		long endTim = end.getTimeInMillis();
//		long diff = endTim - startTim;
//		int days = (int) (diff / 1000 / 3600 / 24);

//		// joda-time
//		LocalDate start = new LocalDate(2012, 12, 14);
//		LocalDate end = new LocalDate(2012, 12, 16);
//		int days = Days.daysBetween(, end).getDays();
//		
//		System.out.println(days);
//		
//		DateTime d1 = ""; // 开房
//
//		DateTime d2 = ""; // 退房
//
//		Period p = new Period(d1,d2);
//		if(p.getHours() != 0) return p.getDays() + 1;
//		return p.getDays();
		
		DateTime nowTime = new DateTime();

		DateTime futureTime = new DateTime(2016, 10, 26, 1, 1, 1);

		int days = Days.daysBetween(nowTime, futureTime).getDays();
		
		System.out.println(days);
		
//		int days = Days.daysBetween(new DateTime(tCarnival.getFstartTime()), new DateTime(tCarnival.getFendTime()))
//		.getDays();
//System.out.println(
//		days + "查了几天" + new DateTime(tCarnival.getFstartTime()) + new DateTime(tCarnival.getFendTime()));
//medalRuleDTO.setNumber(days);

	}

	/**
	 * * 二分查找算法 * *
	 * 
	 * @param srcArray
	 *            有序数组 *
	 * @param des
	 *            查找元素 *
	 * @return des的数组下标，没找到返回-1
	 */
	public static int binarySearch(int[] srcArray, int des) {

		int low = 0;
		int high = srcArray.length - 1;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (des == srcArray[middle]) {
				return middle;
			} else if (des < srcArray[middle]) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		return -1;
	}

	/**
	 * 递归方法实现二分查找法.
	 * 
	 * @param Array数组
	 * @param low
	 *            数组第一位置
	 * @param high
	 *            最高
	 * @param key
	 *            要查找的值.
	 * @return 返回值.
	 */
	public static int binarySearch(int[] dataset, int data, int beginIndex, int endIndex) {
		int midIndex = (beginIndex + endIndex) / 2;
		if (data < dataset[beginIndex] || data > dataset[endIndex] || beginIndex > endIndex) {
			return -1;
		}
		if (data < dataset[midIndex]) {
			return binarySearch(dataset, data, beginIndex, midIndex - 1);
		} else if (data > dataset[midIndex]) {
			return binarySearch(dataset, data, midIndex + 1, endIndex);
		} else {
			return midIndex;
		}
	}

	private void HightArrAy() {
		// TODO Auto-generated method stub
	}

}
