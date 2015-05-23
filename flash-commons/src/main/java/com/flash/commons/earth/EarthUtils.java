package com.flash.commons.earth;


/**
 * 商铺工具类 包含 距离的计算
 * 
 * @author Leon
 * @date 2014年12月27日
 * @since v 1.0
 */
public class EarthUtils {
	private final static int EARTH_RADIUS = 6371000;// 单位米

	/**
	 * 计算店铺到指定点的距离
	 * @param lng1	经度1 南极到北极的连线
	 * @param lat1	纬度1 横着赤道的是纬度
	 * @param lng2 	经度2
	 * @param lat2	纬度2
	 * @return
	 */
	public static double getDistance(double lng1, double lat1, double lng2,
			double lat2) {
		lng1 = rad(lng1);//经度
		lng2 = rad(lng2);//经度
		lat1 = rad(lat1);//纬度
		lat2 = rad(lat2);
		double calcLatitude = lat1 - lat2;
		double calcLongitude = lng1 - lng2;
		double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(calcLatitude / 2), 2)
				+ Math.cos(lat1) * Math.cos(lat2)
				* Math.pow(Math.sin(calcLongitude / 2), 2)));
		distance = distance * EARTH_RADIUS;
//		s = (double) (Math.round(s * 10000) / 10000);
		distance = Math.round(distance);
		return distance;
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static void main(String[] args) {
		double distance = EarthUtils.getDistance(109.487023,34.498401,
				109.478112,34.499651);
		System.out.println(distance);
	}
}
