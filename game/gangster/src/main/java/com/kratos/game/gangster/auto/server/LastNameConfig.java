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
public class LastNameConfig{
	/** 编号*/
	private Integer	id;
	/** 姓*/
	private String	lastName;

	/** 编号*/
	public Integer getId(){
		return this.id;
	}
	/** 姓*/
	public String getLastName(){
		return this.lastName;
	}
	/**编号*/
	public void setId(Integer id){
		this.id = id;
	}
	/**姓*/
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
}