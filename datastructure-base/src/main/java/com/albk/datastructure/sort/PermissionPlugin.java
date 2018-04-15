package com.albk.datastructure.sort;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Service
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class PermissionPlugin implements Interceptor {

	private Set<String> skipSqlIdSet = new HashSet<>();

	private boolean skipSqlIdMatch = false;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		final Object[] args = invocation.getArgs();
		MappedStatement ms = (MappedStatement) args[0];
		Object parameterObject = args[1];

		String sqlId = ms.getId();
		if(isSkipIntercept(sqlId)) return invocation.proceed();

		Map o = new HashMap();
		boolean flag = false;
		if(parameterObject != null) {
			if(parameterObject instanceof Map) {
				o = (Map) parameterObject;
				if(((Map)parameterObject).containsKey("param")) {
					flag = true;
					Object param = ((Map)parameterObject).get("param");
					if(param instanceof Map) {
						o.put("param", param);
					} else {
						o.put("param", BeanUtils.transBean2Map(param));
					}
				}
			} else if(parameterObject instanceof BaseBean){
				try {
					o = BeanUtils.transBean2Map(parameterObject);
				} catch (Exception e) {
					throw e;
				}
			} else {
				// 只对参数是map和baseBean的进行处理。其它直接跳过
				return invocation.proceed();
			}
		}
		if(o != null) {
			if(SpringHelper.applicationContext != null) {
				Map m = SpringHelper.getBean(AbstractPermissionService.class).getPermission();
				if(m != null) {
					if(flag) {
						((Map)o.get("param")).putAll(m);
					} else {
						o.putAll(m);
					}
				}
				args[1] = o;
			}
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
		String skipSqlIds = properties.getProperty("skipSqlIds");
		if (StringUtils.isNotEmpty(skipSqlIds)) {
			String[] skipSqlIdArray = StringUtils.split(skipSqlIds, ",");
			for (String skipSqlId : skipSqlIdArray) {
				String trim = skipSqlId.trim();
				if (trim.contains("*")) {
					trim = "^" + trim.replace(".", "[.]").replace("*", ".*") + "$";
					skipSqlIdMatch = true;
				}
				skipSqlIdSet.add(trim);
			}
		}
	}

	private boolean isSkipIntercept(String sqlId){
		if (skipSqlIdSet != null && !skipSqlIdSet.isEmpty()) {
			if (skipSqlIdMatch) {
				for (String skipSqlId : skipSqlIdSet) {
					if (sqlId.matches(skipSqlId)) {
						return true;
					}
				}
			} else {
				return skipSqlIdSet.contains(sqlId);
			}
		}
		return false;
	}
}
