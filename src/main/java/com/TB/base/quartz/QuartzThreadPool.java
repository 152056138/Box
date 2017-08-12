/*
 * Quartz线程池
 */
package com.TB.base.quartz;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

public class QuartzThreadPool {
	static ThreadPoolExecutor executor;
	public QuartzThreadPool(){
		executor = new ThreadPoolExecutor(7, 10, 200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
	}
	/**
	 * 设置任务
	 */
	public void setText(String jobClass,String time){
		//为调用的方法设置参数
		QuartUtils quartUtils = new QuartUtils(jobClass, time);
		executor.execute(quartUtils);
		 executor.shutdown();
	}
	
	public static void main(String[] args) {
		QuartzThreadPool q = new QuartzThreadPool();
		q.setText("firstComplexJobDetail", "2017-8-12 20:56:00");
	}
}

