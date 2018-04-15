package com.albk.datastructure.sort;

import java.util.Map;

/**
 */
public abstract class AbstractPermissionStrategy implements PermissionStrategy {

	@Override
	public Map getPermission() {
		if(isOverride()) {
			return getOverrideStr();
		}
		return getPermissionStr();
	}

	public abstract Map getPermissionStr();

	public boolean isOverride() {
		return false;
	}

	public Map getOverrideStr() {
		return null;
	}
}
