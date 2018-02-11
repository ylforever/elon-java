package com.elon;

/**
 * 自定义的文件不存在异常。
 *
 * @author elon
 */
public class FileNoExistException extends Exception
{
	private static final long serialVersionUID = 7929453457697405891L;
	
	/**
	 * 文件完整路径
	 */
	private String filePath;

	/**
	 * 构造函数。初始化文件路径。
	 * 
	 * @param filePath 文件路径
	 */
	public FileNoExistException(String filePath)
	{
		this.filePath = filePath;
	}
	
	public String getExceptionMsg()
	{
		return "filePath:" + filePath + "|exception trace:" + UtilTool.toStackTrace(this);
	}
}
