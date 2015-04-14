package org.ikgroup.core.mybatis.interceptor;

import java.sql.Connection;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.ikgroup.core.mybatis.PagingBounds;

@Intercepts(@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})) 
public class MysqlPagingInterceptor extends AbstractPagingInterceptor{

	@Override
    protected String getSelectTotalSql(String targetSql) {
        String sql = targetSql.toLowerCase();
        StringBuilder sqlBuilder = new StringBuilder(sql);
        
        int orderByPos = 0;
        if((orderByPos = sqlBuilder.lastIndexOf(ORDER_BY)) != -1) {
            sqlBuilder.delete(orderByPos, sqlBuilder.length());
        }
        sqlBuilder.insert(0, "select count(1) as _count from ( ").append(" ) table_alias");
        
        return sqlBuilder.toString();
    }

    @Override
    protected String getSelectPagingSql(String targetSql, PagingBounds bounds) {
        String sql = targetSql.toLowerCase();
        StringBuilder sqlBuilder = new StringBuilder(sql);
        
        sqlBuilder.insert(0, "select table_alias.* from (");
        sqlBuilder.append(") ");
        sqlBuilder.append("as table_alias limit " + bounds.getOffset() + ", " + bounds.getLimit());
        
        return sqlBuilder.toString();
    }


}
