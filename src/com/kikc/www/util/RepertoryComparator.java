package com.kikc.www.util;

import com.kikc.www.bean.repertorybean.Repertory;

import java.util.Comparator;

public class RepertoryComparator implements Comparator<Repertory> {

	@Override
	public int compare(Repertory o1, Repertory o2) {
		double repertory1 = o1.getDistance();
		double repertory2 = o2.getDistance();
		int matchProductAmount1 = o1.getMatchProductAmount();
		int matchProductAmount2 = o2.getMatchProductAmount();
		String level1 = o1.getLevel();
		String level2 = o2.getLevel();
		/*if(repertory1 > repertory2 && matchProductAmount1 < matchProductAmount2){
			return 1;
		}else if(repertory1 > repertory2 && matchProductAmount1 > matchProductAmount2){
			return -1;
		}
		
		else if(repertory1 == repertory2 && matchProductAmount1 == matchProductAmount2){
			return 0;
		}else if(repertory1 == repertory2 && matchProductAmount1 < matchProductAmount2){
			return 1;
		}else if(repertory1 == repertory2 && matchProductAmount1 > matchProductAmount2){
			return -1;
		}else if(repertory1 > repertory2 && matchProductAmount1 == matchProductAmount2){
			return 1;
		}else if(repertory1 < repertory2 && matchProductAmount1 == matchProductAmount2){
			return -1;
		}
		
		else if(repertory1 < repertory2 && matchProductAmount1 > matchProductAmount2){
			return -1;
		}else if(repertory1 < repertory2 && matchProductAmount1 < matchProductAmount2){
			return 1;
		}*/
		if(o1 == o2){
			return 0;
		}
		if(matchProductAmount1 == matchProductAmount2){
			if (repertory1 > repertory2) {
		      return 1;
		    }
		    if (repertory1 == repertory2) {
		      return 0;
		    }
		    if (repertory1 < repertory2) {
		      return -1;
		    }
		}else{
			double distance = repertory1 - repertory2;
			if(distance < 0){
				distance = distance * -1;
			}
			//距离差别太大，我们还是按照距离来排序
			if(distance > repertory1 /2 || distance > repertory2 / 2){
				if (repertory1 > repertory2) {
			      return 1;
			    }
			    if (repertory1 == repertory2) {
			      return 0;
			    }
			    if (repertory1 < repertory2) {
			      return -1;
			    }
			}else{
				/*if (matchProductAmount1 > matchProductAmount2) {
			      return -1;
			    }*/
			    if (matchProductAmount1 == matchProductAmount2) {
					if(level1.equals(level2)){
						return 0;
					}else{
						return level1.compareTo(level2) * -1;
					}
			    }else{
					int differenceAmount = matchProductAmount1 - matchProductAmount2;
					if(differenceAmount < 0){
						differenceAmount = differenceAmount * -1;
					}
						if(differenceAmount > matchProductAmount1 / 2 || differenceAmount > matchProductAmount2 / 2){
						if(matchProductAmount1 > matchProductAmount2){
							return -1;
						}
						if(matchProductAmount1 == matchProductAmount2){
							return 0;
						}
						if(matchProductAmount1 < matchProductAmount2){
							return 1;
						}
					}else{
						if(level1.equals(level2)){
							return 0;
						}else{
							return level1.compareTo(level2) * -1;
						}
					}
				}
			    /*if (matchProductAmount1 < matchProductAmount2) {
			      return 1;
			    }*/
			}
		}
		return 0;
	}

}
