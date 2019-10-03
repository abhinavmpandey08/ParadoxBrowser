package main;

import java.util.ArrayList;

public class History {
	
	private ArrayList<String> back;
	private ArrayList<String> forward;
	private ArrayList<String> history;
	
	public History()
	{
		back = new ArrayList<String>();
		forward = new ArrayList<String>();
		
		back.add("https://www.google.com");
	}
	
	public void addBack(String str)
	{
		back.add(str);
		if(forward.size() > 0)
			forward.remove(forward.size() - 1);
	}
	
	public void addForward(String str)
	{
		forward.add(str);
		if(back.size() > 0)
			back.remove(forward.size() - 1);
	}
	
	public String getBack()
	{
		//if(back.size() >= 0)
			return back.get(back.size() - 1);
		//return "";*/
		//return "youtube.com";
	}
	
	public String getForward()
	{
		if(forward.size() >= 0)
			return forward.get(forward.size() - 1);
		return "";
	}

}
