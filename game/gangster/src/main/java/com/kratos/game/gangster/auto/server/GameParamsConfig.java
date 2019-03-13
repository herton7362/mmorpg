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
public class GameParamsConfig{
	/** 防御每多x点受到伤害时*/
	private Integer	per_defense;
	/** 伤害降低x%*/
	private Double	reduce_defense;
	/** 当防御超过x点时，防御达到上限*/
	private Integer	max_defense_difference;
	/** 普通防御的减免上限为x%*/
	private Double	max_reduce_defense;

	/** 防御每多x点受到伤害时*/
	public Integer getPer_defense(){
		return this.per_defense;
	}
	/** 伤害降低x%*/
	public Double getReduce_defense(){
		return this.reduce_defense;
	}
	/** 当防御超过x点时，防御达到上限*/
	public Integer getMax_defense_difference(){
		return this.max_defense_difference;
	}
	/** 普通防御的减免上限为x%*/
	public Double getMax_reduce_defense(){
		return this.max_reduce_defense;
	}
	/**防御每多x点受到伤害时*/
	public void setPer_defense(Integer per_defense){
		this.per_defense = per_defense;
	}
	/**伤害降低x%*/
	public void setReduce_defense(Double reduce_defense){
		this.reduce_defense = reduce_defense;
	}
	/**当防御超过x点时，防御达到上限*/
	public void setMax_defense_difference(Integer max_defense_difference){
		this.max_defense_difference = max_defense_difference;
	}
	/**普通防御的减免上限为x%*/
	public void setMax_reduce_defense(Double max_reduce_defense){
		this.max_reduce_defense = max_reduce_defense;
	}
}