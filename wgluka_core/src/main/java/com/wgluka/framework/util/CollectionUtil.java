package com.wgluka.framework.util;

import java.util.Collection;
import java.util.Map;

/**
 * Created by yukai on 2017/4/16.
 */
public class CollectionUtil {
    public static boolean isEmpty(Collection collection) {
        if (collection == null || collection.isEmpty())
            return true;
        return false;
    }

    public static boolean isEmpty(Map map){
        if (map == null || map.isEmpty())
            return true;
        return false;
    }
}
