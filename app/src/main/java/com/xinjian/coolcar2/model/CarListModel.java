package com.xinjian.coolcar2.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 57490 on 2017/6/8.
 */

public class CarListModel implements Serializable{




    public String id;
    public String name;
    public String fullname;
    public String initial;
    public String price;
    public String parentid;
    public String depth;
    public List<CarModel> carlist;


}
