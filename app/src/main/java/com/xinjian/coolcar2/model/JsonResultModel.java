package com.xinjian.coolcar2.model;

import java.io.Serializable;

/**
 * Created by 57490 on 2017/6/8.
 */

public class JsonResultModel<T> implements Serializable {

    public String status;
    public String msg;
    public T result;    //可能是列表,可能是单个对象.

}
