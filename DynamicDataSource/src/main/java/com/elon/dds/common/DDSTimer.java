package com.elon.dds.common;

/**
 * 动态数据源定时器管理。长时间无访问的数据库连接关闭。
 * 
 * @author elon
 * @version 2018年2月25日
 */
public class DDSTimer {
	
	/**
	 * 空闲时间周期。超过这个时长没有访问的数据库连接将被释放。默认为30分钟。
	 */
	private static long idlePeriodTime = 30 * 1000;
	
	/**
	 * 动态数据源
	 */
	private DynamicDataSource dds;
	
	/**
	 * 上一次访问的时间
	 */
	private long lastUseTime;
	
	public DDSTimer(DynamicDataSource dds) {
		this.dds = dds;
		this.lastUseTime = System.currentTimeMillis();
	}
	
	/**
	 * 更新最近访问时间
	 */
	public void refreshTime() {
		lastUseTime = System.currentTimeMillis();
	}
	
	/**
	 * 检测数据连接是否超时关闭。
	 * 
	 * @return true-已超时关闭; false-未超时
	 */
	public boolean checkAndClose() {
		
		if (System.currentTimeMillis() - lastUseTime > idlePeriodTime)
		{
			dds.close();
			return true;
		}
		
		return false;
	}

	public DynamicDataSource getDds() {
		return dds;
	}
}
