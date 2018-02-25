package com.elon.dds.common;

import java.lang.reflect.Field;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.jdbc.Connection;

/**
 * 定义动态数据源派生类。从基础的DataSource派生，动态性自己实现。
 * 
 * @author elon
 * @version 2018-02-25
 */
public class DynamicDataSource extends DataSource {
	
	private static Logger log = LogManager.getLogger(DynamicDataSource.class);
	
	@Autowired
	private DDSHolder holder;
	
	@Autowired
	private ProjectDBMgr dbMgr;
	
	/**
	 * 改写本方法是为了在请求不同工程的数据时去连接不同的数据库。
	 */
	@Override
	public Connection getConnection(){
		
		String projectCode = DBIdentifier.getProjectCode();
		
		//1、获取数据源
		DynamicDataSource dds = holder.getDDS(projectCode);
		
		//2、如果数据源不存在则创建
		if (dds == null) {
			try {
				DynamicDataSource newDDS = initDDS(projectCode);
				holder.addDDS(projectCode, newDDS);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				log.error("Init data source fail. projectCode:" + projectCode);
				return null;
			}
		}
				
		return holder.getDDS(projectCode).getConnection();
	}
	
	/**
	 * 以当前数据对象作为模板复制一份。
	 * 
	 * @return dds
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private DynamicDataSource initDDS(String projectCode) throws IllegalArgumentException, IllegalAccessException {
		DynamicDataSource dds = new DynamicDataSource();

		// 1、clone属性
		Field[] fields = DynamicDataSource.class.getFields();
		for (Field f : fields) {
			f.setAccessible(true);
			Object value = f.get(this);
			f.set(dds, value);
		}

		// 2、设置数据库名称和IP(一般来说，端口和用户名、密码都是统一固定的)
		String urlFormat = this.getUrl();
		String url = String.format(urlFormat, dbMgr.getDBIP(projectCode), dbMgr.getDBName(projectCode));
		dds.setUrl(url);

		return dds;
	}
}
