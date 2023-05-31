package fr.farkas.Main.Characters;

import java.util.Map;
import java.util.HashMap;

public class Roles {
	
	public static final String VULCAN = "Vulcan";
	public static final String GOGUNHEE = "GoGunHee";
	public static final String THOMASANDRE = "ThomasAndre";
	public static final String CHRISTOPHERREED = "ChristopherReed";
	public static final String ARCHITECT = "Architect";
	public static final String SUNGJINWOO = "SungJinWoo";
	public static final String BERU = "Beru";
	public static final String MONARQUEBETES = "MonarqueBetes";
	public static final String CHAHAEIN = "ChaHaeIn";
	public static final String LIUZHIGANG = "LiuZhigang";
	public static final String SELNER = "Selner";
	public static final String WOOCHINJUL = "WooChinjul";
	public static final String ANTHARES = "Anthares";
	public static final String LEGIA = "Legia";
	public static final String BARUKA = "Baruka";
	
	public static final Map<String,Integer>selectableRoles = new HashMap<String, Integer>() {{
	    put(GOGUNHEE, 0);
	    put(THOMASANDRE, 1);
		put(CHRISTOPHERREED, 2);
		put(ANTHARES, 5);
		put(LEGIA, 6);
		put(MONARQUEBETES, 7);
		put(SELNER, 10);
		put(CHAHAEIN, 11);
		put(LIUZHIGANG, 12);
		put(WOOCHINJUL, 13);
		put(VULCAN, 21);
		put(BERU, 22);
		put(BARUKA, 23);
		put(SUNGJINWOO, 25);
		put(ARCHITECT, 26);
	  }};

}
