package com.flash.base.tool.page.utils;

public class PageUtils {
	/**
	 * 根据总数量和pageSize计算总页数
	 * @param totalCount
	 * @param pageSize
	 * @return
	 */
	public static int getTotalPages(int totalCount,int pageSize){
		//将pageSize转为double类型与totalCount相除，比较他俩的大小
		double tempSize = (double) pageSize;
		int temp1 = (int) (totalCount/tempSize);
		double temp2 = totalCount/tempSize;
		return temp1==temp2 ? temp1 : temp1 + 1;
	}
	
	public static int[] getPagesIndexs(int currentPage, int totalPages){
		//设置页码数组
		int beginPage = currentPage - 5;
		int endPage = currentPage + 4 ;
		if(totalPages <= 10){
			beginPage = 1;
			endPage = totalPages;
		}else{
			if(beginPage<1){
				beginPage = 1;
				endPage = 10;
			}
			if(endPage > totalPages){
				endPage = totalPages;
				beginPage = totalPages - 9;
			}
		}
		//构建页码数组
		int[] pagesIndexs = new int[endPage - beginPage + 1];
		for (int i = 0,index = beginPage; i < pagesIndexs.length; i++,index++) {
			pagesIndexs[i] = index;
		}
		return pagesIndexs;
	}
}
