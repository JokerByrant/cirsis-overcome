package com.cloudfish.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源（需要继承AbstractRoutingDataSource）
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
	
	/**
	 * 列出所有的数据源key（常用数据库名称来命名）
	 * 注意：
	 * 1）这里数据源与数据库是一对一的
	 */
	public enum DatabaseType {
		//主库
		primaryDataSource,
		//从库
		readonlyDataSource
	}
	
   private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();
    
   public static void setDataBaseType(DatabaseType type){
	   contextHolder.set(type);
   }
    
    public static DatabaseType getDataBaseType(){
    	DatabaseType db = contextHolder.get();
        if (db == null)
            db = DatabaseType.primaryDataSource;
        return db;
    }
    
    public Object determineCurrentLookupKey() {
        return getDataBaseType();
    }

    /**
     * 清理链接类型
     */
    public static void clearDbType() {
        contextHolder.remove();
    }

}