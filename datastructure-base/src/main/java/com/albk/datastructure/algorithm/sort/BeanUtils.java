package com.albk.datastructure.algorithm.sort;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @team: ALBK
 * @date 2018/4/15 23:51
 */

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtilsBean;

public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {
    public BeanUtils() {
    }

    public static Map<String, Object> transBean2Map(Object obj) throws Exception {
        if (obj == null) {
            return null;
        } else {
            HashMap map = new HashMap();

            BeanInfo beanInfo;
            try {
                beanInfo = Introspector.getBeanInfo(obj.getClass());
            } catch (IntrospectionException var11) {
                throw new Exception("bean转map异常", var11);
            }

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            org.apache.commons.beanutils.PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] arr$ = propertyDescriptors;
            int len$ = propertyDescriptors.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                PropertyDescriptor property = arr$[i$];
                String key = property.getName();

                try {
                    if (!key.equals("class")) {
                                map.put(key, propertyUtilsBean.getNestedProperty(obj, key));
                    }
                } catch (Exception var12) {
                    throw new Exception("bean转map异常", var12);
                }
            }

            return map;
        }
    }
}
