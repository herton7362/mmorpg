package com.kratos.game.gangster.auto.server;
import java.util.List;
import com.kratos.engine.framework.scheme.core.custom.StringIntTuple;
import com.kratos.engine.framework.scheme.core.custom.IntDoubleTuple;
import com.kratos.engine.framework.scheme.core.custom.IntTuple;
import com.kratos.engine.framework.scheme.core.custom.ThreeTuple;
import com.kratos.engine.framework.scheme.core.custom.StringFloatTuple;

/**
*自动生成类
*/
public class ItemConfig{
	/** 编号*/
	private Integer	id;
	/** 物品名*/
	private String	name;
	/** 类型*/
	private Integer	score;
	/** 描述*/
	private String	description;
	/** 数值*/
	private Integer	value;
	/** 图标*/
	private String	icon;
	/** 购买价格*/
	private Integer	price;
	/** 货币类型*/
	private Integer	moneyType;

	/** 编号*/
	public Integer getId(){
		return this.id;
	}
	/** 物品名*/
	public String getName(){
		return this.name;
	}
	/** 类型*/
	public Integer getScore(){
		return this.score;
	}
	/** 描述*/
	public String getDescription(){
		return this.description;
	}
	/** 数值*/
	public Integer getValue(){
		return this.value;
	}
	/** 图标*/
	public String getIcon(){
		return this.icon;
	}
	/** 购买价格*/
	public Integer getPrice(){
		return this.price;
	}
	/** 货币类型*/
	public Integer getMoneyType(){
		return this.moneyType;
	}
	/**编号*/
	public void setId(Integer id){
		this.id = id;
	}
	/**物品名*/
	public void setName(String name){
		this.name = name;
	}
	/**类型*/
	public void setScore(Integer score){
		this.score = score;
	}
	/**描述*/
	public void setDescription(String description){
		this.description = description;
	}
	/**数值*/
	public void setValue(Integer value){
		this.value = value;
	}
	/**图标*/
	public void setIcon(String icon){
		this.icon = icon;
	}
	/**购买价格*/
	public void setPrice(Integer price){
		this.price = price;
	}
	/**货币类型*/
	public void setMoneyType(Integer moneyType){
		this.moneyType = moneyType;
	}
}