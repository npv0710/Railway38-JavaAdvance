package com.vti;

public class DemoSingleton2 {//Lazy Singleton
	
	private static DemoSingleton2 instance;
	
	private DemoSingleton2() {
		
	}
	
	public static DemoSingleton2 getInstance() {
		if (instance == null) {
			instance = new DemoSingleton2();
		}
		return instance;
	}
	
	@Override
	public String toString() {
		return "Demo singleton2";
	}
}
