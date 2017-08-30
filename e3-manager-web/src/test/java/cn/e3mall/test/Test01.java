package cn.e3mall.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class Test01 {
      @Test
      public void test01() throws Exception{
    	  /*SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd mm:HH:ss");
    	  Date date = format.parse("2015-03-08 21:33:18");
    	  System.out.println(date.getTime());*/
    	  Date date =new Date(1425864078000L);
    	  System.out.println(date);
      }
}
