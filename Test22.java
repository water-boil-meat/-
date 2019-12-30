import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 根据一段时间区间，按月份拆分成多个时间段
 * @author lxg
 *
 * 2016年9月5日下午6:18:36
 */
public class Test22 {

	@Test
    public void demo(){
		Map<String, String> map = Test22.getDateByMonth("2015-08-23","2016-05-29");
        System.out.println("开始日期--------------结束日期");
        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
        	 
            Map.Entry<String, String> entry = entries.next();
         
            System.out.println(entry.getKey() + "-----"+ entry.getValue());
        }
    }
	
    public static Map<String, String> getDateByMonth(String startDate,String endDate) {
    	Map<String, String> map = null;
        try {
        	map = new LinkedHashMap<>();

            Date sd = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);// 定义起始日期
            Date ed = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);// 定义结束日期

            Calendar sc = Calendar.getInstance();
            sc.setTime(sd);
            
            Calendar ec = Calendar.getInstance();
            ec.setTime(ed);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            Calendar rc = Calendar.getInstance();// 运行用
            rc.setTime(sd);// 设置日期起始时间
            
            Calendar bc = Calendar.getInstance();
            
            Calendar lastC = Calendar.getInstance();

            String lastDay ="";
            String begDay ="";
            
            boolean flag = true;
            while (flag) {
                if (rc.before(ec)) {
                	bc.setTime(rc.getTime());
                	rc.set(Calendar.DAY_OF_MONTH, rc.getActualMaximum(Calendar.DAY_OF_MONTH));
                	rc.add(Calendar.DATE, 1);
                	if(rc.before(ec)) {
                		lastC.set(Calendar.YEAR, bc.get(Calendar.YEAR));
                		lastC.set(Calendar.MONTH, bc.get(Calendar.MONTH));
                		lastC.set(Calendar.DAY_OF_MONTH, bc.getActualMaximum(Calendar.DAY_OF_MONTH));
                        lastDay = sdf.format(lastC.getTime());
                        begDay = sdf.format(bc.getTime());
                		map.put(begDay, lastDay);
                	}else if(rc.after(ec) || rc.equals(ec)) {
                		begDay = sdf.format(bc.getTime());
                		lastDay = sdf.format(ec.getTime());
                		map.put(begDay, lastDay);
                		flag = false;
                	}
    			}
			}

            
        }catch (ParseException e){
        	e.printStackTrace(System.out);
        	return null;
        }
    	return map;
    }

}
