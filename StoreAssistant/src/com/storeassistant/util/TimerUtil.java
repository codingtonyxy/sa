package com.storeassistant.util;

import java.util.Timer;
import java.util.TimerTask;

public class TimerUtil {
	
	public static Timer getTimer(String name){
		Timer timer = new Timer(name);
		return timer;
	}
	
	/**
	 * 安排在指定延迟后执行指定的任务
	 * @param timer
	 * @param task
	 * @param delay
	 */
	public static void schedule(Timer timer, TimerTask task, long delay) {
		if(timer == null || task == null){
			return;
		}
		timer.schedule(task, delay);
	}
	
	/**
	 * 安排指定的任务从指定的延迟后开始进行重复的固定延迟执行
	 * 根据前一次执行的实际执行时间来安排每次执行
	 * @param timer
	 * @param task
	 * @param delay
	 * @param period
	 */
	public static void schedule(Timer timer, TimerTask task, long delay, long period) {
		if(timer == null || task == null){
			return;
		}
		timer.schedule(task, delay, period);
	}
	
	/**
	 * 安排指定的任务在指定的延迟后开始进行重复的固定速率执行。以近似固定的时间间隔（由指定的周期分隔）进行后续执行
	 * @param task
	 * @param delay
	 * @param period
	 */
	public static void scheduleAtFixedRate(Timer timer, TimerTask task, long delay, long period) {
		if(timer == null || task == null){
			return;
		}
		timer.scheduleAtFixedRate(task, delay, period);;;
	}

}
