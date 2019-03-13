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
public class FirstNameConfig{
	/** 编号*/
	private Integer	id;
	/** 名*/
	private String	firstName;

	/** 编号*/
	public Integer getId(){
		return this.id;
	}
	/** 名*/
	public String getFirstName(){
		return this.firstName;
	}
	/**编号*/
	public void setId(Integer id){
		this.id = id;
	}
	/**名*/
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
}