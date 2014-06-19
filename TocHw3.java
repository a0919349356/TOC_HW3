//name : 彭勝賢
//student_number : F74002078
//首先我將網頁整個讀進<String> tmp 之後利用json的API枚舉每個Object
//判斷這個Object的鄉鎮市區有沒有跟我的參數一樣,交易年月有沒有大於我輸入的年份,地址有沒有包含我輸入的路名
//把那些匹配成功的Object的價錢都加起來,最後除數量,即是答案

import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.net.*;

public class TocHw3 {
	public static void main(String[] argv) throws IOException, JSONException
	{
		String tmp = getUrlSource(argv[0]);
		JSONArray  yy=null;;
		yy = new JSONArray(tmp);
		int cnt=0;
		long total=0;
		String syear = argv[3]+"00";
		int year = Integer.parseInt(syear);
		for(int i=0;i<yy.length();i++)
		{
			JSONObject jo = yy.getJSONObject(i);
			if(jo.getString("鄉鎮市區").equals(argv[1]))
			{
				if(jo.getInt("交易年月")>year)
				{
					String add=jo.getString("土地區段位置或建物區門牌");
					if(add.contains(argv[2]))
					{
						cnt++;
						total+=jo.getInt("總價元");
					}
				}
			}
		}
		System.out.println(total/cnt);
		//fr.close();
	}
	private static String getUrlSource(String url) throws IOException {
        URL yahoo = new URL(url);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();

        return a.toString();
    }

}
