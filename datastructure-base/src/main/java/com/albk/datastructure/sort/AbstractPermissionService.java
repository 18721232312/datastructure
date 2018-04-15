package com.albk.datastructure.sort;


import java.util.Map;

/**
 */
public abstract class AbstractPermissionService {

	private Map<String, PermissionStrategy> permissionStrategy;

	public Map getPermission(){
		String key = getPermissionStrategyKey();
		if(StringUtils.isEmpty(key)) {
			return null;
		}
		return permissionStrategy.get(key).getPermission();
	}

	public abstract String getPermissionStrategyKey();

	public void setPermissionStrategy(Map<String, PermissionStrategy> permissionStrategy) {
		this.permissionStrategy = permissionStrategy;
	}
}