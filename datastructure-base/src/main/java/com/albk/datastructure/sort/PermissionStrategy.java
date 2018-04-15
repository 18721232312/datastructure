package com.albk.datastructure.sort;

import java.util.Map;

/**
 * 权限策略.
 */
public interface PermissionStrategy {

	// 获取权限sql片段
	public Map getPermission();
}
